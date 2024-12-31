package org.wanho.controller;

import org.wanho.entity.Product;
import org.wanho.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
public class ProductController {
    @Autowired(required = false)
    ProductMapper mapper;
    @Value("${dirPath}")
    private String dirPath;

    @RequestMapping("/product/insert")
    public void insert(@RequestBody Product product) {
        System.out.println("product = " + product);
        product.setCreated(new Date());
        mapper.insert(product);
    }

    @RequestMapping("/product/select")
    public List<Product> select() {
        return mapper.select();
    }

    @RequestMapping("/product/delete")
    public void delete(int id) {
        String url = mapper.selectUrById(id);
        String filePath=dirPath+"/"+url;
        new File(filePath).delete();
        mapper.deleteById(id);
    }
    @RequestMapping("/product/select/index")
    public List<Product> selectForIndex(){
        return mapper.selectForIndex();
    }
    @RequestMapping("/product/select/top")
    public List<Product> selectTop(){
        return mapper.selectTop();
    }
    @RequestMapping("/product/selectById")
    public Product selectById(int id){
        System.out.println("id = " + id);
        mapper.updateViewCountById(id);
        return mapper.selectById(id);
    }
    @RequestMapping("/product/selectByCid")
    public List<Product> selectByCid(int cid){
        return mapper.selectByCid(cid);
    }
    @RequestMapping("/product/selectByWd")
    public List<Product> selectByWd(String wd){
        return mapper.selectByWd(wd);
    }
}
