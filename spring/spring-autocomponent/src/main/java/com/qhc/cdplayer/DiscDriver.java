package com.qhc.cdplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscDriver {
    //Autowired annotation will instantiate and inject disc object in to DiscDriver.
    @Autowired
    private Disc disc;

    public void paly(){
        System.out.println(disc.getContent());
    }
}
