/**
 * 
 */
package common.codrim.common;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class ConsumeNum implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String imei;
private Integer num;

public ConsumeNum() {
	super();
	// TODO Auto-generated constructor stub
}

public String getImei() {
	return imei;
}

public void setImei(String imei) {
	this.imei = imei;
}

public Integer getNum() {
	return num;
}

public void setNum(Integer num) {
	this.num = num;
}



}
