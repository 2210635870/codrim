package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbAdvertPutChannelBudget implements Serializable {
    /**
     * tb_advert_put_channel_budget.id
     * @ibatorgenerated 2015-02-09 15:10:07
     */
    private Long id;

    /**
     * tb_advert_put_channel_budget.product_put_advert_id (产品投放id)
     * @ibatorgenerated 2015-02-09 15:10:07
     */
    private Long advertPutAdvertId;

    /**
     * tb_advert_put_channel_budget.budget_type (预算方式 1 无预算 2 总预算 3 日预算)
     * @ibatorgenerated 2015-02-09 15:10:07
     */
    private Short budgetType;

    /**
     * tb_advert_put_channel_budget.budget_time (预算日期  null为总预算)
     * @ibatorgenerated 2015-02-09 15:10:07
     */
    private Date budgetTime;

    /**
     * tb_advert_put_channel_budget.budge_num (预算值)
     * @ibatorgenerated 2015-02-09 15:10:07
     */
    private Long budgeNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getAdvertPutAdvertId() {
		return advertPutAdvertId;
	}

	public void setAdvertPutAdvertId(Long advertPutAdvertId) {
		this.advertPutAdvertId = advertPutAdvertId;
	}

	public Short getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(Short budgetType) {
        this.budgetType = budgetType;
    }

    public Date getBudgetTime() {
        return budgetTime;
    }

    public void setBudgetTime(Date budgetTime) {
        this.budgetTime = budgetTime;
    }

    public Long getBudgeNum() {
        return budgeNum;
    }

    public void setBudgeNum(Long budgeNum) {
        this.budgeNum = budgeNum;
    }
}