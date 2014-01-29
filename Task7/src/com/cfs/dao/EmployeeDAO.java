package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.cfs.databean.Customer;
import com.cfs.databean.Employee;

public class EmployeeDAO extends GenericDAO<Employee>{

	public EmployeeDAO(ConnectionPool cp, String tableName) throws DAOException {
		
		super(Employee.class, tableName, cp);
	}

	public void create(Employee employee) throws RollbackException {

		try {
			Transaction.begin();
			createAutoIncrement(employee);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

	}
	
	public boolean checkcreate(Employee employee) throws DAOException {

		try {

			Employee[] custData = match(MatchArg.equals("username",
					employee.getUsername()));
			if (custData != null && custData.length != 0) {
				return false;
			} else {
				create(employee);
				return true;
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		}

	}
	
	public Employee checkUserExist(String username) throws DAOException {

		try {
			Transaction.begin();
			Employee emp = null;
			Employee[] empData = match(MatchArg.equals("username", username));
			if (empData == null || empData.length == 0) {
				emp = null;
			}
			else
				emp = empData[0];
			Transaction.commit();
			return emp;
		} 
		catch (RollbackException e) {
			throw new DAOException(e);
		}
		finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

	}

}
