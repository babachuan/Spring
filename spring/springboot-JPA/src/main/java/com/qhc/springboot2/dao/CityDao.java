package com.qhc.springboot2.dao;

import com.qhc.springboot2.beans.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City,Integer> {

}
