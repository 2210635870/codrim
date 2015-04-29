package com.codrim.manager;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;


public class PushMessage {
	private static ExecutorService pushService = Executors
			.newFixedThreadPool(2000);
	@Value("#{configProperties['appId']}")
	protected static String appId;
	@Value("#{configProperties['appKey']}")
	protected static String appKey;
	@Value("#{configProperties['master']}")
	protected static String master;
	@Value("#{configProperties['host']}")
	protected static String host;
					
	public static void pushSingle(String clientId) throws IOException{
		 IGtPush push = new IGtPush(host, appKey, master);
	        push.connect();
	        LinkTemplate template = linkTemplateDemo();
	        SingleMessage message = new SingleMessage();
	        message.setOffline(true);
	        //离线有效时间，单位为毫秒，可选
	        message.setOfflineExpireTime(24 * 3600 * 1000);
	        message.setData(template);
	        //message.setPushNetWorkType(1); //判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
	        Target target = new Target();
	   
	        target.setAppId(appId);
	        target.setClientId(clientId);
	        //用户别名推送，cid和用户别名只能2者选其一
	        //String alias = "个";
	        //target.setAlias(alias);
	        IPushResult ret = push.pushMessageToSingle(message, target);
	        System.out.println(ret.getResponse().toString());
		
		
	}
	
	
	//点击通知打开网页模板
	 public static LinkTemplate linkTemplateDemo() {
	        LinkTemplate template = new LinkTemplate();
	        // 设置APPID与APPKEY
	        template.setAppId(appId);
	        template.setAppkey(appKey);
	        // 设置通知栏标题与内容
	        template.setTitle("请输入通知栏标题");
	        template.setText("请输入通知栏内容");
	        // 配置通知栏图标
	        template.setLogo("icon.png");
	        // 配置通知栏网络图标
	        template.setLogoUrl("");
	        // 设置通知是否响铃，震动，或者可清除
	        template.setIsRing(true);
	        template.setIsVibrate(true);
	        template.setIsClearable(true);
	        // 设置打开的网址地址
	        template.setUrl("http://www.baidu.com");
	        return template;
	 }
	// 点击通知打开应用模板
	 public static NotificationTemplate notificationTemplateDemo() {
		 NotificationTemplate template = new NotificationTemplate();
		 // 设置APPID与APPKEY
		 template.setAppId(appId);
		 template.setAppkey(appKey);
		 // 设置通知栏标题与内容
		 template.setTitle("请输入通知栏标题");
		 template.setText("请输入通知栏内容");
		 // 配置通知栏图标
		 template.setLogo("icon.png");
        // 配置通知栏网络图标
         template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
     template.setIsRing(true);
		 template.setIsVibrate(true);
		template.setIsClearable(true);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
          template.setTransmissionType(2);
		 template.setTransmissionContent("请输入您要透传的内容");
		 return template;
		 }
	 
	 
	 public static NotyPopLoadTemplate notyPopLoadTemplateDemo() {
		    NotyPopLoadTemplate template = new NotyPopLoadTemplate();
		    // 设置APPID与APPKEY
		    template.setAppId(appId);
		    template.setAppkey(appKey);
		    // 设置通知栏标题与内容
		    template.setNotyTitle("请输入通知栏标题");
		    template.setNotyContent("请输入通知栏内容");
		    // 配置通知栏图标
		    template.setNotyIcon("icon.png");
		    // 设置通知是否响铃，震动，或者可清除
		    template.setBelled(true);
		    template.setVibrationed(true);
		    template.setCleared(true);
		   
		    // 设置弹框标题与内容
		    template.setPopTitle("弹框标题");
		    template.setPopContent("弹框内容");
		    // 设置弹框显示的图片
		    template.setPopImage("");
		    template.setPopButton1("下载");
		    template.setPopButton2("取消");
		   
		    // 设置下载标题
		    template.setLoadTitle("下载标题");
		    template.setLoadIcon("file://icon.png");
		    //设置下载地址        
		    template.setLoadUrl("");
		    return template;
		}
//透传消息
public static TransmissionTemplate transmissionTemplateDemo() {
    TransmissionTemplate template = new TransmissionTemplate();
    template.setAppId(appId);
    template.setAppkey(appKey);
    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
    template.setTransmissionType(2);
    template.setTransmissionContent("请输入需要透传的内容");
    return template;
}
}
