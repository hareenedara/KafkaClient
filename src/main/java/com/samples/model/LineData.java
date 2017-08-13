package com.samples.model;

import java.io.Serializable;

/**
 * Created by edara on 8/12/17.
 */
public class LineData implements Serializable{
    private String line;

    public LineData(String msg) {
        this.line = msg;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
    @Override
    public String toString(){
        return line;
    }
}
