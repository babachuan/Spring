# 1）SpringBoot与Spring Data JPA整合

## 自定义接口

```
public interface CityDao extends JpaRepository<City,Integer> {
    //这里定义的方法如果转换成SQL语句如下：SELECT * FROM table WHERE city="xxx";
    City findByCity(String cityName);
}
```

这里重点是方法的名字`findByCity`,在驼峰命名法的基础上自动解析，其他参加下表：

![jpa自定义接口1](https://img-blog.csdn.net/20180605094935545?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xIUzE5OTQwMjAz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

![jpa自定义接口2](https://img-blog.csdn.net/20180605094949426?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xIUzE5OTQwMjAz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

详细的说明参见[官方文档](https://docs.spring.io/spring-data/jpa/docs/2.2.5.RELEASE/reference/html/#jpa.repositories)



## 自定义查询

```
public interface CityDao extends JpaRepository<City,Integer> {
    //自定义查询方法，findWithQury这个并不是支持的语法，就是为了说明自定义生效
     @Query("select a from City a where a.id=?1 and a.city=?2")
     City findWithQury(int id, String cityName);
}
```

通过`@Query`注解自定义查询，参数使用位置确定

参见[官方文档](https://docs.spring.io/spring-data/jpa/docs/2.2.5.RELEASE/reference/html/#jpa.query-methods.at-query)

## 更新

```
    //更新表字段，根据城市id进行更新,在使用update/delete时需要在service类或方法上添加事务@Transactional (org.springframework.transaction.annotation.Transactional)
    @Modifying
    @Query("update City c set c.city= ?2 where c.id= ?1")
    int updateCityYourName(int cityid,String resultName);
```

注意`@Modifying`和`@Query`注解的联合使用，同事注意更新时使用的参数。

参见[官方文档](https://docs.spring.io/spring-data/jpa/docs/2.2.5.RELEASE/reference/html/#jpa.modifying-queries)

## 使用原生SQL

```
public interface CityDao extends JpaRepository<City,Integer> {
	//使用原生SQL查询,虽然字段provinceid不再实体类里，但是仍然可以查询到结果，也是单表查询
    @Query(value = "select * from g_cities where provinceid= ?1",nativeQuery = true)
    List<City> findOriginSQL(String provinceId);
    }
```

通过在注解`@Query`中设置`nativeQuery`属性为true即可。

参见[官方文档](https://docs.spring.io/spring-data/jpa/docs/2.2.5.RELEASE/reference/html/#jpa.query-methods.at-query)



