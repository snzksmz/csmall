package org.wanho.controller;

import org.wanho.entity.Category;
import org.wanho.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryMapper mapper;
    @RequestMapping("/category/select")
    public List<Category> select(){
        return mapper.select();
    }
    @RequestMapping("/category/delete")
    public void delete(int id){
        mapper.deleteById(id);
    }
    @RequestMapping("/category/insert")
    public void insert(String name){
        mapper.insert(name);
    }
}
