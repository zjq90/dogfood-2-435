package cn.edu.nuc.shop.service.impl;

import cn.edu.nuc.shop.entity.Product;
import cn.edu.nuc.shop.mapper.ProductMapper;
import cn.edu.nuc.shop.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品服务实现类
 * 实现商品相关业务逻辑
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public Product findById(Integer pid) {
        logger.debug("根据ID查询商品: {}", pid);
        return productMapper.selectByPrimaryKey(pid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        logger.debug("查询所有商品");
        return productMapper.selectAllProducts();
    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo<Product> findByPage(int pageNum, int pageSize) {
        logger.debug("分页查询商品，页码: {}, 每页数量: {}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectAllProducts();
        return new PageInfo<>(productList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findHotProducts() {
        logger.debug("查询热门商品");
        return productMapper.selectHotProducts();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findNewProducts(int limit) {
        logger.debug("查询最新商品，限制数量: {}", limit);
        return productMapper.selectNewProducts(limit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByNameLike(String pname) {
        logger.debug("根据商品名称模糊查询: {}", pname);
        return productMapper.selectByNameLike(pname);
    }

    @Override
    public int addProduct(Product product) {
        logger.info("添加商品: {}", product.getPname());
        int result = productMapper.insertSelective(product);
        logger.info("添加商品成功: {}", product.getPname());
        return result;
    }

    @Override
    public int update(Product product) {
        logger.info("更新商品信息: {}", product.getPid());
        int result = productMapper.updateByPrimaryKeySelective(product);
        logger.info("更新商品信息成功: {}", product.getPid());
        return result;
    }

    @Override
    public int delete(Integer pid) {
        logger.info("删除商品: {}", pid);
        int result = productMapper.deleteByPrimaryKey(pid);
        logger.info("删除商品成功: {}", pid);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public int findCount() {
        logger.debug("查询商品总数");
        return productMapper.selectCount();
    }
}
