package common.codrim.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	@Override
	protected Object determineCurrentLookupKey() {
		return getCurrentLookupKey();
	}
	
	public static String getCurrentLookupKey(){
		return contextHolder.get();
	}
	
	public static void setCurrentLookupKey(String currentLookupKey){
		contextHolder.set(currentLookupKey);
	}

}
