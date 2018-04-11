package cn.gov.hrss.ln.stuenroll.student;

import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.plugin.spring.Spring;

@Spring("studentController")
public class StudentController extends Controller implements I_StudentController {
	private I_StudentService i_StudentService;

	@Override
	public void login() {
		// TODO Auto-generated method stub
		String username = getPara("username");
		String password = getPara("password");
		setSessionAttr("username", username);
		HashMap map = new HashMap();
		boolean bool = i_StudentService.login(username, password);
		if (bool) {
			Subject subject = SecurityUtils.getSubject(); // Shiro客户端对象
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);

			subject.login(token); // 执行Shiro认证
			if (subject.isAuthenticated()) {
				bool = true; // 认证通过

				Long registerId = i_StudentService.queryRegisterIdAtLogin(username);
				setSessionAttr("username", username);
				if (registerId != null) {
					setSessionAttr("registerId", registerId);
				}
				map.put("registerId", registerId);

				String pid = i_StudentService.queryPIDAtLogin(registerId);
				if (pid != null) {
					setSessionAttr("pid", pid);
				}

				String abc = getSessionAttr("username");
				System.out.println("username....:" + abc);

				map.put("username", username);

			}
			else {
				bool = false;
			}
		}
		map.put("result", bool);
		renderJson(map);
	}

	public I_StudentService getI_StudentService() {
		return i_StudentService;
	}

	public void setI_StudentService(I_StudentService i_StudentService) {
		this.i_StudentService = i_StudentService;
	}

}
