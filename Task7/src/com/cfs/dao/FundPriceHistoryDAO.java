package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import com.cfs.databean.FundPriceData;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceData>{

	public FundPriceHistoryDAO(ConnectionPool cp, String tableName) throws DAOException {
		
		super(FundPriceData.class, tableName, cp);
		
	}

}

