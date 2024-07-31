package com.ruoyi.view.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author pjh
 * @created 2024/7/2
 */
@Data
public class BentoSqlVo {
    String name;
    String title;
    Object data;
}
