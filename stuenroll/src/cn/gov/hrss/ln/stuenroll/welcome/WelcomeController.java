package cn.gov.hrss.ln.stuenroll.welcome;

import java.util.HashMap;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;

import com.jfinal.core.Controller;
import com.jfinal.plugin.spring.Spring;

/**
 * 欢迎模块网络类
 * 
 * @author YangDi
 *
 */
@Spring("welcomeController")
public class WelcomeController extends Controller implements I_WelcomeController {
	private I_WelcomeService i_WelcomeService;
	
	@RequiresPermissions({"3_4","4_4"})
	@Override
	public void getApplyInfo() {
		Integer year = getParaToInt("year");
		Integer month = getParaToInt("month");
		String organization=getSessionAttr("organization");
		Long organizationId=getSessionAttr("organizationId");
		if(organization!=null&&organization.equals("辽宁省就业网")){
			organizationId=(long) -1;
		}
		if(month!=null)
			month = month-1;
		HashMap map = i_WelcomeService.getApplyInfo(year, month, organizationId);
		map.put("organization", organization);
		renderJson("applyInfo", map);
	}


	@RequiresPermissions({"3_4","4_4"})
	@Override
	public void statisticsInYear() {
		Integer year = getParaToInt("year");
		String organization=getSessionAttr("organization");
		Long organizationId=getSessionAttr("organizationId");
		if(organization.equals("辽宁省就业网")){
			organizationId=(long) -1;
		}
		
		HashMap map = i_WelcomeService.statisticsInYear(year,organizationId);
		renderJson("statistics", map);

	}
	@RequiresPermissions({"3_4","4_4"})
	@Override
	public void getJob() {
		Integer year = getParaToInt("year");
		String organization=getSessionAttr("organization");
		Long organizationId=getSessionAttr("organizationId");
		if(organization.equals("辽宁省就业网")){
			organizationId=(long) -1;
		}
		if(year!=null)
			year = year;
		HashMap map = i_WelcomeService.getJob(year,organizationId);
		renderJson("statistics_2", map);
	}
	@RequiresPermissions({"3_4","4_4"})
	@Override
	public void professionCount() {
		Integer year = getParaToInt("year");
		String organization=getSessionAttr("organization");
		Long organizationId=getSessionAttr("organizationId");
		if(organization!=null&&organization.equals("辽宁省就业网")){
			organizationId=(long)-1;
		}
		if(year!=null)
			year = year;
		HashMap map = i_WelcomeService.professionCount(year,organizationId);
		renderJson("statistics_4_1", map);
	}
	@RequiresPermissions({"3_4","4_4"})
	@Override
	public void PlaceCount() {
		Integer year = getParaToInt("year");
		String organization=getSessionAttr("organization");
		Long organizationId=getSessionAttr("organizationId");
		if(organization.equals("辽宁省就业网")){
			organizationId=(long) -1;
		}
		if(year!=null)
			year = year;
		HashMap map = i_WelcomeService.PlaceCount(year,organizationId);
		renderJson("statistics_4_2", map);
	}
	
	@RequiresPermissions({"3_4","4_4"})
	@Override
	public void education() {
		Integer year = getParaToInt("year");
		String organization=getSessionAttr("organization");
		Long organizationId=getSessionAttr("organizationId");
		if(organization!=null&&organization.equals("辽宁省就业网")){
			organizationId=(long) -1;
		}
		if(year!=null)
			year = year;
		HashMap map = i_WelcomeService.education(year,organizationId);
		renderJson("statistics_4_3", map);
	}
	@RequiresPermissions({"3_4","4_4"})
	@Override
	public void professionClassCount() {
		Integer year = getParaToInt("year");
		String organization=getSessionAttr("organization");
		Long organizationId=getSessionAttr("organizationId");
		if(organization.equals("辽宁省就业网")){
			organizationId=(long) -1;
		}
		if(year!=null)
			year = year;
		HashMap map = i_WelcomeService.professionClassCount(year,organizationId);
		renderJson("statistics_4_4", map);
	}

	public I_WelcomeService getI_WelcomeService() {
		return i_WelcomeService;
	}

	public void setI_WelcomeService(I_WelcomeService i_WelcomeService) {
		this.i_WelcomeService = i_WelcomeService;
	}


}
