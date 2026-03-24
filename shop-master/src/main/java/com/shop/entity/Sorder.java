package com.shop.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 订单项实体类
 * 
 * @author shop
 */
public class Sorder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer sid;
    private String name;
    private Double price;
    private Integer number;
    private Integer fid;
    private Integer pid;
    private Product product;

    public Sorder() {
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sorder sorder = (Sorder) o;
        return Objects.equals(sid, sorder.sid) &&
                Objects.equals(name, sorder.name) &&
                Objects.equals(price, sorder.price) &&
                Objects.equals(number, sorder.number) &&
                Objects.equals(fid, sorder.fid) &&
                Objects.equals(product, sorder.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, name, price, number, fid, product);
    }

    @Override
    public String toString() {
        return "Sorder{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", fid=" + fid +
                ", pid=" + pid +
                '}';
    }
}
