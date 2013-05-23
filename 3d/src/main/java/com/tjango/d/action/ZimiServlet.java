package com.tjango.d.action;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tjango.d.Zimi;
import com.tjango.d.service.IZimiService;
import com.tjango.d.service.impl.ZimiService;
import com.tjango.d.util.StringUtils;
import com.tjango.d.util.ZimiUtil;

@SuppressWarnings("unused")
public class ZimiServlet extends HttpServlet{

	/**
	 * 日志
	 */
	private Log logger = LogFactory.getLog(ZimiServlet.class);
	
	private static final long serialVersionUID = 1L;
	private IZimiService zmService;
	
	@Override
	public void init() throws ServletException {
		zmService = new ZimiService();
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		try {//通过反射自动调用方法
			Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
			String methodName = method.getName();
			//排除doGet doPost
			if(!methodName.equals("doGet")&&!methodName.equals("doPost")){
				method.setAccessible(true);
				method.invoke(this, req,resp);
			}
		} catch (Exception e) {
			logger.error("",e);
			resp.sendError(404, "the page is not found");
		}
	}
	
	/**
	 * 搜索
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void metadata(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			String startTime = req.getParameter("startTime");
			String endTime = req.getParameter("endTime");
			String keyWord = req.getParameter("keyWord");
			List<Zimi> list = zmService.search(startTime,endTime,keyWord,null);
			req.setAttribute("list", list);
		} catch (Exception e) {
			logger.error("字谜搜索异常", e);
		}
		req.getRequestDispatcher("/WEB-INF/zimi/list-metadata.jsp").forward(req, resp);
	}
	
	/**
	 * report查询
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void report(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			String keyWord = req.getParameter("keyWord");
			String orderBy = req.getParameter("orderBy");
			List<Zimi> list = zmService.search(null,null,keyWord,orderBy);
			req.setAttribute("list", list);
			req.setAttribute("order", StringUtils.isNotEmpty(orderBy));
		} catch (Exception e) {
			logger.error("字谜搜索异常", e);
		}
		req.getRequestDispatcher("/WEB-INF/zimi/list-report.jsp").forward(req, resp);
	}
	
	/**
	 * 跳转增加
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/WEB-INF/zimi/add.jsp").forward(req, resp);
	}
	
	
	/**
	 * 首页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/WEB-INF/zimi/index.jsp").forward(req, resp);
	}
	
	/**
	 * 增加
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Zimi zimi = new Zimi();
		//req.setCharacterEncoding("GBK");
		try {
			String date = req.getParameter("riqi");
			String dijiqi = req.getParameter("dijiqi");
			String shijihao = req.getParameter("shijihao");
			String kaijianghao = req.getParameter("kaijianghao");
			String xiaZhuHao = req.getParameter("xiaZhuHao");
			String content = req.getParameter("content");
			zimi.setInputDate(date);
			zimi.setKaiJiangHao(kaijianghao);
			zimi.setShiJiHao(shijihao);
			zimi.setSerialNumber(dijiqi);
			zimi.setXiaZhuHao(xiaZhuHao);
			content = new String(content.getBytes("ISO-8859-1"),"UTF-8");
			zimi.setZimi(content);
			//去除非中文字符
			content = content.replaceAll("[^\u4E00-\u9FFF]", "");
			ZimiUtil.analyze(zimi, content);
			zmService.save(zimi);
		} catch (Exception e) {
			logger.error("增加字谜异常",e);
		}
		resp.sendRedirect("zimi?action=index");
	}
	
	/**
	 * 删除
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int userId = Integer.valueOf(req.getParameter("id"));
		try {
			zmService.delete(userId);
		} catch (Exception e) {
			logger.error("字谜删除异常",e);
		}
		resp.sendRedirect("zimi?action=metadata");
	}
}
