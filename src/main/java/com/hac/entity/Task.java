package com.hac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 任务实体类
 */
@Data
@TableName("task")
public class Task implements Serializable {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 任务标题
     */
    private String title;
    
    /**
     * 任务描述
     */
    private String description;
    
    /**
     * 优先级：HIGH, MEDIUM, LOW
     */
    private String priority;
    
    /**
     * 截止日期
     */
    private LocalDateTime deadline;
    
    /**
     * 任务状态：PENDING, COMPLETED
     */
    private String status;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}