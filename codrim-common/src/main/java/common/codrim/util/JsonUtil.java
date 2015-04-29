/**
 * 
 */
package common.codrim.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 *
 */
@Repository
public class JsonUtil {

	
	public <T>  String convertObjectToString(T t){
		ObjectMapper mapper=new ObjectMapper();
		String str=null;
		try {
			str=	mapper.writeValueAsString(t);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return	str;
		
	}
	public  <T> T convertStringToObject(String str, Class<T> classType){
		ObjectMapper mapper=new ObjectMapper();
		T t = null;
		try {
	t=	mapper.readValue(str, classType);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	
}
