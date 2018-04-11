//package cn.gov.hrss.ln.stuenroll.test;
//
//import java.util.HashMap;
//
//import com.jfinal.core.Controller;
//import com.jfinal.plugin.spring.Spring;
//
//@Spring("testController")
//public class TestController extends Controller{
//	
//	public void sayHello(){
//		String username=getPara("username");//获得请求上传的数据
//		setAttr("username", username);//绑定数据
//		renderJsp("/test/2.jsp");//跳转JSP页面
//		
//	}
//	
//	public void sayHelloAjax(){
//		String username=getPara("username");	//Ajax上传的数据
//		HashMap map=new HashMap();
//		if(username.equals("admin")){
//			map.put("name", "李强");
//			map.put("sex", "男");
//			map.put("tel", "12345678");
//		}
//		renderJson(map);	//把HashMap转换成JSON写入到Http响应里面返回给浏览器
//	}
//}

package cn.gov.hrss.ln.stuenroll.test;

import cn.gov.hrss.ln.stuenroll.plugin.MongoKit;
import cn.gov.hrss.ln.stuenroll.test.TestValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Spring;
import org.eclipse.xtend.lib.annotations.Accessors;


/**
 * 测试类
 */
@Spring("testController")
@Accessors
@SuppressWarnings("all")
public class TestController extends Controller {
  @Before(TestValidator.class)
  public void test1() {
    String username = this.getPara("username");
    String sex = this.getPara("sex");
    Record record = new Record(); 
    record.set("username", username);
    record.set("sex", sex);
    MongoKit.save("student", record);
    this.renderHtml("OK");
  }
}
