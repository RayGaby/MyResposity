package cn.gov.hrss.ln.stuenroll.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.eclipse.xtext.xbase.lib.Exceptions;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class MongoKit {

	protected static Log logger = Log.getLog(MongoKit.class);

	private static Mongo client;
	private static DB defaultDb;

	public static void init(Mongo client, String database) {
		MongoKit.client = client;
		MongoKit.defaultDb = client.getDB(database);

	}

	public static void updateFirst(String collectionName, Map<String, Object> q, Map<String, Object> o) {
		MongoKit.getCollection(collectionName).findAndModify(toDBObject(q), toDBObject(o));
	}

	public static int removeAll(String collectionName) {
		return MongoKit.getCollection(collectionName).remove(new BasicDBObject()).getN();
	}

	public static int remove(String collectionName, Map<String, Object> filter) {
		return MongoKit.getCollection(collectionName).remove(toDBObject(filter)).getN();
	}

	public static int save(String collectionName, List<Record> records) {
		List<DBObject> objs = new ArrayList<DBObject>();
		for (Record record : records) {
			objs.add(toDbObject(record));
		}
		return MongoKit.getCollection(collectionName).insert(objs).getN();

	}

	public static int save(String collectionName, Record record) {
		return MongoKit.getCollection(collectionName).save(toDbObject(record)).getN();
	}

	public static Record findFirst(String collectionName) {
		return toRecord(MongoKit.getCollection(collectionName).findOne());
	}

	public static Page<Record> paginate(String collection, int pageNumber, int pageSize) {
		return paginate(collection, pageNumber, pageSize, null, null, null);
	}

	public static Page<Record> paginate(String collection, int pageNumber, int pageSize, Map<String, Object> filter) {
		return paginate(collection, pageNumber, pageSize, filter, null, null);
	}

	public static Page<Record> paginate(String collection, int pageNumber, int pageSize, Map<String, Object> filter,
			Map<String, Object> like) {
		return paginate(collection, pageNumber, pageSize, filter, like, null);
	}

	public static Page<Record> paginate(String collection, int pageNumber, int pageSize, Map<String, Object> filter,
			Map<String, Object> like, Map<String, Object> sort) {
		DBCollection logs = MongoKit.getCollection(collection);
		BasicDBObject conditons = new BasicDBObject();
		buildFilter(filter, conditons);
		buildLike(like, conditons);
		DBCursor dbCursor = logs.find(conditons);
		page(pageNumber, pageSize, dbCursor);
		sort(sort, dbCursor);
		List<Record> records = new ArrayList<Record>();
		while (dbCursor.hasNext()) {
			records.add(toRecord(dbCursor.next()));
		}
		int totalRow = dbCursor.count();
		if (totalRow <= 0) {
			return new Page<Record>(new ArrayList<Record>(0), pageNumber, pageSize, 0, 0);
		}
		int totalPage = totalRow / pageSize;
		if (totalRow % pageSize != 0) {
			totalPage++;
		}
		Page<Record> page = new Page<Record>(records, pageNumber, pageSize, totalPage, totalRow);
		return page;
	}

	private static void page(int pageNumber, int pageSize, DBCursor dbCursor) {
		dbCursor = dbCursor.skip((pageNumber - 1) * pageSize).limit(pageSize);
	}

	private static void sort(Map<String, Object> sort, DBCursor dbCursor) {
		if (sort != null) {
			DBObject dbo = new BasicDBObject();
			Set<Entry<String, Object>> entrySet = sort.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				String key = entry.getKey();
				Object val = entry.getValue();
				dbo.put(key, "asc".equalsIgnoreCase(val + "") ? 1 : -1);
			}
			dbCursor = dbCursor.sort(dbo);
		}
	}

	private static void buildLike(Map<String, Object> like, BasicDBObject conditons) {
		if (like != null) {
			Set<Entry<String, Object>> entrySet = like.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				String key = entry.getKey();
				Object val = entry.getValue();
				conditons.put(key, MongoKit.getLikeStr(val));
			}
		}
	}

	private static void buildFilter(Map<String, Object> filter, BasicDBObject conditons) {
		if (filter != null) {
			Set<Entry<String, Object>> entrySet = filter.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				String key = entry.getKey();
				Object val = entry.getValue();
				conditons.put(key, val);
			}

		}
	}

	@SuppressWarnings("unchecked")
	public static Record toRecord(DBObject dbObject) {
		Record record = new Record();
		record.setColumns(dbObject.toMap());
		return record;
	}

	public static BasicDBObject getLikeStr(Object findStr) {
		Pattern pattern = Pattern.compile("^.*" + findStr + ".*$", Pattern.CASE_INSENSITIVE);
		return new BasicDBObject("$regex", pattern);
	}

	public static DB getDB() {
		return defaultDb;
	}

	public static DB getDB(String dbName) {
		return client.getDB(dbName);
	}

	public static DBCollection getCollection(String name) {
		return defaultDb.getCollection(name);
	}

	public static DBCollection getDBCollection(String dbName, String collectionName) {
		return getDB(dbName).getCollection(collectionName);
	}

	public static Mongo getClient() {
		return client;
	}

	public static void setMongoClient(Mongo client) {
		MongoKit.client = client;
	}

	private static BasicDBObject toDBObject(Map<String, Object> map) {
		BasicDBObject dbObject = new BasicDBObject();
		Set<Entry<String, Object>> entrySet = map.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			String key = entry.getKey();
			Object val = entry.getValue();
			dbObject.append(key, val);
		}
		return dbObject;
	}

	private static BasicDBObject toDbObject(Record record) {
		BasicDBObject object = new BasicDBObject();
		for (Entry<String, Object> e : record.getColumns().entrySet()) {
			object.append(e.getKey(), e.getValue());
		}
		return object;
	}

	public static Page<Record> paginateFilterIn(String collection, int pageNumber, int pageSize, Map<String, Object> filter,
			Map<String, Object> like, Map<String, Object> sort) {
		// 对于同一属性取不同值的OR关系读取(自定义方法)
		DBCollection logs = MongoKit.getCollection(collection);
		BasicDBObject conditons = new BasicDBObject();
		BasicDBObject add = new BasicDBObject();
		buildFilterIn(filter, conditons);
		buildLike(like, conditons);
		DBCursor dbCursor = logs.find(conditons);
		page(pageNumber, pageSize, dbCursor);
		sort(sort, dbCursor);
		List<Record> records = new ArrayList<Record>();
		while (dbCursor.hasNext()) {
			records.add(toRecord(dbCursor.next()));
		}
		int totalRow = dbCursor.count();
		if (totalRow <= 0) {
			return new Page<Record>(new ArrayList<Record>(0), pageNumber, pageSize, 0, 0);
		}
		int totalPage = totalRow / pageSize;
		if (totalRow % pageSize != 0) {
			totalPage++;
		}
		Page<Record> page = new Page<Record>(records, pageNumber, pageSize, totalPage, totalRow);
		return page;
	}

	private static void buildFilterIn(Map<String, Object> like, BasicDBObject conditons) {
		// 对于同一属性取不同值的OR关系读取,条件的字符串拆分(附加方法)
		if (like != null) {
			Set<Entry<String, Object>> entrySet = like.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				String key = entry.getKey();
				Object val = entry.getValue();
				Object[] names = ((String) val).split("\\&&");
				conditons.put(key, new BasicDBObject("$in", names));
			}
		}
	}

	public static String save(String collectionName, File file) {
		// 保存文件return 文件id(自定义方法)
		String id = null;
		GridFS gridFS = new GridFS(defaultDb, collectionName);
		GridFSInputFile fsFile;
		try {
			fsFile = gridFS.createFile(file);
			fsFile.save();
			Object _id = fsFile.getId();
			id = _id.toString();
		}
		catch (IOException e) {
		}
		return id;

	}

	public static File getFile(String collectionName, String id) {
		// 获取文件(自定义方法) 返回 java.io.File;
		GridFS gridFS = new GridFS(defaultDb, collectionName);
		ObjectId _objectId = new ObjectId(id);
		GridFSDBFile fsFile = gridFS.findOne(_objectId);
		File file;
		if (fsFile != null) {
			file = new File(fsFile.getFilename());
			try {
				fsFile.writeTo(file);
			}
			catch (IOException e) {
			}
		}
		else {
			file = null;
		}
		return file;

	}
	
	public static int removeFile(String collectionName, String id) {
		// 删除文件(自定义方法) 返回 java.io.File;
		GridFS gridFS = new GridFS(defaultDb, collectionName);
		ObjectId _objectId = new ObjectId(id);
		gridFS.remove(_objectId);
		return 1;
	}

	public static void removeAllFile(String collectionName) {
		GridFS gridFS = new GridFS(defaultDb, collectionName);
		gridFS.remove(new BasicDBObject());
	}
}
