package com.qhc.mix.java2xml;

public class Java2xmlDiscDriver {

    private Java2xmlDisc java2xmlDisc;

    public Java2xmlDiscDriver(Java2xmlDisc java2xmlDisc) {
        this.java2xmlDisc = java2xmlDisc;
    }

    public void paly() {
        System.out.println(java2xmlDisc.getContent());
    }

    public Java2xmlDiscDriver() {
    }

    public Java2xmlDisc getJava2xmlDisc() {
        return java2xmlDisc;
    }
}
