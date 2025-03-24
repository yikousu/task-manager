package com.hac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hac.dto.TaskDTO;
import com.hac.entity.Task;

import java.util.List;

/**
 * 任务服务接口
 */
public interface TaskService extends IService<Task> {
    
    /**
     * 添加任务
     *
     * @param taskDTO 任务DTO
     * @return 是否成功
     */
    boolean addTask(TaskDTO taskDTO);
    
    /**
     * 更新任务状态
     *
     * @param id     任务ID
     * @param status 状态
     * @return 是否成功
     */
    boolean updateTaskStatus(Long id, String status);
    
    /**
     * 删除任务
     *
     * @param id 任务ID
     * @return 是否成功
     */
    boolean deleteTask(Long id);
    
    /**
     * 获取任务列表
     *
     * @param sortBy 排序字段：priority, deadline
     * @return 任务列表
     */
    List<TaskDTO> getTaskList(String sortBy);
}