package com.happyhourplanner.model;

import java.io.PrintWriter;

import com.google.gson.Gson;
import com.happyhourplanner.common.Constant;
import com.happyhourplanner.common.Util;

public class ResponseBean {
	
	private final static Gson _gson = Util.getGson();
	
	private final String msg;
	private final String sessionId; 
	private final String cookieName;
	
	public ResponseBean(final String msg,final String sessionId) {
		this.msg = msg;
		this.sessionId = sessionId;
		this.cookieName = Constant.COOKIE_NAME;
	}
	
	public String getJson() {
		return _gson.toJson(this);
	}
	
	public static void println(PrintWriter out,String msg) {
		println(out,msg,"NA");
	}
	
	public static void println(PrintWriter out,String msg,String sessionId) {
		ResponseBean rb = new ResponseBean(msg,sessionId);
		out.println(rb.getJson());
	}

}
