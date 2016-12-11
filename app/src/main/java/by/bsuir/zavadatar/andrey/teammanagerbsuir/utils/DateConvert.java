package by.bsuir.zavadatar.andrey.teammanagerbsuir.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrey on 28.11.2016.
 */

public class DateConvert {

    public static String getDateAndTimeToString(Date date){
        String result = "default";

        if(date != null)
            result = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(date);

        return result;
    }

    public static Date getDateAndTime(String date) throws ParseException {

        if(date.length() > 8) {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("HH:mm:ss dd.MM.yyyy");
            return format.parse(date);
        } else {
            return null;
        }
    }

    public static String getDateToString(Date date){
        String result = "";

        if(date != null)
            result = new SimpleDateFormat("dd.MM.yyyy").format(date);

        return result;
    }

    public static Date getDate(String date) throws ParseException {

        if(date.length() > 8) {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd.MM.yyyy");
            return format.parse(date);
        } else {
            return null;
        }
    }

}
