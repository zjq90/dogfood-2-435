package cn.edu.nuc.shop.service;

import cn.edu.nuc.shop.entity.Product;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 商品服务接口
 * 定义商品相关业务逻辑接口
 */
public interface ProductService {

    /**
     * 根据ID查询商品
     * @param pid 商品ID
     * @return 商品对象
     */
    Product findById(Integer pid);

    /**
     * 查询所有商品
     * @return 商品列表
     */
    List<Product> findAll();

    /**
     * 分页查询商品
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 分页数据
     */
    PageInfo<Product> findByPage(int pageNum, int pageSize);

    /**
     * 查询热门商品
     * @return 热门商品列表
     */
    List<Product> findHotProducts();

    /**
     * 查询最新商品
     * @param limit 限制数量
     * @return 最新商品列表
     */
    List<Product> findNewProducts(int limit);

    /**
     * 根据商品名称模糊查询
     * @param pname 商品名称
     * @return 商品列表
     */
    List<Product> findByNameLike(String pname);

    /**
     * 添加商品
     * @param product 商品对象
     * @return 影响行数
     */
    int addProduct(Product product);

    /**
     * 更新商品信息
     * @param product 商品对象
     * @return 影响行数
     */
    int update(Product product);

    /**
     * 删除商品
     * @param pid 商品ID
     * @return 影响行数
     */
    int delete(Integer pid);

    /**
     * 查询商品总数
     * @return 商品总数
     */
    int findCount();
}
