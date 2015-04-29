/**
 * 
 */
package common.codrim.common;

import java.sql.Timestamp;

/**
 * @author Administrator
 *
 */
public class Contans {
	/**
	 * 缓存时间7天
	 */
	public static final int CACHE_SEVEN_DAY = 7 * 24 * 60 * 60 * 1000;
	/**
	 * 缓存时间3天
	 */
	public static final int CACHE_THREE_DAY = 3 * 24 * 60 * 60 * 1000;
	/**
	 * 微信相关
	 */
	public static final String WEIXIN_TOKEN_KEY="weixin_token";
	public static final String WEIXIN_TICKET_KEY="weixin_ticket";
	/**
	 * 盈+任务积分限制 缓存
	 */
	public static final String TASK_NUM_LIMIT_KEY="task_num_limit";
	public static final Short TASK_NUM_LIMIT_TASK=1;//每天任务限制数
	public static final Short TASK_NUM_LIMIT_POINTS=2;//积分限制
	public static final Short TASK_NUM_LIMIT_INVITE=3;//每日邀请人数限制
	public static final Short TASK_NUM_LIMIT_GROUP=4;//团队人数邀请

	
	public static final String CODRIM_SIGN="C43A4DC12F87747";
	public static final String CACHE_PRODUCT = "product";
	
	public static final String CACHE_RANDOMSENDNUM="randomSendNum";

	public static final int OPERATE_CLICK = 1;

	public static final String SUCCESS = "1000";
	public static final String ERROR = "0000";

	public static final String SUCCESS_MESSAGE = "success";
	public static final String ERROR_MESSAGE = "fail";
    
	public static final Short PRODUCT_RESOURCE_ICON=1;
	public static final Short PRODUCT_RESOURCE_SCREENSHOT=2;
	public static final Short PRODUCT_RESOURCE_BANNER=3;
	public static final Short PRODUCT_RESOURCE_TABLE_SCREEN=4;
	public static final Short PRODUCT_RESOURCE_FULL_SCREEN=5;

	public static final Short LINK_TYPE_CP=1;
	public static final Short LINK_TYPE_CHANNEL=0;
	
	public static final Short TASK__PUT_TYPE_DANDELION=1;
	public static final Short TASK__PUT_TYPE_YINGJIA=2;
	
	//效果失败
	public static final short RANKINGS_ACTION_EFFECT_ERROR = 0;
	//效果成功
    public static final short RANKINGS_ACTION_EFFECT_SUCCESS = 1;
	// 激活
	public static final short RANKINGS_ACTION_ACTIVATE = 2;
	// 注册
	public static final short RANKINGS_ACTION_REGISTER = 3;
	// 消费
	public static final short RANKINGS_ACTION_CONSUME = 4;
	// 充值
	public static final short RANKINGS_ACTION_RECHARGE = 5;
	// 绑卡
	public static final short RANKINGS_ACTION_CREDIT = 6;
	// 坏账
	public static final short RANKINGS_ACTION_BADDEPT = 7;
	// 点击
    public static final short RANKINGS_ACTION_CLICK = 8;
	
	
	//广告运行状态 运行
	public static final short RUNNING_STATUS_RUNNING=1;
	//广告运行状态 暂停
	public static final short RUNNING_STATUS_PAUSE=2;
	//广告运行状态 下线
	public static final short RUNNING_STATUS_OFFLINE=3;
	//广告运行状态 新加入（暂未评估）
	public static final short RUNNING_STATUS_NEW=4;
	
	//渠道询价
	public static final short EVALUATE_STATUS_CHANNEL_ENQUIRY=0;
	//运营评估
	public static final short EVALUATE_STATUS_OPERATE=1;
	//商务
	public static final short EVALUATE_STATUS_FINAL=2;

	//运营评估不接入更改状态
	public static final short OPERATE_RESULT=3;
	
	
	public static final short PUT_CHANNEL_TYPE_NETWORK =1;
	public static final short PUT_CHANNEL_TYPE_DSP_CPC =2;
	public static final short PUT_CHANNEL_TYPE_DSP_CPA =3;

	public static  final short BUDGE_TYPE_NULL=1;
	public static  final short BUDGE_TYPE_ALL=2;
	public static  final short BUDGE_TYPE_DAY=3;

	public static final short EFFECT_CALLBACK_TYPE_URL=1;
	public static final short EFFECT_CALLBACK_TYPE_PARAM=2;

		
		public static final short TRUE=1;
		public static final short FALSE=0;
		
		
		public static final String PRODUCT_PROPERTY_GENERAL="普通";
		
		public static final String PRODUCT_PROPERTY_INTERMODAL="联运";
	
		//渠道添加结算方式
	public static final short SETTLEMENT_WAY_CPA=1;
		
		public static final short  SETTLEMENT_WAY_CPS=2;
		
		//渠道添加效果定义
		public static final short EFFECT_TYPE_REGISTER=1;//注册
		
		public static final short EFFECT_TYPE_ACTIVATE=2;//激活
		
	public static final short EFFECT_TYPE_CONSUME_ONE=3;//消费一次
		
		public static final short EFFECT_TYPE_CONSUME_MORE=4;//消费两次以上
		
	public static final short EFFECT_TYPE_CONSUME_NUMBER=5;//消费金额
		
		
		
	
   
}
