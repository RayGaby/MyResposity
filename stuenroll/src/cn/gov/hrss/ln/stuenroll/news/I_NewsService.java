package cn.gov.hrss.ln.stuenroll.news;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;

/**
 * 获取新闻模块业务接口
 * @author 圣
 *
 */
public interface I_NewsService {
	/**
	 * 刷新新闻
	 * @param start
	 * @param page
	 * @return
	 */
	public List<Record> searchNews(int start, int pageSize);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Record searchNewsById(Long id);
}
