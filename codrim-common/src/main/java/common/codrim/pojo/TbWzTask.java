package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbWzTask implements Serializable {
	private static final long serialVersionUID = -7159679185447978257L;

	/**
     * tb_wz_task.task_id
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private Long taskId;

    /**
     * tb_wz_task.customer_id
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private Long customerId;

    /**
     * tb_wz_task.task_name
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private String taskName;

    /**
     * tb_wz_task.task_desc
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private String taskDesc;

    /**
     * tb_wz_task.task_remark
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private String taskRemark;

    /**
     * tb_wz_task.task_orig_price
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private Double taskOrigPrice;

    /**
     * tb_wz_task.task_status
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private Integer taskStatus;

    /**
     * tb_wz_task.task_effect
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private Integer taskEffect;

    /**
     * tb_wz_task.task_start_date
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private Date taskStartDate;

    /**
     * tb_wz_task.task_end_date
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private Date taskEndDate;

    /**
     * tb_wz_task.app_icon
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private String appIcon;

    /**
     * tb_wz_task.app_screen1
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private String appScreen1;

    /**
     * tb_wz_task.app_screen2
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private String appScreen2;

    /**
     * tb_wz_task.app_url
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private String appUrl;

    /**
     * tb_wz_task.app_pname
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private String appPname;

    /**
     * tb_wz_task.app_size
     * @ibatorgenerated 2014-12-29 19:00:07
     */
    private Integer appSize;
    
    private Short taskBelong;
    private Short weight;
    
    private Date addDate;
    
    private Short productWeight;
    private String appScreenlockFile;
    
    public Short getTaskBelong() {
		return taskBelong;
	}

	public void setTaskBelong(Short taskBelong) {
		this.taskBelong = taskBelong;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}


	public Short getWeight() {
		return weight;
	}

	public void setWeight(Short weight) {
		this.weight = weight;
	}

	public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
    }

    public Double getTaskOrigPrice() {
        return taskOrigPrice;
    }

    public void setTaskOrigPrice(Double taskOrigPrice) {
        this.taskOrigPrice = taskOrigPrice;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getTaskEffect() {
        return taskEffect;
    }

    public void setTaskEffect(Integer taskEffect) {
        this.taskEffect = taskEffect;
    }

    public Date getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(Date taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public Date getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(Date taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppScreen1() {
        return appScreen1;
    }

    public void setAppScreen1(String appScreen1) {
        this.appScreen1 = appScreen1;
    }

    public String getAppScreen2() {
        return appScreen2;
    }

    public void setAppScreen2(String appScreen2) {
        this.appScreen2 = appScreen2;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppPname() {
        return appPname;
    }

    public void setAppPname(String appPname) {
        this.appPname = appPname;
    }

    public Integer getAppSize() {
        return appSize;
    }

    public void setAppSize(Integer appSize) {
        this.appSize = appSize;
    }

	public Short getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(Short productWeight) {
		this.productWeight = productWeight;
	}

	public String getAppScreenlockFile() {
		return appScreenlockFile;
	}

	public void setAppScreenlockFile(String appScreenlockFile) {
		this.appScreenlockFile = appScreenlockFile;
	}
}