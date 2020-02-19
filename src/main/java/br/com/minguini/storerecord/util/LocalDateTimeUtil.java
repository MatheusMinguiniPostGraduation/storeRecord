package br.com.minguini.storerecord.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {

    public static LocalDateTime getLocalDateTimeToLookUp(String dateString, String hour) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if(dateString != null){
            String dateWithHour = dateString.concat(hour);
            LocalDateTime dateTime = LocalDateTime.parse(dateWithHour, formatter);
            return dateTime;
        }

        return null;
    }

    public static String getFormattedDate(LocalDateTime date){

        if(date != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            return date.format(formatter);
        }

        return null;
    }

    public static String getFormattedTime(LocalDateTime date){
        if(date != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            return date.format(formatter);
        }

        return null;
    }


}
