package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
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
				return "addFund.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "addFund.jsp";
			}
						
			Fund fund = new Fund();
			fund.setFundName(form.getFundName());
			fund.setSymbol(form.getTicker());
			
			if (!fundDAO.checkcreate(fund)) {
				errors.add("Ticker already in use");
				return "addFund.jsp";
			} else {
				request.setAttribute("successMessage", fund.getFundName() + " created");
			}

			return "viewFundList.do";
		
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
	
	
	
}
