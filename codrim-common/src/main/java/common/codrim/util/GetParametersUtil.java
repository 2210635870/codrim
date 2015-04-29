/**
 * 
 */
package common.codrim.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 *
 */
public class GetParametersUtil {
	
	@SuppressWarnings("unchecked")
	public static void   getParameters(HttpServletRequest request,Logger log) {
		String requestQuery=request.getQueryString();
		StringBuffer buffer=new StringBuffer();
		if(StringUtils.isNotEmpty(requestQuery)){
			String paramStr = null;
			try {
				paramStr = URLDecoder.decode(requestQuery,"UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
			buffer.append(request.getRequestURI()+"?"+paramStr);
			log.info(buffer.toString());
		}else {
			Enumeration<String> paramNames= request.getParameterNames();
			buffer.append(request.getRequestURI()+"?");
			while(paramNames.hasMoreElements()){
				String paramName = paramNames.nextElement();
				buffer.append(paramName+"="+request.getParameter(paramName)+"&");
			}
			log.info(buffer.toString().substring(0, buffer.toString().length()));
		}
	}
}
