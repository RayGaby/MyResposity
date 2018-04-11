package cn.gov.hrss.ln.stuenroll.checkin;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Spring;


@Spring("checkController")
public class CheckController extends Controller implements I_CheckController {
	
	private I_CheckService i_CheckService;
	
	
	@Override
	public void searchcheck() {
		
	String pid = getPara("pid");
	String startDate = getPara("startDate");
	String endDate = getPara("endDate");
	
	Long count = i_CheckService.searchCheck(pid,startDate,endDate);
	
	renderJson("check",count);
	
	}
	
	@Override
	public void searchlate() {
			String pid = getPara("pid");
			String startDate = getPara("startDate");
			String endDate = getPara("endDate");
			
			Long count = i_CheckService.searchLate(pid,startDate,endDate);
			
			renderJson("late",count);
			
			}
		
	
		
		
	public I_CheckService getI_CheckService() {
		return i_CheckService;
	}

	public void setI_CheckService(I_CheckService i_CheckService) {
		this.i_CheckService = i_CheckService;
	}




	
	
	
	
	
}
