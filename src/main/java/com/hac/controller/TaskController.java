package com.hac.controller;

import com.hac.dto.TaskDTO;
import com.hac.enums.StatusEnum;
import com.hac.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService taskService;
    
    /**
     * 添加任务
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addTask(@RequestBody TaskDTO taskDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = taskService.addTask(taskDTO);
            result.put("success", success);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("添加任务失败", e);
            result.put("success", false);
            result.put("message", "添加任务失败: " + e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    /**
     * 标记任务为完成
     */
    @PutMapping("/{id}/complete")
    public ResponseEntity<Map<String, Object>> completeTask(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = taskService.updateTaskStatus(id, StatusEnum.COMPLETED.getCode());
            result.put("success", success);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("标记任务完成失败", e);
            result.put("success", false);
            result.put("message", "标记任务完成失败: " + e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    /**
     * 删除任务
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteTask(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = taskService.deleteTask(id);
            result.put("success", success);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("删除任务失败", e);
            result.put("success", false);
            result.put("message", "删除任务失败: " + e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    /**
     * 获取任务列表
     *
     * @param sortBy 排序字段：priority, deadline
     */
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTaskList(@RequestParam(required = false, defaultValue = "priority") String sortBy) {
        try {
            List<TaskDTO> taskList = taskService.getTaskList(sortBy);
            return ResponseEntity.ok(taskList);
        } catch (Exception e) {
            log.error("获取任务列表失败", e);
            return ResponseEntity.badRequest().body(null);
        }
    }
}