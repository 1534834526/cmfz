package com.baizhi.conf;

import java.text.ParseException;

public class TimeConvertMinute {
    public static String getStringDate(int seconds) throws ParseException {
        int temp=0;
        StringBuffer sb=new StringBuffer();
        temp = seconds/3600;
        sb.append((temp<10)?"0"+temp+":":""+temp+":");

        temp=seconds%3600/60;
        sb.append((temp<10)?"0"+temp+":":""+temp+":");

        temp=seconds%3600%60;
        sb.append((temp<10)?"0"+temp:""+temp);

       /* SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
      Date date = dateFormat.parse(sb.toString());
      */
        return sb.toString();
    }
}
