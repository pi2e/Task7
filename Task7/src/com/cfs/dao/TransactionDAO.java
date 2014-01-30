package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.cfs.databean.FundTransaction;

public class TransactionDAO extends GenericDAO<FundTransaction> {

	public TransactionDAO(ConnectionPool cp, String tableName)
			throws DAOException {

		super(FundTransaction.class, tableName, cp);
	}

	public void create(FundTransaction transaction) throws RollbackException {

		createAutoIncrement(transaction);
	}

	public synchronized FundTransaction[] getExecutedTransactions()
			throws DAOException {
		try {
			FundTransaction[] transactionList = match(MatchArg.notEquals(
					"executeDate", null));

			return transactionList;
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	public synchronized FundTransaction[] getTransactions(int customerID)
			throws DAOException {
		try {
			FundTransaction[] transactionList = match(MatchArg.equals(
					"customerId", customerID));

			return transactionList;
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	public FundTransaction[] getExecutedTransactions(int customerID)
			throws DAOException {

		try {
			FundTransaction[] transactionList = match(MatchArg.and(
					MatchArg.equals("customerId", customerID),
					MatchArg.notEquals("executeDate", null)));

			return transactionList;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}

	public FundTransaction[] getPendingTransaction(int customerID)
			throws DAOException {

		try {
			FundTransaction[] transactionList = match(MatchArg.and(
					MatchArg.equals("customerId", customerID),
					MatchArg.equals("executeDate", null)));

			return transactionList;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}

	public synchronized FundTransaction[] getPendingTransactions()
			throws DAOException {
		try {
			FundTransaction[] transactionList = match(MatchArg.equals(
					"executeDate", null));

			return transactionList;
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

}
