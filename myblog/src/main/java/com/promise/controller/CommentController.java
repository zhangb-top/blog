package com.promise.controller;

import com.promise.controller.result.CommentResult;
import com.promise.pojo.Comment;
import com.promise.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/count")
    @ResponseBody
    public CommentResult commentCount(Integer currentPage,
                                      Integer pageSize, Comment comment) {
        Integer commentCount = 0;
        if (currentPage != null && pageSize != null && comment != null)
            commentCount = commentService.commentCount((currentPage - 1) * pageSize, pageSize,
                    comment);
        else commentCount = commentService.commentCount(currentPage, pageSize, comment);
        return new CommentResult(Code.SELECT_SUC, "统计成功", commentCount);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    @ResponseBody
    public CommentResult selectByPage(@PathVariable Integer currentPage,
                                      @PathVariable Integer pageSize, Comment comment) {
        comment.setAuthor("%" + comment.getAuthor() + "%");
        comment.setArticleTitle("%" + comment.getArticleTitle() + "%");
        comment.setContext("%" + comment.getContext() + "%");
        Integer commentCount = commentService.commentCount(0, pageSize,
                comment);
        System.out.println(commentCount);
        List<Comment> comments = commentService.selectByPage((currentPage - 1) * pageSize, pageSize,
                comment);
        Integer code = comments != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = comments != null ? "评论都在这里啦" : "查询失败了呀";
        HashMap<String, Object> data = new HashMap<>();
        data.put("comments", comments);
        data.put("total", commentCount);
        return new CommentResult(code, msg, data);
    }

    // 查询一级评论
    @GetMapping("/{articleId}")
    @ResponseBody
    public CommentResult selectByArticleId(@PathVariable Integer articleId) {
        List<Comment> comments = commentService.selectAllByArticleId(articleId);
        Integer code = comments != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = comments != null ? "评论都在这里啦" : "查询失败了呀";
        return new CommentResult(code, msg, comments);
    }

    // 查询一级评论id下的所有子评论
    @GetMapping("/level/{articleId}/{topId}")
    @ResponseBody
    public CommentResult selectByTopId(@PathVariable Integer articleId,
                                       @PathVariable Integer topId) {
        List<Comment> comments = commentService.selectByTopId(articleId, topId);
        Integer code = comments != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = comments != null ? "评论都在这里啦" : "查询失败了呀";
        return new CommentResult(code, msg, comments);
    }

    // 添加评论
    @PostMapping
    @ResponseBody
    public CommentResult addComment(@RequestBody Comment comment) {
        boolean flag = commentService.addComment(comment);
        Integer code = flag ? Code.ADD_SUC : Code.ADD_ERR;
        String msg = flag ? "发布成功" : "发布失败";
        return new CommentResult(code, msg, flag);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public CommentResult delete(@PathVariable Integer id) {
        boolean flag = commentService.delete(id);
        Integer code = flag ? Code.DELETE_SUC : Code.DELETE_ERR;
        String msg = flag ? "删除成功" : "删除失败";
        return new CommentResult(code, msg, flag);
    }
}