package com.promise.service;

import com.promise.pojo.Article;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public interface ArticleService {

    /*
        查询所有文章
     */
    List<Article> selectAll();

    /*
        修改文章
     */
    boolean update(Article article);

    /*
        根据id，修改文章状态
     */
    boolean updateStatusById(Integer status, Integer id);

    /*
        添加文章
     */
    boolean add(Article article);

    /*
        根据id查找文章
     */
    Article selectById(Integer id);

    /*
        删除文章
     */
    boolean delete(Integer[] ids);

    /*
        分页查询
     */
    List<Article> selectByPage(Integer start, Integer pageSize, Boolean flag);

    /*
         文章总条数
     */
    int totalArticles(Boolean flag);

    /*
        模糊查询
     */
    List<Article> selectDim(String author, Integer status, Date startDate, Date endDate, Integer start
            , Integer pageSize);

    /*
        模糊查询的总数
     */
    int totalSelectDim(String author, Integer status, Date startDate, Date endDate);

    /*
        查询总喜欢量和总浏览量
     */
    int loveCount();

    int watchCount();

    /*
        根据分类查询
     */
    List<Article> getArticlesByTypeId(Integer typeId, Integer start, Integer pageSize, Boolean flag);

    /*
        根据分类id查询得到的文章总数
     */
    int totalGetArticlesByTypeId(Integer typeId, Boolean flag);

    /*
        根据标签id查询得到的文章总数
     */
    List<Article> getArticlesByTagId(Integer tagId, Integer start, Integer pageSize, Boolean flag);


    /*
        根据标签id查询得到的文章总数
     */
    int totalGetArticlesByTagId(Integer tagId, Boolean flag);

    /*
        归档查询所有年月
     */
    List<String> selectYearMonthFromArticles();

    /*
        归档查询所有年月下的标题文章
     */
    List<Article> selectTitleByYearMonth(String date);
}
