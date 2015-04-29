package common.codrim.pojo;

import java.io.Serializable;

public class TbSeries implements Serializable {
    /**
     * tb_series.id
     * @ibatorgenerated 2015-02-06 13:23:28
     */
    private Long id;

    /**
     * tb_series.series_name (系列名)
     * @ibatorgenerated 2015-02-06 13:23:28
     */
    private String seriesName;

    /**
     * tb_series.remark (备注)
     * @ibatorgenerated 2015-02-06 13:23:28
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}