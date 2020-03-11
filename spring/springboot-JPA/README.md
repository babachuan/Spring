# 1）SpringBoot与Spring Data JPA整合

引入Pom

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
```

这里要使用JPA连接mysql数据库。

**cityDao层**

```
package com.qhc.springboot2.dao;

import com.qhc.springboot2.beans.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City,Integer> {

}
```

使用JPA时，dao层实现`JpaRepository<City,Integer>`接口，同时注意自己本身就是个`interface`

---

**Service层使用**

```
package com.qhc.springboot2.service;

import com.qhc.springboot2.beans.City;
import com.qhc.springboot2.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityService {

    @Autowired
    CityDao cityDao;

    public List<City> findAll() {

        return cityDao.findAll();
    }

}
```

`findAll()`是JPA里封装的默认方法。

使用`http://localhost:8088/demo/list`可以访问到对应的结果

