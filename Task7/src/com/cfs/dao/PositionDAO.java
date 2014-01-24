package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.cfs.databean.Position;


	public class PositionDAO extends GenericDAO<Position>{

		public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {
			
			super(Position.class, tableName, cp);
		}
		
		
		public synchronized Position getPosition(long userId, long fundId) throws DAOException {
			
			try {
				Position[] positions = match(MatchArg.and(MatchArg.equals("customerId", userId),MatchArg.equals("fundId", fundId)));
				
				if(positions != null) {
					return positions[0];
				}
				
				return null;
			} catch (RollbackException e) {
				throw new DAOException(e);
			}
		}
		
		
		public synchronized Position[] getCustomerFunds(long userId) throws DAOException {
			
			try {
				Position[] positions = match(MatchArg.equals("customerId", userId));
				return positions;
			} catch (RollbackException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
	}

