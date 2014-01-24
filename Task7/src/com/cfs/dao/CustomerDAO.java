package com.cfs.dao;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.cfs.databean.Customer;



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

	public Customer[] getAllCustomers() throws DAOException {

		Customer[] customers;
		
		try {
			customers = match();

			if (customers.length != 0) {
				Arrays.sort(customers);
			}

		} catch (RollbackException e) {
			throw new DAOException(e);
		}
		
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

	public void updateCash(long userId, long amount) throws DAOException, RollbackException{

		try {
			Transaction.begin();
			Customer[] customers = match(MatchArg.equals("customerId", userId));
			Customer cust = customers[0];
			cust.setCash(cust.getCash() + amount);
			this.update(cust);
			Transaction.commit();
			
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if(Transaction.isActive()) {
				Transaction.rollback();
			}
		}
		
	}
	
//	public void setPassword(String customerId, String password) throws RollbackException {
//        try {
//        	Transaction.begin();
//			Customer cust = null;
//			cust = read(customerId);
//			
//			if (cust == null) {
//				throw new RollbackException("User no longer exists");
//			}
//			
//			cust.setPassword(password);
//			
//			update(cust);
//			Transaction.commit();
//		} finally {
//			if (Transaction.isActive()) Transaction.rollback();
//		}
//	}

}
