package org.wanho.mapper;

import org.wanho.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Insert("insert into product values(null,#{title},#{url},#{price},#{oldPrice}," +
            "#{saleCount},#{num},0,#{created},#{categoryId})")
    void insert(Product product);

    //当表字段的名字和封装数据的实体类属性名不一致时,需要通过Result注解指定一下对应关系
    @Select("select id,title,price,sale_count,url from product")
    @Result(column = "sale_count", property = "saleCount")
    List<Product> select();

    @Select("select url from product where id=#{id}")
    String selectUrById(int id);

    @Delete("delete from product where id=#{id}")
    void deleteById(int id);

    @Select("select id,title,url,price,old_price,sale_count from product")
    @Result(column = "sale_count", property = "saleCount")
    @Result(column = "old_price", property = "oldPrice")
    List<Product> selectForIndex();

    @Select("select id,if(length(title)>9,concat(substring(title,1,3),'...'),title) title,sale_count from product order by sale_count desc limit 0,6")
    @Result(column = "sale_count", property = "saleCount")
    List<Product> selectTop();

    @Select("select id,title,url,price,old_price,sale_count,view_count,num,created from product where id=#{id}")
    @Result(column = "sale_count", property = "saleCount")
    @Result(column = "old_price", property = "oldPrice")
    @Result(column = "view_count", property = "viewCount")
    Product selectById(int id);

    @Update("update product set view_count=view_count+1 where id=#{id}")
    void updateViewCountById(int id);

    @Select("select id,title,url,price,old_price,sale_count from product where category_id=#{cid}")
    @Result(column = "sale_count", property = "saleCount")
    @Result(column = "old_price", property = "oldPrice")
    List<Product> selectByCid(int cid);

    @Select("select id,title,url,price,old_price,sale_count from product where title like concat('%',#{wd},'%')")
    @Result(column = "sale_count", property = "saleCount")
    @Result(column = "old_price", property = "oldPrice")
    List<Product> selectByWd(String wd);
}
