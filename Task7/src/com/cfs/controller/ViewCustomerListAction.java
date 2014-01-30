package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;

import com.cfs.dao.CustomerDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.Model;
import com.cfs.utility.CommonUtilities;

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
			Customer[] customers;
			customers = customerDAO.getAllCustomers();

			List<String> cash = new ArrayList<String>();
			List<String> balance = new ArrayList<String>();

			for (int i = 0; i < customers.length; i++) {

				cash.add(CommonUtilities.convertToMoney(customers[i].getCash()));
				balance.add(CommonUtilities.convertToMoney(customers[i]
						.getBalance()));

			}

			request.setAttribute("cash", cash);
			request.setAttribute("balance", balance);
			request.setAttribute("customers", customers);
			return "customerlist.jsp";

		} catch (DAOException e) {
			return "customerlist.jsp";
		}

	}

}
