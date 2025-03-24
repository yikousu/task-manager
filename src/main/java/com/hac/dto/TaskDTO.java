package com.hac.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 任务数据传输对象
 */
@Data
public class TaskDTO implements Serializable {
    
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
     * 优先级描述
     */
    private String priorityDesc;
    
    /**
     * 截止日期
     */
    private LocalDateTime deadline;
    
    /**
     * 任务状态：PENDING, COMPLETED
     */
    private String status;
    
    /**
     * 状态描述
     */
    private String statusDesc;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}