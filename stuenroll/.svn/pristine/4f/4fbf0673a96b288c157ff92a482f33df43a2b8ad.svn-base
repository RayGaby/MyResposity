package cn.gov.hrss.ln.stuenroll.profession;

import java.util.HashMap;

import com.jfinal.plugin.activerecord.Record;

public interface I_OrganizationJoinDao {
	/**
	 * 模块：专业管理
	 * 操作对象：机构用户  就业网用户
	 * 操作内容：从organization_join表中查出关联机构和关联年届
	 * @param map
	 * @return
	 */
	public Record queryOrganizationAndYear(HashMap map);
	
	/**
	 * 模块：专业管理
	 * 操作对象：机构用户
	 * 操作内容：机构用户查询到的专业总数
	 * @param organizationId
	 * @return
	 */
	public Record queryProfessionCount(Long organizationId);
}
