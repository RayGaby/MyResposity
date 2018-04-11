package cn.gov.hrss.ln.stuenroll.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_NewsDao;
import cn.gov.hrss.ln.stuenroll.plugin.MongoKit;
import jodd.util.Base64;

/**
 * news集合实现类
 * 
 * @author 圣
 *
 */
public class NewsDao implements I_NewsDao {
	@Override
	public List<Record> searchNews(int start, int pageSize) {
		Map<String,Object> sort = new HashMap<String,Object>();
		
		sort.put("date", "des");//按日期排序
		Page<Record> page = MongoKit.paginate("news", start, pageSize, null, null, sort);
		List<Record> list=page.getList();
		System.out.println("size:"+list.size());
		//解码
		for(int i=0;i<list.size();i++){
			Record record=list.get(i);
			byte[] photo=(byte[])record.get("photo");
			record.set("photo", Base64.encodeToString(photo));
			record.set("id", record.get("id").toString());
		}
		return list;
	}

	@Override
	public Record searchNewsById(Long id) {
		Map<String,Object> filter = new HashMap<String,Object>();
		if(id!=null){
			filter.put("id", id);
		}
		Page<Record> page=MongoKit.paginate("news", 1,1, filter);
		Record record=page.getList().get(0);
		//System.out.println("Daoid:"+record.toString());
		return record;
	}

}
