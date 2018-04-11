package cn.gov.hrss.ln.stuenroll.util.csv;

/*import java.io.File;*/
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.jfinal.plugin.activerecord.Record;

public class ReadCsv {
	    
  /* public static void main(String[] args) {  
	   //read("D:/test.csv");
	   File file = new File("D:/test.csv");
	   File f = new File(file.getAbsolutePath());
	   System.out.println(f.getAbsolutePath());
       read(f.getAbsolutePath());  
	} */
	 
	public static List<Record> read(String fileName){  
	    try {          
	         List<Record> list = new ArrayList<Record>(); //用来保存数据  
	         String csvFilePath = fileName;  
	         CsvReader reader = new CsvReader(csvFilePath,',',Charset.forName("utf-8"));    //一般用这编码读就可以了      
	      
	         reader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
	         String[] headers = reader.getHeaders();
	         
	         while(reader.readRecord()){ //逐行读入除表头的数据      
	             String[] data = reader.getValues();
	             Record record = new Record();
	             for(int i=0; i<headers.length; i++){
	            	 record.set(headers[i], data[i]);
	             }
	             list.add(record);
	         }              
	         reader.close();  
	         System.out.println(list);
	         return list;    
	    }catch(Exception ex){  
	         System.out.println(ex); 
	         return null;
	    }  
	}  
}
