package common.codrim.pojo;

import java.io.Serializable;

public class TbWzSequence implements Serializable {
    /**
     * tb_wz_sequence.id
     * @ibatorgenerated 2015-04-16 11:59:22
     */
    private Long id;

    /**
     * tb_wz_sequence.value
     * @ibatorgenerated 2015-04-16 11:59:22
     */
    private Short value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getValue() {
        return value;
    }

    public void setValue(Short value) {
        this.value = value;
    }
}