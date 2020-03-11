package com.qhc.springboot2.beans;

import javax.persistence.*;

@Entity
@Table(name = "g_cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return city;
    }

    public void setName(String name) {
        this.city = name;
    }
}
