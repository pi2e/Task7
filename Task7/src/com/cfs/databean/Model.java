package com.cfs.databean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.EmployeeDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.dao.PositionDAO;
import com.cfs.dao.TransactionDAO;

public class Model {

	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceDAO;

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public FundDAO getFundDAO() {
		return fundDAO;
	}

	public void setFundDAO(FundDAO fundDAO) {
		this.fundDAO = fundDAO;
	}

	public FundPriceHistoryDAO getFundPriceDAO() {
		return fundPriceDAO;
	}

	public void setFundPriceDAO(FundPriceHistoryDAO fundPriceDAO) {
		this.fundPriceDAO = fundPriceDAO;
	}

	public PositionDAO getPositionDAO() {
		return positionDAO;
	}

	public void setPositionDAO(PositionDAO positionDAO) {
		this.positionDAO = positionDAO;
	}

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}

	public void setTransactionDAO(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}

	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;

	public Model(ServletConfig servletConfig) throws ServletException {

		try {
			String jdbcDriver = servletConfig.getInitParameter("jdbcDriver");
			String jdbcURL = servletConfig.getInitParameter("jdbcURL");
			ConnectionPool cp = new ConnectionPool(jdbcDriver, jdbcURL);

			customerDAO = new CustomerDAO(cp, "CUSTOMER");
			employeeDAO = new EmployeeDAO(cp, "EMPLOYEE");
			fundDAO = new FundDAO(cp, "FUND");
			fundPriceDAO = new FundPriceHistoryDAO(cp, "FUND_PRICE_HISTORY");
			positionDAO = new PositionDAO(cp, "POSITION");
			transactionDAO = new TransactionDAO(cp, "TRANSACTION");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
