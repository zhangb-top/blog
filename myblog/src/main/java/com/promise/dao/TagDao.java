package com.promise.dao;

import com.promise.pojo.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagDao {

    @Select("select id, name from tag;")
    List<Tag> selectAll();

    @Select("SELECT id,name FROM tag WHERE id = #{id}")
    Tag selectById(int id);

    @Insert("INSERT INTO tag (name) VALUES (#{name})")
    int add(Tag tag);

    @Update("UPDATE tag SET name = #{name} WHERE id = #{id}")
    int update(Tag tag);

    @Delete("delete from tag where id=#{id};")
    int delete(int id);

    // 根据tag的id查询其下所有文章的id
    @Select("select id from article where tag_id=#{id};")
    Integer[] selectArticleIdsByTagId(int id);
}
