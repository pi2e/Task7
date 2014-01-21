package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.dao.PositionDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.FundTransaction;
import com.cfs.databean.Model;
import com.cfs.formbean.CustomerForm;

public class ViewCustomerAction extends Action {
	
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistorDAO;

	public ViewCustomerAction(Model model) {

		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		positionDAO = model.getPositionDAO();
		fundPriceHistorDAO = model.getFundPriceDAO();
	}

	@Override
	public String getName() {

		return "viewcustomer.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		
		 Customer customer = (Customer) request.getSession().getAttribute("user");
		 
		 CustomerForm custForm = new  CustomerForm();
		 
		 custForm.setAddress1(customer.getAddressLine1());
		 custForm.setAddress2(customer.getAddressLine2());
		 custForm.setCity(customer.getCity());
		 custForm.setFirstName(customer.getFirstName());
		 custForm.setLastName(customer.getLastName());
		 custForm.setState(customer.getState());
		 custForm.setZipcode(String.valueOf(customer.getZipCode()));
		 custForm.setUsername(customer.getUsername());
		 
		 request.setAttribute("customerForm", custForm);
		 
		 //FundTransaction[] fundTransaction = transactionDAO.getTransactions(customer.getCustomerId());
		 
		 
		 
		return null;
	}

}
