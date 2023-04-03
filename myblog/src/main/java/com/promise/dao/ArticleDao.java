package com.promise.dao;

import com.promise.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ArticleDao {
    @Select("select id, title, description, context, author, create_time, status, type_id, tag_id, " +
            "love, watch,(select name from tag where id=tag_id ) as tagName from article order by " +
            "create_time desc;")
    @ResultMap("articleResultDao")
    List<Article> selectAll();

    @Update("update article set title = #{title},description = #{description},context = #{context}," +
            "author = #{author}, create_time = #{createTime},status = #{status},type_id = #{typeId}, " +
            "tag_id=#{tagId},love = #{love},watch = #{watch} where id = #{id};")
    int update(Article article);

    @Update("update article set status = #{status} where id = #{id};")
    int updateStatusById(@Param("status") Integer status, @Param("id") Integer id);

    @Insert("insert into article values (null, #{title},#{description},#{context},#{author}, " +
            "#{createTime},#{status},#{typeId},#{tagId},#{love}, #{watch});")
    int add(Article article);

    @Select("select id, title, description, context, author, create_time, status, type_id, tag_id, " +
            "love, watch,(select name from tag where id=tag_id) as tagName from article where id = " +
            "#{id};")
    @ResultMap("articleResultDao")
    Article selectById(Integer id);

    int delete(@Param("ids") Integer[] ids);

    @ResultMap("articleResultDao")
    List<Article> selectByPage(@Param("start") Integer start,
                               @Param("pageSize") Integer pageSize, @Param("flag") Boolean flag);

    int totalArticles(Boolean flag);

    List<Article> selectDim(@Param("author") String author,
                            @Param("status") Integer status, @Param("startDate") Date startDate, @Param(
            "endDate") Date endDate, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    int totalSelectDim(@Param("author") String author,
                       @Param("status") Integer status, @Param("startDate") Date startDate, @Param(
            "endDate") Date endDate);

    @Select("select sum(love) from article;")
    int loveCount();

    @Select("select sum(watch) from article;")
    int watchCount();

    List<Article> getArticlesByTypeId(@Param("typeId") Integer typeId, @Param("start") Integer start,
                                      @Param("pageSize") Integer pageSize, @Param("flag") Boolean flag);

    List<Article> getArticlesByTagId(@Param("tagId") Integer tagId, @Param("start") Integer start,
                                     @Param("pageSize") Integer pageSize, @Param("flag") Boolean flag);

    int totalGetArticlesByTypeId(@Param("typeId") Integer typeId, @Param("flag") Boolean flag);

    int totalGetArticlesByTagId(@Param("tagId") Integer tagId, @Param("flag") Boolean flag);

    /*
        归档查询所有年月
     */
    @Select("select extract(year_month from create_time) from article group by extract(year_month from" +
            " create_time)")
    List<String> selectYearMonthFromArticles();

    /*
        归档查询所有年月下的标题文章
     */
    @Select("select id,title from article where extract(year_month from create_time)=#{date} and " +
            "status=1;")
    List<Article> selectTitleByYearMonth(String date);
}
