package com.tjango.d.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tjango.d.HttpTookit;
import com.tjango.d.Zimi;

public final class ZimiUtil {
	/**
	 * 解析地址
	 */
	private static final String URL = "http://www.cha.la/tools/turn_bihua.asp";
	/**
	 * input name
	 */
	private static final String NAME = "hanzi";
	/**
	 * 编码
	 */
	private static final String CHARSET = "GBK";
	
	public static void analyze(Zimi zimi,String content) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		String html = HttpTookit.doGet(URL, NAME+"="+content, CHARSET, false);
		Pattern pattern =Pattern.compile("<span.*</span>");
		Matcher match = pattern.matcher(html);
		if(match.find()){
			String str = match.group();
			String spanRegex = "(<span.*?>|</span>)";
			String[] result = str.replaceAll(spanRegex,"").split("<br\\s*/>");
			System.out.println(Arrays.toString(result));
			StringBuffer bhStr = new StringBuffer();
			for (int i = 1; i < result.length-1; i++) {
				String re = result[i];
				String z = re.substring(0, 1);
				int bh = Integer.valueOf(re.replaceAll("[^\\d]", ""));
				bhStr.append(bh);
				//字赋值
				Method method = Zimi.class.getDeclaredMethod("setZ"+i, String.class);
				method.invoke(zimi, z);
				//笔画赋值
				Method bmethod = Zimi.class.getDeclaredMethod("setB"+i, int.class);
				bmethod.invoke(zimi, bh);
			}
			//获取总笔画
			String res = result[result.length-1];
			int bhTotal = Integer.valueOf(res.replaceAll("全部 12 个汉字", "").replaceAll("[^\\d]", ""));
			zimi.setbTotal(bhTotal);
			zimi.setNpis(analyzeNpis(bhStr.toString()));
		}
	}
	
	/**
	 * 解析笔画出现的字数
	 * @param str
	 * @return
	 */
	private static String analyzeNpis(String str){
		char[] ch = str.toCharArray();
		StringBuffer buffer = new StringBuffer();
		Set<Character> cha = new HashSet<Character >();
		for (char c : ch) 
			cha.add(new Character(c));
		List<Character> list = new ArrayList<Character>();
		for (Character c : cha)
			list.add(c);
		Collections.sort(list);
		for (Character c : list)
			buffer.append(c);
		return buffer.toString();
	}
}
