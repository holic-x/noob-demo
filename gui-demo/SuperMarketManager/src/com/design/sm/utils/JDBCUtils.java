package com.design.sm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 将数据库连接设置为单例模式（一次只能在内存中存在一个对象）
 * 1.定义私有的静态的属于自己的变量
 * 2.构造函数私有化
 * 3.通过静态代码块初始化相关的属性（数据库连接的四个属性）
 * 4.单例获取JDBCUtils工具类  ： 双重验证模式（区分懒汉式、饿汉式）
 *   a.判断当前对象是否为空
 *   b.如果为空则进行锁定synchronized (JDBCUtils.class) {...}
 *   c.二次判断当前对象是否为空
 *     （有可能存在在锁定的时候其他的对象已经创建了，则不需要重复创建）
 *   d.返回当前的对象
 * 5.获取数据库连接：创建连接、加载驱动
 * 最终数据库连接获取方式：JDBCUtils.getConnection();
 */
public class JDBCUtils {
	//1.定义私有的静态的属于自己的变量
	private static JDBCUtils jdbcutils;
	/**
	 *   定义数据库连接的四个属性
	 *   用户名 username:haha
	 *   密码    password:haha
	 *   驱动    driver:
	 *   连接    url:
	 */
	private static String username = null;
	private static String password = null;
	private static String driver = null;
	private static String url = null;
	//2.构造函数私有化
	private JDBCUtils()
	{  
		
	}
	//3.通过静态代码块初始化相关的属性
	static
	{
		Properties p = new Properties();
		try {
			// 利用当前类的类加载器完成配置文件的加载
			InputStream in = JDBCUtils.class.getClassLoader()
					.getResourceAsStream("jdbc.properties");
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 获取配置文件中的相关属性信息
		username = p.getProperty("jdbc.username");
		password = p.getProperty("jdbc.password");
		driver = p.getProperty("jdbc.driver");
		url = p.getProperty("jdbc.url");
	}
	/**
	 *   获取数据库连接
	 *   a.加载驱动：Class.forName(driver);
	 *   b.通过DriverManager的getConnection方法获取数据库连接
	 *   c.最终返回获取的连接
	 *   为了方便说明此处处理异常统一抛给上一级处理
	 *   @throws Exception 
	 */
	//4.单例获取JDBCUtils工具类  ： 双重验证模式
	public static JDBCUtils getJDBCUtils()
	{
		//判断当前jdbcutils是否为空
		if(jdbcutils==null)
		{
			//如果jdbcutils为空则进行锁定
			synchronized (JDBCUtils.class) {
				/**
				 * 再进行二次判断，因为有可能会出现加锁的同时另外一个对象
				 * 已经创建了新的内容，此时则不需要再次创建
				 */
				if(jdbcutils==null)
				{
					jdbcutils=new JDBCUtils();
				}
			}
		}
		//由双重锁验证获取当前的对象
		return jdbcutils;
	}
	//5.获取数据库连接
	public static Connection getConnection() 
	{
		
		Connection conn = null;
		// 创建连接：加载驱动、建立连接、返回创建的连接.....
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	//关闭连接
	public static void freeAll(Connection conn,Statement st,ResultSet rs)
	{
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(st!=null)
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}	
