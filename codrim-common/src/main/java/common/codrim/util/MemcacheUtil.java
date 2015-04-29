package common.codrim.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.danga.MemCached.MemCachedClient;

@Repository
public class MemcacheUtil {
	@Autowired
	private MemCachedClient memcachedClient;
	
	public String getCacheByKey(String key){
		return  (String) memcachedClient.get(key);
	}
	
	public  void addCache(String key, String value, Date date){
		memcachedClient.add(key, value, date);
	}
	
	public  void addCache(String key, String value){
		memcachedClient.add(key, value);
	}
	
	public  void updateCache(String key, String value, Date date){
		memcachedClient.replace(key, value, date);
	}
	
	public  void updateCache(String key, String value){
		memcachedClient.replace(key, value);
	}
	
	public void deleteCache(String key){
		memcachedClient.delete(key);
	}
	public void flushALl(){
	memcachedClient.flushAll();
}
	
}
