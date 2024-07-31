package com.ruoyi.view.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author pjh
 * @created 2024/7/12
 */
public class DateUtil {

    /**
     * 获取当前时间并转为 "yyyy-MM-dd HH:mm:ss"的格式字符串
     * @return
     */
    public static String now(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 获取当前时间并转为 "yyyyMMddHHmmss"的格式字符串
     * @param date
     * @return
     */
    public static String YYYYMMDDHHMMSS(Date date){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }

    /**
     * 获取当前时间并转为 "yyyy-MM-dd HH:mm:ss"的格式字符串
     * @param date
     * @return
     */
    public static String YYYY_MM_DD_HH_MM_SS(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
