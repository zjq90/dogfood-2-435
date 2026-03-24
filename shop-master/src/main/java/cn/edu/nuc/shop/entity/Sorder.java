package cn.edu.nuc.shop.entity;

import java.io.Serializable;

/**
 * 订单明细实体类（子订单）
 * 对应数据库表：zk_sorder
 */
public class Sorder implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Integer sid;

    /** 商品名称 */
    private String name;

    /** 商品单价 */
    private Double price;

    /** 购买数量 */
    private Integer number;

    /** 订单ID */
    private Integer fid;

    /** 商品ID */
    private Integer pid;

    /** 关联商品对象 */
    private Product product;

    /**
     * 无参构造方法
     */
    public Sorder() {
        super();
    }

    /**
     * 全参构造方法
     */
    public Sorder(Integer sid, String name, Double price, Integer number,
                  Integer fid, Integer pid, Product product) {
        super();
        this.sid = sid;
        this.name = name;
        this.price = price;
        this.number = number;
        this.fid = fid;
        this.pid = pid;
        this.product = product;
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
    public String toString() {
        return "Sorder{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", fid=" + fid +
                ", pid=" + pid +
                ", product=" + product +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fid == null) ? 0 : fid.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + ((sid == null) ? 0 : sid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sorder other = (Sorder) obj;
        if (fid == null) {
            if (other.fid != null)
                return false;
        } else if (!fid.equals(other.fid))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (sid == null) {
            if (other.sid != null)
                return false;
        } else if (!sid.equals(other.sid))
            return false;
        return true;
    }
}
