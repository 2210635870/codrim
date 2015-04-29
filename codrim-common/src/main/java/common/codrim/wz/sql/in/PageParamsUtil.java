package common.codrim.wz.sql.in;

import java.util.Map;

public class PageParamsUtil {

	public static void calculatePageParams(Map<String, Object> params) {
		if( params.containsKey("start") && params.containsKey("size") ) {
			int size = Integer.parseInt(params.get("size").toString());
			int start = Integer.parseInt(params.get("start").toString());
			start = (start-1) * size;	
			params.put("start", start);
		}
	}
}
