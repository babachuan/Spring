package com.qhc.springboot2.service;

import com.qhc.springboot2.beans.City;
import com.qhc.springboot2.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CityService {

    @Autowired
    CityDao cityDao;

    public List<City> findAll() {

        return cityDao.findAll();
    }

    public City findByCity(String CityName) {
        return cityDao.findByCity(CityName);
    }

    public int countByCity(String cityName) {
        return cityDao.countByCity(cityName);
    }


    public City findWithQuery(int id, String cityName) {
        return cityDao.findWithQury(id,cityName);
    }

    @Transactional
    public int updateCityYourName(int cityid,String resultName) {
        return cityDao.updateCityYourName(cityid,resultName);
    }

    public List<City> findOriginSQL(String provinceId) {
        return cityDao.findOriginSQL(provinceId);
    }
}
