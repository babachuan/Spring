package cn.org.geneplus.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Author:quhaichuan
 * @Date:2025/6/12 14:44
 */

public abstract class BaseDaoImpl<T> implements BaseDao<T>{

    /**
     * 1、简要解释报错原因： @Autowired标注的成员变量必须在Spring管理的Bean中（如@Component、@Service等），而当前类是抽象类，不会被Spring实例化，因此无法注入。
     * 2、修复建议： 将 BaseDaoImpl 类标记为 @Component 或其子类注解（如 @Repository），或者确保其子类是 Spring Bean，并通过构造函数或
     * 3、最终修复方法：通过让子类UserDaoImpl继承，然后在子类UserDaoImpl上添加@Repository注解交给Spring管理
     */
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public final Class<T> entityClass;

    public BaseDaoImpl() {
        // 获取泛型的实际类型
        this.entityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    // 指定表名的抽象方法由子类实现
    protected abstract String getTableName();

    @Override
    public void save(T entity) {
        throw new UnsupportedOperationException("请在子类中实现save()");

    }

    @Override
    public void update(T entity) {
        throw new UnsupportedOperationException("请在子类中实现 update()");

    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM " + getTableName() + " WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public T findById(Integer id) {
        String sql = "SELECT * FROM "+ getTableName() + " WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(entityClass),id);
    }

    @Override
    public List<T> findAll() {
        String sql = "SELECT * FROM " + getTableName();
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(entityClass));
    }
}
