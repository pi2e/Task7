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

	public boolean checkcreate(Customer customer) throws DAOException {

		try {

			Customer[] custData = match(MatchArg.equals("username",
					customer.getUsername()));
			if (custData != null && custData.length != 0) {
				return false;
			} else {
				create(customer);
				return true;
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		}

	}

	public Customer getCustomer(String username) throws DAOException {

		Customer cust = null;

		try {

			Customer[] custData = match(MatchArg.equals("username", username));
			if (custData == null || custData.length == 0) {
				cust = null;
			} else {
				cust = custData[0];
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		}

		return cust;
	}

	public synchronized boolean update(int customerId, long balance, long cash)
			throws DAOException {
		try {
			Customer[] customers = match(MatchArg.equals("customerId",
					customerId));
			Customer customer;

			if (customers == null || customers.length == 0)
				return false;
			else
				customer = customers[0];

			customer.setBalance(customer.getBalance() + balance);
			customer.setCash(customer.getCash() + cash);

			if (customer.getBalance() < 0 || customer.getCash() < 0)
				return false;
			else {
				update(customer);
				return true;
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		}

	}

	public synchronized void update(Customer bean) {
		try {
			super.update(bean);
		} catch (RollbackException e) {
			e.printStackTrace();
		}

	}

}
