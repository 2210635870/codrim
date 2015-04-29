package common.codrim.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {

	
	 public static String SHA1(String decript) {
	        try {
	            MessageDigest digest = java.security.MessageDigest
	                    .getInstance("SHA-1");
	            digest.update(decript.getBytes());
	            byte messageDigest[] = digest.digest();
	            // Create Hex String
	            StringBuffer hexString = new StringBuffer();
	            // 字节数组转换为 十六进制 数
	            for (int i = 0; i < messageDigest.length; i++) {
	                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
	                if (shaHex.length() < 2) {
	                    hexString.append(0);
	                }
	                hexString.append(shaHex);
	            }
	            return hexString.toString();
	 
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return "";
	    }
	public static void main(String[] args) {
		//8b1a9953c4611296a827abf8c47804d7
		System.out.println(toMd5("C43A4DC12F8774733572EA813CEC3D3A"));
//		System.out.println(getUUID());
//		System.out.println(getUniqueFilenameByImgType("2"));
//		System.out.println(readableFileSize(3644935888L));
		//	F828404CB6EB87695CE5E5AA10DBEBAA
		//0B33962A8E48B8BD5AF77EA4473029E9
	}

	/**
	 * MD5加密算法
	 * @param inputValue 要加密的字符
	 * @return MD5串
	 * xulin
	 */
	public static String toMd5(String inputValue){
		if(inputValue==null)return "";
        try {
        	MessageDigest m=MessageDigest.getInstance("MD5");
			m.update(inputValue.getBytes("UTF8"));
			byte s[ ]=m.digest( );
		    String result="";
		    for(int i=0;i<s.length;i++){
		        result+=Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
		    }
		    return result.toUpperCase();
		} catch (UnsupportedEncodingException e) {
			System.out.println(inputValue+ "toMd5 error ,error message:"+e.getMessage());
			return "";
		} catch (NoSuchAlgorithmException e) {
			System.out.println(inputValue+ "toMd5 error ,error message:"+e.getMessage());
			return "";
		}
	}
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if (ip.equals("127.0.0.1")) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		return ip.indexOf(",") > -1 ? ip.substring(0, ip.indexOf(",")) : ip;
	}
	
	public static boolean isEmpty(String s) {
        if ((s == null) || (s.trim().length() == 0)) {
            return true;
        }

        return false;
    }
	
	public static String toString(Object s) {
        if (s == null) {
            return "";
        }

        return String.valueOf(s);
    }
	
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
	}
	
	/**
	 * 生成唯一文件名
	 * @param filename
	 * @return
	 */
	public static String getUniqueFilename(String filename) {
		return DateUtil.getCurrentTimestap() 
				+ RandomStringUtils.randomAlphanumeric(5).toLowerCase()
				+ filename.substring(filename.lastIndexOf("."));
	}
	
	/**
	 * 根据图片类型生成唯一文件名
	 * @param filename
	 * @return
	 */
	public static String getUniqueFilenameByImgType(String type) {
		String ext = ".png";
		if ("1".equals(type)) {
			ext = ".png";
		} else if ("2".equals(type)) {
			ext = ".jpg";
		}
		
		return DateUtil.getCurrentTimestap() 
				+ RandomStringUtils.randomAlphanumeric(5).toLowerCase()
				+ ext;
	}
	
	/**
	 * 根据文件大小自动加上单位
	 * @param size
	 * @return
	 */
	public static String readableFileSize(long size) {
	    if(size <= 0) return "0";
	    final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
	    return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
}
