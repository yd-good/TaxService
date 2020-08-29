/*
信息:
*/
package yd.itcast.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetoString {
    public static String dateCovertoString(Date birthday){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return format.format(birthday);
    }
}
