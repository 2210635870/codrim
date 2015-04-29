package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbWzWallpaper implements Serializable {
	private static final long serialVersionUID = 6641143211946946761L;

	private Long id;
	private String wallpaper;
	private Date addDate;
	
	public TbWzWallpaper() {}
	
	public TbWzWallpaper(Long id, String wallpaper) {
		this.id = id;
		this.wallpaper = wallpaper;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWallpaper() {
		return wallpaper;
	}

	public void setWallpaper(String wallpaper) {
		this.wallpaper = wallpaper;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
}
