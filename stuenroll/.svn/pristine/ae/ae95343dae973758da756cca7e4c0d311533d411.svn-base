package cn.gov.hrss.ln.stuenroll.userinfo;

import java.util.HashMap;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import cn.gov.hrss.ln.stuenroll.db.I_UserInfoDao;

public class UserInfoService implements I_UserInfoService {
	private I_UserInfoDao i_UserInfoDao;

	@Override
	public List<Record> searchUserInfo(HashMap map) {
		List<Record> list = i_UserInfoDao.searchUserInfo(map);
		return list;
	}

	@Before(Tx.class)
	@Override
	public int deleteUserInfo(Long[] id) {
		int i = i_UserInfoDao.deleteUserInfo(id);
		return i;
	}

	@Override
	public List<Record> searchSelectableRole() {
		List<Record> list = i_UserInfoDao.searchSelectableRole();
		return list;
	}

	@Override
	public long searchUserInfoCount(HashMap map) {
		long count = i_UserInfoDao.searchUserInfoCount(map);
		return count;
	}

	@Override
	public List<Record> searchOrganizationJoinInYearAtDropDown(int year) {
		List<Record> list = i_UserInfoDao.searchOrganizationJoinInYearAtDropDown(year);
		return list;
	}

	@Before(Tx.class)
	@Override
	public int insertUserInfo(HashMap map) {
		int i = i_UserInfoDao.insertUserInfo(map);
		return i;
	}

	@Override
	public List<Record> searchOrganizationArchiveMember(HashMap map, long start, long length) {
		List<Record> list = i_UserInfoDao.searchOrganizationArchiveMember(map, start, length);
		return list;
	}

	@Override
	public long searchMemberCount(HashMap map) {
		long count = i_UserInfoDao.searchMemberCount(map);
		return count;
	}

	@Before(Tx.class)
	@Override
	public int updateUserInfo(HashMap map) {
		int result = i_UserInfoDao.updateUserInfo(map);
		return result;
	}

	@Override
	public List<Record> searchExsitingdata(String id) {
		List<Record> exsitingdata = i_UserInfoDao.searchExsitingdata(id);
		return exsitingdata;
	}

	@Before(Tx.class)
	@Override
	public int updateUserBlock(String id, int block) {
		int result = i_UserInfoDao.updateUserBlock(id, block);
		return result;
	}

	@Override
	public String searchPhotoId(String id) {
		String photo_id = i_UserInfoDao.searchPhotoId(id);
		return photo_id;
	}

	@Before(Tx.class)
	@Override
	public int importFile(List<Record> list) {
		int count = 0;
		int block1 = 1;

//		for (Record record : list) {
//			String block = record.getStr("block");
//			System.out.println(block);
//		}
		for (Record record : list) {
			String username = record.getStr("username");
			String password = record.getStr("password");
			String name = record.getStr("name");
			String sex = record.getStr("sex");
			String pid = record.getStr("pid");
			String job = record.getStr("job");
			String organization = record.getStr("organization");
			String role = record.getStr("role");
			String block = record.getStr("block");
			String tel = record.getStr("tel");
			String email = record.getStr("email");
			String photo_id = record.getStr("photo_id");
			// 如果解析出来的用户名为空或者null的话，跳过当次循环
			if (username == null || username == "") {
				continue;
			}
			// 如果添加的某些记录已经存在，跳出当次循环
			boolean exist = i_UserInfoDao.userNameIfExist(username);
			if (exist) {
				LogKit.warn("当前添加的记录" + username + "在user表中已经存在");
				continue;
			}
			else {
				if (block.equals("0")) {
					block1 = 0;
				}
				else {
					block1 = 1;
				}
				HashMap map = new HashMap();
				map.put("username", username);
				map.put("password", password);
				map.put("name", name);
				map.put("sex", sex);
				map.put("pid", pid);
				map.put("job", job);
				map.put("block", block1);
				map.put("photo_id", photo_id);
				map.put("organization", organization);
				map.put("role", role);
				map.put("tel", tel);
				map.put("email", email);
				i_UserInfoDao.insertUserInfo(map);
				count = count + 1;
			}
		}
		LogKit.info("成功添加了" + count + "条记录");
		return count;
	}

	public I_UserInfoDao getI_UserInfoDao() {
		return i_UserInfoDao;
	}

	public void setI_UserInfoDao(I_UserInfoDao i_UserInfoDao) {
		this.i_UserInfoDao = i_UserInfoDao;
	}

}
