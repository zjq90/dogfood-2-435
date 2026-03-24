package cn.edu.nuc.shop.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品实体类
 * 对应数据库表：zk_product
 */
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private Integer pid;

    /** 商品名称 */
    private String pname;

    /** 销售价格 */
    private double sprice;

    /** 成本价格 */
    private double cprice;

    /** 商品图片 */
    private String pic;

    /** 商品描述 */
    private String pdesc;

    /** 是否热门 */
    private Boolean isHot;

    /** 添加日期 */
    private Date pdate;

    /** 库存数量 */
    private int number;

    /**
     * 无参构造方法
     */
    public Product() {
        super();
    }

    /**
     * 全参构造方法
     */
    public Product(Integer pid, String pname, double sprice, double cprice,
                   String pic, String pdesc, Boolean isHot, Date pdate, int number) {
        super();
        this.pid = pid;
        this.pname = pname;
        this.sprice = sprice;
        this.cprice = cprice;
        this.pic = pic;
        this.pdesc = pdesc;
        this.isHot = isHot;
        this.pdate = pdate;
        this.number = number;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public double getSprice() {
        return sprice;
    }

    public void setSprice(double sprice) {
        this.sprice = sprice;
    }

    public double getCprice() {
        return cprice;
    }

    public void setCprice(double cprice) {
        this.cprice = cprice;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc == null ? null : pdesc.trim();
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", sprice=" + sprice +
                ", cprice=" + cprice +
                ", pic='" + pic + '\'' +
                ", pdesc='" + pdesc + '\'' +
                ", isHot=" + isHot +
                ", pdate=" + pdate +
                ", number=" + number +
                '}';
    }
}
