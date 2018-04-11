package cn.gov.hrss.ln.stuenroll.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import com.jfinal.plugin.activerecord.Record;

/**
 * 读取excel解析出来为Record
 * 读写要求：要进行读写操作的Excel的每一个sheet的第一行的值对应到要插入的数据库表的每一列的属性名
 * @author GoldRoger
 *
 */
public class ReadExcel {
	
	//设置日期格式，读取excel里面的日期
	private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");  
	public static List<Record> read(String fileName){  
       
        List<Record> list = new ArrayList<Record>();   
        
        Workbook workbook = null; //这种方式 Excel 2003/2007/2010 都是可以处理的
        
        File file  = null;  
        FileInputStream fis = null;   
       
        try {  
            file = new File(fileName);  
            if(!file.exists()){  
                throw new RuntimeException("文件不存在");  
            }else{  
                fis = new FileInputStream(file);  
                workbook  = WorkbookFactory.create(fis);  //创建 excel的解析对象
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e.getMessage());  
        } finally {  
            if(fis != null){  
                try {
					fis.close();   //关闭文件流
				} catch (IOException e) {
					e.printStackTrace();
				}  
            }  
        }  

        
        try{   
	    	   Sheet fristSheet = workbook.getSheetAt(0);   //取第一张纸
	    	   Row firstRow = fristSheet.getRow(0); //得到第一行的excel数据
	    	   int fristCellCount = firstRow.getPhysicalNumberOfCells(); //获取第一行的总列数  
	    	   Record firstRecord = new Record();
	    	   //遍历第一行的每一列
	    	   for(int i=0; i<fristCellCount; i++){
	    		   Cell firstRowCell = firstRow.getCell(i);
	    		   firstRecord.set(String.valueOf(i),getValue(firstRowCell)); //将第一行的每一列的数据放到record里面
	    	   }
            	 
	    	   //循环sheet，放入数据
	    	   int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量  
	    	   for(int s = 0; s < sheetCount; s++){
	    		   Sheet sheet = workbook.getSheetAt(s);  
	               int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数  
	               for (int r=1; r < rowCount; r++) {  //从第二行开始遍历
	                   Row row = sheet.getRow(r);  
	                   int cellCount = row.getPhysicalNumberOfCells(); //获取总列数  
	                   //遍历每一列  
	                   Record record = new Record(); 
	                   for (int c = 0; c < cellCount; c++) {  
	                	   Cell cell = row.getCell(c);
	                       record.set(firstRecord.getStr(String.valueOf(c)), getValue(cell));
	                   }
	                   list.add(list.size(),record);
	               }
	    	   }
	    	   System.out.println(list);
        }catch(Exception e){   
            e.printStackTrace();   
            System.out.println("POI解析错误");   
        }   
        return list;   
    }
	
	@SuppressWarnings("static-access")
	private static String getValue(Cell cell) {
		if(cell.getCellType() == Cell.CELL_TYPE_STRING){
			//文本
			return cell.getStringCellValue();
		}
	    else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
            // 布尔
            return String.valueOf(cell.getBooleanCellValue());
        } 
        else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
        	// 数值类型
        	if(DateUtil.isCellDateFormatted(cell)) { 
        		//日期
               return fmt.format(cell.getDateCellValue()); 
            }  
            else {  
            	//数字
            	//将原始数据的Double类型格式化为String类型返回
            	DecimalFormat df=new DecimalFormat("0");
            	String cellNum=df.format(cell.getNumericCellValue());
            	return cellNum;
                //return String.valueOf(cell.getNumericCellValue());
            }  
        } 
        else if(cell.getCellType() == Cell.CELL_TYPE_BLANK ){
            // 空白
            return String.valueOf(cell.getStringCellValue());
        }
        else if(cell.getCellType() == Cell.CELL_TYPE_ERROR){
        	//错误
        	return "错误";
        }
        else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){
        	//公式
        	return "错误";
        }else{
        	//其他
        	return "错误";
        }
    }
}
