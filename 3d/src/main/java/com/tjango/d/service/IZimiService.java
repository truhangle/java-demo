package com.tjango.d.service;

import java.sql.SQLException;
import java.util.List;

import com.tjango.d.Zimi;

public interface IZimiService {
	/**
	 * 添加字谜
	 * @param zm
	 * @throws SQLException 
	 */
	void save(Zimi zm) throws SQLException;
	
	/**
	 * 字谜查询
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param keyWord 关键字
	 * @return
	 * @throws SQLException 
	 */
	List<Zimi> search(String startTime,String endTime,String keyWord,String orderBy) throws SQLException;
	
	/**
	 * 删除字谜
	 * @param zimiId
	 * @throws SQLException 
	 */
	void delete(int zimiId) throws SQLException;
}
