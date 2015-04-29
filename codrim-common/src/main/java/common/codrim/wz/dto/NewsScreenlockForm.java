package common.codrim.wz.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class NewsScreenlockForm implements Serializable{

	private static final long serialVersionUID = -2568192495592119138L;
	
	private String id;
	private String title;
	private String newsUrl;
	private String newsScreenlock;
	
	private MultipartFile newsScreenlockFile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public String getNewsScreenlock() {
		return newsScreenlock;
	}

	public void setNewsScreenlock(String newsScreenlock) {
		this.newsScreenlock = newsScreenlock;
	}

	public MultipartFile getNewsScreenlockFile() {
		return newsScreenlockFile;
	}

	public void setNewsScreenlockFile(MultipartFile newsScreenlockFile) {
		this.newsScreenlockFile = newsScreenlockFile;
	}
	
}
