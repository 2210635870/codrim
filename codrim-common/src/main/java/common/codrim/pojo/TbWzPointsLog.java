package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbWzPointsLog implements Serializable {
    private Long id;

    private Long userId;

    private Integer pointsType;

    private String name;

    private Long incomeGold;

    private Date incomeDate;

    
    
    
    
    public TbWzPointsLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TbWzPointsLog(Long userId, Integer pointsType, String name,
			Long incomeGold, Date incomeDate) {
		super();
		this.userId = userId;
		this.pointsType = pointsType;
		this.name = name;
		this.incomeGold = incomeGold;
		this.incomeDate = incomeDate;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPointsType() {
        return pointsType;
    }

    public void setPointsType(Integer pointsType) {
        this.pointsType = pointsType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIncomeGold() {
        return incomeGold;
    }

    public void setIncomeGold(Long incomeGold) {
        this.incomeGold = incomeGold;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }
}