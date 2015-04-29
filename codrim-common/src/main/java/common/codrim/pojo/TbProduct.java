package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import common.codrim.util.CustomDateSerializer;

public class TbProduct implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TbProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TbProduct(Long id, String productName, String productIden,
			String customerName, Short productType, String createDescribe,
			String createName, Date createTime, String androidPacketSize,
			String androidPacketUrl, String iosPacketSize, String iosPacketUrl) {
		super();
		this.id = id;
		this.productName = productName;
		this.productIden = productIden;
		this.customerName = customerName;
		this.productType = productType;
		this.createDescribe = createDescribe;
		this.createName = createName;
		this.createTime = createTime;
		this.androidPacketSize = androidPacketSize;
		this.androidPacketUrl = androidPacketUrl;
		this.iosPacketSize = iosPacketSize;
		this.iosPacketUrl = iosPacketUrl;
	}

	public TbProduct(String productName, String productIden,
			String customerName, Short productType, String createDescribe,
			String createName, Date createTime, String androidPacketSize,
			String androidPacketUrl, String iosPacketSize, String iosPacketUrl) {
		super();
		this.productName = productName;
		this.productIden = productIden;
		this.customerName = customerName;
		this.productType = productType;
		this.createDescribe = createDescribe;
		this.createName = createName;
		this.createTime = createTime;
		this.androidPacketSize = androidPacketSize;
		this.androidPacketUrl = androidPacketUrl;
		this.iosPacketSize = iosPacketSize;
		this.iosPacketUrl = iosPacketUrl;
	}

	
	private Short isBack;
	/**
     * tb_product.id (自增id)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Long id;

    /**
     * tb_product.product_name (产品名称)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String productName;

    /**
     * tb_product.product_iden (产品标识)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String productIden;

    /**
     * tb_product.customer_name (客户名称)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String customerName;

    /**
     * tb_product.product_type (应用类别)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Short productType;

    /**
     * tb_product.create_describe (描述)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String createDescribe;

    /**
     * tb_product.create_name (创建人)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String createName;

    /**
     * tb_product.create_time (创建时间)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Date createTime;

    /**
     * tb_product.android_packet_size (android包大小)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String androidPacketSize;

    /**
     * tb_product.android_packet_url (android包地址)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String androidPacketUrl;

    /**
     * tb_product.ios_packet_size (ios包大小)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String iosPacketSize;

    /**
     * tb_product.ios_packet_url (ios包地址)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String iosPacketUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
	public Short getIsBack() {
		return isBack;
	}

	public void setIsBack(Short isBack) {
		this.isBack = isBack;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductIden() {
		return productIden;
	}

	public void setProductIden(String productIden) {
		this.productIden = productIden;
	}

	public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Short getProductType() {
        return productType;
    }

    public void setProductType(Short productType) {
        this.productType = productType;
    }

    public String getCreateDescribe() {
        return createDescribe;
    }

    public void setCreateDescribe(String createDescribe) {
        this.createDescribe = createDescribe;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAndroidPacketSize() {
        return androidPacketSize;
    }

    public void setAndroidPacketSize(String androidPacketSize) {
        this.androidPacketSize = androidPacketSize;
    }

    public String getAndroidPacketUrl() {
        return androidPacketUrl;
    }

    public void setAndroidPacketUrl(String androidPacketUrl) {
        this.androidPacketUrl = androidPacketUrl;
    }

    public String getIosPacketSize() {
        return iosPacketSize;
    }

    public void setIosPacketSize(String iosPacketSize) {
        this.iosPacketSize = iosPacketSize;
    }

    public String getIosPacketUrl() {
        return iosPacketUrl;
    }

    public void setIosPacketUrl(String iosPacketUrl) {
        this.iosPacketUrl = iosPacketUrl;
    }
}