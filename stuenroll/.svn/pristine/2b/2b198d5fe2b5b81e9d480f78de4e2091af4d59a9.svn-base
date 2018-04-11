package cn.gov.hrss.ln.stuenroll.util.csv;

import java.io.IOException;
import java.nio.charset.Charset;
/*import java.util.ArrayList;
import java.util.Date;*/
import java.util.List;

import com.csvreader.CsvWriter;
import com.jfinal.plugin.activerecord.Record;

public class WriteCsv {
	
  /*  public static void main(String[] args) {
    	String[] headers = {"id","name"};
  	    String filePath = "src/dom4j-modify.csv";
  	    Record record1 = new Record();
  	    record1.set("id", 1).set("name", "占淦");
  	    Record record2 = new Record();
  	    record2.set("id",new Date()).set("name", "why");
  	    List<Record> list = new ArrayList<Record>();
  	    list.add(record1);
  	    list.add(record2);
        write(headers,filePath,list); 
	} */
	public static void write(String[] headers, String filePath, List<Record> list){  
        try { 
             String csvFilePath = filePath;  
             CsvWriter wr =new CsvWriter(csvFilePath,',',Charset.forName("utf-8"));  
             wr.writeRecord(headers); //存入表头的数据
             for(Record record:list){
            	 String[] content = new String[headers.length]; //声明一个字符串数组来保存要导出的数据
            	 for(int i=0; i<headers.length; i++){
                	 content[i] = record.get(headers[i]).toString();
                 }
                 wr.writeRecord(content);  
             }
             wr.flush();
             wr.close();  
         } catch (IOException e) {  
            e.printStackTrace();  
         }  
    }  
	
}
