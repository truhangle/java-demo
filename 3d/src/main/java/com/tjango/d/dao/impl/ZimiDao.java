package com.tjango.d.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tjango.d.DaoTemplate;
import com.tjango.d.Zimi;
import com.tjango.d.dao.IZimiDao;
import com.tjango.d.util.StringUtils;

/**
 * 字谜数据接口
 * @author Administrator
 *
 */
public class ZimiDao implements IZimiDao {

	private DaoTemplate templdate;
	
	public ZimiDao(){
		templdate = templdate.getInstance();
	}
	
	public void save(Zimi zm) throws SQLException {
		String sql = "INSERT INTO [demo].[dbo].[Zimi]([Z1],[Z2],[Z3],[Z4],[Z5],[Z6],[Z7],[Z8],[Z9],[Z10],[Z11],[Z12],[B1],[B2],[B3],[B4],[B5],[B6],[B7],[B8],[B9],[B10],[B11],[B12],[Npis],[BTotal],[ShiJiHao],[KaiJiangHao],[XiaZhuHao],[InputDate],[SerialNumber],[Zimi])VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt =  templdate.getConnection().prepareStatement(sql);
		pstmt.setString(1,zm.getZ1());
		pstmt.setString(2,zm.getZ2());
		pstmt.setString(3,zm.getZ3());
		pstmt.setString(4,zm.getZ4());
		pstmt.setString(5,zm.getZ5());
		pstmt.setString(6,zm.getZ6());
		pstmt.setString(7,zm.getZ7());
		pstmt.setString(8,zm.getZ8());
		pstmt.setString(9,zm.getZ9());
		pstmt.setString(10,zm.getZ10());
		pstmt.setString(11,zm.getZ11());
		pstmt.setString(12,zm.getZ12());
		pstmt.setInt(13,zm.getB1());
		pstmt.setInt(14,zm.getB2());
		pstmt.setInt(15,zm.getB3());
		pstmt.setInt(16,zm.getB4());
		pstmt.setInt(17,zm.getB5());
		pstmt.setInt(18,zm.getB6());
		pstmt.setInt(19,zm.getB7());
		pstmt.setInt(20,zm.getB8());
		pstmt.setInt(21,zm.getB9());
		pstmt.setInt(22,zm.getB10());
		pstmt.setInt(23,zm.getB11());
		pstmt.setInt(24,zm.getB12());
		pstmt.setString(25,zm.getNpis());
		pstmt.setInt(26,zm.getbTotal());
		pstmt.setString(27,zm.getShiJiHao());
		pstmt.setString(28,zm.getKaiJiangHao());
		pstmt.setString(29,zm.getXiaZhuHao());
		pstmt.setString(30,zm.getInputDate());
		pstmt.setString(31, zm.getSerialNumber());
		pstmt.setString(32, zm.getZimi());
		pstmt.execute();
		pstmt.close();
		templdate.closeConnection();
	}

	public List<Zimi> search(String startTime, String endTime, String keyWord,String orderBy) throws SQLException {
		StringBuffer sql = new StringBuffer("select * from Zimi where 1=1");
        List<String> param = new ArrayList<String>();
		if(StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)){
			sql.append(" and InputDate >=? and InputDate <= ?");
			param.add(startTime);
			param.add(endTime);
		}
		if(StringUtils.isNotEmpty(keyWord)){
			sql.append(" and (Npis like %?% or ShiJiHao like %?% or KaiJiangHao like  %?% or XiaZhuHao like %?%)");
			param.add(keyWord);
			param.add(keyWord);
			param.add(keyWord);
			param.add(keyWord);
		}
		if(StringUtils.isNotEmpty(orderBy)){
			sql.append(" order by "+orderBy);
		}
		PreparedStatement pstmt =  templdate.getConnection().prepareStatement(sql.toString());
		int size = param.size();
		if(size==2 || size ==6){
			pstmt.setString(1, startTime);
			pstmt.setString(2, endTime);
		}
		if(size==4){
			pstmt.setString(1, keyWord);
			pstmt.setString(2, keyWord);
			pstmt.setString(3, keyWord);
			pstmt.setString(4, keyWord);
		}
		if(size==6){
			pstmt.setString(3, keyWord);
			pstmt.setString(4, keyWord);
			pstmt.setString(5, keyWord);
			pstmt.setString(6, keyWord);
		}
		ResultSet rs = pstmt.executeQuery();
		List<Zimi> list = new ArrayList<Zimi>();
		while(rs.next()){
			Zimi zm = new Zimi();
			//字
			zm.setZ1(rs.getString("Z1"));
			zm.setZ2(rs.getString("Z2"));
			zm.setZ3(rs.getString("Z3"));
			zm.setZ4(rs.getString("Z4"));
			zm.setZ5(rs.getString("Z5"));
			zm.setZ6(rs.getString("Z6"));
			zm.setZ7(rs.getString("Z7"));
			zm.setZ8(rs.getString("Z8"));
			zm.setZ9(rs.getString("Z9"));
			zm.setZ10(rs.getString("Z10"));
			zm.setZ11(rs.getString("Z11"));
			zm.setZ12(rs.getString("Z12"));
			//笔画
			zm.setB1(rs.getInt("B1"));
			zm.setB2(rs.getInt("B2"));
			zm.setB3(rs.getInt("B3"));
			zm.setB4(rs.getInt("B4"));
			zm.setB5(rs.getInt("B5"));
			zm.setB6(rs.getInt("B6"));
			zm.setB7(rs.getInt("B7"));
			zm.setB8(rs.getInt("B8"));
			zm.setB9(rs.getInt("B9"));
			zm.setB10(rs.getInt("B10"));
			zm.setB11(rs.getInt("B11"));
			zm.setB12(rs.getInt("B12"));
			//其他
			zm.setNpis(rs.getString("Npis"));
			zm.setbTotal(rs.getInt("BTotal"));
			zm.setShiJiHao(rs.getString("ShiJiHao"));
			zm.setKaiJiangHao(rs.getString("KaiJiangHao"));
			zm.setXiaZhuHao(rs.getString("XiaZhuHao"));
			zm.setInputDate(rs.getString("InputDate"));
			zm.setSerialNumber(rs.getString("SerialNumber"));
			list.add(zm);
		}
		rs.close();
		pstmt.close();
		templdate.closeConnection();
		return list;
	}

	public void delete(int zimiId) throws SQLException {
		String sql = "delete from Zimi where ID = ?";
		PreparedStatement pstmt =  templdate.getConnection().prepareStatement(sql);
		pstmt.setInt(1, zimiId);
		pstmt.execute();
		pstmt.close();
		templdate.closeConnection();
	}
}
