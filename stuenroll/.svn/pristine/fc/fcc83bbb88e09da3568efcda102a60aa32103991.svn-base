package cn.gov.hrss.ln.stuenroll.welcome;

import java.util.ArrayList;
import java.util.HashMap;

import com.jfinal.plugin.activerecord.Db;

import cn.gov.hrss.ln.stuenroll.db.I_ArchiveDao;
import cn.gov.hrss.ln.stuenroll.db.I_EnrollDao;

/**
 * 欢迎模块业务类
 * 
 * @author YangDi
 *
 */
public class WelcomeService implements I_WelcomeService {
	private I_EnrollDao i_EnrollDao;
	private I_ArchiveDao i_ArchiveDao;
	

	@Override
	public HashMap getApplyInfo(Integer year, Integer month, Long organizationId) {
		HashMap map1 = i_EnrollDao.getApplyInfo(year, month, organizationId);
		HashMap map2 = i_ArchiveDao.getApplyInfo(year, month, organizationId);
		HashMap map = new HashMap();
		Long actualNumber = (Long) map1.get("actualNumber")+(Long) map2.get("actualNumber");
		Long applyNumber = (Long) map1.get("applyNumber");
		Long ckeckNumber = (Long) map1.get("ckeckNumber");
		Long learnNumber = (Long) map1.get("learnNumber");
		Long quitNumber = (Long) map1.get("quitNumber")+(Long) map2.get("quitNumber");
		Long passNumber = (Long) map1.get("passNumber")+ (Long) map2.get("actualNumber");
		Long archiveNumber = (Long) map2.get("actualNumber");
		map.put("actualNumber", actualNumber);
		map.put("applyNumber", applyNumber);
		map.put("ckeckNumber", ckeckNumber);
		map.put("learnNumber", learnNumber);
		map.put("quitNumber", quitNumber);
		map.put("archiveNumber", archiveNumber);
		map.put("passNumber", passNumber);
		return map;
	}

	@Override
	public HashMap professionClassCount(Integer year, Long organizationId) {
		HashMap temp = professionCount(year, organizationId);
		return i_EnrollDao.professionClassCount(temp , year,organizationId);
	}
	@Override
	public HashMap getJob(Integer year, Long organizationId) {
		HashMap temp = professionCount(year, organizationId);
		return i_ArchiveDao.getJob(temp ,year, organizationId);
	}

