package cn.edu.nuc.shop.mapper;

import cn.edu.nuc.shop.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品Mapper接口
 * 提供商品相关的数据库操作
 */
@Mapper
public interface ProductMapper {

    /**
     * 根据主键删除商品
     * @param pid 商品ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer pid);

    /**
     * 插入商品（全字段）
     * @param record 商品对象
     * @return 影响行数
     */
    int insert(Product record);

    /**
     * 插入商品（可选字段）
     * @param record 商品对象
     * @return 影响行数
     */
    int insertSelective(Product record);

    /**
     * 根据主键查询商品
     * @param pid 商品ID
     * @return 商品对象
     */
    Product selectByPrimaryKey(Integer pid);

    /**
     * 根据主键更新商品（可选字段）
     * @param record 商品对象
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * 根据主键更新商品（全字段）
     * @param record 商品对象
     * @return 影响行数
     */
    int updateByPrimaryKey(Product record);

    /**
     * 查询热门商品
     * @return 热门商品列表
     */
    List<Product> selectHotProducts();

    /**
     * 分页查询商品列表
     * @param start 起始位置
     * @param pageSize 每页数量
     * @return 商品列表
     */
    List<Product> selectByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * 查询商品总数
     * @return 商品总数
     */
    int selectCount();

    /**
     * 根据商品名称模糊查询
     * @param pname 商品名称
     * @return 商品列表
     */
    List<Product> selectByNameLike(String pname);

    /**
     * 查询最新商品（按时间倒序）
     * @param limit 限制数量
     * @return 商品列表
     */
    List<Product> selectNewProducts(int limit);

    /**
     * 根据分类查询商品（如果有分类的话，当前表没有分类字段，预留）
     * @return 商品列表
     */
    List<Product> selectAllProducts();
}
