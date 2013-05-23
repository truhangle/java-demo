package com.tjango.d.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tjango.d.Zimi;
import com.tjango.d.dao.IZimiDao;
import com.tjango.d.dao.impl.ZimiDao;
import com.tjango.d.service.IZimiService;

public class ZimiService implements IZimiService {

   private IZimiDao zimiDao;
   
    public ZimiService(){
    	zimiDao = new ZimiDao();
    }
	
	public void save(Zimi zm) throws SQLException {
		zimiDao.save(zm);
	}

	public List<Zimi> search(String startTime, String endTime, String keyWord,String orderBy) throws SQLException {
		return zimiDao.search(startTime, endTime, keyWord,orderBy);
	}

	public void delete(int zimiId) throws SQLException {
		 zimiDao.delete(zimiId);
	}

}
