package com.codrim.manager;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import common.codrim.pojo.TbWzBinding;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzUserService;
import common.codrim.util.PropertiesUtil;
import common.codrim.util.StringUtil;

@Repository
public class IntegralWallManager {
	
	@Autowired
	private WzUserService userService;
	private static final Logger logger = Logger.getLogger("yj");
	@Value("#{configProperties['dev_server_secret']}")
	protected String dev_server_secret;
	@Value("#{configProperties['duomeng_private_key']}")
	protected String duomeng_private_key;
	@Value("#{configProperties['call_back_Key']}")
	protected String call_back_Key;
	@Value("#{configProperties['anwo_Key']}")
	protected String anwo_Key;
	public IntegralWallResultBean resolveYouMi(HttpServletRequest request,
			IntegralWallResultBean bean) throws UnsupportedEncodingException {
		
		String queryString=request.getQueryString();
		logger.info(queryString);
		String app=request.getParameter("app");
		String order=request.getParameter("order");
		String userId=this.decoder(request.getParameter("user"));
		String points=request.getParameter("points");
		String sig=request.getParameter("sig");
		String chn=request.getParameter("chn");
		String ad=this.decoder(request.getParameter("ad"));

		logger.info("有米回调 参数：订单id" + order + "|| 开发者应用id" + app + "||用户id" + userId + "||渠道号" + chn + 
				"||广告名" + ad + "||积分" + points);
		String sign =this.MD5(dev_server_secret + "||" + order + "||" + app + "||" + userId + "||" + chn + "||" + ad + "||" + points).substring(12, 20).toLowerCase();
         if(sign.equals(sig)&&sig!=null){
 			TbWzBinding existedBinding = userService.getBindingByDevice(userId);
               if(existedBinding!=null){
            	   TbWzUser user=userService.getUserById(existedBinding.getUserId());
            	   user.setBalance(user.getBalance()+Long.parseLong(points));
            	  userService.modifyUser(user);
            	 bean.setSuccess(true);
          	   logger.info("更新成功！");
               }else{
            	   logger.info("乜有绑定信息！");
               }
         }else{
        	 logger.info("验证失败！");
         }
		return bean;
	}

	public String decoder(String value) throws UnsupportedEncodingException{
		if(value==null)return null ;
		return URLDecoder.decode(value.replace("+", "%20"), "utf-8");
	}

