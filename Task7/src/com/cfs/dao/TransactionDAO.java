package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import com.cfs.databean.FundTransaction;


	public class TransactionDAO extends GenericDAO<FundTransaction>{

		public TransactionDAO(ConnectionPool cp, String tableName) throws DAOException {
			
			super(FundTransaction.class, tableName, cp);
		}

}
