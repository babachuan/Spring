package cn.org.geneplus.dao;

import cn.org.geneplus.common.BaseDao;
import cn.org.geneplus.common.BaseDaoImpl;
import cn.org.geneplus.pojo.User;

/**
 * @Author:quhaichuan
 * @Date:2025/6/12 15:00
 */
public interface UserDao extends BaseDao<User> {

    // 这里只定义假设除公用方法之外的方法
    User findByName(String name);


}
