package com.happyhourplanner.model;

import java.io.PrintWriter;

import com.google.gson.Gson;
import com.happyhourplanner.common.Constant;
import com.happyhourplanner.common.Util;

public class ResponseBean {
	
	//private final static Gson _gson = Util.getGson();
	private final static Gson _gson = new Gson();
	
	private final String msg;
	private final String sessionId; 
	private final String cookieName;
	private final String html;
	
	public ResponseBean(final String msg,final String sessionId,final String html) {
		this.msg = msg;
		this.sessionId = sessionId;
		this.cookieName = Constant.COOKIE_NAME;
		this.html = html;
	}
	
	public String getJson() {
		return _gson.toJson(this);
	}
	
	public static void println(PrintWriter out,String msg) {
		println(out,msg,"NA");
	}
	
	public static void println(PrintWriter out,String msg,String sessionId) {
		println(out,msg,sessionId,"");
	}
	
	public static void println(PrintWriter out,String msg,String sessionId,String html) {
		ResponseBean rb = new ResponseBean(msg,sessionId,html);
		out.println(rb.getJson());
	}
	
	

}
