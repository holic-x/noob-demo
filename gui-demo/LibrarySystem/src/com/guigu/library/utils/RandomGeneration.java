package com.guigu.library.utils;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import com.guigu.library.service.BooksService;
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.LibraryCardServiceImpl;

/**
 * 定义随机生成指定id的工具类
 */
public class RandomGeneration {
	public static BooksService booksService = new BooksServiceImpl();
	public static LibraryCardService libraryCardService = new LibraryCardServiceImpl();

	// 随机生成32位字符序列
	public static String getRandom32charSeq() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	// 随机生成8位数字字符序列
	public static String getRandom8numSeq() {
		StringBuffer sb = new StringBuffer();
		int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int i = 0; i < 8; i++) {
			Random r = new Random();
			int temp = r.nextInt(10);
			sb.append(nums[temp]);
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
	 * 自定义生成图书的索书号 根据图书分类编号生成 生成规则：“分类编号-”+“图书序列号”
	 */
	public static String getBooksCallno(String classify_num) {
		String callno = "";
		try {
			callno = null;
			String seq = booksService.getBookSeq();
			callno = classify_num + "-" + seq;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return callno;
	}
	
	/**
	 * 自定义生成借阅证编号：根据读者分类生成 生成规则：“当前年份4位+读者分类编号2位+读者序列号”
	 */
	public static String getLibraryCardNum(int classify_num) {
		String num = "";
		try {
			num = null;
			String seq = libraryCardService.getLibraryCardSeq();
			Calendar c = Calendar.getInstance();
			String year = String.valueOf(c.get(Calendar.YEAR));
			if(classify_num==0)
			{
				num = year + "00" + seq;
			}else if(classify_num==1){
				num = year + "01" + seq;
			}else if(classify_num==2){
				num = year + "02" + seq;
			}else if(classify_num==3){
				num = year + "03" + seq;
			}else if(classify_num==4){
				num = year + "04" + seq;
			}
			return num;      
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			// System.out.println(RandomGeneration.getRandom10charSeq());
			System.out.println(RandomGeneration.getRandom10numSeq());
			// System.out.println(RandomGeneration.getRandom32charSeq());
			// System.out.println(RandomGeneration.getRandomCustomerSeq());
			// System.out.println(RandomGeneration.getRandomVendorSeq());
		}

	}
}
