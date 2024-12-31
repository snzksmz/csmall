package org.wanho.controller;

import org.wanho.entity.Banner;
import org.wanho.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
public class BannerController {
    @Autowired(required = false)
    BannerMapper mapper;
    @Value("${dirPath}")
    private String dirPath;

    @RequestMapping("/banner/select")
    public List<Banner> select() {
        return mapper.select();
    }
    @RequestMapping("/banner/insert")
    public void insert(String url){
        mapper.insert(url);
    }
    @RequestMapping("/banner/delete")
    public void delete(int id){
        String url=mapper.selectUrById(id);
        String filePath=dirPath+"/"+url;
        new File(filePath).delete();
        mapper.deleteById(id);
    }
}
