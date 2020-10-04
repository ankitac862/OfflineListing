package com.anki.offlinelisting.local.convertor;

import java.util.Date;

import androidx.room.TypeConverter;
/**
 * Covert date object so that we can save in DB
 */
public class DateConverter {
    @TypeConverter
    public static Date toDate(Long dateLong){
        return dateLong == null ? null: new Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date){
        return date == null ? null : date.getTime();
    }
}
