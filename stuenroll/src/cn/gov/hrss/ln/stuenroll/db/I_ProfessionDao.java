package cn.gov.hrss.ln.stuenroll.db;

import com.jfinal.plugin.activerecord.Record;
import java.util.List;

public interface I_ProfessionDao {	  
	  /**
	   * 查询专业名称
	   * @param id 专业ID
	   * @return 查询结果
	   */
	  public String searchName(long id);
	  /**
	   * 查询专业ID
	   * 
	   * @param name
	   * @return
	   */
	  public String searchId(String name);
	  /**
	   * 查询当前机构所有年份的专业
	   * 
	   * @return
	   */
	  public List<Record> searchProfessionWithOrganization(long organizationId);
	  /**
	   * 查询所有专业
	   * @return
	   */
	  public List<Record> searchAll();
}
