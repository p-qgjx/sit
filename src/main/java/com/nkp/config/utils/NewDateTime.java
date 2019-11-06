package com.nkp.config.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewDateTime {
    public static Date getDateTime(String str) throws ParseException {
        Date date = new Date();
        SimpleDateFormat temp = new SimpleDateFormat(str);
        String date2 = temp.format(date);

        return temp.parse(date2);
    }
}
