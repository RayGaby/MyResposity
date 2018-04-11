package cn.gov.hrss.ln.stuenroll.report;

import java.net.URL;
import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Spring;

import jodd.datetime.JDateTime;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

@Spring("reportController")
public class ReportController extends Controller implements I_ReportController {

	private I_ReportService i_ReportService;

	@Override
	public void searchPersonalEnroll() {
		Long registerId = getSessionAttr("registerId");
		if (registerId == null) {
			renderJson("result", false);
			return;
		}
		Record record = i_ReportService.searchPersonalEnroll(registerId, 0);
		setSessionAttr("EnrollRecord", record);
		renderJson("result", record);
	}

	@Override
	public void searchPersonalState() {
		Long registerId = getSessionAttr("registerId");
		if (registerId == null) {
			renderJson("result", false);
			return;
		}
		String state = i_ReportService.searchPersonalState(registerId);
		renderJson("result", state);
	}

	@Override
	public void getNewsfeed() {
		int currentPage = getParaToInt("currentPage");
		Long registerId = getSessionAttr("registerId");
		if (registerId == null || currentPage == 0) {
			renderJson("result", false);
			return;
		}
		;
		Page<Record> page = i_ReportService.getNewsfeed(registerId, currentPage);
		renderJson("result", page);
	}

	@Override
	public void downloadEnrollRecord() {
		Long pid = getParaToLong("pid");// 网页获取的pid
		Long registerId = getSessionAttr("registerId");
		Record record = new Record();
		if (pid != null) {
			// TODO pid查询数据
			record = i_ReportService.searchPersonalEnroll(0, pid);
		}
		else if (registerId != null && getSessionAttr("EnrollRecord") != null) {
			record = getSessionAttr("EnrollRecord");
		}
		else {
			record = null;
		}

		try {
			this.renderNull();
			if (record == null) {
				renderJson("result", "none");
				return;
			}

			Class<? extends ReportController> _class = this.getClass();
			URL _resource = _class.getResource("RegisterReport.jasper");
			String file = _resource.getFile();
			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("name", record.getStr("name"));
			map.put("sex", record.getStr("sex"));
			map.put("nation", record.getStr("nation"));
			map.put("healthy", record.getStr("healthy"));
			map.put("pid", record.getStr("pid"));
			map.put("birthday", record.getStr("birthday"));
			map.put("politics", record.getStr("politics"));
			map.put("education", record.getStr("education"));
			map.put("graduate_school", record.getStr("graduate_school"));
			map.put("major", record.getStr("major"));
			map.put("graduate_date", record.getStr("graduate_date"));
			map.put("graduate_year", record.getStr("graduate_year"));
			map.put("tel", record.getStr("tel"));
			map.put("home_tel", record.getStr("home_tel"));
			map.put("email", record.getStr("email"));
			map.put("wechat", record.getStr("wechat"));
			map.put("resident_address", record.getStr("resident_address"));
			map.put("permanent_address", record.getStr("permanent_address"));
			map.put("home_address", record.getStr("home_address"));
			map.put("organization", record.getStr("organization"));
			map.put("profession", record.getStr("profession"));
			map.put("remark", record.getStr("remark"));
			map.put("liaison", record.getStr("liaison"));
			map.put("liaison_tel", record.getStr("liaison_tel"));
			map.put("abbreviation", record.getStr("abbreviation"));
			map.put("year", new JDateTime().toString("YYYY"));

			JasperPrint jasperPrint = JasperFillManager.fillReport(file, map, new JREmptyDataSource());
			JRDocxExporter exporter = new JRDocxExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			this.getResponse().setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			this.getResponse().setHeader("Content-Disposition", "attachment;filename=report.docx");
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, this.getResponse().getOutputStream());
			try {
				exporter.exportReport();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				this.renderNull();
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public I_ReportService getI_ReportService() {
		return i_ReportService;
	}

	public void setI_ReportService(I_ReportService i_ReportService) {
		this.i_ReportService = i_ReportService;
	}

	@Override
	public void notlogin() {
		// TODO 测试用\
		long registerId = getParaToLong("RegisterId");
		// 用户登陆成功需要执行Shiro的认证与授权

		setSessionAttr("registerId", registerId);

		renderJson("result", true);
	}

	@Override
	public void getpic() {
		// TODO Auto-generated method stub
		Record records = cn.gov.hrss.ln.stuenroll.plugin.MongoKit.findFirst("testimg");
		String a = records.get("pic");

		renderJson("result", a);
	}

}
