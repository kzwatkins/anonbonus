package com.watkinstechpro.anonbonus.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.istack.internal.logging.Logger;
import com.watkinstechpro.anonbonus.combos.Combos;
import com.watkinstechpro.anonbonus.db.Database;

/**
 * Servlet implementation class CodeGenServlet
 */
@WebServlet("/CodeGenServlet")
public class CodeGenServlet extends HttpServlet {
//	private static final Logger LOGGER = Logger.getLogger(CodeGenServlet.class);
	private static final long serialVersionUID = 1L;
	private final String ANON_CODE_PARAM = "anonCode";
	private final String URL_PARAM = "url";
	private final String INVALID_PARAM = "invalidLoginMessage";
	private final String LOGIN_REDIRECT ="index.jsp";
	private final String ANON_CODE_REDIRECT = "jsp/anonCombo.jsp";
	
//	private final int MAX_INACTIVE_MIN = 5;
//	private final int MAX_REFRESH_MIN = 1;
//	private final int SEC_PER_MIN = 60;
//	private final int MILLI = 1000;
	String code = "";
	String uri = "";
//	private final int RESTART_TIME = MAX_REFRESH_MIN * SEC_PER_MIN * MILLI; // Maximum minutes for session timing out!
	private  boolean refreshBlocked = false;
	private long beginTime = -1;
	private String redirectStr = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CodeGenServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
	}

//	private void reset(HttpSession sess) {
//		refreshBlocked = false;
//		beginTime = sess.getCreationTime();
//		sess.setMaxInactiveInterval(MAX_INACTIVE_MIN * SEC_PER_MIN);
////		LOGGER.info("reset, beginTime: "+beginTime);
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		HttpSession sess = request.getSession();
		SessionHandler sessionHandler = new SessionHandler(request);
		
		if (sessionHandler.isSessionNew()) {
//			LOGGER.info("new session");
			sessionHandler.reset();
			//redirectStr = "codegen";
		} else {
//			beginTime = sess.getCreationTime();
//			long diffTime = sess.getLastAccessedTime()-beginTime;
//			LOGGER.info("diffTime: "+diffTime);
			
//			if(diffTime < RESTART_TIME){
//				//sess = null;
//				//refreshBlocked = true;
//				//redirectStr = "codegen";
//
//			} else {
			if(sessionHandler.isTimeExpired()){
				sessionHandler.getNewSession(request);
//				sess = getNewSession(request);
//				sess.setAttribute("invalidLoginMessage", "Session timed out!");
				sessionHandler.setAttribute(request, INVALID_PARAM, "Session timed out!");
				redirectStr = LOGIN_REDIRECT;
////				response.sendRedirect("index.jsp");
			}
		}

		if (!refreshBlocked) {
			code = Combos.getRandomCode();
			uri = request.getScheme() + "://" + request.getServerName();
			refreshBlocked = true;
			//redirectStr = "codegen";
		} 

//		if (sess != null) {
//
//			if (!sess.isNew()) {
//				sess.invalidate(); // this clears the session
//				refreshBlocked = true; 
//				sess = null;
//				// redirectStr = "";
//			}
//		}
		
//		response.setContentType("text/html;charset=UTF-8");
		if(redirectStr.isEmpty()){
			redirectStr = ANON_CODE_REDIRECT;
			sessionHandler.setAttribute(request, ANON_CODE_PARAM, code);
			sessionHandler.setAttribute(request, URL_PARAM, uri);
//			sess.setAttribute("anonCode", code);
//			sess.setAttribute("url", uri);
		}
		
		response.sendRedirect(redirectStr);
		redirectStr = "";


//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
//		out.println("<head>");
//		out.println("<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>");
//		out.println("<title>Bonus Points Code</title>");
//		out.println("<STYLE>");
//
//		out.println("<!--");
//
//		out.println("body    {font-family: Arial, Helvetica, sans-serif; font-size: 85%}");
//		out.println("a:link  {color: #006633}");
//		out.println("a:active        {color: #006633}");
//		out.println("a:visited       {color: #996600}");
//		out.println("td      {font-family: Arial, Helvetica, sans-serif}");
//		out.println("p       {font-family: Arial, Helvetica, sans-serif}");
//		out.println("ol      {font-family: Arial, Helvetica, sans-serif}");
//		out.println("ul      {font-family: Arial, Helvetica, sans-serif}");
//		out.println("h1      {font-size: 145%; color: #006633}");
//		out.println("h2      {font-size: 145%; color: #006633}");
//		out.println("h3      {font-family: Arial, Helvetica, sans-serif; font-size: 125%; color: #000000}");
//		out.println("h4      {font-size: 115%; color: #000000}");
//		out.println(".footer {font-family: 'Times New Roman', Times, serif}");
//		out.println(".base {font-family: Arial, Helvetica, sans-serif}");
//		out.println(".smaller {font-family: Arial, Helvetica, sans-serif; font-size: 80%}");
//
//		out.println("-->");
//
//		out.println("</STYLE>");
////		out.println("<script src='https://www.google.com/recaptcha/api.js'></script>");
//		out.println("</head>");
//
//		out.println("<body bgcolor='#CCCC66'>");
//		out.println("<table width='580' border='0' cellspacing='0' cellpadding='0' align='center'>");
//		out.println("<tr valign='middle'>");
//		out.println("<td width='129' height='41' align='center' bgcolor='#006633'><a href='pics/biplane_color.png' width='139' height='41' alt='Wright State University Home Page' border='0' align='middle'></a></td>");
//		out.println("<td width='10' height='41' bgcolor='#CCCC66'>&nbsp;</td>");
//		out.println("<td width='428' height='41' bgcolor='#CCCC66'>");
//		out.println("<font size='+1' color='#006633' style='Arial'>K. Z. Watkins, Ph.D.</font><br>");
//		out.println("<font size='-1' color ='#006633' style='Arial'>Anonymous User Bonus Point Generator</font>");
//		out.println("</td></tr>");
//		out.println("</table><p>");
//
//		out.println("<div align='center'> <font size='+2' color='#006633'>Congrats! You Qualify for Bonus Points for Completing the Survey!!</font><p>");
//		out.println("</div>");
//		out.println("<hr>");
//		out.println("<b><font size='+2' color = '#006F51'><div id='rand' align = 'center'></div></font></b>");
//
//		out.println("<script type='text/javascript'>");
//		out.println("var rand = document.getElementById('rand');");
//		out.println("rand.innerHTML = '" + uri + "<br>" + code + "';");
//		out.println("</script>");
//		out.println("</body></html>");
//		out.close();

	}

//	private HttpSession getNewSession(HttpServletRequest request) {
//		HttpSession sess = request.getSession();
//		sess.invalidate();
//		sess = request.getSession(true); // creates a new session
//		reset(sess);
//		return sess;
//	}
}
