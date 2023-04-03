package com.promise.controller;

import com.promise.controller.result.TypeTagResult;
import com.promise.pojo.Type;
import com.promise.service.ArticleService;
import com.promise.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private ArticleService articleService;

    @GetMapping
    @ResponseBody
    TypeTagResult selectAll() {
        List<Type> types = typeService.selectAll();
        Integer code = types != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = types != null ? "文章分类都在这啦" : "一个分类也木有啊";
        return new TypeTagResult(code, msg, types);
    }

    @GetMapping("/{id}")
    @ResponseBody
    TypeTagResult selectById(@PathVariable int id) {
        Type type = typeService.selectById(id);
        Integer code = type != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = type != null ? "这是你要查询的分类吧" : "请不要让我白找一趟";
        return new TypeTagResult(code, msg, type);
    }

    @PostMapping
    @ResponseBody
    TypeTagResult add(@RequestBody Type type) {
        boolean flag = typeService.add(type);
        Integer code = flag ? Code.ADD_SUC : Code.ADD_ERR;
        String msg = flag ? "添加成功" : "添加失败";
        return new TypeTagResult(code, msg, flag);
    }

    @PutMapping
    @ResponseBody
    TypeTagResult update(@RequestBody Type type) {
        boolean flag = typeService.update(type);
        Integer code = flag ? Code.UPDATE_SUC : Code.UPDATE_ERR;
        String msg = flag ? "修改成功" : "修改失败";
        return new TypeTagResult(code, msg, flag);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    TypeTagResult delete(@PathVariable int id) {
        articleService.delete(typeService.getArticleIdsByTypeId(id));
        boolean flag = typeService.delete(id);
        Integer code = flag ? Code.DELETE_SUC : Code.DELETE_ERR;
        String msg = flag ? "删除成功" : "删除失败";
        return new TypeTagResult(code, msg, flag);
    }
}
