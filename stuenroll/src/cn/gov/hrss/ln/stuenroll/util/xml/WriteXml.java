package cn.gov.hrss.ln.stuenroll.util.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/*import java.util.ArrayList;
import java.util.Date;*/
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import com.jfinal.plugin.activerecord.Record;

public class WriteXml {
/*public static void main(String[] args) { 
	  String[] headers = {"id","name"};
	  String filePath = "src/dom4j-modify.xml";
	  Record record1 = new Record();
	  record1.set("id", 1).set("name", "占淦");
	  Record record2 = new Record();
	  record2.set("id",new Date()).set("name", "why");
	  List<Record> list = new ArrayList<Record>();
	  list.add(record1);
	  list.add(record2);
      write(headers,filePath,list);  
   }  */
	
	/**
	 * 
	 * @param headers  xml第二层的属性名
	 * @param filePath 生成文件的路径+生成文件的名称
	 * @param list 要导出的数据
	 */
	public static void write(String[] headers, String filePath, List<Record> list) {  
        try {  
            // 创建一个xml文档  
            Document doc = DocumentHelper.createDocument();  
            Element root = doc.addElement("list");  
            root.addAttribute("name", "export");  
           
            for(Record record:list){
            	Element second = root.addElement("record");
            	for(int i=0; i<headers.length; i++){
            		second.addAttribute(headers[i], record.get(headers[i]).toString());
            	}
            }
            File file = new File(filePath);  
            if (file.exists()) {  
                file.delete();  
            }  
            file.createNewFile();  
            OutputFormat xmlFormat = new OutputFormat();
            xmlFormat.setEncoding("UTF-8");
            xmlFormat.setNewlines(true);//设置换行
            xmlFormat.setIndent(true);//生成缩进
            xmlFormat.setIndent("    "); //使用四个空格进行缩进，可以兼容文本编辑器
            XMLWriter out = new XMLWriter(new FileWriter(file),xmlFormat);  
            out.write(doc);  
            out.flush();  
            out.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
