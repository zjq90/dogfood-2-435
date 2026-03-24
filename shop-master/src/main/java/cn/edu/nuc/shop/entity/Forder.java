package cn.edu.nuc.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 订单实体类（主订单）
 * 对应数据库表：zk_forder
 */
public class Forder implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Integer fid;

    /** 收货人姓名 */
    private String name;

    /** 联系电话 */
    private String phone;

    /** 订单备注 */
    private String remark;

    /** 下单时间 */
    private Date date;

    /** 订单总金额 */
    private Double total;

    /** 邮编 */
    private String post;

    /** 收货地址 */
    private String address;

    /** 用户ID */
    private Integer uid;

    /** 订单状态：0-未支付，1-已支付，2-已发货，3-已完成 */
    private int status;

    /** 订单明细集合 */
    private Set<Sorder> sorderSet;

    /**
     * 无参构造方法
     */
    public Forder() {
        super();
    }

    /**
     * 带订单明细的构造方法
     */
    public Forder(Set<Sorder> sorderSet) {
        super();
        this.sorderSet = sorderSet;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<Sorder> getSorderSet() {
        return sorderSet;
    }

    public void setSorderSet(Set<Sorder> sorderSet) {
        this.sorderSet = sorderSet;
    }

    @Override
    public String toString() {
        return "Forder{" +
                "fid=" + fid +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", remark='" + remark + '\'' +
                ", date=" + date +
                ", total=" + total +
                ", post='" + post + '\'' +
                ", address='" + address + '\'' +
                ", uid=" + uid +
                ", status=" + status +
                ", sorderSet=" + sorderSet +
                '}';
    }
}
