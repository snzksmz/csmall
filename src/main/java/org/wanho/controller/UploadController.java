package org.wanho.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {
    //读取application.properties文件中的内容 并赋值给变量
    @Value("${dirPath}")
    private String dirPath;

    @RequestMapping("/upload")
    public String upload(MultipartFile picFile) throws IOException {
        System.out.println("picFile = " + picFile);
        //得到文件的原始文件名
        String fileName = picFile.getOriginalFilename();
        //得到文件名的后缀部分    abc.jpg     .jpg
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //得到唯一的文件夹 UUID.randomUUID()得到唯一标识符 是一个字符串
        fileName = UUID.randomUUID()+suffix;
        System.out.println("新文件名="+fileName);
        //保存文件的文件夹
        File dirFile = new File(dirPath);
        //如果不存在则创建
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }
        //得到文件的完整路径    F:/files/xxxxx.jpg
        String filePath = dirPath+"/"+fileName;
        //把文件保存到filePath这个路径   异常抛出
        picFile.transferTo(new File(filePath));
        //把新的文件名响应出去, 因为删除图片时需要用到
        return fileName;
    }

    @RequestMapping("/remove")
    public void remove(String fileName){
        System.out.println("删除的文件名 = " + fileName);
        String filePath = dirPath+"/"+fileName;
        //删除文件
        new File(filePath).delete();
        System.out.println(filePath);
    }

}
