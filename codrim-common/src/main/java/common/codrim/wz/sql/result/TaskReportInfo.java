package common.codrim.wz.sql.result;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TaskReportInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long taskId;
	
	private String taskName;
	
	private Date finishDate; // 完成日期
	
	private String finishDateFrom;
	
	private String finishDateTo;
	
	private Double taskOrigPrice; //接入单价
	
	private Integer effect;  //效果
	
	private Double cost; //成本
	
	private Double income; //收入 = 接入单价 x 效果
	
	private Double profit; //利润 = 收入 - 成本
	
	private Double profitRate; //利率 = 利润/收入 %
	
	private Integer stepEffectTotal;
	
	private List<StepReportInfo> stepReports;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Double getTaskOrigPrice() {
		return taskOrigPrice;
	}

	public void setTaskOrigPrice(Double taskOrigPrice) {
		this.taskOrigPrice = taskOrigPrice;
	}

	public Integer getEffect() {
		return effect;
	}

	public void setEffect(Integer effect) {
		this.effect = effect;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(Double profitRate) {
		this.profitRate = profitRate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public List<StepReportInfo> getStepReports() {
		return stepReports;
	}

	public void setStepReports(List<StepReportInfo> stepReports) {
		this.stepReports = stepReports;
	}

	public Integer getStepEffectTotal() {
		return stepEffectTotal;
	}

	public void setStepEffectTotal(Integer stepEffectTotal) {
		this.stepEffectTotal = stepEffectTotal;
	}

	public String getFinishDateFrom() {
		return finishDateFrom;
	}

	public void setFinishDateFrom(String finishDateFrom) {
		this.finishDateFrom = finishDateFrom;
	}

	public String getFinishDateTo() {
		return finishDateTo;
	}

	public void setFinishDateTo(String finishDateTo) {
		this.finishDateTo = finishDateTo;
	}

	
}