	@Override
	public HashMap professionCount(Integer year, Long organizationId) {
		HashMap map1 = i_EnrollDao.professionCount(year,organizationId);
		HashMap map2 = i_ArchiveDao.professionCount(year,organizationId);
		HashMap map = new HashMap();
		ArrayList<Long> list1 = new ArrayList();
		ArrayList<String> list2 = new ArrayList();
		list1.add((Long)map1.get("pc_1")+(Long)map2.get("pc_1"));
		list1.add((Long)map1.get("pc_2")+(Long)map2.get("pc_2"));
		list1.add((Long)map1.get("pc_3")+(Long)map2.get("pc_3"));
		list1.add((Long)map1.get("pc_4")+(Long)map2.get("pc_4"));
		list1.add((Long)map1.get("pc_5")+(Long)map2.get("pc_5"));
		list1.add((Long)map1.get("pc_6")+(Long)map2.get("pc_6"));
		list1.add((Long)map1.get("pc_7")+(Long)map2.get("pc_7"));
		list1.add((Long)map1.get("pc_8")+(Long)map2.get("pc_8"));
		list1.add((Long)map1.get("pc_9")+(Long)map2.get("pc_9"));
		list1.add((Long)map1.get("pc_10")+(Long)map2.get("pc_10"));
		list1.add((Long)map1.get("pc_11")+(Long)map2.get("pc_11"));
		list1.add((Long)map1.get("pc_12")+(Long)map2.get("pc_12"));
		list1.add((Long)map1.get("pc_13")+(Long)map2.get("pc_13"));
		list1.add((Long)map1.get("pc_14")+(Long)map2.get("pc_14"));
		list1.add((Long)map1.get("pc_15")+(Long)map2.get("pc_15"));
		list2.add((String)map1.get("pn_1"));
		list2.add((String)map1.get("pn_2"));
		list2.add((String)map1.get("pn_3"));
		list2.add((String)map1.get("pn_4"));
		list2.add((String)map1.get("pn_5"));
		list2.add((String)map1.get("pn_6"));
		list2.add((String)map1.get("pn_7"));
		list2.add((String)map1.get("pn_8"));
		list2.add((String)map1.get("pn_9"));
		list2.add((String)map1.get("pn_10"));
		list2.add((String)map1.get("pn_11"));
		list2.add((String)map1.get("pn_12"));
		list2.add((String)map1.get("pn_13"));
		list2.add((String)map1.get("pn_14"));
		list2.add((String)map1.get("pn_15"));
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				if(list1.get(i)>=list1.get(j)){
					Long temp = list1.get(i);
					list1.set(i, list1.get(j));
					list1.set(j, temp);
					String t = list2.get(i);
					list2.set(i, list2.get(j));
					list2.set(j, t);
				}
			}
		}
		map.put("pn_1",list2.get(0));
		map.put("pn_2",list2.get(1));
		map.put("pn_3",list2.get(2));
		map.put("pn_4",list2.get(3));
		map.put("pn_5",list2.get(4));
		map.put("pc_1",list1.get(0));
		map.put("pc_2",list1.get(1));
		map.put("pc_3",list1.get(2));
		map.put("pc_4",list1.get(3));
		map.put("pc_5",list1.get(4));
		return map;
	}


	@Override
	public HashMap PlaceCount(Integer year, Long organizationId) {
		HashMap map = new HashMap();
		long count_1 = i_EnrollDao.PlaceCount(year, "大连",organizationId)+i_ArchiveDao.PlaceCount(year, "大连",organizationId);
		long count_2 = i_EnrollDao.PlaceCount(year, "沈阳",organizationId)+i_ArchiveDao.PlaceCount(year, "沈阳",organizationId);
		long count_3 = i_EnrollDao.PlaceCount(year, "鞍山",organizationId)+i_ArchiveDao.PlaceCount(year, "鞍山",organizationId);
		map.put("大连", count_1);
		map.put("沈阳", count_2);
		map.put("鞍山", count_3);
		return map;
	}


	@Override
	public HashMap education(Integer year, Long organizationId) {
		HashMap map = new HashMap();
		long count_1 = i_EnrollDao.education(year, "大专",organizationId)+i_ArchiveDao.education(year, "大专",organizationId);
		long count_2 = i_EnrollDao.education(year, "本科",organizationId)+i_ArchiveDao.education(year, "本科",organizationId);
		long count_3 = i_EnrollDao.education(year, "硕士",organizationId)+i_ArchiveDao.education(year, "硕士",organizationId);
		long count_4 = i_EnrollDao.education(year, "博士",organizationId)+i_ArchiveDao.education(year, "博士",organizationId);
		map.put("专科", count_1);
		map.put("本科", count_2);
		map.put("硕士", count_3);
		map.put("博士", count_4);
		return map;
	}

	@Override
	public HashMap statisticsInYear(Integer year, Long organizationId) {
		HashMap<String, ArrayList<Long>> map = new HashMap();
		ArrayList<Long> list_1 = new ArrayList<>();
		ArrayList<Long> list_2 = new ArrayList<>();
		ArrayList<Long> list_3 = new ArrayList<>();
		ArrayList<Long> list_4 = new ArrayList<>();
		ArrayList<Long> list_5 = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			// 获取月报名总数
			long count_1 = i_EnrollDao.searchCountByCondition(year, i, 1,organizationId);
			// 获取月审查总数
			long count_2 = i_EnrollDao.searchCountByCondition(year, i, 2,organizationId);
			// 获取月学习人数
			long count_3 = i_EnrollDao.searchCountByCondition(year, i, 3,organizationId) + i_ArchiveDao.searchCountByCondition(year, i, 3,organizationId);
			// 获取月中退人数
			long count_4 = i_EnrollDao.searchCountByCondition(year, i, 4,organizationId) + i_ArchiveDao.searchCountByCondition(year, i, 4,organizationId);
			// 获取月就业人数
			long count_5 = i_EnrollDao.searchCountByCondition(year, i, 5,organizationId) + i_ArchiveDao.searchCountByCondition(year, i, 5,organizationId);

			list_1.add(count_1);
			list_2.add(count_2);
			list_3.add(count_3);
			list_4.add(count_4);
			list_5.add(count_5);
		}
		map.put("报名数据", list_1);
		map.put("审查数据", list_2);
		map.put("学习数据", list_3);
		map.put("中退数据", list_4);
		map.put("就业数据", list_5);

		return map;
	}

	public I_EnrollDao getI_EnrollDao() {
		return i_EnrollDao;
	}

	public void setI_EnrollDao(I_EnrollDao i_EnrollDao) {
		this.i_EnrollDao = i_EnrollDao;
	}

	public I_ArchiveDao getI_ArchiveDao() {
		return i_ArchiveDao;
	}

	public void setI_ArchiveDao(I_ArchiveDao i_ArchiveDao) {
		this.i_ArchiveDao = i_ArchiveDao;
	}



}
