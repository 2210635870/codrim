package com.os.wz.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.os.wz.util.Hashids;

import common.codrim.service.WzSettingService;
import common.codrim.wz.constant.DataConstant;

public class BaseController {

	@Value("#{configProperties['upload.root']}")
	protected String uploadRoot;
	
	@Value("#{configProperties['img.upload.root']}")
	protected String imgUploadRoot;
	
	@Value("#{configProperties['app.upload.root']}")
	protected String appUploadRoot;
	
	@Value("#{configProperties['img.root']}")
	protected String imgRoot;

	@Value("#{configProperties['app.root']}")
	protected String appRoot;
	
	@Value("#{configProperties['url.root']}")
	protected String urlRoot;
	
	@Value("#{configProperties['url.invite.root']}")
	protected String urlInviteRoot;
	
	
	private static Hashids INVITE_USEAPP_HASHID = new Hashids("codrim user", 6, Hashids.DEFAULT_ALPHABET_USER);
	private static Hashids INVITE_JOINGROUP_HASHID = new Hashids("codrim group", 6, Hashids.DEFAULT_ALPHABET_GROUP);
	
	@Autowired
	protected WzSettingService settingService;
	
	protected static BigDecimal toBigDecimal(String s) {
		return new BigDecimal(s);
	}
	
	protected static BigDecimal toBigDecimal(double d) {
		return new BigDecimal(String.valueOf(d));
	}
	
	protected static BigDecimal toBigDecimal(long l) {
		return new BigDecimal(String.valueOf(l));
	}
	
	protected static String multiply(BigDecimal a, BigDecimal b) {
		return a.multiply(b).stripTrailingZeros().toPlainString();
	}
	
	protected static String subtract(BigDecimal a, BigDecimal b) {
		return a.subtract(b).stripTrailingZeros().toPlainString();
	}
	
	protected static String divide(BigDecimal a, BigDecimal b) {
		return a.divide(b,2).stripTrailingZeros().toPlainString();
	}
	
	/**
	 * 通过用户ID获取邀请码
	 * @param id
	 * @return
	 */
	protected static String getInviteCodeByUserId(long id, int codeType) {
		if (DataConstant.INVITE_CODE_TYPE_USEAPP == codeType) {
			return INVITE_USEAPP_HASHID.encrypt(id);
		} else if (DataConstant.INVITE_CODE_TYPE_JOINGROUP == codeType) {
			return INVITE_JOINGROUP_HASHID.encrypt(id);
		}
		
		return "";
	}
	
	/**
	 * 通过邀请码获取用户ID
	 * @param code
	 * @param codeType
	 * @return
	 */
	protected static long getUserIdByInviteCode(String code, int codeType) {
		
		long[] decodeIds = null;
		
		try {
			if (DataConstant.INVITE_CODE_TYPE_USEAPP == codeType) {
				decodeIds = INVITE_USEAPP_HASHID.decrypt(code);
			} else if (DataConstant.INVITE_CODE_TYPE_JOINGROUP == codeType) {
				decodeIds = INVITE_JOINGROUP_HASHID.decrypt(code);
			}
		} catch (Exception e) {
			return 0;
		}
		
		if (decodeIds != null && decodeIds.length > 0) {
			return decodeIds[0];
		} else {
			return 0;
		}
	}
}
