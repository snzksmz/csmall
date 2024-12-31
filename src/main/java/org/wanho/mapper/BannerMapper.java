package org.wanho.mapper;

import org.wanho.entity.Banner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface BannerMapper {
    @Select("select * from banner")
    List<Banner> select();

    @Insert("insert into banner values(null,#{url})")
    void insert(String url);

    @Select("select url from banner where id=#{id}")
    String selectUrById(int id);

    @Delete("delete from banner where id=#{id}")
    void deleteById(int id);
}
