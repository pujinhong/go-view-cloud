package com.ruoyi.view.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GoviewProjectVo implements Serializable {
    private static final long serialVersionUID = 1L;

	
	//(value = "主键")
	private String id;
	
	//(value = "项目名称")
	private String projectName;
	
	//(value = "项目状态[-1未发布,1发布]")
	private Integer state;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	//(value = "创建时间")
	private Date createTime;
	
	//(value = "创建人id")
	private String createUserId;
	
	//(value = "删除状态[1删除,-1未删除]")
	private Integer isDelete;
	
	//(value = "首页图片")
	private String indexImage;
	
	//(value = "项目介绍")
	private String remarks;
	
	private String content;


}