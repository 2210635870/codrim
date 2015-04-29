package com.codrim.controller;

import java.io.OutputStream;
import java.math.BigDecimal;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.codrim.util.Hashids;

import common.codrim.pojo.TbWzCommonSetting;
import common.codrim.pojo.TbWzTaskNumLimit;
import common.codrim.service.WzPointsLogService;
import common.codrim.service.WzSettingService;
import common.codrim.service.WzTaskNumLimitService;
import common.codrim.util.NumberFormatUtil;
import common.codrim.util.StringUtil;
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
	
	@Value("#{configProperties['img_project']}")
	protected String imgProjectPath;
	
	@Value("#{configProperties['sign_key']}")
	protected String sign_key;
	
	@Value("#{configProperties['user_disable_image_path']}")
	protected String user_disable_image_path;
	
	@Autowired
	protected WzSettingService settingService;
	
	@Autowired
	protected WzPointsLogService pointsLogService;
	@Autowired
	WzTaskNumLimitService numLimitService;
	
	private static Hashids INVITE_USEAPP_HASHID = new Hashids("codrim user", 6, Hashids.DEFAULT_ALPHABET_USER);
	private static Hashids INVITE_JOINGROUP_HASHID = new Hashids("codrim group", 6, Hashids.DEFAULT_ALPHABET_GROUP);
	/**
	 * 通过限制数量
	 * @param id
	 * @return
	 */
	protected  int getLimitNum(String limitType) {
	TbWzTaskNumLimit numLimit=numLimitService.getTaskNumLimitWithCache(limitType);
	int num=0;
		if(numLimit!=null){
			num=numLimit.getLimitNum();
		}
		return num;
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

	
	protected boolean checkSign(String sign,String oriString){
		String oriSign=StringUtil.toMd5(sign_key+oriString);
		if(oriSign.equals(sign)){
			return true;
		}else{
			return false;
		}
		
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
	
	/**
	 * 根据邀请码生成邀请链接
	 * @param code
	 * @return
	 */
	protected String getInviteURLByInviteCode(String code, int type) {
		if (DataConstant.INVITE_CODE_TYPE_USEAPP == type)
			return urlInviteRoot + "install.html?code=" + code;
		else
			return urlInviteRoot + "team.html?code=" + code;
	}
	
	/**
	 * 根据URL生成二维码
	 * @param code
	 * @return
	 */
	protected void writeQRCodeByURL(String url, OutputStream os) {
		QRCode.from(url).to(ImageType.PNG).writeTo(os);
	}
	
	/**
	 * 普通任务的价格
	 * @param origPrice
	 * @return
	 */
	protected static String getUserTaskPrice(TbWzCommonSetting setting, double origPrice) {
		double cp = NumberFormatUtil.numberToPercent(100 - setting.getUserCodrimPercent());
		
		BigDecimal taskPrice = toBigDecimal(origPrice)
				.multiply(toBigDecimal(cp))
				.multiply(toBigDecimal(setting.getExchangeRate()));
		
		return taskPrice.stripTrailingZeros().toPlainString();
	}
	
	/**
	 * 团队任务的价格
	 * @param origPrice
	 * @param leaderPercent
	 * @return
	 */
	protected static String getGroupTaskPrice(TbWzCommonSetting setting, double origPrice, int leaderPercent) {
		double lp = NumberFormatUtil.numberToPercent(100 - leaderPercent);
		double cp = NumberFormatUtil.numberToPercent(100 - setting.getGroupCodrimPercent());
		
		BigDecimal taskPrice = toBigDecimal(origPrice)
				.multiply(toBigDecimal(cp))
				.multiply(toBigDecimal(lp))
				.multiply(toBigDecimal(setting.getExchangeRate()));
		return  taskPrice.stripTrailingZeros().setScale(0, BigDecimal.ROUND_DOWN).toPlainString();
	}
	
	/**
	 * 结算普通任务步骤的价格
	 * @param origPrice
	 * @param stepPercent
	 * @param unit
	 * @return
	 */
	protected static String getUserTaskStepPrice(TbWzCommonSetting setting, double origPrice, int stepPercent, int unit) {
		double cp = NumberFormatUtil.numberToPercent(100 - setting.getUserCodrimPercent());
		double sp = NumberFormatUtil.numberToPercent(stepPercent);
		
		BigDecimal taskStepPriceYuan = toBigDecimal(origPrice)
				.multiply(toBigDecimal(cp))
				.multiply(toBigDecimal(sp));
		
		if (DataConstant.YUAN == unit) {
			return taskStepPriceYuan.stripTrailingZeros().toPlainString();
		} else {
			return taskStepPriceYuan.multiply(toBigDecimal(setting.getExchangeRate())).setScale(0, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
		}
	}
	
	/**
	 * 结算团队任务步骤给团员的价格
	 * @param origPrice
	 * @param stepPercent
	 * @param leaderPercent
	 * @param unit
	 * @return
	 */
	protected static String getGroupTaskStepPrice(TbWzCommonSetting setting, double origPrice, int stepPercent, int leaderPercent, int unit) {
		double lp = NumberFormatUtil.numberToPercent(100 - leaderPercent);
		double cp = NumberFormatUtil.numberToPercent(100 - setting.getGroupCodrimPercent());
		double sp = NumberFormatUtil.numberToPercent(stepPercent);
		
		BigDecimal taskStepPriceYuan = toBigDecimal(origPrice)
				.multiply(toBigDecimal(cp))
				.multiply(toBigDecimal(sp))
				.multiply(toBigDecimal(lp));
		
		if (DataConstant.YUAN == unit) {
			return taskStepPriceYuan.stripTrailingZeros().toPlainString();
		} else {
			return taskStepPriceYuan.multiply(toBigDecimal(setting.getExchangeRate())).setScale(0, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
		}
		
	}
	
	/**
	 * 结算团队任务步骤给Leader的金币
	 * @param origPrice
	 * @param stepPercent
	 * @param leaderPercent
	 * @param unit
	 * @return
	 */
	protected static String getGroupTaskStepPrice4Leader(TbWzCommonSetting setting, double origPrice, int stepPercent, int leaderPercent, int unit) {
		double lp = NumberFormatUtil.numberToPercent(leaderPercent);
		double cp = NumberFormatUtil.numberToPercent(100 - setting.getGroupCodrimPercent());
		double sp = NumberFormatUtil.numberToPercent(stepPercent);
		
		BigDecimal taskStepPriceYuan = toBigDecimal(origPrice)
				.multiply(toBigDecimal(cp))
				.multiply(toBigDecimal(sp))
				.multiply(toBigDecimal(lp));
		
		if (DataConstant.YUAN == unit) {
			return taskStepPriceYuan.stripTrailingZeros().toPlainString();
		} else {
			return taskStepPriceYuan.multiply(toBigDecimal(setting.getExchangeRate())).setScale(0, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
		}
	}
	
	protected static String getSignInReward(TbWzCommonSetting setting) {
		return toBigDecimal(setting.getSignInReward())
				.multiply(toBigDecimal(setting.getExchangeRate()))
				.stripTrailingZeros().toPlainString();
	}
	
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
		return a.multiply(b).stripTrailingZeros().setScale(0, BigDecimal.ROUND_DOWN).toPlainString();
	}
	
	public static void main(String[] args) {
		TbWzCommonSetting setting = new TbWzCommonSetting();
		setting.setExchangeRate(10000000L);
		setting.setUserCodrimPercent(23);
		setting.setGroupCodrimPercent(13);
		
		System.out.println(getGroupTaskStepPrice4Leader(setting, 1.5, 20, 10, DataConstant.GOLD));
		
		String codeUseApp = getInviteCodeByUserId(1002, DataConstant.INVITE_CODE_TYPE_USEAPP);
		String codeJoinGroup = getInviteCodeByUserId(1002, DataConstant.INVITE_CODE_TYPE_JOINGROUP);
		System.out.println(codeUseApp + " = " + getUserIdByInviteCode(codeUseApp, DataConstant.INVITE_CODE_TYPE_USEAPP));
		System.out.println(codeJoinGroup + " = " + getUserIdByInviteCode(codeJoinGroup, DataConstant.INVITE_CODE_TYPE_JOINGROUP));		
		String codeUseApp2 = getInviteCodeByUserId(1003, DataConstant.INVITE_CODE_TYPE_USEAPP);
		String codeJoinGroup2 = getInviteCodeByUserId(1003, DataConstant.INVITE_CODE_TYPE_JOINGROUP);
		System.out.println(codeUseApp2 + " = " + getUserIdByInviteCode(codeUseApp2, DataConstant.INVITE_CODE_TYPE_USEAPP));
		System.out.println(codeJoinGroup2 + " = " + getUserIdByInviteCode(codeJoinGroup2, DataConstant.INVITE_CODE_TYPE_JOINGROUP));
		
		/*Set<String> hashes = new HashSet<String>();
		for (int i = 100000; i <= 199999; i++) {
			hashes.add(getInviteCodeByUserId(i, DataConstant.INVITE_CODE_TYPE_USEAPP));
			hashes.add(getInviteCodeByUserId(i, DataConstant.INVITE_CODE_TYPE_JOINGROUP));
		}
		System.out.println(hashes.size());*/
		
		System.out.println(getUserIdByInviteCode("DL40RZ", DataConstant.INVITE_CODE_TYPE_JOINGROUP));
		
		
	}
}
