package cn.gov.hrss.ln.stuenroll.db;

public interface I_MobileRegisterDao {
	public int saveRegisteInfo(String username, String password, long tel);
	public boolean checkUsername(String username);
}
