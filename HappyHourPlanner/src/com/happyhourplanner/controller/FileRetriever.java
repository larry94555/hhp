package com.happyhourplanner.controller;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

public class FileRetriever {
	
	private static final ConcurrentHashMap<String,String> FILE_MAP = new ConcurrentHashMap<String,String>();
	
	public static String getContent(final ServletContext context, final String path) throws IOException {
		
		if (FILE_MAP.get(path) == null) {
			synchronized(FILE_MAP) {
				if (FILE_MAP.get(path) == null) {
					FILE_MAP.put(path, IOUtils.toString(context.getResourceAsStream(path)));
				}
			}
			
		}
		
		return FILE_MAP.get(path);
	}
	

}
