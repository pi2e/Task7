package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.EmployeeDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.Employee;
import com.cfs.databean.Model;
import com.cfs.formbean.LoginForm;

public class LoginAction extends Action {

	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory
			.getInstance(LoginForm.class);

	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;

	public LoginAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {

		return "login.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {
			if(session.getAttribute("user") instanceof Customer) {
				return "viewcustomer.do";
			} else {
				return "viewFundList.do";
			}
		}
		
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			LoginForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			// request.setAttribute("accountType", form.getAccountType());

			if (!form.isPresent()) {
				return "home.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				request.setAttribute("username", form.getUsername());
				return "home.jsp";
			}

			if (form.getAccountType().equals("C")) {
				Customer customer = customerDAO.getCustomer(form.getUsername());
				if (customer == null) {
					errors.add("Username not found");
					return "home.jsp";
				}

				// Check the password
				if (!customer.checkPassword(form.getPassword())) {
					errors.add("Incorrect password");
					return "home.jsp";
				}

				session.setAttribute("user", customer);
				session.setAttribute("accountType", form.getAccountType());
				return "viewcustomer.do";

			}

			else {

				Employee employee = employeeDAO.checkUserExist(form
						.getUsername());
				if (employee == null) {
					errors.add("Username not found");
					return "home.jsp";
				}

				// Check the password
				if (!employee.checkPassword(form.getPassword())) {
					errors.add("Incorrect password");
					return "home.jsp";
				}

				session.setAttribute("user", employee);
				session.setAttribute("accountType", form.getAccountType());

				return "viewFundList.do";
			}
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "home.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "home.jsp";
		}

	}

}
