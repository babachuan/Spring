package cn.org.geneplus.common;

import java.util.List;

/**
 * @Author:quhaichuan
 * @Date:2025/6/12 14:42
 */
public interface BaseDao<T> {
    void save(T entity);
    void update(T entity);
    void deleteById(Integer id);
    T findById(Integer id);
    List<T> findAll();
}
