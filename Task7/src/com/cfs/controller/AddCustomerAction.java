package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.CustomerDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.Model;
import com.cfs.formbean.CustomerForm;

public class AddCustomerAction extends Action {

	private CustomerDAO customerDAO;
	private FormBeanFactory<CustomerForm> formBeanFactory = FormBeanFactory
			.getInstance(CustomerForm.class);

	public AddCustomerAction(Model model) {

		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "addCustomer.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			CustomerForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return "createcustomer.jsp";
			}

			System.out.println("check errors");
			errors.addAll(form.getValidationErrors());

			if (errors.size() != 0) {
				return "createcustomer.jsp";
			}

			Customer cust = new Customer();
			cust.setFirstName(form.getFirstName());
			cust.setLastName(form.getLastName());
			cust.setUsername(form.getUsername());
			cust.setAddressLine1(form.getAddress1());
			cust.setAddressLine2(form.getAddress2());
			cust.setPassword(form.getPassword1());
			cust.setPassword(form.getPassword2());
			cust.setState(form.getState());
			cust.setCity(form.getCity());
			cust.setZipCode(Integer.parseInt(form.getZipcode()));

			if (!customerDAO.checkcreate(cust)) {
				errors.add("Username has already been registered");
				return "createcustomer.jsp";
			} else {
				request.setAttribute("successMessage",
						"Customer " + cust.getUsername()
								+ " created successfully");
			}

		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "createcustomer.jsp";
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "createcustomer.jsp";
		}
		return "viewCustomerList.do";
	}
}
