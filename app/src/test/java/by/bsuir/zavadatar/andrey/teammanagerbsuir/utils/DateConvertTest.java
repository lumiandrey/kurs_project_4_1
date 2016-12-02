package by.bsuir.zavadatar.andrey.teammanagerbsuir.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Andrey on 02.12.2016.
 */
public class DateConvertTest {

    private String dateString;

    @Before
    public void setUp(){

        dateString = "15:24:30 12.10.2016";
    }

    @Test
    public void getDate() throws Exception {

        Date date = DateConvert.getDate(dateString);

        String dateEquals = DateConvert.getDateToString(date);

        assertTrue(dateEquals.equals(dateString));

    }

    @Test
    public void getNullData() throws Exception {

        String date = DateConvert.getDateToString(null);

        assertTrue(date.equals(""));

    }

    @Test
    public void getShortDataToString() throws Exception {

        Date date = DateConvert.getDate("15:24:30");

        assertTrue(null == date);

    }

}