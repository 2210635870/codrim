/**
 * 
 */
package com.codrim.util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;


/**
 * @author Administrator
 *
 */
public class Sign {
	public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
	
	public static  String generateSign(String content,String privateKey){
		try {
			PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) ); 
		  	KeyFactory keyf = KeyFactory.getInstance("RSA");
		   	PrivateKey priKey = keyf.generatePrivate(priPKCS8);
	        Signature signature = Signature
	            .getInstance(SIGN_ALGORITHMS);
	        signature.initSign(priKey);
	        signature.update( content.getBytes("UTF-8") );
	        byte[] signed = signature.sign();
	        return UrlBase64.encode(signed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
      return null;
	}
	
	
}
