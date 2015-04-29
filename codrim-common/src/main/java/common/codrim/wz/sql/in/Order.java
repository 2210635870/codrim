package common.codrim.wz.sql.in;

public class Order {
	
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";

	private String property;
	
	private String order;

	public Order() {
		super();
	}

	public Order(String property, String order) {
		super();
		this.property = property;
		this.order = order;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
}
