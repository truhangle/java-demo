package com.tjango.d;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.net.URISyntaxException;
 import java.util.Properties;
 
 /**
  * 数据库访问配置文件各参数的获取
  */
 public class DBconfig {
     //数据库及server配置文件路径
     private static final String ACTIONPATH = "db.properties";  
     private static DBconfig instance;
     
     //驱动名称
     private String driverClassName;
     //连接url
     private String url;
     //数据库用户名
     private String username;
     //数据库密码
     private String password;
     
     private DBconfig(){}
     
     public static DBconfig getInstance(){
         if(instance==null){
             instance= new DBconfig().getNewDbConfig();
         }
         return instance;
     }
     
     private DBconfig getNewDbConfig(){
    	 DBconfig dc=new DBconfig();
         Properties prop = new Properties();  
         String path=null;
         FileInputStream fis=null;
         try {
             path = DBconfig.class.getClassLoader().getResource("").toURI().getPath();
             fis = new FileInputStream(new File(path + ACTIONPATH));
             prop.load(fis);
             dc.setDriverClassName(prop.getProperty("driverClassName")); 
             dc.setUrl(prop.getProperty("url")); 
             dc.setUsername(prop.getProperty("username")); 
             dc.setPassword(prop.getProperty("password")); 
         } catch (URISyntaxException e) {
             e.printStackTrace();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }  
         return dc;
     }

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
 }