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

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);


		try {
			LoginForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			request.setAttribute("accountType", form.getAccountType());

			if (!form.isPresent()) {
				System.out.println("errors");
				return "home.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				request.setAttribute("username", form.getUsername());
				return "home.jsp";
			}

			if(form.getAccountType().equals("C")){
				Customer customer = customerDAO.checkUserExist(form.getUsername());
				if (customer == null) {
					errors.add("Username not found");
					return "home.jsp";
				}

				// Check the password
				if (!customer.checkPassword(form.getPassword())) {
					errors.add("Incorrect password");
					return "home.jsp";
				}

				HttpSession session = request.getSession();
				session.setAttribute("user", customer);
				return "customer.do";

			} 

			else{

				Employee employee = employeeDAO.checkUserExist(form.getUsername());
				if (employee == null) {
					errors.add("Username not found");
					return "home.jsp";
				}

				// Check the password
				if (!employee.checkPassword(form.getPassword())) {
					errors.add("Incorrect password");
					return "home.jsp";
				}

				HttpSession session = request.getSession();
				session.setAttribute("user", employee);

				return "fundlist.jsp";
			}
		}catch (DAOException e) {
			errors.add(e.getMessage());
			return "home.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "home.jsp";
		}

	}

}




