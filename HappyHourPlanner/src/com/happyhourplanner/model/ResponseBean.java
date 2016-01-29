package com.happyhourplanner.model;

import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.happyhourplanner.common.Constant;
import com.happyhourplanner.common.Util;

public class ResponseBean {
	
	//private final static Gson _gson = Util.getGson();
	private final static Gson _gson = new Gson();
	
	public static final Logger _log = Logger.getLogger(ResponseBean.class.getName());
	
	private final String msg;
	private final String sessionId; 
	private final String cookieName;
	private final String html;
	private final String[] list;
	
	public ResponseBean(final String msg,final String sessionId,final String html) {
		this.msg = msg;
		this.sessionId = sessionId;
		this.cookieName = Constant.COOKIE_NAME;
		this.html = html;
		this.list = null;
	}
	
	public ResponseBean(final String msg,final String sessionId, final List<String> list) {
		this.msg = msg;
		this.sessionId = sessionId;
		this.cookieName = Constant.COOKIE_NAME;
		this.html = null;
		if (list == null) {
			this.list=null;
		}
		else {
			this.list = list.toArray(list.toArray(new String[list.size()]));
		}
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
	
	public static void println(PrintWriter out,String msg,String sessionId,List<String> list) {
		//_log.info("println for ResponseBean with list");
		ResponseBean rb = new ResponseBean(msg,sessionId,list);
		out.println(rb.getJson());
	}
	
	

}
