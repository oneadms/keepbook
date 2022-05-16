package com.keepbook.app.model.dto;

import java.util.Date;
import java.util.Objects;

public class
KeepBookDTO {
    private Integer id;
    private String category;
    private Double money;
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public KeepBookDTO() {

    }

    public KeepBookDTO(Integer id, String category, Double money, Date time, String remark) {
        this.id = id;
        this.category = category;
        this.money = money;
        this.time = time;
        this.remark = remark;
    }

    public KeepBookDTO(String category, Double money, Date time, String remark) {
        this.category = category;
        this.money = money;
        this.time = time;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "KeepBookDTO{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", money=" + money +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeepBookDTO that = (KeepBookDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(category, that.category) && Objects.equals(money, that.money) && Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, money, remark);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String remark;
}
