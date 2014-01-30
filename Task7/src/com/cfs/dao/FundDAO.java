package com.cfs.dao;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.cfs.databean.Fund;

public class FundDAO extends GenericDAO<Fund> {

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

	public Fund getFund(int fundId) throws DAOException {
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

	public boolean checkcreate(Fund fund) throws DAOException {
		Fund[] funds;

		try {
			funds = match(MatchArg.equalsIgnoreCase("symbol", fund.getSymbol()));
			if (fund != null && funds.length != 0) {
				return false;
			}
			create(fund);
			return true;

		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException(e);
		}

	}

	public Fund getFund(String ticker) throws DAOException {
		Fund[] funds;

		try {
			funds = match(MatchArg.equalsIgnoreCase("symbol", ticker));

		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException(e);
		}

		if (funds.length == 0 || funds == null) {
			return null;
		}
		return funds[0];
	}
}