	public IntegralWallResultBean resolveWanPu(HttpServletRequest request,
			IntegralWallResultBean bean) throws UnsupportedEncodingException {
		String queryString=request.getQueryString();
		logger.info(queryString);
		String adv_id = request.getParameter("adv_id");//被下载应用ID
		String app_id = request.getParameter("app_id");//万普平台注册应用id
		String key = request.getParameter("key");//通过SDK调用积分墙时传入的key或者用户id
		String udid = request.getParameter("udid");//设备唯码
		String bill = request.getParameter("bill");//价格
		if(bill == null){bill = "";}
		String points = request.getParameter("points");//积分
		if(points == null){points = "";}
		String ad_name = request.getParameter("ad_name");//下载的应用名称
		String status = request.getParameter("status");//状态
		if(status == null){status = "";}
		String type = request.getParameter("type");//状态
		if(type == null){ type = "";}
		String activate_time = request.getParameter("activate_time");//激活时间
		//以下参数为旧版验证数据的参数，可以废弃此方法加密
		String order_id = request.getParameter("order_id");//订单号由此字段判断接收数据是否唯一
		String random_code = request.getParameter("random_code");//随机串
		String secret_key = request.getParameter("secret_key");//验证密钥(老)
		String wapskey = request.getParameter("wapskey");//新版验证密钥，推荐使用	
		
		//当满足价格、积分不为空且不为0和状态码为1时才为有效数据
		
		logger.info("万普回调 参数：订单id" + order_id + "|| 开发者应用id" + app_id + "||用户id" + key + 
				"||广告名" + ad_name + "||积分" + points);
		if(!bill.equals("0")&&!points.equals("0")&&status.equals("1")){
			activate_time = URLEncoder.encode(activate_time, "UTF-8");//激活时间在传输时会自动解码，加密是要用url编码过的，如果没有自动解码请忽略转码
			//加密并判断密钥
			String plaintext = adv_id+app_id+key+udid+bill+points+activate_time+order_id+call_back_Key;
			System.out.println("加密前的密钥：" + plaintext);
			String keys = MD5(plaintext);
			System.out.println("解密后的密钥：" + keys);
			//判断和wapskey是否相同，注意大小写
			if(keys.equalsIgnoreCase(wapskey)){
				//成功接收数据
				logger.info("验证成功");
				TbWzBinding existedBinding = userService.getBindingByDevice(key);
	               if(existedBinding!=null){
	            	   TbWzUser user=userService.getUserById(existedBinding.getUserId());
	            	   user.setBalance(user.getBalance()+Long.parseLong(points));
	            	  userService.modifyUser(user);
	            	 bean.setSuccess(true);
	            	 bean.setMessage("成功接收");
	               }
				
				
			}else{
				logger.info("万普 积分 回调 结果：验证失败");
			}
		}else{
			logger.info("万普 积分 回调 结果：无效数据");
		}
return bean;
	}


	
	
	
	public    String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public IntegralWallResultBean resolveDomob(HttpServletRequest request,
			IntegralWallResultBean bean) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String queryString=request.getQueryString();
		logger.info(queryString);
		String action=request.getParameter("action");//整型	动作编号，从0开始。通常0表示激活，1表示签到
		String action_name=this.decoder(request.getParameter("action_name"));//字符串	动作名称，例如“激活”，“签到-1”，“签到-2”等。
		String ad=this.decoder(request.getParameter("ad"));//字符串	广告名称
		String adid=request.getParameter("adid");//整型	广告ID，即offer ID
		String channel=request.getParameter("channel");//渠道号。预留给android的推广，对于iOS来说该值为0
		String device=this.decoder(request.getParameter("device"));//统一返回设备的imei
		String orderid=this.decoder(request.getParameter("orderid"));//字符串	订单ID。该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。
		String pkg=this.decoder(request.getParameter("pkg"));//字符串	被激活的广告的包名，例如 cn.domob.demo
		String point=request.getParameter("point");
		String price=request.getParameter("price");//开发者获得的收入
		String pubid=this.decoder(request.getParameter("pubid"));//Publisher ID，即开发者的积分墙应用ID
		String ts=request.getParameter("ts");//成功结算的时间戳，精确到秒		
		String user=this.decoder(request.getParameter("user"));//android平台，如果开发者没有在积分墙android SDK中绑定userid，则user为设备的imei
		String sig=this.decoder(request.getParameter("sign"));
		String 	sign=this.MD5("action="+action+"action_name="+action_name+"ad="+ad+"adid="+adid+"channel="+channel+"device="+device+"orderid="+orderid+""
				+ "pkg="+pkg+"point="+point+"price="+price+"pubid="+pubid+"ts="+ts+"user="+user+""+duomeng_private_key);
		logger.info("多盟回调 参数：订单id" + orderid + "|| 开发者应用id" + pubid + "||用户id" + user + "||渠道号" + channel + 
				"||广告名" + ad + "||积分" + point);
         if(sign.equalsIgnoreCase(sig)&&sig!=null){
 			TbWzBinding existedBinding = userService.getBindingByDevice(user);
               if(existedBinding!=null){
            	   TbWzUser tbuser=userService.getUserById(existedBinding.getUserId());
            	   tbuser.setBalance(tbuser.getBalance()+Long.parseLong(point));
            	  userService.modifyUser(tbuser);
            	 bean.setSuccess(true);
          	   logger.info("更新成功！");
               }else{
            	   logger.info("没有有绑定信息！");
               }
         }else{
        	 logger.info("验证失败！");
         }
		return bean;
	}

	public IntegralWallResultBean resolveDianjoy(HttpServletRequest request,
			IntegralWallResultBean bean) {
		// TODO Auto-generated method stub
		String queryString=request.getQueryString();
		logger.info(queryString);
		String user=request.getParameter("snuid");//开发者给用户分配的userid(仅限数字、英文，不超过100字符。*切勿使用中文*)
		String device_id=request.getParameter("device_id");//设备号，手机唯一，一个手机对应一个账户
		String app_id=request.getParameter("app_id");//在点乐的应用所对应的DIANLE_APP_ID值
		String currency=request.getParameter("currency");//积分，注意：不是钱
		String app_ratio=request.getParameter("app_ratio");//汇率：1分钱=多少积分(>=1)
		String time_stamp=request.getParameter("time_stamp");//时间戳
		String ad_name=request.getParameter("ad_name");//广告名
		String pack_name=request.getParameter("pack_name");//包名
		String token=request.getParameter("token");//验证密文，其计算方法为：time_stamp（是服务器当前时间戳，毫秒）的值和前面提到的secret_key连接成字符串，然后MD5加密，即：
		//token = MD5(time_stamp + secret_key)。
		String task_id=request.getParameter("task_id");//深度任务的唯一标识符
		//*32位的16进制码 
		//*当trade_type=1时，不出现此字段
		String trade_type=request.getParameter("trade_type");//表示广告任务的类型 1-安装激活任务 4-次日打开深度任务
		//*注意：同一广告可产生多次任务，并产生更多收入
		String 	sign=this.MD5(time_stamp+call_back_Key);
		logger.info("点入回调 参数：|| 开发者应用id" + app_id + "||用户id" + user +
				"||广告名" + ad_name + "||积分" + currency);
         if(sign.equalsIgnoreCase(token)&&token!=null){
 			TbWzBinding existedBinding = userService.getBindingByDevice(user);
               if(existedBinding!=null){
            	   TbWzUser tbuser=userService.getUserById(existedBinding.getUserId());
            	   tbuser.setBalance(tbuser.getBalance()+Long.parseLong(currency));
            	  userService.modifyUser(tbuser);
            	 bean.setSuccess(true);
          	   logger.info("更新成功！");
               }else{
            	   logger.info("没有有绑定信息！");
               }
         }else{
        	 logger.info("验证失败！");
         }
		return bean;
	}

	public IntegralWallResultBean resolveChukong(HttpServletRequest request,
			IntegralWallResultBean bean) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String queryString=request.getQueryString();
		logger.info(queryString);
		String os=request.getParameter("os");//必填项,系统类型。定义enum: ['iOS', 'Android']
		String os_version=this.decoder(request.getParameter("os_version"));//OS版本号
		String mac=this.decoder(request.getParameter("mac"));//MAC地址,没有分隔符全部小写，示例：a0edcd359c7d
		String imei=request.getParameter("imei");//Android系统的唯一设备ID
		String ip=request.getParameter("ip");//
		String transactionid=this.decoder(request.getParameter("transactionid"));//必填项,用于识别是否会用重复的积分返还请求，同一个任务的积分返还id不变
		String coins=this.decoder(request.getParameter("coins"));//必填项,积分数。
		String adid=this.decoder(request.getParameter("adid"));//必填项,广告id
		String adtitle=request.getParameter("adtitle");//广告标题（一般为积分墙列表中的app名称）
		String taskname=this.decoder(request.getParameter("taskname"));//任务名称（比如：安装、激活）
		String taskcontent=this.decoder(request.getParameter("taskcontent"));//任务内容描述（比如：安装游戏试玩3分钟即获得奖励）
		String user=this.decoder(request.getParameter("token"));//token的值是媒体开发人员在广告sdk中设置的token值，触控广告平台并不关心
		//其具体的含义，是userId也好还是account也好，都有媒体的开发人员自己定义	
		String sig=this.decoder(request.getParameter("sign"));
		String 	sign=this.MD5(queryString+call_back_Key);
		logger.info("触控回调 参数：订单id" + adid  + "||用户id" + user + 
				"||广告名" + taskname + "||积分" + coins);
        if(sign.equalsIgnoreCase(sig)&&sig!=null){
 			TbWzBinding existedBinding = userService.getBindingByDevice(user);
               if(existedBinding!=null){
            	   TbWzUser tbuser=userService.getUserById(existedBinding.getUserId());
            	   tbuser.setBalance(tbuser.getBalance()+Long.parseLong(coins));
            	  userService.modifyUser(tbuser);
            	 bean.setSuccess(true);
          	   logger.info("更新成功！");
               }else{
            	   logger.info("没有有绑定信息！");
               }
     }else{
  	 logger.info("验证失败！");
     }
		return bean;
	}

	public IntegralWallResultBean resolveAnwo(HttpServletRequest request,
			IntegralWallResultBean bean) {
		// TODO Auto-generated method stub
		String queryString=request.getQueryString();
		logger.info(queryString);
		String device=request.getParameter("device");//mac
		String androidid=request.getParameter("androidid");//androidid
		String appid=request.getParameter("appid");//开发者应用 ID:
		String imei=request.getParameter("imei");//imei
		String point=request.getParameter("point");//赠送积分, 整型
		String adid=request.getParameter("adid");//下载应用 ID
		String adname=request.getParameter("adname");//广告名
		String ts=request.getParameter("ts");//时间戳
		String sign=request.getParameter("sign");//验证密文，
		String keyword=request.getParameter("keyword");
		String 	token=this.MD5("adid="+adid+"adname="+adname+"androidid="+androidid+"appid="+appid+"device="+device+""
				+ "imei="+imei+"point="+point+"ts="+ts+"key="+anwo_Key);
		logger.info("安沃回调 参数：|| 开发者应用id" + appid + "||用户id" + keyword +
				"||广告名" + adname + "||积分" + point);
         if(token.equalsIgnoreCase(sign)&&sign!=null){
 			TbWzBinding existedBinding = userService.getBindingByDevice(keyword);
               if(existedBinding!=null){
            	   TbWzUser tbuser=userService.getUserById(existedBinding.getUserId());
            	   tbuser.setBalance(tbuser.getBalance()+Long.parseLong(point));
            	  userService.modifyUser(tbuser);
            	 bean.setSuccess(true);
          	   logger.info("更新成功！");
               }else{
            	   logger.info("没有有绑定信息！");
               }
         }else{
        	 logger.info("验证失败！");
         }
		return bean;
	}

}

	
	
	
	

