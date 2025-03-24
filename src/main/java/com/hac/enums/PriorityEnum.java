package com.hac.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务优先级枚举
 */
@Getter
@AllArgsConstructor
public enum PriorityEnum {
    
    HIGH("HIGH", "高"),
    MEDIUM("MEDIUM", "中"),
    LOW("LOW", "低");
    
    private final String code;
    private final String desc;
    
    /**
     * 根据code获取描述
     */
    public static String getDescByCode(String code) {
        for (PriorityEnum priorityEnum : PriorityEnum.values()) {
            if (priorityEnum.getCode().equals(code)) {
                return priorityEnum.getDesc();
            }
        }
        return "";
    }
}