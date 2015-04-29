package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbWzAppUpgrade implements Serializable {
    /**
     * tb_wz_app_upgrade.id
     * @ibatorgenerated 2015-01-19 16:10:34
     */
    private Long id;

    /**
     * tb_wz_app_upgrade.apk_version
     * @ibatorgenerated 2015-01-19 16:10:34
     */
    private String apkVersion;

    /**
     * tb_wz_app_upgrade.apk_url
     * @ibatorgenerated 2015-01-19 16:10:34
     */
    private String apkUrl;

    /**
     * tb_wz_app_upgrade.upgrade_info
     * @ibatorgenerated 2015-01-19 16:10:34
     */
    private String upgradeInfo;

    /**
     * tb_wz_app_upgrade.is_mandatory
     * @ibatorgenerated 2015-01-19 16:10:34
     */
    private Integer isMandatory;

    /**
     * tb_wz_app_upgrade.publish_date
     * @ibatorgenerated 2015-01-19 16:10:34
     */
    private Date publishDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApkVersion() {
        return apkVersion;
    }

    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getUpgradeInfo() {
        return upgradeInfo;
    }

    public void setUpgradeInfo(String upgradeInfo) {
        this.upgradeInfo = upgradeInfo;
    }

    public Integer getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Integer isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}