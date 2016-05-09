package com.mangocity.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlLite {
	public static void main(String[] args) {
		// 加载驱动
		ResultSet rs = null;
		// 得到连接 会在你所填写的目录建一个你命名的文件数据库
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动未找到!");
		}
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:./apitree.db",
					null, null);
			// 设置自动提交为false
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			// 判断表是否存在
			ResultSet rsTables = conn.getMetaData().getTables(null, null,
					"app", null);
			if (rsTables.next()) {
				System.out.println("表存在");
			} else {
				stmt.executeUpdate("create table app (id text,secret text);");
				stmt.executeUpdate("create table method (method text);");
				stmt.executeUpdate("create table  relevance(app text,method text);");
			}
			/*stmt.executeUpdate("insert into student values (1,'hehe');");
			stmt.executeUpdate("insert into student values (2,'xixi');");
			stmt.executeUpdate("insert into student values (3,'haha');");*/
			// 提交
			conn.commit();
			// 得到结果集
			rs = stmt.executeQuery("select * from app;");
			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("rsmd =  " + rsmd.getTableName(1));
			int columnCount = rsmd.getColumnCount();
			System.out.println("columnCount =  " + columnCount);
			String cName = rsmd.getColumnLabel(1);
			String cType = rsmd.getColumnClassName(2);
			System.out.println("cName =  " + cName);
			System.out.println("cType =  " + cType);
			while (rs.next()) {
				System.out.println("id = " + rs.getString("id"));
				System.out.println("name = " + rs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL异常!");
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
