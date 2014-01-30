package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.EmployeeDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.Employee;
import com.cfs.databean.Model;
import com.cfs.formbean.ChangePwdForm;

public class ChangePwdAction extends Action {

	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;

	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePwdForm.class);

	public ChangePwdAction(Model model) {

		customerDAO = model.getCustomerDAO();
		employeeDAO = model.getEmployeeDAO();

	}

	@Override
	public String getName() {
		return "changePwd.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		Customer customer = null;
		Employee employee = null;
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		// Load the form parameters into a form bean
		try {

			// customer changing own password
			if (request.getSession().getAttribute("user") instanceof Customer) {

				customer = (Customer) request.getSession().getAttribute("user");
				customer = customerDAO.read(customer.getCustomerId());
				request.setAttribute("customer", customer);
				request.setAttribute("c", "c");

				// employee changing customer password
			} else if (request.getParameter("custId") != null) {

				String userID = (String) request.getParameter("custId");
				customer = customerDAO.read(Integer.parseInt(userID));
				request.setAttribute("customer", customer);
				request.setAttribute("c", "c");

				// employee changing own password
			} else if (request.getSession().getAttribute("user") instanceof Employee) {

				employee = (Employee) request.getSession().getAttribute("user");
				employee = employeeDAO.read(employee.getUserId());
				request.setAttribute("employee", employee);
				request.setAttribute("c", "");
			}

			ChangePwdForm form = null;
			form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will
			// be
			// presented (we assume for the first time).
			System.out.println(!form.isPresent());
			if (!form.isPresent()) {
				return "changePwd.do";
			}

			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "changePwd.do";
			}

			if (request.getAttribute("c").toString().equals("c")) {
				customer.setPassword(form.getNewPassword());
				customerDAO.update(customer);
			} else {
				employee.setPassword(form.getNewPassword());
				employeeDAO.update(employee);
			}

			request.setAttribute("successMessage",
					"Password changed successfully");

		} catch (NumberFormatException e) {
			errors.add(e.toString());
		} catch (RollbackException e) {
			errors.add(e.toString());
		} catch (FormBeanException e) {
			errors.add(e.toString());
		}

		return "changePwd.do";
	}
}
