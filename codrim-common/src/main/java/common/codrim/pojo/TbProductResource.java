package common.codrim.pojo;

import java.io.Serializable;

public class TbProductResource implements Serializable {
	
	
	
	
    public TbProductResource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TbProductResource(Long id, Long productId, String sourceUrl,
			Short sourceType) {
		super();
		this.id = id;
		this.productId = productId;
		this.sourceUrl = sourceUrl;
		this.sourceType = sourceType;
	}

	public TbProductResource(Long productId, String sourceUrl, Short sourceType) {
		super();
		this.productId = productId;
		this.sourceUrl = sourceUrl;
		this.sourceType = sourceType;
	}

	/**
     * tb_product_resource.id
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Long id;

    /**
     * tb_product_resource.product_id (产品id)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Long productId;

    /**
     * tb_product_resource.source_url (资源存放路径)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String sourceUrl;

    /**
     * tb_product_resource.source_type (资源类型 1 icon 2 截图 3 banner 4 插屏 5 全屏)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Short sourceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Short getSourceType() {
        return sourceType;
    }

    public void setSourceType(Short sourceType) {
        this.sourceType = sourceType;
    }
}