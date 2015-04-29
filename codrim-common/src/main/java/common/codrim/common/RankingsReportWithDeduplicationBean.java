/**
 * 
 */
package common.codrim.common;

/**
 * @author Administrator
 *
 */
public class RankingsReportWithDeduplicationBean {
	  /**
     * tb__rankings_report.click_num (点击数)
     * @ibatorgenerated 2014-12-15
     */
    private Long clickNum;
    /**
     * tb__rankings_report.effect_num (效果数)
     * @ibatorgenerated 2014-12-15
     */
    private Long effectNum;
    
    /**
     * tb__rankings_report.active (激活数)
     * @ibatorgenerated 2014-12-15
     */
    private Integer activate;
    /**
     * tb__rankings_report.register (注册数)
     * @ibatorgenerated 2014-12-15
     */
    private Integer register;
    
    /**
     * tb__rankings_report.recharge (充值)
     * @ibatorgenerated 2014-12-15
     */
    private Integer recharge;
    /**
     * tb__rankings_report.credit (绑卡)
     * @ibatorgenerated 2014-12-15
     */
    private Integer credit;
    /**
     * tb__rankings_report.baddept (坏账)
     * @ibatorgenerated 2014-12-15
     */
    private Integer baddept;
    
    
    private Integer consumeMore;
    private Integer consumeOne;
    
    
	public RankingsReportWithDeduplicationBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getConsumeMore() {
		return consumeMore;
	}
	public void setConsumeMore(Integer consumeMore) {
		this.consumeMore = consumeMore;
	}
	public Integer getConsumeOne() {
		return consumeOne;
	}
	public void setConsumeOne(Integer consumeOne) {
		this.consumeOne = consumeOne;
	}
	public Long getClickNum() {
		return clickNum;
	}
	public void setClickNum(Long clickNum) {
		this.clickNum = clickNum;
	}
	public Long getEffectNum() {
		return effectNum;
	}
	public void setEffectNum(Long effectNum) {
		this.effectNum = effectNum;
	}
	public Integer getActivate() {
		return activate;
	}
	public void setActivate(Integer activate) {
		this.activate = activate;
	}
	public Integer getRegister() {
		return register;
	}
	public void setRegister(Integer register) {
		this.register = register;
	}
	public Integer getRecharge() {
		return recharge;
	}
	public void setRecharge(Integer recharge) {
		this.recharge = recharge;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	public Integer getBaddept() {
		return baddept;
	}
	public void setBaddept(Integer baddept) {
		this.baddept = baddept;
	}
    
}
