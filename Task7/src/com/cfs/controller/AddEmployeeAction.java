package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.EmployeeDAO;
import com.cfs.databean.Employee;
import com.cfs.databean.Model;
import com.cfs.formbean.EmployeeForm;

public class AddEmployeeAction extends Action{
	private EmployeeDAO employeeDAO;
	private FormBeanFactory<EmployeeForm> formBeanFactory = FormBeanFactory
			.getInstance(EmployeeForm.class);
	
	public AddEmployeeAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "addEmployee.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String> ();
		request.setAttribute("errors", errors);
		
		try {
			EmployeeForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			if (!form.isPresent()) {
				return "createEmployee.jsp";
			}
			
			errors.addAll(form.getValidationErrors());

			if (errors.size() != 0) {
				return "createEmployee.jsp";
			}
			
			if (employeeDAO.checkUserExist(form.getUsername()) != null) {
				errors.add("Username has already been registered");
				return "createEmployee.jsp";
			}
			
			Employee employee = new Employee();
			employee.setFirstName(form.getFirstName());
			employee.setLastName(form.getLastName());
			employee.setPassword(form.getPassword1());
			employee.setUsername(form.getUsername());
			
			employeeDAO.create(employee);
			
			request.setAttribute("successMessage", "Employee " + employee.getUsername() + " created successfully");
			
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "createEmployee.jsp";
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "createEmployee.jsp";
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "createEmployee.jsp";
		}
		return "createEmployee.jsp";
	}
}
