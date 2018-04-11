package cn.gov.hrss.ln.stuenroll.myClass;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Spring;

@Spring("myClassController")
public class MyClassController extends Controller implements I_MyClassController {

	private I_MyClassService i_MyClassService;

	
	@Override
	public void searchClassInfo() {
//		String longpid= "210403199010181548";
//		setSessionAttr("pid","210403199010181548");
		String pid = getSessionAttr("pid");
		
		
		List<Record> list = i_MyClassService.searchClassInfo(pid);
		for (Record record : list) {
//			String classid = record.get("classid");
//			String period = record.get("period");
//			String o_address = record.get("address");
//			String organization = record.get("organization");
//			String o_tel = record.get("tel");
//			String liaison = record.get("liaison");
//			Date date=record.get("date");
//			
//			try {
//				setSessionAttr("classid",classid);
//				setSessionAttr("period",period);
//				setSessionAttr("o_address",o_address);
//				setSessionAttr("organization",organization);
//				setSessionAttr("o_tel",o_tel);
//				setSessionAttr("liaison",liaison);
//				setSessionAttr("classdate",date.toString());
//			}
//			catch (Exception e) {
//				// TODO: handle exception
//			}			
			record.set("pid", pid);		
		}
//		System.out.println(list);
		
		renderJson("result",list);
	}
	
	@Override
	public void searchClassmate() {
		String classid=getSessionAttr("classid");
		String pid = getSessionAttr("pid");
		List<Record> list = i_MyClassService.searchClassmate(classid);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).get("pid").equals(pid)){
				list.remove(i);
				break;
			}
		}
		renderJson("result",list);
		
	}
	

	public I_MyClassService getI_MyClassService() {
		return i_MyClassService;
	}

	public void setI_MyClassService(I_MyClassService i_MyClassService) {
		this.i_MyClassService = i_MyClassService;
	}

}
