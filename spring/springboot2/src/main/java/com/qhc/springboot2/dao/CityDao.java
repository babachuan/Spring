package com.qhc.springboot2.dao;

import com.qhc.springboot2.beans.City;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CityDao {

    static Map<Integer, City> dataMap =  Collections.synchronizedMap(new HashMap<Integer, City>());
    public List<City> findAll() {
        return new ArrayList<>(dataMap.values());
    }

    public void save(City city) throws Exception {
        City data = dataMap.get(city.getId());
        if(data != null){
            throw new Exception("数据已经存在");
        }else{
            dataMap.put(city.getId(),city);
            System.out.println("====数据添加成功");
        }
    }
}
