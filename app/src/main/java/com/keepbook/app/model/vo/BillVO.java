package com.keepbook.app.model.vo;

public class BillVO {
    /**
     * 月份
     */
    private String time;
    /**
     * 支出
     */
    private Double pay=0.0;
    /**
     * 收入
     */
    private Double come=0.0;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public Double getCome() {
        return come;
    }

    public void setCome(Double come) {
        this.come = come;
    }

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    /**
     * 结余
     */
    private Double left;
}
