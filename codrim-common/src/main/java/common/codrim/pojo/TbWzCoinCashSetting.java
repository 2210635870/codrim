package common.codrim.pojo;

import java.io.Serializable;

public class TbWzCoinCashSetting implements Serializable {
    private Integer id;

    private Short cashType;

    private Integer goldCoin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getCashType() {
        return cashType;
    }

    public void setCashType(Short cashType) {
        this.cashType = cashType;
    }

    public Integer getGoldCoin() {
        return goldCoin;
    }

    public void setGoldCoin(Integer goldCoin) {
        this.goldCoin = goldCoin;
    }
}