package cn.gov.hrss.ln.stuenroll.db.mariadb;

import com.jfinal.plugin.activerecord.Db;

import cn.gov.hrss.ln.stuenroll.db.I_StudentDao;

/**
 * 学生模块具体实现类
 * @author Administrator
 *
 */
public class StudentDao implements I_StudentDao{
	
	/**
	 * 学生模块登录具体实现
	 */
	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT ");
		sql.append("COUNT(*) ");
		sql.append("FROM ");
		sql.append("studentapp_user ");
		sql.append("WHERE ");
		sql.append("username = ? ");
		sql.append("AND `password` = HEX( ");
		sql.append("AES_ENCRYPT(?, 'HelloHrss') ");
		sql.append("); ");
		
		long count=Db.queryLong(sql.toString(),username,password);
		boolean bool=(count==1);
		return bool;
	}
	@Override
	public Long queryRegisterIdAtLogin(String username) {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT id ");
		sql.append("FROM studentapp_user ");
		sql.append("WHERE username = ?; ");
		
		Long registerId=Db.queryLong(sql.toString(),username);
		return registerId;
	}
	@Override
	public String queryPIDAtLogin(Long registerId) {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT pid ");
		sql.append("FROM enroll ");
		sql.append("WHERE register_id = ?; ");
		
		String pid=Db.queryStr(sql.toString(),registerId);
		return pid;
	}

}

