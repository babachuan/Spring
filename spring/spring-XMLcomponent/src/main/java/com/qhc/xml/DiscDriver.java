package com.qhc.xml;

public class DiscDriver {
    private Disc disc;

    //借助构造器注入bean
    public DiscDriver(Disc disc) {
        this.disc = disc;
    }

    public void paly(){
        System.out.println(disc.getContent());
    }

    public DiscDriver() {
    }
}
