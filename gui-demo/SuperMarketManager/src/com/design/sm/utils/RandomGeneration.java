package com.design.sm.utils;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

/**
 * 定义随机生成指定id的工具类
 */
public class RandomGeneration {
	// 随机生成32位字符序列
	public static String getRandom32charSeq() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	// 随机生成10位字符序列
	public static String getRandom10charSeq() {
		StringBuffer sb = new StringBuffer();
		char[] ch = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n',
				'o','p','q','r','s','t','u','v','w','x','y','z'};
		for (int i = 0; i < 10; i++) {
			Random r = new Random();
			int temp = r.nextInt(26);
			sb.append(ch[temp]);
		}
		return sb.toString();
	}

	// 随机生成10位数字字符序列
	public static String getRandom10numSeq() {
		StringBuffer sb = new StringBuffer();
		int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int i = 0; i < 10; i++) {
			Random r = new Random();
			int temp = r.nextInt(10);
			sb.append(nums[temp]);
		}
		return sb.toString();
	}

	/**
	 * 随机自动生成员工编号
	 * 生成规则：
	 * 入职年份+入职部门代号+自增数
	 * 入职年份默认获取当前系统时间
	 * 入职部门代号由相应的入职部门代号进行代替
	 * 自增数代表的是该部门的第几个员工
	 * 人事部：01
	 * 财务部：02
	 * 营销部：03
	 * 客服部：04
	 * 物流部：05
	 * 备    用：06
	 * 根据数据库中的设计，员工工号是10位的int类型的数据
	 * 随着员工数目的增多，则有可能出现计数超过9999时引发异常
	 * 按照相应的实际逻辑，此处设置每到6666便将计数器进行重置
	 */
	public static String getEmployeeNum(String deptId){
		String id = null;
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR));
		if(deptId.equals("8132456543"))
		{
			id = year + "01";
		}else if(deptId.equals("7168462722")){
			id = year + "02";
		}else if(deptId.equals("3153961019")){
			id = year + "03";
		}else if(deptId.equals("1674836044")){
			id = year + "04";
		}else if(deptId.equals("2249164713")){
			id = year + "05";
		}else{
			id = year + "06";
		}
		return id;      
	}
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomGeneration.getRandom10charSeq());
//			System.out.println(RandomGeneration.getRandom10numSeq());
//			System.out.println(RandomGeneration.getRandom32charSeq());
//			System.out.println(RandomGeneration.getRandomCustomerSeq());
//			System.out.println(RandomGeneration.getRandomVendorSeq());
		}
//		System.out.println(RandomGeneration.getEmployeeNum("8132456543"));
//		System.out.println(RandomGeneration.getEmployeeNum("7168462722"));
//		System.out.println(RandomGeneration.getEmployeeNum("3153961019"));
//		System.out.println(RandomGeneration.getEmployeeNum("1674836044"));
//		System.out.println(RandomGeneration.getEmployeeNum("2249164713"));
//		System.out.println(RandomGeneration.getEmployeeNum("2245564713"));
	}
}
