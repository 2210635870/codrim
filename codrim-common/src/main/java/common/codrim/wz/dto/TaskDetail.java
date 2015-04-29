package common.codrim.wz.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class TaskDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String taskId;
	private String taskName;
	private String taskOrigPrice;
	private String taskStartDate;
	private String taskEndDate;
	private String taskEffect;
	private String taskStatus;
	private String customerId;
	private String taskDesc;
	private String taskRemark;
	private String appIcon;
	private String appScreen1;
	private String appScreen2;
	private String appScreenLock;
	private String appUrl;
	private String appSize;
	private String appPname;
	private String maxStepIndex;
	
	private Short taskBelong;
    private Short weight;
    private Short productWeight;
	
	private MultipartFile appFile;
	private MultipartFile appIconFile;
	private MultipartFile appScreen1File;
	private MultipartFile appScreen2File;
	private MultipartFile appScreenLockFile;
	
	List<TaskStep> taskSteps = new ArrayList<TaskStep>();
	
	public void addStep(TaskStep step) {
		taskSteps.add(step);
	}
	
	public Short getTaskBelong() {
		return taskBelong;
	}

	public void setTaskBelong(Short taskBelong) {
		this.taskBelong = taskBelong;
	}

	public Short getWeight() {
		return weight;
	}

	public void setWeight(Short weight) {
		this.weight = weight;
	}

	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskOrigPrice() {
		return taskOrigPrice;
	}
	public void setTaskOrigPrice(String taskOrigPrice) {
		this.taskOrigPrice = taskOrigPrice;
	}
	public String getTaskStartDate() {
		return taskStartDate;
	}
	public void setTaskStartDate(String taskStartDate) {
		this.taskStartDate = taskStartDate;
	}
	public String getTaskEndDate() {
		return taskEndDate;
	}
	public void setTaskEndDate(String taskEndDate) {
		this.taskEndDate = taskEndDate;
	}
	public String getTaskEffect() {
		return taskEffect;
	}
	public void setTaskEffect(String taskEffect) {
		this.taskEffect = taskEffect;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	public String getAppSize() {
		return appSize;
	}
	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}
	public List<TaskStep> getTaskSteps() {
		return taskSteps;
	}
	public void setTaskSteps(List<TaskStep> taskSteps) {
		this.taskSteps = taskSteps;
	}

	public MultipartFile getAppFile() {
		return appFile;
	}

	public void setAppFile(MultipartFile appFile) {
		this.appFile = appFile;
	}

	public MultipartFile getAppIconFile() {
		return appIconFile;
	}

	public void setAppIconFile(MultipartFile appIconFile) {
		this.appIconFile = appIconFile;
	}

	public MultipartFile getAppScreen1File() {
		return appScreen1File;
	}

	public void setAppScreen1File(MultipartFile appScreen1File) {
		this.appScreen1File = appScreen1File;
	}

	public MultipartFile getAppScreen2File() {
		return appScreen2File;
	}

	public void setAppScreen2File(MultipartFile appScreen2File) {
		this.appScreen2File = appScreen2File;
	}

	public String getAppPname() {
		return appPname;
	}

	public void setAppPname(String appPname) {
		this.appPname = appPname;
	}

	public String getMaxStepIndex() {
		return maxStepIndex;
	}

	public void setMaxStepIndex(String maxStepIndex) {
		this.maxStepIndex = maxStepIndex;
	}

	public Short getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(Short productWeight) {
		this.productWeight = productWeight;
	}

	public MultipartFile getAppScreenLockFile() {
		return appScreenLockFile;
	}

	public void setAppScreenLockFile(MultipartFile appScreenLockFile) {
		this.appScreenLockFile = appScreenLockFile;
	}

	public String getAppScreenLock() {
		return appScreenLock;
	}

	public void setAppScreenLock(String appScreenLock) {
		this.appScreenLock = appScreenLock;
	}
}
