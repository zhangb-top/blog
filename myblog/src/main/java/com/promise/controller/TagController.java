package com.promise.controller;

import com.promise.controller.result.TypeTagResult;
import com.promise.pojo.Tag;
import com.promise.service.ArticleService;
import com.promise.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;

    @GetMapping
    @ResponseBody
    public TypeTagResult selectAll() {
        List<Tag> tags = tagService.selectAll();
        Integer code = tags != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = tags != null ? "标签都在这里啦" : "一个标签也没有啦";
        return new TypeTagResult(code, msg, tags);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TypeTagResult selectById(@PathVariable int id) {
        Tag tag = tagService.getById(id);
        Integer code = tag != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = tag != null ? "这是你要查询的标签吧" : "请不要让我白找一趟";
        return new TypeTagResult(code, msg, tag);
    }

    @PostMapping
    @ResponseBody
    public TypeTagResult add(@RequestBody Tag tag) {
        boolean flag = tagService.add(tag);
        Integer code = flag ? Code.ADD_SUC : Code.ADD_ERR;
        String msg = flag ? "添加成功" : "添加失败";
        return new TypeTagResult(code, msg, flag);
    }

    @PutMapping
    @ResponseBody
    public TypeTagResult update(@RequestBody Tag tag) {
        boolean flag = tagService.update(tag);
        Integer code = flag ? Code.UPDATE_SUC : Code.UPDATE_ERR;
        String msg = flag ? "修改成功" : "修改失败";
        return new TypeTagResult(code, msg, flag);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public TypeTagResult delete(@PathVariable int id) {
        articleService.delete(tagService.getArticleIdsByTagId(id));
        boolean flag = tagService.delete(id);
        Integer code = flag ? Code.DELETE_SUC : Code.DELETE_ERR;
        String msg = flag ? "删除成功" : "删除失败";
        return new TypeTagResult(code, msg, flag);
    }
}
