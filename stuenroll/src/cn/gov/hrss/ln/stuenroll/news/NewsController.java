package cn.gov.hrss.ln.stuenroll.news;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Spring;
@Spring("newsController")
public class NewsController extends Controller implements I_NewsController {
	private I_NewsService i_NewsService;
	private int pageSize;
	public I_NewsService getI_NewsService() {
		return i_NewsService;
	}
	public void setI_NewsService(I_NewsService i_NewsService) {
		this.i_NewsService = i_NewsService;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public void searchNews() {
		Integer start = getParaToInt("page");
		
		if (start == null) {
			start = 1;
		}
		
		List<Record> list =i_NewsService.searchNews(start, pageSize);
		renderJson("result", list);
		
	}
	@Override
	public void searchNewsById() {
		Long id = getParaToLong("id");
		System.out.println(id);
		Record record =i_NewsService.searchNewsById(id);
		System.out.println(record.toString());
		renderJson("result", record);
	}

}
