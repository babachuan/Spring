package com.qhc.mix.xml2xml;
import org.springframework.beans.factory.annotation.Autowired;

public class XML2XMLDiscDriver {

    private XML2XMLDisc xml2XMLDisc;

    public XML2XMLDiscDriver(XML2XMLDisc xml2XMLDisc) {
        this.xml2XMLDisc = xml2XMLDisc;
    }

    public void paly() {
        System.out.println(xml2XMLDisc.getContent());
    }

    public XML2XMLDiscDriver() {
    }

}
