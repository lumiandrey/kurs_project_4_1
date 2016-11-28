package by.bsuir.zavadatar.andrey.teammanagerbsuir.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrey on 28.11.2016.
 */

public class DateConvert {

    public static String getDate(Date date){
        return new SimpleDateFormat("HH:mm:ss dd MM yyyy").format(date);
    }

    public static Date getDate(String date) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("HH:mm:ss dd.mm.yyyy");
        return format.parse(date);
    }
}
