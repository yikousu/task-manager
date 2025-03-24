package com.hac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hac.dto.TaskDTO;
import com.hac.entity.Task;
import com.hac.enums.PriorityEnum;
import com.hac.enums.StatusEnum;
import com.hac.mapper.TaskMapper;
import com.hac.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 任务服务实现类
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Override
    public boolean addTask(TaskDTO taskDTO) {
        Task task = new Task();
        BeanUtils.copyProperties(taskDTO, task);
        task.setDeadline(taskDTO.getDeadline().plusHours(8));
        return save(task);
    }

    @Override
    public boolean updateTaskStatus(Long id, String status) {
        Task task = getById(id);
        if (task == null) {
            return false;
        }
        task.setStatus(status);
        return updateById(task);
    }

    @Override
    public boolean deleteTask(Long id) {
        return removeById(id);
    }

    @Override
    public List<TaskDTO> getTaskList(String sortBy) {
        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper<>();
        List<Task> taskList = list(queryWrapper);

        List<TaskDTO> taskDTOList = taskList.stream().map(task -> {
            TaskDTO taskDTO = new TaskDTO();
            BeanUtils.copyProperties(task, taskDTO);
            // 设置优先级描述
            taskDTO.setPriorityDesc(PriorityEnum.getDescByCode(task.getPriority()));
            // 设置状态描述
            taskDTO.setStatusDesc(StatusEnum.getDescByCode(task.getStatus()));
            return taskDTO;
        }).collect(Collectors.toList());

        // 根据排序字段排序
        if ("priority".equals(sortBy)) {
            // 按优先级排序：HIGH > MEDIUM > LOW
            taskDTOList.sort((o1, o2) -> {
                int priority1 = getPriorityOrder(o1.getPriority());
                int priority2 = getPriorityOrder(o2.getPriority());
                return priority2 - priority1; // 降序
            });
        } else if ("deadline".equals(sortBy)) {
            // 按截止日期排序：早 -> 晚
            taskDTOList.sort(Comparator.comparing(TaskDTO::getDeadline,
                    Comparator.nullsLast(Comparator.naturalOrder())));
        }

        return taskDTOList;
    }

    /**
     * 获取优先级顺序
     *
     * @param priority 优先级
     * @return 顺序值
     */
    private int getPriorityOrder(String priority) {
        if ("HIGH".equals(priority)) {
            return 3;
        } else if ("MEDIUM".equals(priority)) {
            return 2;
        } else if ("LOW".equals(priority)) {
            return 1;
        }
        return 0;
    }
}