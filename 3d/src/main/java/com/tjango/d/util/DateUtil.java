package com.tjango.d.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 * @author tiandesgin
 */
public class DateUtil {

	protected static final String DATE = "yyyy-MM-dd";
	protected static final String TIME = "HH:mm:ss";
	protected static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	protected static final String PRECISE = "yyyy-MM-dd HH.mm.ss.SSS";

	/**
	 * 将指定date以"yyyy-MM-dd"格式返回
	 * 
	 * @param date
	 *            待转化日期
	 * @return String
	 */
	public static String date(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE);
		return sdf.format(date);
	}

	/**
	 * 将时间字符串转化为yyyy-MM-dd格式 fn.tld相关,勿修改
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static String date(String dateString) {
		return toPattern(dateString, DATE);
	}

	public static Date dateAdd(String datePart, int amount, Date date) {
		if (date == null) {
			throw new RuntimeException("date is null");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int field = 0;
		if (datePart.equals("year")) {
			field = Calendar.YEAR;
		}
		if (datePart.equals("month")) {
			field = Calendar.MONTH;
		}
		if (datePart.equals("day")) {
			field = Calendar.DATE;
		}
		if (datePart.equals("hour")) {
			field = Calendar.HOUR_OF_DAY;
		}
		if (datePart.equals("minute")) {
			field = Calendar.MINUTE;
		}
		if (datePart.equals("second")) {
			field = Calendar.SECOND;
		}
		if (field == 0) {
			throw new RuntimeException("datePart wrong");
		}
		calendar.add(field, amount);
		return calendar.getTime();
	}

	public static Date dateAdd(String datePart, int amount, String date) {
		Date temp = toDate(date);
		return dateAdd(datePart, amount, temp);
	}

	/**
	 * 按度量单位返回两个日期的时间差
	 * 
	 * @param datePart
	 *            度量单位 year,quarter,month,week,day,hour,minute,second
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return long 时间差
	 */
	public static long dateDiff(String datePart, Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new RuntimeException("date1 or date2 is null");
		}
		if (datePart.equals("year")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date2);
			int time = calendar.get(Calendar.YEAR);
			calendar.setTime(date1);
			return time - calendar.get(Calendar.YEAR);
		}

		if (datePart.equals("quarter")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date2);
			int time = calendar.get(Calendar.YEAR) * 4;
			calendar.setTime(date1);
			time -= calendar.get(Calendar.YEAR) * 4;
			calendar.setTime(date2);
			time += calendar.get(Calendar.MONTH) / 4;
			calendar.setTime(date1);
			return time - calendar.get(Calendar.MONTH) / 4;
		}

		if (datePart.equals("month")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date2);
			int time = calendar.get(Calendar.YEAR) * 12;
			calendar.setTime(date1);
			time -= calendar.get(Calendar.YEAR) * 12;
			calendar.setTime(date2);
			time += calendar.get(Calendar.MONTH);
			calendar.setTime(date1);
			return time - calendar.get(Calendar.MONTH);
		}

		if (datePart.equals("week")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date2);
			int time = calendar.get(Calendar.YEAR) * 52;
			calendar.setTime(date1);
			time -= calendar.get(Calendar.YEAR) * 52;
			calendar.setTime(date2);
			time += calendar.get(Calendar.WEEK_OF_YEAR);
			calendar.setTime(date1);
			return time - calendar.get(Calendar.WEEK_OF_YEAR);
		}

		if (datePart.equals("day")) {
			long time = date2.getTime() / 1000 / 60 / 60 / 24;
			return time - date1.getTime() / 1000 / 60 / 60 / 24;
		}

		if (datePart.equals("hour")) {
			long time = date2.getTime() / 1000 / 60 / 60;
			return time - date1.getTime() / 1000 / 60 / 60;
		}

		if (datePart.equals("minute")) {
			long time = date2.getTime() / 1000 / 60;
			return time - date1.getTime() / 1000 / 60;
		}

		if (datePart.equals("second")) {
			long time = date2.getTime() / 1000;
			return time - date1.getTime() / 1000;
		}
		return date2.getTime() - date1.getTime();
	}
	/**
	 * 生成新文件名
	 * 
	 * @param
	 * @return
	 */
	public static String getDate() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + new Random().nextInt(1000);
	}
	/**
	 * 获得年月日
	 * 
	 * @return
	 */
	public static String getCalendar() {
		return Integer.toString(DateUtil.getYear()) + DateUtil.getMonth() + DateUtil.getDay();
	}
	/**
	 * 按度量单位返回两个日期格式的时间差
	 * 
	 * @param timeInterval
	 *            度量单位 year,quarter,month,week,day,hour,minute,second
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return long 时间差
	 */
	public static long dateDiff(String timeInterval, String date1, String date2) {
		Date d1 = toDate(date1);
		Date d2 = toDate(date2);
		return dateDiff(timeInterval, d1, d2);
	}

	/**
	 * 将指定date以"yyyy-MM-dd HH:mm:ss"格式返回
	 * 
	 * @param date
	 *            待转化日期
	 * @return String
	 */
	public static String datetime(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME);
		return sdf.format(date);
	}

	/**
	 * 将时间字符串转化为yyyy-MM-dd HH:mm:ss格式 fn.tld相关,勿修改
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static String datetime(String dateString) {
		return toPattern(dateString, DATETIME);
	}

	public static int get(Date date, int type) {
		if (date == null) {
			throw new RuntimeException("null date");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(type);
	}

	public static int get(String date, int type) {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate(date));
		return c.get(type);
	}

	public static int getDay() {
		return get(getNowDate(), Calendar.DAY_OF_MONTH);
	}

	public static int getDay(Date date) {
		return get(date, Calendar.DAY_OF_MONTH);
	}

	public static int getMonth() {
		return get(getNowDate(), Calendar.MONTH);
	}

	public static int getMonth(Date date) {
		return get(date, Calendar.MONTH);
	}

	/**
	 * 返回当前时间
	 * 
	 * @return Date
	 */
	public static Date getNowDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 按"yyyy-MM-dd HH:mm:ss"格式返回当前时间
	 * 
	 * @return String
	 */
	public static String getNowString() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME);
		return sdf.format(getNowDate());
	}

	/**
	 * 按"yyyy-MM-dd"格式返回当前日期
	 * 
	 * @return String
	 */
	public static String getTodayString() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE);
		return sdf.format(getNowDate());
	}

	/**
	 * 实现给定某日期，判断是星期几 <br>
	 * 
	 * @return <br>
	 */
	public static String getWeekday(String date) {// 必须yyyy-MM-dd
		if ("".equals(date) || date.length() == 0) {
			return date;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdw = new SimpleDateFormat("E");
		Date d = null;
		try {
			d = sd.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sdw.format(d);
	}

	public static int getYear() {
		return get(getNowDate(), Calendar.YEAR);
	}

	public static int getYear(Date date) {
		return get(date, Calendar.YEAR);
	}

	/**
	 * 将指定date以"yyyy-MM-dd HH.MM.ss.SSSSSS"格式返回
	 * 
	 * @param date
	 *            待转化日期
	 * @return String
	 */
	public static String precisetime(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(PRECISE);
		return sdf.format(date);
	}

	/**
	 * 将指定date以"HH:mm:ss"格式返回
	 * 
	 * @param date
	 *            待转化日期
	 * @return String
	 */
	public static String time(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(TIME);
		return sdf.format(date);
	}

	/**
	 * 将时间字符串转化为HH:mm:ss格式 fn.tld相关,勿修改
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static String time(String dateString) {
		return toPattern(dateString, TIME);
	}

	/**
	 * 将时间字符串转化为Date型
	 * 
	 * @param dateTimeStr
	 *            待转化日期的字符串
	 * @return Date
	 */
	public static Date toDate(String dateTimeStr) {
		if (dateTimeStr == null || dateTimeStr.equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME);
		try {
			return sdf.parse(dateTimeStr);
		} catch (ParseException e1) {
			try {
				sdf = new SimpleDateFormat(DATE);
				return sdf.parse(dateTimeStr);
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将指定date按指定pattern格式返回
	 * 
	 * @param date
	 *            待转化日期
	 * @param pattern
	 *            日期格式
	 * @return String
	 */
	public static String toPattern(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 将日期字符串转换为toPattern
	 * 
	 * @param dateString
	 *            日期字符串
	 * @param toPattern
	 *            转化后格式
	 * @return String
	 */
	public static String toPattern(String dateString, String pattern) {
		if (dateString == null || dateString.equals("")) {
			return null;
		}
		if (pattern == null || pattern.equals("")) {
			throw new RuntimeException("toPattern is null");
		}
		Date date = toDate(dateString);
		return toPattern(date, pattern);
	}

	private DateUtil() {
		super();
	}

}
