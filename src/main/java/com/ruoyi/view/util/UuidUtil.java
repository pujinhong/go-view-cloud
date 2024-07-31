package com.ruoyi.view.util;

/**
 * @author pjh
 * @created 2024/7/12
 */
public class UuidUtil {

    public static String snow(){
        return SnowflakeIdWorker.getUUID();
    }

}
