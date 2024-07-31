package com.ruoyi.view.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 读取项目相关配置
 * 
 * @author fuce
 */
@Data
@Component
public class V2Config {

	/**
	 * 请求url
	 */
	@Value("${v2.file.publishUrl}")
	private String httpurl;

	/**
	 * 存储方式
	 */
	@Value("${v2.file.storageType}")
	private String storageType;

	/**
	 * 存储路径
	 */
	@Value("${v2.file.local.path}")
	private String fileurl;
	
	/**
	 * 默认文件格式
	 */
	@Value("${v2.file.defaultType}")
	private String defaultFormat;

	//TODO oss minio
	
	
}
