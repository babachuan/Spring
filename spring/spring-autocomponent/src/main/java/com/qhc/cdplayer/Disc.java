package com.qhc.cdplayer;

import org.springframework.stereotype.Component;

@Component
public class Disc {
    //This is a Disc and it can be played by DiscDriver
    private String content="This is a beautiful music";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
