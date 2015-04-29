package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbWzNews implements Serializable {
	private static final long serialVersionUID = 8152647602774002497L;
	
	private Long id;
	private String title;
	private String newsScreenLock;
	private String newsUrl;
	private Date addDate;
	
	public TbWzNews() {}
	
	public TbWzNews(Long id, String title, String newsScreenLock, String newsUrl, Date addDate) {
		this.id = id;
		this.title = title;
		this.newsScreenLock = newsScreenLock;
		this.newsUrl = newsUrl;
		this.addDate = addDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNewsScreenLock() {
		return newsScreenLock;
	}

	public void setNewsScreenLock(String newsScreenLock) {
		this.newsScreenLock = newsScreenLock;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
}
