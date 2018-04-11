package cn.gov.hrss.ln.stuenroll.profession;

import java.util.ArrayList;
import java.util.HashMap;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class OrganizationJoinDao implements I_OrganizationJoinDao{

	@Override
	public Record queryOrganizationAndYear(HashMap map) {
		ArrayList<Long> param = new ArrayList<Long>();
	    Long professionId = (Long) map.get("professionId");
	    Long organizationId = (Long) map.get("organizationId");
	    param.add(professionId);
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	count(DISTINCT oj.organization_id) AS organization, ");
		sql.append("    count(DISTINCT oj.`year`) AS year ");
		sql.append("FROM ");
		sql.append("	organization_join oj ");
		sql.append("WHERE ");
		sql.append("	oj.id IN ( ");
		sql.append("		SELECT ");
		sql.append("			op.organization_join_id AS organization_join_id ");
		sql.append("		FROM ");
		sql.append("			organization_profession op ");
		sql.append("		WHERE ");
		sql.append("			op.profession_id = ? ");
		sql.append("	) ");
		
		if(organizationId!=null){
			sql.append("AND oj.organization_id = ? ");
			param.add(organizationId);
		}
		
		Record result = Db.findFirst(sql.toString(), param.toArray());
	    return result;
	}

	@Override
	public Record queryProfessionCount(Long organizationId) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ");
		sql.append("	COUNT(DISTINCT(op.profession_id)) AS count ");
		sql.append("FROM ");
		sql.append("	organization_join oj ");
		sql.append("JOIN organization_profession op ON oj.id = op.organization_join_id ");
		sql.append("WHERE ");
		sql.append("	oj.organization_id = ? ");
		
		Record record = Db.findFirst(sql.toString(),organizationId);
		return record;
	}

}
