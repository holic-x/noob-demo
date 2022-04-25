package com.noob.jdbc.pool;

import com.noob.utils.JDBCUtil;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/5/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class CustomDataSourceDemo1{

    public static void main(String[] args) throws Exception{
        // 创建数据库连接池对象
        CommonDataSource dataSource = new CommonDataSource();

        System.out.println("使用之前连接池数量：" + dataSource.getSize());

        // 获取数据库连接对象
        Connection con = dataSource.getConnection();
        System.out.println(con.getClass());// JDBC4Connection

        // 查询学生表全部信息
        String sql = "SELECT * FROM student";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getInt("sid") + "\t"
                    + rs.getString("sname") + "\t"
                    + rs.getInt("sage") + "\t"
                    + rs.getDate("sdate"));
        }
        // 释放资源
        rs.close();
        pst.close();
        // 目前的连接对象close方法，是直接关闭连接，而不是将连接归还池中
        con.close();
        System.out.println("使用之后连接池数量：" + dataSource.getSize());
    }
}

class CommonDataSource implements DataSource {
    // 定义集合容器，用于保存多个数据库连接对象
    private static List<Connection> pool = Collections.synchronizedList(new ArrayList<Connection>());

    // 静态代码块，生成10个数据库连接保存到集合中
    static {
        for (int i = 0; i < 10; i++) {
            Connection con = JDBCUtil.getConnection();
            pool.add(con);
        }
    }

    // 返回连接池的大小
    public int getSize() {
        return pool.size();
    }

    // 从连接池中返回一个数据库连接
    @Override
    public Connection getConnection() {
        if(pool.size() > 0) {
            // 从连接池中获取数据库连接
            return pool.remove(0);
        }else {
            throw new RuntimeException("数据库连接数量已用尽");
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}