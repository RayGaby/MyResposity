package cn.gov.hrss.ln.stuenroll.config;

import org.apache.zookeeper.proto.RequestHeader;

import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.plugin.spring.SpringPlugin;
import com.jfinal.render.ViewType;

import cn.gov.hrss.ln.stuenroll.archive.ArchiveController;
import cn.gov.hrss.ln.stuenroll.classinfo.ClassinfoController;
import cn.gov.hrss.ln.stuenroll.classinfo.VisitController;
import cn.gov.hrss.ln.stuenroll.config.RequestHandler;
import cn.gov.hrss.ln.stuenroll.enroll.EnrollController;
import cn.gov.hrss.ln.stuenroll.friend.FriendController;
import cn.gov.hrss.ln.stuenroll.message.MessageController;
import cn.gov.hrss.ln.stuenroll.mobileRegister.MobileRegisterController;
import cn.gov.hrss.ln.stuenroll.myClass.MyClassController;
import cn.gov.hrss.ln.stuenroll.organization.OrganizationController;
import cn.gov.hrss.ln.stuenroll.plugin.MongodbPlugin;
import cn.gov.hrss.ln.stuenroll.profession.ProfessionController;
import cn.gov.hrss.ln.stuenroll.register.RegisterController;
import cn.gov.hrss.ln.stuenroll.report.ReportController;
import cn.gov.hrss.ln.stuenroll.rolepermission.RolePermissionController;
import cn.gov.hrss.ln.stuenroll.student.StudentController;
import cn.gov.hrss.ln.stuenroll.setting.SettingController;
import cn.gov.hrss.ln.stuenroll.test.TestController;
import cn.gov.hrss.ln.stuenroll.user.UserController;
import cn.gov.hrss.ln.stuenroll.userinfo.UserInfoController;
import cn.gov.hrss.ln.stuenroll.welcome.WelcomeController;

public class SystemConfig extends JFinalConfig {

	private Routes routes;

	@Override
	public void configConstant(Constants constants) {
		// 这里配置的都是网站项目的通用配置
		constants.setDevMode(true);// 开启开发者模式，它会把所有Http请求打印到控制台
		constants.setViewType(ViewType.JSP); // 网页使用的是JSP和HTML
	}

	@Override
	public void configHandler(Handlers handlers) {
		handlers.add(new RequestHandler());
	}

	@Override
	public void configInterceptor(Interceptors interceptors) {
		interceptors.add(new ShiroInterceptor()); // 为Java方法添加Shiro拦截器

	}

	@Override
	public void configPlugin(Plugins plugins) {
		// 添加Spring插件
		plugins.add(new SpringPlugin("Spring.xml"));

		// 添加数据库连接池插件
		final String URL = "jdbc:mysql://192.168.1.52:8066/hrss";
		final String USERNAME = "admin";
		final String PASSWORD = "abc123456";
		DruidPlugin druidPlugin = new DruidPlugin(URL, USERNAME, PASSWORD); // Druid数据库连接池
		// 关闭数据库连接池防火墙的SQL解析，使得NEXT VALUE FOR MYCATSEQ_GLOBAL全局主键可以生成
		WallFilter wallFilter = new WallFilter(); // 防火墙对象
		wallFilter.setDbType(JdbcConstants.MARIADB);
		WallConfig wallConfig = new WallConfig(); // 防火墙配置信息封装类
		wallConfig.setStrictSyntaxCheck(false);// 关闭SQL解析
		wallFilter.setConfig(wallConfig);// 提交设置

		druidPlugin.addFilter(wallFilter); // 设置防火墙

		// 数据库连接池常用设置
		druidPlugin.setDriverClass("org.mariadb.jdbc.Driver");
		druidPlugin.setInitialSize(10);// 初始连接数量
		druidPlugin.setMaxActive(20);// 最大连接数量
		druidPlugin.setMinIdle(10);// 最小连接数量
		druidPlugin.setTestOnBorrow(true); // 使用连接之前先检测
		druidPlugin.setTestOnReturn(false);// 归还连接时不检测
		druidPlugin.setValidationQuery("SELECT 1"); // 检测连接的SQL语句
		plugins.add(druidPlugin); // 把数据库连接池插件注册到JFinal框架

		// 添加ActiveRecord插件，简化JDBC操作
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
		plugins.add(activeRecordPlugin); // 把ActiveRecord注册给JFinal框架

		// 添加Redis连接插件
		RedisPlugin redisPlugin = new RedisPlugin("EnrollCache", "192.168.1.52", 6379, 20000, "abc123456", 1);
		plugins.add(redisPlugin); // 把Redis插件添加到JFinal框架

		// 添加Shiro插件
		plugins.add(new ShiroPlugin(routes));
		// 配置mongo数据库插件
		plugins.add(new MongodbPlugin("192.168.1.52", 60001, "hrss"));
	}

	@Override
	public void configRoute(Routes routes) {
		this.routes = routes;
		// 这里配置的网络类
		routes.add("/test/testController", TestController.class);
		routes.add("/user", UserController.class);
		routes.add("/welcome", WelcomeController.class);
		routes.add("/enroll", EnrollController.class);
		routes.add("/archive",ArchiveController.class);
		routes.add("/organization",OrganizationController.class);
		// 手机端 申报进度 网络类
		routes.add("/report", ReportController.class);
		// 手机端 培训报名 网络类 (web端也可以调用)
		routes.add("/register", RegisterController.class);
		// 好友列表
		routes.add("/friend", FriendController.class);
		// 用户信息
		routes.add("/userinfo", UserInfoController.class);
		// 角色权限
		routes.add("/rolepermission", RolePermissionController.class);
		// 手机端 个人设置
		routes.add("/setting", SettingController.class);

		//学生权限
		routes.add("/student",StudentController.class);
		//我的班级
		routes.add("/myclass",MyClassController.class);
		routes.add("/mobileRegister",MobileRegisterController.class);
		routes.add("/message",MessageController.class);
		routes.add("/classinfo",ClassinfoController.class);
		routes.add("/visit",VisitController.class);
		routes.add("/profession",ProfessionController.class);

	}

}

