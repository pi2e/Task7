package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.cfs.databean.Position;

public class PositionDAO extends GenericDAO<Position> {

	public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {

		super(Position.class, tableName, cp);
	}

	public synchronized boolean update(int customerId, int fundId,
			long availableShares, long shares) throws DAOException {
		try {
			Position[] positions = match(MatchArg.and(
					MatchArg.equals("customerId", customerId),
					MatchArg.equals("fundId", fundId)));

			if (positions == null || positions.length == 0) {
				return false;
			}

			Position position = positions[0];
			position.setAvailableShares(position.getAvailableShares()
					+ availableShares);
			position.setShares(position.getShares() + shares);
			if (position.getAvailableShares() < 0 || position.getShares() < 0)
				return false;
			else {
				update(position);
				return true;
			}

		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}

	public synchronized Position getPosition(int userId, int fundId)
			throws DAOException {

		try {
			Position[] positions = match(MatchArg.and(
					MatchArg.equals("customerId", userId),
					MatchArg.equals("fundId", fundId)));

			if (positions.length != 0) {
				return positions[0];
			}

			return null;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}

	public synchronized Position[] getCustomerFunds(int userId)
			throws DAOException {

		try {
			Position[] positions = match(MatchArg.equals("customerId", userId));
			return positions;
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
}
