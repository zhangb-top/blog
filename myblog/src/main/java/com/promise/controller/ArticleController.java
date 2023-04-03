package com.promise.controller;

import com.promise.controller.result.ArticleResult;
import com.promise.pojo.Article;
import com.promise.service.ArticleService;
import com.promise.util.ReplaceMdPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /*
        查询所有文章
     */
    @GetMapping
    @ResponseBody
    public ArticleResult selectAll() {
        List<Article> articles = articleService.selectAll();
        Integer code = articles != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = articles != null ? "文章都在这里啦" : "一篇文章也没有啦";
        return new ArticleResult(code, msg, articles);
    }

    /*
        修改文章
     */
    @PutMapping
    @ResponseBody
    public ArticleResult update(@RequestBody Article article) {
        article.setContext(ReplaceMdPic.replace(article.getContext()));
        boolean flag = articleService.update(article);
        Integer code = flag ? Code.UPDATE_SUC : Code.UPDATE_ERR;
        String msg = flag ? "编辑成功" : "编辑失败";
        return new ArticleResult(code, msg, flag);
    }

    /*
        根据id修改文章状态
     */
    @PutMapping("/{id}/{status}")
    @ResponseBody
    public ArticleResult updateStatusById(@PathVariable Integer id, @PathVariable Integer status) {
        boolean flag = articleService.updateStatusById(status, id);
        Integer code = flag ? Code.UPDATE_SUC : Code.UPDATE_ERR;
        String msg = flag ? "修改成功" : "修改失败";
        return new ArticleResult(code, msg, flag);
    }

    /*
        添加文章
     */
    @PostMapping
    @ResponseBody
    public ArticleResult add(@RequestBody Article article) {
        article.setContext(ReplaceMdPic.replace(article.getContext()));
        boolean flag = articleService.add(article);
        Integer code = flag ? Code.ADD_SUC : Code.ADD_ERR;
        String msg = flag ? "发布成功" : "发布失败";
        return new ArticleResult(code, msg, flag);
    }

    /*
        根据id查找文章
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ArticleResult selectById(@PathVariable Integer id) {
        Article article = articleService.selectById(id);
        Integer code = article != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = article != null ? "文章在这里啦" : "文章不存在啦";
        return new ArticleResult(code, msg, article);
    }

    /*
        删除文章
     */
    @DeleteMapping("/{ids}")
    @ResponseBody
    public ArticleResult delete(@PathVariable Integer[] ids) {
        boolean flag = articleService.delete(ids);
        Integer code = flag ? Code.DELETE_SUC : Code.DELETE_ERR;
        String msg = flag ? "删除成功" : "删除失败";
        return new ArticleResult(code, msg, flag);
    }

    /*
       分页查询
    */
    @GetMapping("/{currentPage}/{pageSize}")
    @ResponseBody
    public ArticleResult selectByPage(@PathVariable Integer currentPage,
                                      @PathVariable Integer pageSize, Boolean flag) {
        int total = articleService.totalArticles(flag);
        List<Article> articles = articleService.
                selectByPage((currentPage - 1) * pageSize, pageSize, flag);
        Integer code = articles != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = articles != null ? "文章都在这里啦" : "一篇文章也没有啦";
        return new ArticleResult(code, msg, articles, total);
    }

    /*
        模糊查询
     */
    @GetMapping("/selectDim")
    @ResponseBody
    public ArticleResult selectDim(Integer status,
                                   String author,
                                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                   Integer currentPage, Integer pageSize) {
        int total = articleService.totalSelectDim(author, status, startDate, endDate);
        if (author != null) {
            author = "%" + new String(author.getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8) + "%";
        }
        List<Article> articles = articleService.
                selectDim(author, status,
                        startDate,
                        endDate, (currentPage - 1) * pageSize, pageSize);
        Integer code = articles != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = articles != null ? "文章都在这里啦" : "一篇文章也没有啦";
        return new ArticleResult(code, msg, articles, total);
    }

    /*
        查询总喜欢量和总浏览量
     */
    @GetMapping("/counts")
    @ResponseBody
    public ArticleResult totalLoveWatch(Boolean flag) {
        Map<String, Object> counts = new HashMap<>();
        int articleCount = articleService.totalArticles(flag);
        int loveCount = articleService.loveCount();
        int watchCount = articleService.watchCount();
        counts.put("articleCount", articleCount);
        counts.put("loveCount", loveCount);
        counts.put("watchCount", watchCount);
        return new ArticleResult(Code.SELECT_SUC, "统计成功", counts);
    }

    /*
        根据分类id查询文章
     */
    @GetMapping("/type/{currentPage}/{pageSize}")
    @ResponseBody
    public ArticleResult getArticlesByTypeId(Integer typeId, Boolean flag,
                                             @PathVariable Integer currentPage,
                                             @PathVariable Integer pageSize) {
        int total = articleService.totalGetArticlesByTypeId(typeId, flag);
        List<Article> articles = articleService.
                getArticlesByTypeId(typeId, (currentPage - 1) * pageSize, pageSize, flag);
        Integer code = articles != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = articles != null ? "文章都在这里啦" : "一篇文章也没有啦";
        return new ArticleResult(code, msg, articles, total);
    }

    @GetMapping("/tag/{currentPage}/{pageSize}")
    @ResponseBody
    public ArticleResult getArticlesByTagId(Integer tagId, Boolean flag,
                                            @PathVariable Integer currentPage,
                                            @PathVariable Integer pageSize) {
        int total = articleService.totalGetArticlesByTagId(tagId, flag);
        List<Article> articles = articleService.
                getArticlesByTagId(tagId, (currentPage - 1) * pageSize, pageSize, flag);
        Integer code = articles != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = articles != null ? "文章都在这里啦" : "一篇文章也没有啦";
        return new ArticleResult(code, msg, articles, total);
    }

    /*
        归档查询所有年月
     */
    @GetMapping("/yearmonth")
    @ResponseBody
    public ArticleResult selectYearMonthFromArticles() {
        List<String> dates = articleService.selectYearMonthFromArticles();
        Integer code = dates != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = dates != null ? "都在这里啦" : "一条数据也没有啦";
        return new ArticleResult(code, msg, dates);
    }

    /*
        归档查询所有年月下的标题文章
     */
    @GetMapping("/titles")
    @ResponseBody
    public ArticleResult selectTitleByYearMonth(String date) {
        List<Article> titles = articleService.selectTitleByYearMonth(date);
        Integer code = titles != null ? Code.SELECT_SUC : Code.SELECT_ERR;
        String msg = titles != null ? "都在这里啦" : "一条数据也没有啦";
        return new ArticleResult(code, msg, titles);
    }

    /*
        单独查询文章总数量
     */
    @GetMapping("/total")
    @ResponseBody
    public ArticleResult selectTotalArticles(Boolean flag) {
        return new ArticleResult(Code.SELECT_SUC, "", null, articleService.totalArticles(flag));
    }
}
