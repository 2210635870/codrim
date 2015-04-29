package common.codrim.wz.sql.result;

import java.io.Serializable;
import java.util.Date;

import common.codrim.pojo.TbWzTaskStep;

public class StepWithUserRecord extends TbWzTaskStep implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date finishDate;
	
	private int reviewStatus;
	
	private int incomeGold;

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public int getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(int reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public int getIncomeGold() {
		return incomeGold;
	}

	public void setIncomeGold(int incomeGold) {
		this.incomeGold = incomeGold;
	}
	
	
}
