package com.promise.service;

import com.promise.pojo.Tag;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TagService {

    /*
        查询所有标签
     */
    List<Tag> selectAll();

    Tag getById(int id);

    boolean add(Tag tag);

    boolean update(Tag tag);

    boolean delete(int id);

    /*
        根据tag的id查询其下所有文章的id
     */
    @Select("select id from article where tag_id=#{id};")
    Integer[] getArticleIdsByTagId(int id);
}
