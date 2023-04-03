package com.promise.dao;

import com.promise.pojo.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {
    Integer commentCount(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
                         Comment comment);

    List<Comment> selectByPage(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
                               Comment comment);

    @Delete("delete from comment where id = #{id};")
    int delete(Integer id);


    // 查询所有一级评论
    List<Comment> selectAllByArticleId(Integer articleId);

    // 根据一级评论的id，查询其下的所有子级评论
    List<Comment> selectByTopId(Integer articleId, Integer topId);

    // 添加评论
    @Insert("insert into comment values (null,#{author},#{context},#{articleId},#{parentId},#{topId}," +
            "#{email},#{createTime});")
    int addComment(Comment comment);
}
