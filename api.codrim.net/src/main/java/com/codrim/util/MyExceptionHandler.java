package com.codrim.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionHandler  implements HandlerExceptionResolver {
	private static final Logger log = Logger.getLogger("error");
	private static final Logger log_interface = Logger.getLogger("interface");
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		PropertyConfigurator.configure(MyExceptionHandler.class.getResource(
				"/").getPath()
				+ "log4j.properties");
			GetParametersUtil.getParameters(request, log_interface);
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
//		Map<String, String> map=new HashMap<String, String>();
		StackTraceElement[] elements=ex.getStackTrace();
		StringBuffer buffer=new StringBuffer();
		for(StackTraceElement ele:elements){
			buffer.append(ele.toString()+"\r\n");
		}
		
//		ObjectMapper object=new ObjectMapper();
//		try {
//			String string=object.writeValueAsString(map);
//			response.getWriter().write(string);
//		} catch (JsonGenerationException e) {
//			// TODO Auto-generated catch block
//			log.info(e);
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			log.info(e);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			log.info(e);
//		}finally{
			log.error("request   info :"+request.getQueryString()+"|"+request.getRequestURI()+ex.getMessage()+buffer.toString()+handler.toString());
//		}
		return new ModelAndView();
	}


}
