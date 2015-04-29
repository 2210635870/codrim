package com.codrim.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class ParametersDebugUtils {
	
	private static final Logger logger = Logger.getLogger("yj");

	public static void debugParameters(HttpServletRequest request, String function, String... params) {
		String paramStr = "";
		for (String param : params) {
			if (param.toLowerCase().contains("icon"))
				paramStr += (param + "=" + (request.getParameter(param) == null? 0 : request.getParameter(param).length())  + ", ");
			else
				paramStr += (param + "=" + request.getParameter(param) + ", ");
		}
		
		logger.debug(">>>>> " + function + ": " + paramStr);
	}
	
}
