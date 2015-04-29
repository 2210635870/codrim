package common.codrim.common;

/**
 * 返回bean
 * @author Administrator
 *
 */
public class ResultJsonBean {
private boolean success;
private Object object;

public Object getObject() {
	return object;
}

public void setObject(Object object) {
	this.object = object;
}

public boolean isSuccess() {
	return success;
}

public void setSuccess(boolean success) {
	this.success = success;
}

}
