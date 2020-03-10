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

    public String add(City city) {
        try {
            cityDao.save(city);
            return "保存成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "保存失败";
        }
    }
}
