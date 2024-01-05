package com.solvd.laba.parsers;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.sql.Date;

public class MyAdapter extends XmlAdapter<String, Date> {
    @Override
    public Date unmarshal(String string) throws Exception {
        return null;
    }

    @Override
    public String marshal(Date date) throws Exception {
        return null;
    }
}
