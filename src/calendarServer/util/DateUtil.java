/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarServer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sreis
 */
public class DateUtil {
    
    private static final SimpleDateFormat week = new SimpleDateFormat("W");
    private static final SimpleDateFormat day = new SimpleDateFormat("yyyyD");
    private static final SimpleDateFormat time = new SimpleDateFormat("Hms");
    private static final SimpleDateFormat general = new SimpleDateFormat("yyyyDHms");
    
    public static int compareWeek(Date d1, Date d2){
        return week.format(d1).compareTo(week.format(d2));
    }
    
    public static int compareDay(Date d1, Date d2){
        return day.format(d1).compareTo(day.format(d2));
    }
    
    public static int compareTime(Date d1, Date d2){
        return time.format(d1).compareTo(time.format(d2));
    }
    
    public static int compare(Date d1, Date d2){
        return general.format(d1).compareTo(general.format(d2));
    }
    
    public static Date min(Date d1, Date d2){
        return d1.before(d2) ? d1 : d2;
    }
    
    public static Date max(Date d1, Date d2){
        return d1.before(d2) ? d2 : d1;
    }
}
