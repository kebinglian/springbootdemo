package com.kbldemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author kbl
 * @since 2021-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String flag;

    @TableField("userId")
    private String userId;

    @TableField("userName")
    private String userName;

    @TableField("clientIp")
    private String clientIp;

    @TableField("operationMethod")
    private String operationMethod;

    @TableField("operationValue")
    private String operationValue;

    private String operation;

    @TableField("operationTime")
    private Date operationTime;


}
