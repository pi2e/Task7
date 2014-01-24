package com.cfs.dao;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

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
		
		public Fund[] getFunds() throws RollbackException {
			Fund[] funds = match();
			
			if (funds != null) {

				Arrays.sort(funds);
			}

			return funds;
		}
		
		public Fund getFund(long fundId) throws DAOException {
			Fund[] funds = null;
			
			try {
				funds = match(MatchArg.equals("fundId", fundId));

			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new DAOException(e);
				
			}
			
			return funds[0];
		}
		
		public Fund getFund(String ticker) throws DAOException {
			Fund[] funds;
			
			try {
				funds = match(MatchArg.equals("symbol", ticker));

			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new DAOException(e);
			}
			
			if(funds.length == 0 || funds == null) {
				return null;
			}
			return funds[0];
		}
	}
