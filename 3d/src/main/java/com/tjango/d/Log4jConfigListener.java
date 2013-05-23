package com.tjango.d;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *@author: tiandesgin@live.cn
 *@time: 2011-10-27
 *@description:动态的配置log4j的相对路径
 */
public class Log4jConfigListener implements ServletContextListener {

	private static Log log =LogFactory.getLog(Log4jConfigListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent scEvent) {
		String root = scEvent.getServletContext().getRealPath("/");
		String log4j = "WEB-INF/classes/log4j.properties";
		Properties props = new Properties();
		try {
			log.info("日志路径为:"+root + log4j);
			FileInputStream fStream = new FileInputStream(root + log4j);
			props.load(fStream);
			fStream.close();
			String path = root + props.getProperty("log4j.appender.STEEL.file");
			props.setProperty("log4j.appender.STEEL.file", path);
			//PropertyConfigurator.configure(props);
			if (log.isInfoEnabled())
				log.info("log4j root path config success!! by tiandesgin");
		} catch (FileNotFoundException e) {
			log.error("log4j root path config FileNotFoundException:", e);
		} catch (IOException e) {
			log.error("log4j root path config IOException:", e);
		}
	}

}
