package com.tokopedia.testproject.problems.news.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class DateFormater {

    public static String formatDefault = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static String formatDate = "d MMMM yyyy";

    public static String format(String format, String input) {
        SimpleDateFormat from = new SimpleDateFormat(formatDefault, Locale.getDefault());
        SimpleDateFormat to = new SimpleDateFormat(format, Locale.getDefault());
        from.setTimeZone(TimeZone.getTimeZone("UTC"));
        to.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
        try {
            String output = to.format(from.parse(input));
            return output;
        } catch (ParseException e) {
            e.printStackTrace();
            return input;
        }
    }

    public static String format(String formatFrom, String formatTo, String input) {
        SimpleDateFormat from = new SimpleDateFormat(formatFrom, Locale.getDefault());
        SimpleDateFormat to = new SimpleDateFormat(formatTo, Locale.getDefault());
        to.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
        try {
            String output = to.format(from.parse(input));
            return output;
        } catch (ParseException e) {
            e.printStackTrace();
            return input;
        }
    }

    public static Date parseToDate(String input) {
        SimpleDateFormat from = new SimpleDateFormat(formatDefault, Locale.getDefault());
        Date date;
        try {
            date = from.parse(input);
            return date;
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Date simpleDate(String input) {
        String dateOnly = format(formatDate, input);
        SimpleDateFormat from = new SimpleDateFormat(formatDate, Locale.getDefault());
        from.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
        try {
            return from.parse(dateOnly);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}
