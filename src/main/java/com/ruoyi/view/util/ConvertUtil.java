package com.ruoyi.view.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author pjh
 * @created 2024/7/4
 */
public class ConvertUtil {

    public static boolean isDateString(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateFormat.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public static Date parseDateString(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + value);
        }
    }

    /**
     * 转换为List<String>数组<br>
     * @return 结果
     */
    public static List<String> toListStrArray(String str) {
        String[] stringArray = toStrArray(str);
        List<String> stringB = Arrays.asList(stringArray);
        return stringB;
    }



    /**
     * 转换为String数组<br>
     * @return 结果
     */
    public static String[] toStrArray(String str) {
        return toStrArray(",", str);
    }

    /**
     * 转换为String数组<br>
     *
     * @param split 分隔符
     * @param split 被转换的值
     * @return 结果
     */
    public static String[] toStrArray(String split, String str) {
        return str.split(split);
    }


}
