package com.cfs.dao;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.cfs.databean.Customer;
import com.cfs.databean.FundTransaction;


public class CustomerDAO extends GenericDAO<Customer> {

	public CustomerDAO(ConnectionPool cp, String tableName) throws DAOException {

		super(Customer.class, tableName, cp);
	}

	public void create(Customer cust) throws RollbackException {

		try {
			Transaction.begin();
			createAutoIncrement(cust);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

	}

	public Customer[] getAllCustomers() throws RollbackException {

		Customer[] customers = match();
		
		/*
		try {
			customers = match();

			if (customers != null || customers.length != 0) {

				Arrays.sort(customers);
			}

		} catch (RollbackException e) {
			throw new DAOException(e);
		}
		*/
		
		return customers;
	}

	public Customer checkUserExist(String username) throws DAOException {

		try {
			Transaction.begin();
			Customer cust = null;
			Customer[] custData = match(MatchArg.equals("username", username));
			if (custData == null || custData.length == 0) {
				cust = null;
			}
			else
				cust = custData[0];
			Transaction.commit();
			return cust;
		} 
		catch (RollbackException e) {
			throw new DAOException(e);
		}
		finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

	}
	
	public synchronized void update(Customer bean) {
		try {
			super.update(bean);
		} catch (RollbackException e) {
			e.printStackTrace();
		}
		
	}

	public Customer getCustomerDetails(String userID) {
		return null;

	}

}
