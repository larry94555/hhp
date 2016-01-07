package com.happyhourplanner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happyhourplanner.model.ResponseBean;

public class LetsEncryptHandlerServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(LetsEncryptHandlerServlet.class.getName());
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			resp.setContentType("text/plain");
			String id = req.getRequestURI().substring("/.well-known/acme-challenge/".length());
			
			_log.info("id = " + id);
			
			//responses = {
            //        '[challenge 1]': '[response 1]'
            //        '[challenge 2]': '[response 2]'
            //    }
			//self.response.write(responses.get(challenge, ''))
			Map<String,String> responses = new HashMap<String,String>();
			//responses.put("","BfKqG7uZr--KzSVNfBo0X29eFZPruILwmtglGqvvlgU");
			responses.put("2U1cYzIalOdFqP8k6uLbRhlqPctt2m5Nzl6MGtnjWCk","2U1cYzIalOdFqP8k6uLbRhlqPctt2m5Nzl6MGtnjWCk.MSOnzktqrV1lIg-0MvaDMloLo2v6uueJR9hDeFKayo0");
	
			
		    PrintWriter out = resp.getWriter();
		    
		    out.print(responses.get(id));
		    //ResponseBean.println(out, req.getRemoteAddr());
		    
		    
		    out.close();
		
		}
		catch (Exception ex) {
			_log.log(Level.WARNING, "Failure in resending link : " +
					ex.getMessage());
			ex.printStackTrace();
			
		}
	}
	
	

}
