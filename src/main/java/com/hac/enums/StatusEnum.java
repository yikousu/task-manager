package com.hac.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务状态枚举
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    
    PENDING("PENDING", "待完成"),
    COMPLETED("COMPLETED", "已完成");
    
    private final String code;
    private final String desc;
    
    /**
     * 根据code获取描述
     */
    public static String getDescByCode(String code) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getDesc();
            }
        }
        return "";
    }
}