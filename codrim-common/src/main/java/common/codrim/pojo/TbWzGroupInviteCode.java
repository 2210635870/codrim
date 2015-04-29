package common.codrim.pojo;

import java.io.Serializable;

public class TbWzGroupInviteCode implements Serializable {
    /**
     * tb_wz_group_invite_code.code
     * @ibatorgenerated 2014-12-31 16:54:06
     */
    private Long code;

    /**
     * tb_wz_group_invite_code.status
     * @ibatorgenerated 2014-12-31 16:54:06
     */
    private Integer status;

    /**
     * tb_wz_group_invite_code.group_id
     * @ibatorgenerated 2014-12-31 16:54:06
     */
    private Long groupId;

    /**
     * tb_wz_group_invite_code.contact
     * @ibatorgenerated 2014-12-31 16:54:06
     */
    private String contact;

    /**
     * tb_wz_group_invite_code.qq
     * @ibatorgenerated 2014-12-31 16:54:06
     */
    private String qq;

    /**
     * tb_wz_group_invite_code.phone
     * @ibatorgenerated 2014-12-31 16:54:06
     */
    private String phone;

    /**
     * tb_wz_group_invite_code.email
     * @ibatorgenerated 2014-12-31 16:54:06
     */
    private String email;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}