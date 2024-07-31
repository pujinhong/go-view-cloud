package com.ruoyi.view.entity.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 数据源配置信息vo类
 */
@Data
public class DataSourceConfiguration {

    public DataSourceConfiguration() {

    }

    public DataSourceConfiguration(Object dbSource) {
        if (dbSource != null) {
            BeanUtils.copyProperties(dbSource, this);
        }
    }

    /**
     * id
     */
    private String id;
    /**
     * 数据源编码
     */
    private String code;
    /**
     * 数据库类型
     */
    private String dbType;
    /**
     * 驱动类
     */
    private String dbDriver;
    /**
     * 数据源地址
     */
    private String dbUrl;

    /**
     * 数据库名称
     */
    private java.lang.String dbName;

    /**
     * 用户名
     */
    private String dbUsername;
    /**
     * 密码
     */
    private String dbPassword;

}