package com.cfs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cfs.databean.Customer;
import com.cfs.databean.Model;

public class HomePageAction extends Action {

	public HomePageAction(Model model) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {

		return "home.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			if (session.getAttribute("user") instanceof Customer) {
				return "viewcustomer.do";
			} else {
				return "viewFundList.do";
			}
		}

		return "../jsp/home.jsp";
	}

}
