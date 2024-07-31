package com.ruoyi.view.util;

import java.io.File;

/**
 * @author pjh
 * @created 2024/7/12
 */
public class StringUtil {

    public static String PathsJoin(String... paths) {
        StringBuilder sb = new StringBuilder();
        for (String path : paths) {
            if (path.startsWith(File.separator)) {
                sb.append(path.substring(1));
            }else if(path.endsWith(File.separator)){
                sb.append(path, 0, path.length() - 1);
            }else{
                sb.append(path);
            }
            sb.append(File.separator);
        }
        return sb.substring(0, sb.length() - 1);
    }

}
