package org.wanho.mapper;

import org.wanho.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select * from category")
    List<Category> select();

    @Delete("delete from category where id=#{id}")
    void deleteById(int id);

    @Insert("insert into category values(null,#{name})")
    void insert(String name);
}
