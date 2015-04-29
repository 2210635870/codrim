package common.codrim.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public final class HttpUtils {
	private static Logger log = Logger.getLogger(HttpUtils.class);
	
	
	private static String appendParamToUrl(String url, Map<String, Object> map) {
		if (null == map || map.isEmpty()) {
			return url;
		}

		Set<String> keys = map.keySet();

		if (keys.isEmpty()) {
			return url;
		}

		Iterator<String> iterator = keys.iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			String key = iterator.next();

			if (i == 0) {
				url += ("?" + key + "=" + map.get(key));
			} else {
				url += ("&" + key + "=" + map.get(key));
			}
		}

		return url;
	}

	private static List<NameValuePair> convertMap(Map<String, Object> map) {
		List<NameValuePair> result = new ArrayList<NameValuePair>();

		if (null == map || map.isEmpty()) {
			return result;
		}

		Set<String> keys = map.keySet();

		if (keys.isEmpty()) {
			return result;
		}

		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();

			result.add(new BasicNameValuePair(key, String.valueOf(map.get(key))));
		}

		return result;
	}

	private static String baseHttpGet(String url, Map<String, Object> map,
			DefaultHttpClient client) {
		String result = null;

		url = appendParamToUrl(url, map);
		HttpGet httpget = new HttpGet(url);
		log.info(url);
		if (null == client) {
			client = new DefaultHttpClient();
		}

		try {
			httpget.setHeader(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36");
			httpget.addHeader("Content-type", "text/html; charset=utf-8");
			httpget.getParams()
					.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
							new Integer(40000));
			HttpResponse response = client.execute(httpget);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				log.info("HttpGet method failed: "
						+ response.getStatusLine());
			}

			result = EntityUtils.toString(response.getEntity(),"UTF-8");
		} catch (Exception e) {
			log.error("httpclient failed");
		} finally {
			client.getConnectionManager().shutdown();
		}

		return result;
	}

	private static String baseHttpPost(String url, Map<String, Object> map,
			HttpClient client) {
		String result = null;

		HttpPost httpost = new HttpPost(url);

		if (null == client) {
			client = new DefaultHttpClient();
		}

		try {
			List<NameValuePair> nvps = convertMap(map);
			httpost.setHeader(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36");
			httpost.getParams()
					.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
							new Integer(40000));
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			String string = url + "?";
			for (String key : map.keySet()) {
				string = string + key + "=" + map.get(key) + "&";
			}
			log.debug(string);
			HttpResponse response = client.execute(httpost);
			InputStream is = response.getEntity().getContent();

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				log.debug("HttpGet method failed: " + response.getStatusLine());
			}
			result = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			client.getConnectionManager().shutdown();
		}

		return result;
	}

	public static String httpPost(String url, Map<String, Object> map) {
		return baseHttpPost(url, map, null);
	}

	public static String httpGet(String url, Map<String, Object> map) {
		return baseHttpGet(url, map, null);
	}

	public static String httpGzipGet(String url, Map<String, Object> map) {
		DefaultHttpClient client = new DefaultHttpClient();
		wrapGzipClient(client);
		return baseHttpGet(url, map, client);
	}

	public static String httpGzipPost(String url, Map<String, Object> map) {
		DefaultHttpClient client = new DefaultHttpClient();
		wrapGzipClient(client);
		return baseHttpPost(url, map, client);
	}

	private static void wrapGzipClient(DefaultHttpClient client) {
		client.addRequestInterceptor(new HttpRequestInterceptor() {

			public void process(HttpRequest request, HttpContext context)
					throws HttpException, IOException {
				if (!request.containsHeader("Accept-Encoding")) {
					request.addHeader("Accept-Encoding", "gzip");
				}
			}
		});

		client.addResponseInterceptor(new HttpResponseInterceptor() {

			public void process(HttpResponse response, HttpContext context)
					throws HttpException, IOException {
				HttpEntity entity = response.getEntity();
				Header ceheader = entity.getContentEncoding();
				if (ceheader != null) {
					HeaderElement[] codecs = ceheader.getElements();
					for (int i = 0; i < codecs.length; i++) {
						if (codecs[i].getName().equalsIgnoreCase("gzip")) {
							response.setEntity(new GzipDecompressingEntity(
									response.getEntity()));
						}
					}
				}
			}
		});
	}
}
