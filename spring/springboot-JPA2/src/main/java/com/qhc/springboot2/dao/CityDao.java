package com.qhc.springboot2.dao;

import com.qhc.springboot2.beans.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityDao extends JpaRepository<City,Integer> {
//这里定义的方法如果转换成SQL语句如下：SELECT * FROM table WHERE city="xxx";
    City findByCity(String cityName);
    //查询指定名称的数量:select count(city0_.id) as col_0_0_ from g_cities city0_ where city0_.city=?
    int countByCity(String cityName);

    //自定义查询方法，findWithQury这个并不是支持的语法，就是为了说明自定义生效
//    @Query("select a from City a where a.id=?1 and a.city=?2")
//    City findWithQury(int id, String cityName);

    //上面默认根据位置指定参数，下面对参数进行重命名，查询结果与上面一样
    @Query("select a from City a where a.id= :id and a.city= :cityName")
    City findWithQury(@Param("id") int myid, @Param("cityName") String mycityName);

    //更新表字段，根据城市id进行更新,在使用update/delete时需要在service类或方法上添加事务@Transactional (org.springframework.transaction.annotation.Transactional)
    @Modifying
    @Query("update City c set c.city= ?2 where c.id= ?1")
    int updateCityYourName(int cityid,String resultName);

    //使用原生SQL查询,虽然字段provinceid不再实体类里，但是仍然可以查询到结果，也是单表查询
    @Query(value = "select * from g_cities where provinceid= ?1",nativeQuery = true)
    List<City> findOriginSQL(String provinceId);
}
