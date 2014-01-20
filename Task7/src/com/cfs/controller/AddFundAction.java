package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanFactory;
import org.mybeans.form.FormBeanException;

import com.cfs.dao.FundDAO;
import com.cfs.databean.Fund;
import com.cfs.databean.Model;
import com.cfs.formbean.FundForm;

public class AddFundAction extends Action{

	private FormBeanFactory<FundForm> formBeanFactory = 
			FormBeanFactory.getInstance(FundForm.class);
	private FundDAO fundDAO ;
	
	
	public AddFundAction(Model model) {
		fundDAO = model.getFundDAO();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "addFund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			FundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			if (!form.isPresent()) {
				System.out.println("errors");
				return "home.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "home.jsp";
			}
			
			Fund fund = new Fund();
			fund.setFundName(form.getFundname());
			fund.setSymbol(form.getTickername());
			fundDAO.create(fund);
			HttpSession session = request.getSession();
			session.setAttribute("success", fund);	
			return  "fundList.do";
		
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
	
	
	
}
