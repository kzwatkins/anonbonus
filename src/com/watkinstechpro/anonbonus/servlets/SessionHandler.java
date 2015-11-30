package com.watkinstechpro.anonbonus.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHandler {
	private final int MAX_INACTIVE_MIN = 1;
	private final int MAX_REFRESH_MIN = 1;
	private final int SEC_PER_MIN = 60;
	private final int MILLI = 1000;
	private static final String TIMED_OUT_MSG = "Session Timed Out!";

	private final int RESTART_TIME = MAX_REFRESH_MIN * SEC_PER_MIN * MILLI; // Maximum minutes for session timing out!
	private long beginTime = -1;
	private long diffTime = 0;
	private HttpSession sess;
	
	public SessionHandler(HttpSession sess){
		this.sess = sess;
		reset();
	}
	
	public SessionHandler(HttpServletRequest request){
		this.sess = request.getSession();
		reset();
	}

	
	public void getNewSession(HttpServletRequest request) {
		sess = request.getSession();
		sess.invalidate();
		sess = request.getSession(true); // creates a new session
		reset();
	}
	
	public boolean isSessionNew(){
		return(sess.isNew());
	}

	public void setAttribute(HttpServletRequest request, String param, String msg){
		sess = request.getSession();
		sess.setAttribute(param, msg);
	}
	
	public void reset() {
//		refreshBlocked = false;
		beginTime = sess.getCreationTime();
		sess.setMaxInactiveInterval(MAX_INACTIVE_MIN * SEC_PER_MIN);
	}
	
	public boolean isTimeExpired(){
		updateDiffTime();
		return diffTime >= RESTART_TIME;
	}
	
	private void updateDiffTime(){
		diffTime = sess.getLastAccessedTime()-beginTime;
	}
}
