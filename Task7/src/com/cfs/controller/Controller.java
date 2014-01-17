package com.cfs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cfs.databean.Model;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = -3816380241319936080L;

	public void init() throws ServletException {
		 Model model = new Model(getServletConfig());
		 Action.add(new HomePageAction(model));
		// Action.add(new ChangePwdAction(model));
		// Action.add(new FundListAction(model));
		Action.add(new LoginAction(model));
		// Action.add(new LogoutAction(model));
		// Action.add(new CustomerRegisterAction(model));
		// Action.add(new EmployerRegisterAction(model));
		// Action.add(new AddFundAction(model));
		// Action.add(new TransactionHistoryction(model));
		// Action.add(new ResearchFundAction(model));
		// Action.add(new BuyFundAction(model));
		// Action.add(new SellFundAction(model));
		// Action.add(new CustomerListAction(model));
		// Action.add(new TransitionFundAction(model));
		// Action.add(new DepositChecktAction(model));
		// Action.add(new RequestCheckAction(model));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	private String performTheAction(HttpServletRequest request) {
		// HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		String action = getActionName(servletPath);

		if (action.equals("login.do")) {
			return Action.perform(action, request);
		}

		return Action.perform(action, request);
	}

	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("/jsp/"
					+ nextPage);
			d.forward(request, response);
			return;
		}

		else if (nextPage != null) {
			response.sendRedirect(nextPage);
			return;
		}

	}

	private String getActionName(String path) {
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}