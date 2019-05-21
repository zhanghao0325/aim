//package com.aim.service.Impl;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.itheima.dao.ProductDao;
//import com.itheima.domain.PageBean;
//import com.itheima.domain.Product;
//import com.itheima.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public class ProductServiceImpl implements ProductService {
//
//
//    @Autowired
//    private ProductDao productDao;
//    @Override
//    public List<Product> queryAll() {
//        return productDao.queryAll();
//    }
//
//    @Override
//    public void save(Product product) {
//        productDao.save(product);
//    }
//
//    @Override
//    public Product updateId(Integer id) {
//        return productDao.updateId(id);
//    }
//
//    @Override
//    public void update(Product product) {
//        productDao.update(product);
//    }
//
//    @Override
//    public void del(Integer id) {
//        productDao.del(id);
//    }
//
//    @Override
//    public void delmoney(Integer[] ids) {
//        if (ids!=null){
//            for (Integer id : ids) {
//            productDao.del(id);
//
//            }
//        }
//    }
//
//    @Override
//    public PageBean<Product> queryByPage(Integer pageSize, Integer pageNum) {
////        private Integer pageSize;
//        PageBean<Product> pageBean = new PageBean<>();
//        pageBean.setPageSize(pageSize);
////        private Integer pageNum;
//        pageBean.setPageNum(pageNum);
////        private Integer tatolCount;
//        Integer totalCount=productDao.totalCount();
//        pageBean.setTatolCount(totalCount);
////        private Integer tatolPage;
//       pageBean.setTatolPage((int) Math.ceil(totalCount*1.0 / pageSize));
////        private List<T> list;
//        /*
//        * 1  1    5
//        * 2  5   10
//        * 3  11  15
//        * pageNum*pageSize pageNum*pageSize-(pageSize-1)
//        * */
//        Integer startRow=pageNum*pageSize-(pageSize-1);
//        Integer endRow=pageNum*pageSize;
//        List<Product> productList=productDao.queryByPage(startRow,endRow);
//        pageBean.setList(productList);
//        return pageBean;
//    }
//
//    @Override
//    public PageInfo<Product> queryByPageHelper(Integer pageSize, Integer pageNum) {
//        PageHelper.startPage(pageNum,pageSize);
//        List<Product> products = productDao.queryAll();
//        PageInfo<Product> productPageInfo = new PageInfo<>(products);
////        System.out.println(productPageInfo.getNavigateLastPage());
////        System.out.println(productPageInfo.getNavigateFirstPage());
//        return productPageInfo;
//    }
//}
