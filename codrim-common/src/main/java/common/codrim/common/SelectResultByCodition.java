package common.codrim.common;

import java.util.Date;

/**
 * 按条件查询报表信息的类
 * */
public class SelectResultByCodition {
	private String customerName;
	private Long productId;
	private String productName;
	private String channelName;
	private String deviceplate;//设备系统 ios android
	private Date startTime;
	private Date endTime;
	private short clickNum;   //现代表点击    8
	private short effect;             //效果     
	private short activate;           //是否激活
	private short register;          //是否注册
	private short credit;            //是否绑卡
	private short baddept;           //是否坏账
	private short becharge;          //是否充值
	private short consumeOne;             //消费次数
	private short consumeMore;    //两次消费以上
	private String channelNumber;//冲榜投入渠道号
	private int startPage;
	private int size;
	private short hour;
	private String markType;// idfa mac  imei
	private String appId;
	private Short runningStatus;//运动状态
	private short evaluateResult;
	private double income;   //收入
	private double costs;   //成本
	private double grossProfit;  //毛利
	private Short type;
	private String isDeduplication; //去重
	private String productProperty;
	
	
	
	public SelectResultByCodition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIsDeduplication() {
		return isDeduplication;
	}

	public String getProductProperty() {
		return productProperty;
	}

	public void setProductProperty(String productProperty) {
		this.productProperty = productProperty;
	}

	public void setIsDeduplication(String isDeduplication) {
		this.isDeduplication = isDeduplication;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public short getEvaluateResult() {
		return evaluateResult;
	}

	public void setEvaluateResult(short evaluateResult) {
		this.evaluateResult = evaluateResult;
	}

	public SelectResultByCodition(String customerName,
			String productName, String channelName, Date startTime,
			Date endTime, int startPage, int size) {
		super();
		this.customerName = customerName;
		this.productName = productName;
		this.channelName = channelName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startPage = startPage;
		this.size = size;
	}
 


	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getDeviceplate() {
		return deviceplate;
	}

	public void setDeviceplate(String deviceplate) {
		this.deviceplate = deviceplate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public short getClickNum() {
		return clickNum;
	}

	public void setClickNum(short clickNum) {
		this.clickNum = clickNum;
	}

	public short getEffect() {
		return effect;
	}

	public void setEffect(short effect) {
		this.effect = effect;
	}

	public short getActivate() {
		return activate;
	}

	public void setActivate(short activate) {
		this.activate = activate;
	}

	public short getRegister() {
		return register;
	}

	public void setRegister(short register) {
		this.register = register;
	}

	public short getCredit() {
		return credit;
	}

	public void setCredit(short credit) {
		this.credit = credit;
	}

	public short getBaddept() {
		return baddept;
	}

	public void setBaddept(short baddept) {
		this.baddept = baddept;
	}

	public short getBecharge() {
		return becharge;
	}

	public void setBecharge(short becharge) {
		this.becharge = becharge;
	}

	public short getConsumeOne() {
		return consumeOne;
	}

	public void setConsumeOne(short consumeOne) {
		this.consumeOne = consumeOne;
	}

	public short getConsumeMore() {
		return consumeMore;
	}

	public void setConsumeMore(short consumeMore) {
		this.consumeMore = consumeMore;
	}

	public String getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public short getHour() {
		return hour;
	}

	public void setHour(short hour) {
		this.hour = hour;
	}

	public String getMarkType() {
		return markType;
	}

	public void setMarkType(String markType) {
		this.markType = markType;
	}

	public Short getRunningStatus() {
		return runningStatus;
	}

	public void setRunningStatus(Short runningStatus) {
		this.runningStatus = runningStatus;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getCosts() {
		return costs;
	}

	public void setCosts(double costs) {
		this.costs = costs;
	}

	public double getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(double grossProfit) {
		this.grossProfit = grossProfit;
	}


}
