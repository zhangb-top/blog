package com.promise.service;

import com.promise.pojo.Type;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TypeService {
    List<Type> selectAll();

    Type selectById(int id);

    boolean add(Type type);

    boolean update(Type type);

    boolean delete(int id);

    // 根据type的id查询其下所有文章的id
    Integer[] getArticleIdsByTypeId(int id);
}
