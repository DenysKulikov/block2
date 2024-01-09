package com.solvd.laba.parsers;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MyAdapter extends XmlAdapter<String, Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date unmarshal(String string) throws Exception {
        java.util.Date utilDate = dateFormat.parse(string);
        return new Date(utilDate.getTime());
    }

    @Override
    public String marshal(Date date) {
        return dateFormat.format(date);
    }
}
