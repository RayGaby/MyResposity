package cn.gov.hrss.ln.stuenroll.profession;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Spring;
import com.jfinal.upload.UploadFile;

import cn.gov.hrss.ln.stuenroll.util.csv.ReadCsv;
import cn.gov.hrss.ln.stuenroll.util.csv.WriteCsv;
import cn.gov.hrss.ln.stuenroll.util.excel.ReadExcel;
import cn.gov.hrss.ln.stuenroll.util.excel.WriteExcel;
import cn.gov.hrss.ln.stuenroll.util.xml.ReadXml;
import cn.gov.hrss.ln.stuenroll.util.xml.WriteXml;

@Spring("professionController")
public class ProfessionController extends Controller implements I_ProfessionController{
	private I_ProfessionService i_ProfessionService;
	private int rowsInPage;
	private int rowsInStu;
	
	@RequiresPermissions({"6_4"})
	@Override
	public void queryProfession() {
		Long organizationId = null;
	    HashMap map = new HashMap();
	   
	    String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			organizationId = getSessionAttr("organizationId");
			//System.out.println(organization_id);
		}
		map.put("organizationId", organizationId);
		//System.out.println(organization_id);
		Long page = getParaToLong("page");
		if (page == null) {
			page = 1L;
		}
		long start = (page - 1) * rowsInPage;
		long length = rowsInPage;
		map.put("start", start);
		map.put("length", length);
		List<Record> list =  i_ProfessionService.queryProfession(map);
	    renderJson("result",list);
		
	}
	
	@RequiresPermissions({"6_4"})
	@Override
	public void queryProfessionCount() {
		Long organizationId = null;
	   
	    String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			organizationId = getSessionAttr("organizationId");
		}
		
		
		Record list =  i_ProfessionService.queryProfessionCount(organizationId);
	    renderJson("result",list);
		
	}
	
	@RequiresPermissions({"6_1"})
	@Override
	public void addProfession() {
		String professionName = getPara("name");
		String organization = getSessionAttr("organization");
		int i = 0;
		if(organization.equals("辽宁省就业网") == false){
			i = -1;
		}
		else{
			i = i_ProfessionService.addProfession(professionName);
		}
		renderJson("result",i);
		
	}
	
	@RequiresPermissions({"6_3"})
	@Override
	public void modifyProfession() {
		String professionName = getPara("name");
		Long professionId = getParaToLong("id");
		String organization = getSessionAttr("organization");
		int i = 0;
		if(organization.equals("辽宁省就业网") == false){
			i = -1;
		}
		else{
			i = i_ProfessionService.modifyProfession(professionName, professionId);
		}
		renderJson("result",i);
		
	}
	
	@RequiresPermissions({"6_2"})
	@Override
	public void deleteProfession() {
		Long[] id = getParaValuesToLong("id");
		int count;
		String organization = getSessionAttr("organization");
		
		if(organization.equals("辽宁省就业网") == false){
			count = -1;
		}
		count = i_ProfessionService.deleteProfession(id);
		renderJson("deleteRows", count);
 		
	}
	
	@RequiresPermissions({"6_4"})
	@Override
	public void queryProfessionMember() {
		Long professionId = getParaToLong("id");
		Long organizationId = null;
	    HashMap map = new HashMap();
	    map.put("professionId", professionId);
	    String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			organizationId = getSessionAttr("organizationId");
			//System.out.println(organization_id);
		}
		map.put("organizationId", organizationId);
		//System.out.println(organization_id);
		Long page = getParaToLong("page");
		if (page == null) {
			page = 1L;
		}
		long start = (page - 1) * rowsInStu;
		long length = rowsInStu;
		map.put("start", start);
		map.put("length", length);
		
		List<Record> list = i_ProfessionService.queryProfessionMember(map);
		
		renderJson("result",list);
		
	}
	
	@RequiresPermissions({"6_4"})
	public void exportExcel(){
		/**
		 * 先获取页面要进行导出的list
		 */
		Long organization_id = getParaToLong("organizationId");
	    HashMap map = new HashMap();
	    String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			organization_id = getSessionAttr("organizationId");
			System.out.println(organization_id);
		}else{
			organization_id = null;
		}
		map.put("organizationId", organization_id);
		List<Record> list =  i_ProfessionService.queryProfession(map);
		//这是最终导出excel的list
		List<Record> resultList = new ArrayList<Record>(); 
		int index = 0;
		for(Record record:list){
			DecimalFormat df = new DecimalFormat("#.00");
			index = index+1;
			String percentrate = "0"; //默认percentage = 0
			String professionId = record.getStr("profession_id");
			String professionName = record.getStr("professionName");
			Long connectedOrganization = record.getLong("organization");
			Long connectedYear = record.getLong("year");
			Long classCount = record.getLong("class_count");
			Long studentCount = record.getLong("student_count");
			Long employedCount = record.getLong("employed_count");
			if(employedCount!=0){
				int rate = (int)((employedCount*1.0/studentCount*1.0)*100.0);
			    percentrate =String.valueOf(rate) +"%";
			}
			else if(employedCount==0){
				percentrate = "0%";
			}
				
			Record temp = new Record();
			temp.set("序号", index)
			.set("专业ID",professionId)
			.set("专业名称", professionName)
			.set("关联机构", connectedOrganization)
			.set("关联年届", connectedYear)
			.set("班级数量", classCount)
			.set("培训人数", studentCount)
			.set("就业人数", employedCount)
			.set("就业率", percentrate);
			resultList.add(resultList.size(),temp);		
		}
		String filePath = PathKit.getWebRootPath()+"/export.xls";
		//System.out.println(PathKit.getWebRootPath());
		File file = new File(filePath); //取临时文件
		if(!file.exists()){
			try {
				file.createNewFile(); //如果临时文件不存在创建临时文件
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			WriteExcel writer = new WriteExcel();
			String title = "导出专业信息";
			String[] headers = {"序号","专业ID","专业名称","关联机构","关联年届","班级数量","培训人数","就业人数","就业率"};
			OutputStream out = new FileOutputStream(filePath);
			writer.exportExcel(title, headers, resultList, out);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//写完临时文件之后，开始启动浏览器下载
		String fileName = file.getName();
		//System.out.println(fileName);
		HttpServletResponse response = this.getResponse();
		//设置content-disposition响应头控制浏览器弹出保存框，若没有该行代码，浏览器会直接打开并且显示文件
		//中文名要经过URLEncoder.encode编码，否则客户端能下载，但是文件名是乱码
		try {
			response.reset();//重置输出流
			response.setHeader("content-disposition", "attachment;filename=profession-"+URLEncoder.encode(fileName,"UTF-8"));
			response.setContentType("application/vnd.ms-excel");
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream()); 
			
			InputStream in = new BufferedInputStream(new FileInputStream(filePath)); //获取要下载的文件流
			byte[] buffer = new byte[(int) file.length()];
			in.read(buffer);
			in.close();
			toClient.write(buffer);
			toClient.close();
			file.delete();//删除临时文件
			response.getOutputStream().close();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e2) {
			e2.printStackTrace();
		}finally{
			this.renderNull();
		}
	}
	
	@RequiresPermissions({"6_4"})
	public void exportXml(){
		/**
		 * 先获取页面要进行导出的list
		 */
		Long organization_id = getParaToLong("organizationId");
	    HashMap map = new HashMap();
	    String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			organization_id = getSessionAttr("organizationId");
			System.out.println(organization_id);
		}else{
			organization_id = null;
		}
		map.put("organization_id", organization_id);
		List<Record> list =  i_ProfessionService.queryProfession(map);
		//这是最终导出excel的list
		List<Record> resultList = new ArrayList<Record>(); 
		int index = 0;
		for(Record record:list){
			index = index+1;
			String percentrate = "0"; //默认percentage = 0
			String professionId = record.getStr("profession_id");
			String professionName = record.getStr("professionName");
			Long connectedOrganization = record.getLong("organization");
			Long connectedYear = record.getLong("year");
			Long classCount = record.getLong("class_count");
			Long studentCount = record.getLong("student_count");
			Long employedCount = record.getLong("employed_count");
			if(employedCount!=0){
				int rate = (int)((employedCount*1.0/studentCount*1.0)*100.0);
			    percentrate =String.valueOf(rate) +"%";
			}
			else if(employedCount==0){
				percentrate = "0%";
			}
				
			Record temp = new Record();
			temp.set("序号", index)
			.set("专业ID",professionId)
			.set("专业名称", professionName)
			.set("关联机构", connectedOrganization)
			.set("关联年届", connectedYear)
			.set("班级数量", classCount)
			.set("培训人数", studentCount)
			.set("就业人数", employedCount)
			.set("就业率", percentrate);
			resultList.add(resultList.size(),temp);	
		}
		String filePath = PathKit.getWebRootPath()+"/export.xml";
		//System.out.println(PathKit.getWebRootPath());
		File file = new File(filePath); //取临时文件
		if(!file.exists()){
			try {
				file.createNewFile(); //如果临时文件不存在创建临时文件
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		WriteXml writer = new WriteXml();
		String[] headers = {"序号","专业ID","专业名称","关联机构","关联年届","班级数量","培训人数","就业人数","就业率"};
		writer.write( headers,filePath,resultList);
		
		//写完临时文件之后，开始启动浏览器下载
		String fileName = file.getName();
		//System.out.println(fileName);
		HttpServletResponse response = this.getResponse();
		//设置content-disposition响应头控制浏览器弹出保存框，若没有该行代码，浏览器会直接打开并且显示文件
		//中文名要经过URLEncoder.encode编码，否则客户端能下载，但是文件名是乱码
		try {
			response.reset();//重置输出流
			response.setHeader("content-disposition", "attachment;filename=profession-"+URLEncoder.encode(fileName,"UTF-8"));
			response.setContentType("application/vnd.ms-excel");
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream()); 
			
			InputStream in = new BufferedInputStream(new FileInputStream(filePath)); //获取要下载的文件流
			byte[] buffer = new byte[(int) file.length()];
			in.read(buffer);
			in.close();
			toClient.write(buffer);
			toClient.close();
			file.delete();//删除临时文件
			response.getOutputStream().close();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e2) {
			e2.printStackTrace();
		}finally{
			this.renderNull();
		}
	}
	
	@RequiresPermissions({"6_4"})
	public void exportCsv(){
		/**
		 * 先获取页面要进行导出的list
		 */
		Long organization_id = getParaToLong("organizationId");
	    HashMap map = new HashMap();
	    String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			organization_id = getSessionAttr("organizationId");
			System.out.println(organization_id);
		}else{
			organization_id = null;
		}
		map.put("organization_id", organization_id);
		List<Record> list =  i_ProfessionService.queryProfession(map);
		//这是最终导出excel的list
		List<Record> resultList = new ArrayList<Record>(); 
		int index = 0;
		for(Record record:list){
			index = index+1;
			String percentrate = "0"; //默认percentage = 0
			String professionId = record.getStr("profession_id");
			String professionName = record.getStr("professionName");
			Long connectedOrganization = record.getLong("organization");
			Long connectedYear = record.getLong("year");
			Long classCount = record.getLong("class_count");
			Long studentCount = record.getLong("student_count");
			Long employedCount = record.getLong("employed_count");
			if(employedCount!=0){
				int rate = (int)((employedCount*1.0/studentCount*1.0)*100.0);
			    percentrate =String.valueOf(rate) +"%";
			}
			else if(employedCount==0){
				percentrate = "0%";
			}
				
			Record temp = new Record();
			temp.set("序号", index)
			.set("专业ID",professionId)
			.set("专业名称", professionName)
			.set("关联机构", connectedOrganization)
			.set("关联年届", connectedYear)
			.set("班级数量", classCount)
			.set("培训人数", studentCount)
			.set("就业人数", employedCount)
			.set("就业率", percentrate);
			resultList.add(resultList.size(),temp);	
		}
		String filePath = PathKit.getWebRootPath()+"/export.csv";
		//System.out.println(PathKit.getWebRootPath());
		File file = new File(filePath); //取临时文件
		if(!file.exists()){
			try {
				file.createNewFile(); //如果临时文件不存在创建临时文件
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		WriteCsv writer = new WriteCsv();
		String[] headers = {"序号","专业ID","专业名称","关联机构","关联年届","班级数量","培训人数","就业人数","就业率"};
		writer.write( headers,filePath,resultList);
		
		//写完临时文件之后，开始启动浏览器下载
		String fileName = file.getName();
		//System.out.println(fileName);
		HttpServletResponse response = this.getResponse();
		//设置content-disposition响应头控制浏览器弹出保存框，若没有该行代码，浏览器会直接打开并且显示文件
		//中文名要经过URLEncoder.encode编码，否则客户端能下载，但是文件名是乱码
		try {
			response.reset();//重置输出流
			response.setHeader("content-disposition", "attachment;filename=profession-"+URLEncoder.encode(fileName,"UTF-8"));
			response.setContentType("application/vnd.ms-excel");
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream()); 
			
			InputStream in = new BufferedInputStream(new FileInputStream(filePath)); //获取要下载的文件流
			byte[] buffer = new byte[(int) file.length()];
			in.read(buffer);
			in.close();
			toClient.write(buffer);
			toClient.close();
			file.delete();//删除临时文件
			response.getOutputStream().close();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e2) {
			e2.printStackTrace();
		}finally{
			this.renderNull();
		}
	}
	
	@RequiresPermissions({"6_1"})
	@Override
	public void importFile() {
		int count = 0;
		//判断是否是就业网用户，如果不是，不可以导入
		String organization = getSessionAttr("organization");
		if(organization.equals("辽宁省就业网") == false){
			count = -1;
			renderJson("result",count);
			return;
		}
		UploadFile _file = this.getFile("file");
		File file = _file.getFile(); //获得文件
		String fileName = file.getName();//获得文件名
		String extension = fileName.substring(fileName.lastIndexOf("."));
		List<Record> list = new ArrayList<Record>();
		if(extension.equals(".xml")){
			try {
				list = ReadXml.read(file);
				count = i_ProfessionService.importFile(list);
				file.delete();
				renderJson("result",count);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(extension.equals(".csv")){
			list = ReadCsv.read(file.getAbsolutePath());
			count = i_ProfessionService.importFile(list);
			file.delete();
			renderJson("result",count);
	    }else if(extension.equals(".xls")||extension.equals(".xlsx")){
			list = ReadExcel.read(file.getAbsolutePath());
			count = i_ProfessionService.importFile(list);
			file.delete();
			renderJson("result",count);
		}else{
			file.delete();
			count = -2;
			renderJson("result",count);
		} 
		
	}
	
	public I_ProfessionService getI_ProfessionService() {
		return i_ProfessionService;
	}
	public void setI_ProfessionService(I_ProfessionService i_ProfessionService) {
		this.i_ProfessionService = i_ProfessionService;
	}
	public int getRowsInPage() {
		return rowsInPage;
	}
	public void setRowsInPage(int rowsInPage) {
		this.rowsInPage = rowsInPage;
	}
	
	public int getRowsInStu() {
		return rowsInStu;
	}
	public void setRowsInStu(int rowsInStu) {
		this.rowsInStu = rowsInStu;
	}
	
}
