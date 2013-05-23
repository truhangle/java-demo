package com.tjango.d;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoTemplate {
	
	private static DaoTemplate daoTemplate;
	
	private static DBconfig dBconfig;
	
	private Connection connection;
	
	private DaoTemplate(){
	}
	
	public static DaoTemplate getInstance(){
		if(daoTemplate==null){
			dBconfig  = DBconfig.getInstance();
			try {
				//加载驱动
				Class.forName(dBconfig.getDriverClassName()).newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			daoTemplate = new DaoTemplate();
		}
		return daoTemplate;
	}
	
	
	public Connection getConnection(){
		try {
			connection = DriverManager.getConnection(dBconfig.getUrl(), dBconfig.getUsername(), dBconfig.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection(){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				connection = null;
			}
			connection = null;
		}
	}
	
	public static void main(String[] args) {
		//测试
		DaoTemplate dao = DaoTemplate.getInstance();
		Connection connection = dao.getConnection();
	}

}
