package common.codrim.pojo;

import java.io.Serializable;

public class TbCustomer implements Serializable {
    /**
     * tb_customer.id (自增id)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private Long id;

    /**
     * tb_customer.customer_name (客户名字)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String customerName;

    /**
     * tb_customer.customer_type (客户类型)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String customerType;

    /**
     * tb_customer.person_in_charge (客户负责人)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String personInCharge;

    /**
     * tb_customer.contact_name (联系人名字)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String contactName;

    /**
     * tb_customer.contact_phone (联系人电话)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String contactPhone;

    /**
     * tb_customer.contact_position (联系人职务)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String contactPosition;

    /**
     * tb_customer.contact_email (联系人邮箱)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String contactEmail;

    /**
     * tb_customer.contact_wx (联系人QQ微信)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String contactWx;

    /**
     * tb_customer.company_name (公司名称)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String companyName;

    /**
     * tb_customer.company_phone (公司电话)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String companyPhone;

    /**
     * tb_customer.company_country (公司所在国家)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String companyCountry;

    /**
     * tb_customer.company_city (公司城市)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String companyCity;

    /**
     * tb_customer.company_address (公司详细地址)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String companyAddress;

    /**
     * tb_customer.website (公司官网)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String website;

    /**
     * tb_customer.remark (备注)
     * @ibatorgenerated 2015-01-29 13:55:55
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactWx() {
        return contactWx;
    }

    public void setContactWx(String contactWx) {
        this.contactWx = contactWx;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}