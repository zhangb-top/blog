package com.promise.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    // 主键id
    private Integer id;
    // 标题
    private String title;
    // 描述
    private String description;
    // 内容
    private String context;
    // 作者
    private String author;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    // 博客状态 1 展示，0不展示
    private Integer status;
    // 分类id
    private Integer typeId;
    // 标签id
    private Integer tagId;

    private String tagName;
    // 点赞次数
    private Integer love;
    // 浏览次数
    private Integer watch;
}
