package com.cfs.dao;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.cfs.databean.FundPriceData;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceData>{

	public FundPriceHistoryDAO(ConnectionPool cp, String tableName) throws DAOException {
		
		super(FundPriceData.class, tableName, cp);
		
	}

	public FundPriceData fetchLatestPrice(long fundId) throws DAOException{
		
		try {
			Transaction.begin();
			FundPriceData fundPriceData = null;
			FundPriceData[] fundPrice = match(MatchArg.equals("fundId", fundId));
			
			if (fundPrice.length == 0) {
				fundPriceData = null;
			}
			else {
				Arrays.sort(fundPrice);
				fundPriceData = fundPrice[0];
			}
			
			Transaction.commit();
			return fundPriceData;
		} 
		catch (RollbackException e) {
			throw new DAOException(e);
		}
		finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		
	}
}

