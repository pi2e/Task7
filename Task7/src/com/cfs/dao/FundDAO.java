package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.cfs.databean.Employee;
import com.cfs.databean.Fund;

	public class FundDAO extends GenericDAO<Fund>{

		public FundDAO(ConnectionPool cp, String tableName) throws DAOException {
			
			super(Fund.class, tableName, cp);
		}

		
		public void create(Fund fund) throws RollbackException {

			try {
				Transaction.begin();
				createAutoIncrement(fund);
				Transaction.commit();
			} finally {
				if (Transaction.isActive())
					Transaction.rollback();
			}

		}
	}
