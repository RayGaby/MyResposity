package cn.gov.hrss.ln.stuenroll.util.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.Record;



public class ReadXml {
	   /*public static void main(String[] args) throws IOException {  
		     File file = new File("src/Spring.xml");
		     File f = new File(file.getAbsolutePath());
	         System.out.println(read(f));  
	   }*/
	  public static List<Record> read(File file) throws IOException{
		try {  
            SAXReader reader = new SAXReader(); 
            InputStream in = new FileInputStream(file);
            Document doc = reader.read(in);  
            Element root = doc.getRootElement();  
            List<Record> result = new ArrayList<Record>();
            result = readNode(root); 
            return result;
        } catch (DocumentException e) {  
            e.printStackTrace(); 
            return null;
        }  
		
	}
	
	@SuppressWarnings("unchecked")  
	public static List<Record> readNode(Element root) {  
	    if (root == null){  //如果xml从第一层就没有数据，直接返回
	    	return null;  
	    }
	    // 获取第一层root的属性  
	    List<Attribute> attrs = root.attributes(); 
	    
	    //满足解析的格式：第二层放置要解析的数据
		if (attrs != null && attrs.size()>0) {  
			List<Record> list = new ArrayList<Record>();
			   
			// 获取第二层的数据  
			List<Element> childNodes = root.elements();
			for (Element e : childNodes) { 
				List<Attribute> childAttrs = e.attributes(); 
				Record record = new Record();
				for (Attribute attr : childAttrs) {  
	                record.set(attr.getName(), attr.getValue());
	            } 
				list.add(list.size(),record);
			}  
			return list;
		}else{
			LogKit.error("传进来的xml格式不对，无法解析");
			return null;
		}
	}
	
}
