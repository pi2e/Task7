package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.databean.Customer;
import com.cfs.databean.FundTransaction;
import com.cfs.databean.Model;
import com.cfs.formbean.ChangePwdForm;
import com.cfs.utility.CommonUtilities;

public class ChangePwdAction extends Action {

	private CustomerDAO customerDAO;

	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePwdForm.class);

	public ChangePwdAction(Model model) {

		customerDAO = model.getCustomerDAO();

	}

	@Override
	public String getName() {
		return "changePwd.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		Customer customer = null;
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		// Load the form parameters into a form bean
		try {

			String userID = (String) request.getParameter("custId");
			System.out.println("user" + userID);
			customer = customerDAO.read(Integer.parseInt(userID));
			request.setAttribute("customer", customer);
			System.out.println(customer);
			ChangePwdForm form = null;
			form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will
			// be
			// presented (we assume for the first time).
			System.out.println(!form.isPresent());
			if (!form.isPresent()) {
				return "changePwd.jsp";
			}

			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "changePwd.jsp";
			}

			customer.setPassword(form.getNewPassword());
			customerDAO.update(customer);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormBeanException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return "changePwd.jsp";
	}
}
