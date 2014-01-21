package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import com.cfs.dao.CustomerDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.Model;

public class ViewCustomerListAction extends Action {

	private CustomerDAO customerDAO;

	public ViewCustomerListAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "viewCustomerList.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		try {			
			Customer[] customers = customerDAO.getAllCustomers();
			request.setAttribute("customers", customers);
			return "customerlist.jsp";
			
		} catch (RollbackException e) {
			//create error list
			return "customerlist.jsp";
		}
		

	}

}




