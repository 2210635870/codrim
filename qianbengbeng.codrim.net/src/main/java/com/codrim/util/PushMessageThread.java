/**
 * 
 */
package com.codrim.util;

import java.util.Map;

import org.apache.log4j.Logger;

import com.mysql.jdbc.log.Log;

import common.codrim.util.HttpUtils;

/**
 * @author Administrator
 *
 */
public class PushMessageThread implements Runnable {
	private static final Logger log = Logger.getLogger("yj");
    private String url;
    private Map map;
    
	public PushMessageThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PushMessageThread(String url, Map map) {
		super();
		this.url = url;
		this.map = map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		send();
	}

	public void send() {
		String json = HttpUtils.httpGet(url,map);
		log.info("--------------------------------" +url+"--------"+ json);
	}
}
