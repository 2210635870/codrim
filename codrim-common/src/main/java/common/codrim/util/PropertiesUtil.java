package common.codrim.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
//public static void main(String[] args) throws BusinessException {
//	System.out.println(getValue("game.properties","key"));
//}
//	
	public static String  getValue(String path,String key) {
		Properties prop = new Properties();
		try {
			prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return prop.getProperty(key);
	}
	
	
	
	
	
}
