package com.qhc.mybatis3.dao;

import com.qhc.mybatis3.beans.User;
import com.qhc.mybatis3.config.SqlSessionFactoryResource;
import com.qhc.mybatis3.mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDao {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryResource.getSqlSessionFactory();

    public User selectOneById(int id){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectOneById(id);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }

    public List<User> getAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = mapper.getAll();
        sqlSession.commit();
        sqlSession.close();
        return all;
    }

    public int update (User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int updateCount = mapper.update(user);
        sqlSession.commit();
        sqlSession.close();
        return updateCount;
    }

    public int insert(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int insertCount = mapper.insert(user);
        sqlSession.commit();
        sqlSession.close();
        return insertCount;
    }

    public int delete(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int deleteCount = mapper.delete(user);
        sqlSession.commit();
        sqlSession.close();
        return deleteCount;
    }

    public List<User> getUserLikeName(String name){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //这里为了拼凑模糊查询的参数，在Mybatis的SQL里不能识别%
        List<User> list = mapper.getByName("%"+name+"%");
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

}
