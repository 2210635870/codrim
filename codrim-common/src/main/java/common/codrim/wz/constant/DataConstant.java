package common.codrim.wz.constant;

public class DataConstant {
	
	public static final String SESSION_KEY_SETTING_CURRENCY = "settingCurrency";
	
	public static final int GROUP_LEADER_PERCENT_DEFAULT = 20;
		
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	
	public static final int FALSE = 0;
	public static final int TRUE = 1;
	
	public static final int PAGESIZE_20 = 20;
	public static final int PAGESIZE_50 = 50;
	
	public static final int GOLD = 1;
	public static final int YUAN = 2;
	
	public static final int INVITE_CODE_TYPE_USEAPP = 1;
	public static final int INVITE_CODE_TYPE_JOINGROUP = 2;
	
	//获取积分类型
	/** 做任务 */
	public static final int INCOME_POINTS_TYPE_TASK = 1;
	/** 邀请 */
	public static final int INCOME_POINTS_TYPE_INVITE = 2;
	/** 签到 */
	public static final int INCOME_POINTS_TYPE_SIGNIN = 3;
	/** 抽奖 */
	public static final int INCOME_POINTS_TYPE_CHOUJIANG = 4;
	/** 积分墙 */
	public static final int INCOME_POINTS_TYPE_INTEGRALWALL = 5;
	/**团队成员 */
	public static final int INCOME_POINTS_TYPE_GROUP = 6;
	/** 被邀请 */
	public static final int INCOME_POINTS_TYPE_INVITE_BY = 7;
	/** 滑动签到 */
	public static final int INCOME_POINTS_TYPE_SCREENLOCK_SIGNIN = 8;

	/** User Status **/
	public static final int USER_STATUS_ENABLE = 1;
	public static final int USER_STATUS_DISABLE = 2;
	
	/** Task Target **/
	public static final int TASK_TARGET_USER = 1;
	public static final int TASK_TARGET_GROUP = 2;
	
	/** Task Status **/
	public static final int TASK_STATUS_INPROGRESS = 1;
	public static final int TASK_STATUS_SUSPEND = 2;
	public static final int TASK_STATUS_OFFLINE = 3;
	
	/** Step Type **/
	public static final int STEP_TYPE_DOWNLOAD = 1;
	public static final int STEP_TYPE_COUNT = 2;
	public static final int STEP_TYPE_SCREEN = 3;
	
	/** Group Invite Code Status **/
	public static final int GROUP_INVITE_CODE_STATUS_UNUSE = 1;
	public static final int GROUP_INVITE_CODE_STATUS_USED = 2;
	
	/** Review Status **/
	public static final int REVIEW_STATUS_PENDING = 1;
	public static final int REVIEW_STATUS_APPROVED = 2;
	public static final int REVIEW_STATUS_DENY = 3;
	
	/** Group Search Param Type **/
	public static final int GROUP_SEARCH_GID = 1;
	public static final int GROUP_SEARCH_GNAME = 2;
	public static final int GROUP_SEARCH_INVID = 3; //邀请码
	
	/** Group User Role **/
	public static final int GROUP_ROLE_NORMAL = 1;
	public static final int GROUP_ROLE_LEADER = 2;
	
	/** Exchange Type **/
	public static final String EXCHANGE_TYPE_ZFB_PREFIX = "Z"; //支付宝
	public static final String EXCHANGE_TYPE_HF_PREFIX = "H"; //话费
	
	public static final int EXCHANGE_TYPE_ZFB = 1;
	public static final int EXCHANGE_TYPE_HF = 2;
	
	/** Task Finish Status **/
	public static final String TASK_FINISH_STATUS_NEW = "1";
	public static final String TASK_FINISH_STATUS_PART = "2";
	public static final String TASK_FINISH_STATUS_ALL = "3";
	
	/** Screenlock  **/
	public static final int TASK_LIST_SIZE = 20;
	/** 下载任务 */
	public static final int TASK_DOWNLOAD_TYPE = 1;
	/** 截图任务 */
	public static final int TASK_CUT_SCREEN_TYPE = 2;
	/** 体验任务 */
	public static final int TASK_EXPERIENCE_TYPE= 3;
	/** 资讯任务 */
	public static final int TASK_INFO_TYPE= 4;
	
}
