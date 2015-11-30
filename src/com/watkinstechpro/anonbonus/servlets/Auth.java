package com.watkinstechpro.anonbonus.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.istack.internal.logging.Logger;
import com.watkinstechpro.anonbonus.constants.Constants;
import com.watkinstechpro.anonbonus.db.Database;
import com.watkinstechpro.anonbonus.userlogininput.UserLogins;

/**
 * Servlet implementation class Auth
 */
@WebServlet(description = "authorize the user", urlPatterns = { "/Auth" })
public class Auth extends HttpServlet {
//	private static final Database dbInstance = Database.getInstance();

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Auth.class);
	private final String USERNAME = "user";
	private final String PASSWORD = "password";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Auth() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
//		Constants.renewSession(request);

		String username = request.getParameter(USERNAME).trim();
		String password = request.getParameter(PASSWORD).trim();

		LOGGER.info("username: "+username);
		LOGGER.info("password: "+password);
		if(username.isEmpty() || password.isEmpty()){
			invalidLogin(request, response, "Username and password cannot be empty");
		} else if (isValidLogin(username, password)) {
//			request.getSession().invalidate();
			response.sendRedirect("codegen");
		} else {
			invalidLogin(request, response, "Invalid username or password");
//			request.getSession().invalidate();
//			response.sendRedirect("index.jsp");
		}	
	}
	
	private void invalidLogin(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException {
		request.getSession().setAttribute("invalidLoginMessage", msg);
		resetParams(request, response);
		response.sendRedirect("index.jsp");
	}

	private boolean isValidLogin(String username, String password) {
//		LOGGER.setLevel(Level.INFO);
//		LOGGER.info(username);;
		
		Map<String, String> usrCreds = Database.LOGIN_DB;

		return (usrCreds.containsKey(username) && usrCreds.get(username)
				.equals(Database.encryptPasswd(password)));
	}
	
	private void resetParams(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.setAttribute(USERNAME, "");
		session.setAttribute(PASSWORD, "");
	}

}
