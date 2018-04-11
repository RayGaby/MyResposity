package cn.gov.hrss.ln.stuenroll.friend;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.eclipse.xtext.xbase.lib.Exceptions;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Spring;
import com.jfinal.upload.UploadFile;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import cn.gov.hrss.ln.stuenroll.plugin.MongoKit;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Spring("friendController")
public class FriendController extends Controller implements I_FriendController {
	private I_FriendService i_FriendService;
	private int rowsInPage;

	@Override
	public void searchFriend() {
		String username = getSessionAttr("username");
		String friendname = getPara("friendname");

		// 总页数
		Long page = getParaToLong("page");
		if (page == null) {
			page = 1L;
		}

		HashMap map = new HashMap();

		map.put("username", username);
		map.put("friendname", friendname);

		long start = (page - 1) * rowsInPage;
		long length = rowsInPage;

		List<Record> list = i_FriendService.searchFriend(map, start, length);
		
		for(Record record:list){
			String name=record.get("friendname");
			
			String id= i_FriendService.getPhoto(name);
			String photo_base64=FriendController.getImageBinary(id, "image");
			
			record.set("photo_base64", photo_base64);
			
		}
		
		renderJson("result", list);
	}

	@Override
	public void searchFriendCount() {
		String username = getSessionAttr("username");
		long count = i_FriendService.searchFriendCount(username);
		renderJson("result", count);

	}

	// 加权限
	@Override
	public void deleteById() {
		Long id = getParaToLong("id");
		int i = i_FriendService.deleteById(id);
		renderJson("deleteRows", i);

	}

	@Override
	public void searchProfessionWithOrganization() {
		HashMap map = new HashMap();
		ArrayList<Long> param = new ArrayList<Long>();
		ArrayList<String> param1 = new ArrayList<String>();
		String organization = getSessionAttr("organization");
		long organizationId = getSessionAttr("organizationId");

		List<Record> list;
		if (organization.equals("辽宁省就业网")) {
			// 查询所有机构
			list = i_FriendService.searchAllProfession();
		}else {
			list = i_FriendService.searchProfessionWithOrganization(organizationId);
		}
		
		
		if (organization.equals("辽宁省就业网")) {
			organizationId = -1;
		}
		
		// 查询当前机构某一专业的人数
		for (Record record : list) {
			long count = i_FriendService.statisticsAtProfession(record.getStr("name"), organizationId);
			param.add(count);
			param1.add(record.getStr("name"));
		}
		map.put("专业", param1);
		map.put("人数", param);
		renderJson("result", map);
	}

	@Override
	public void searchKeyWords() {
		HashMap map = new HashMap();
		ArrayList<Long> param = new ArrayList<Long>();
		ArrayList<String> param1 = new ArrayList<String>();

		String organization = getSessionAttr("organization");
		long organizationId = getSessionAttr("organizationId");

		// 查询当前机构所有专业
		List<Record> list = i_FriendService.searchProfessionWithOrganization(organizationId);

		if (organization.equals("辽宁省就业网")) {
			organizationId = -1;
		}

		// 查询当前机构某一专业的人数
		for (Record record : list) {
			long count = i_FriendService.statisticsAtProfession(record.getStr("name"), organizationId);
			param.add(count);
			param1.add(record.getStr("name"));
		}
		// 查询所有机构
		List<Record> list2 = i_FriendService.searchAll();
		// 查询某一机构的人数
		for (Record record2 : list2) {
			String organization_name = record2.getStr("organization_name");
			long count = i_FriendService.searchCountAtOrganization(organization_name);
			if (count == 0) {
				count = 10;
			}
			param.add(count);
			param1.add(record2.getStr("organization_name"));
		}

		map.put("Key", param1);
		map.put("Numbers", param);
		renderJson("result", map);
	}

	@Override
	public void friendTop() {
		Long id = getParaToLong("id");
		String username = getSessionAttr("username");
		boolean bool = i_FriendService.friendTop(id, username);
		renderJson("result", bool);
	}

	@Override
	public void searchFriendFromUser() {
		String username = getPara("username");
		Record record = i_FriendService.searchFriendFromUser(username);
		
		if(record!=null){
			
			String name=record.get("username");
			
			String id= i_FriendService.getPhoto(name);
			String photo_base64=FriendController.getImageBinary(id, "image");
			
			record.set("photo_base64", photo_base64);
		}
	
		renderJson("result", record);
	}

	@Override
	public void addFriend() {
		String username = getSessionAttr("username");
		String friendname = getPara("friendname");
		String role = getPara("role");
		String organization = getPara("organization");
		boolean bool = i_FriendService.addFriend(username, friendname, role, organization);
		renderJson("result", bool);

	}

	@Override
	public void userOnLine() {
		String username = getPara("username");
		boolean bool = i_FriendService.userOnLine(username);
		renderJson("result", bool);
	}

	@Override
	public void save() {
		Mongo client;
		try {
			client = new Mongo("192.168.1.52", 60001);
			MongoKit.init(client, "hrss");
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		try {
			UploadFile _file1 = this.getFile("photo");
			File file1 = _file1.getFile();
			DB db = MongoKit.getDB();
			GridFS gridFS = new GridFS(db, "image");
			GridFSInputFile fsFile = gridFS.createFile(file1);
			fsFile.save();
			file1.delete();
			HashMap map = new HashMap<Object, Object>();
			map.put("result", Boolean.valueOf(true));
			Object _id = fsFile.getId();
			String _string = _id.toString();
			map.put("id", _string);
			this.renderJson(map);
		}
		catch (Throwable _e) {
			throw Exceptions.sneakyThrow(_e);
		}

	}

	@Override
	public void getPhoto() {
		//测试
		Mongo client;
		try {
			client = new Mongo("192.168.1.52", 60001);
			MongoKit.init(client, "hrss");
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		try {		
			String id= i_FriendService.getPhoto(getPara("name"));		
			//读取图片
			String photo=FriendController.getImageBinary(id, "image");
			
			renderJson("photo",photo);	
		}
		catch (Throwable _e) {
			throw Exceptions.sneakyThrow(_e);
			
		}
	}
	@Override
	public void initPersonalPhoto() {
		String id= i_FriendService.getPhoto(getPara("name"));
		String photo_base64=FriendController.getImageBinary(id, "image");
		renderJson("result",photo_base64);
	}
	
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder(); 
	public static String getImageBinary(String id,String collectionName)  
    {  
		Mongo client;
		try {
			client = new Mongo("192.168.1.52", 60001);
			MongoKit.init(client, "hrss");
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
    	  DB db = MongoKit.getDB();
		  GridFS gridFS = new GridFS(db, collectionName);
		  ObjectId _objectId = new ObjectId(id);
		  DBObject query = new BasicDBObject("_id",_objectId);
		  GridFSDBFile fsFile = gridFS.findOne(query);
		  BufferedImage bi;  
	        try {
	          bi = ImageIO.read(fsFile.getInputStream());  
	          ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	          ImageIO.write(bi, "jpg", baos);  
	          byte[] bytes = baos.toByteArray();  
	            
	          return encoder.encodeBuffer(bytes).trim();
	          
	       } catch (IOException e) {  
	          e.printStackTrace();  
	       }  
	      return null;
    } 

      
	public I_FriendService getI_FriendService() {
		return i_FriendService;
	}

	public void setI_FriendService(I_FriendService i_FriendService) {
		this.i_FriendService = i_FriendService;
	}

	public int getRowsInPage() {
		return rowsInPage;
	}

	public void setRowsInPage(int rowsInPage) {
		this.rowsInPage = rowsInPage;
	}


}
