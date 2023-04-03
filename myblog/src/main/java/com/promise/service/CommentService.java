package com.promise.service;

import com.promise.pojo.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CommentService {
    /*
        统计评论总数
     */
    Integer commentCount(Integer start, Integer pageSize, Comment comment);

    /*
        删除评论
     */
    boolean delete(Integer id);

    /*
        分页查询
     */
    List<Comment> selectByPage(Integer start, Integer pageSize, Comment comment);

    /*
        根据文章id查询所有一级评论
     */
    List<Comment> selectAllByArticleId(Integer articleId);

    /*
        根据一级评论的id，查询其下的所有子级评论
     */
    List<Comment> selectByTopId(Integer articleId, Integer topId);

    /*
        添加评论
     */
    boolean addComment(Comment comment);
}
