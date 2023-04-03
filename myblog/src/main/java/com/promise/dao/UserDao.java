package com.promise.dao;

import com.promise.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select password from tb_user where username = #{username};")
    String selectByUsername(String username);

    @Insert("insert into tb_user values (#{id},#{username},#{password});")
    void add(User user);
}
