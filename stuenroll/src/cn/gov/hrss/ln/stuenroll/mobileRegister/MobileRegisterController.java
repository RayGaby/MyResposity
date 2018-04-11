package cn.gov.hrss.ln.stuenroll.mobileRegister;

import com.jfinal.core.Controller;
import com.jfinal.plugin.spring.Spring;

@Spring("mobileRegisterController")
public class MobileRegisterController extends Controller implements I_MobileRegisterController {
	private I_MobileRegisterService i_MobileRegisterService;

	@Override
	public void saveRegisterInfo() {
		String username = getPara("username");
		String password = getPara("password");
		long tel = getParaToLong("tel");
		int result = i_MobileRegisterService.saveRegisteInfo(username, password, tel);
		renderJson("result",result);
	}

	public I_MobileRegisterService getI_MobileRegisterService() {
		return i_MobileRegisterService;
	}

	public void setI_MobileRegisterService(I_MobileRegisterService i_MobileRegisterService) {
		this.i_MobileRegisterService = i_MobileRegisterService;
	}

}
