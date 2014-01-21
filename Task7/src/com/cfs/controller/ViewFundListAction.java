package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.DAOException;

import com.cfs.dao.FundDAO;
import com.cfs.databean.Fund;
import com.cfs.databean.Model;

public class ViewFundListAction extends Action {

	private FundDAO fundDAO;

	public ViewFundListAction(Model model) {
		fundDAO = model.getFundDAO();
	}

	@Override
	public String getName() {
		return "viewFundList.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		try {			
			Fund[] funds = fundDAO.getFunds();
			request.setAttribute("funds", funds);
			return "fundlist.jsp";
			
		} catch (Exception e) {
			//create error list
			return "fundlist.jsp";
		}
		

	}

}




