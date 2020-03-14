package com.qhc.mybatis3.mappers;

import com.qhc.mybatis3.beans.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where id= #{id}")
    public User selectOneById(int id);

    @Select("select * from user")
    public List<User> getAll();

    @Update("update user set passWord=#{passWord} where userName= #{userName}")
    public int update(User user);

    @Insert("insert into user (userName,passWord) values (#{userName},#{passWord})")
    public int insert(User user);

    @Delete("delete from user where userName= #{userName}")
    public int delete(User user);

    @Select("select * from user where userName like #{name}")
    public List<User> getByName(String name);
}
