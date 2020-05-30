package com.xiaoxi.study.common.constants;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author liuteng
 */
public enum SexEnum {

    /**
     * man
     */
    MAN(1),
    /**
     * woman
     */
    WOMAN(0);

    SexEnum(int code) {
        this.code = code;
    }

    @EnumValue
    private int code;

    public int getCode() {
        return code;
    }

    public static SexEnum getSexEnum(int code){
        for (SexEnum sexEnum : values()){
            if (sexEnum.getCode() == code){
                return sexEnum;
            }
        }
        return null;
    }
}
