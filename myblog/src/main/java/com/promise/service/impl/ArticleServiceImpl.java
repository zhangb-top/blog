package com.promise.service.impl;

import com.promise.dao.ArticleDao;
import com.promise.pojo.Article;
import com.promise.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    /*
        查询所有文章
     */
    @Override
    public List<Article> selectAll() {
        return articleDao.selectAll();
    }

    /*
        修改文章
     */
    @Override
    public boolean update(Article article) {
        return articleDao.update(article) > 0;
    }

    /*
        根据id修改文章状态
     */
    @Override
    public boolean updateStatusById(Integer status, Integer id) {
        return articleDao.updateStatusById(status, id) > 0;
    }

    /*
        添加文章
     */
    @Override
    public boolean add(Article article) {
        return articleDao.add(article) > 0;
    }

    /*
        根据id查找文章
     */
    @Override
    public Article selectById(Integer id) {
        return articleDao.selectById(id);
    }

    /*
        删除文章
     */
    @Override
    public boolean delete(Integer[] ids) {
        return articleDao.delete(ids) > 0;
    }

    /*
        分页查询
     */
    @Override
    public List<Article> selectByPage(Integer start, Integer pageSize, Boolean flag) {
        return articleDao.selectByPage(start, pageSize, flag);
    }

    @Override
    public int totalArticles(Boolean flag) {
        return articleDao.totalArticles(flag);
    }

    @Override
    public List<Article> selectDim(String author, Integer status, Date startDate, Date endDate,
                                   Integer start, Integer pageSize) {
        return articleDao.selectDim(author, status, startDate, endDate, start, pageSize);
    }

    @Override
    public int totalSelectDim(String author, Integer status, Date startDate, Date endDate) {
        return articleDao.totalSelectDim(author, status, startDate, endDate);
    }

    @Override
    public int loveCount() {
        return articleDao.loveCount();
    }

    @Override
    public int watchCount() {
        return articleDao.watchCount();
    }

    /*
        根据分类id查询
     */
    @Override
    public List<Article> getArticlesByTypeId(Integer typeId, Integer start, Integer pageSize,
                                             Boolean flag) {
        return articleDao.getArticlesByTypeId(typeId, start, pageSize, flag);
    }

    /*
        根据分类查询得到的文章总数
     */
    @Override
    public int totalGetArticlesByTypeId(Integer typeId, Boolean flag) {
        return articleDao.totalGetArticlesByTypeId(typeId, flag);
    }

    @Override
    public List<Article> getArticlesByTagId(Integer tagId, Integer start, Integer pageSize,
                                            Boolean flag) {
        return articleDao.getArticlesByTagId(tagId, start, pageSize, flag);
    }

    @Override
    public int totalGetArticlesByTagId(Integer tagId, Boolean flag) {
        return articleDao.totalGetArticlesByTagId(tagId, flag);
    }

    @Override
    public List<String> selectYearMonthFromArticles() {
        return articleDao.selectYearMonthFromArticles();
    }

    @Override
    public List<Article> selectTitleByYearMonth(String date) {
        return articleDao.selectTitleByYearMonth(date);
    }
}
