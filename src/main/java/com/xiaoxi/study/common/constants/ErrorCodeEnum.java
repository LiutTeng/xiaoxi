package com.xiaoxi.study.common.constants;

/**
 * @author liuteng
 */
public enum ErrorCodeEnum {

    /** ID生成失败 */
    ID_GENERATE_FAILURE("10010", "ID生成失败");

    private String code;

    private String desc;

    ErrorCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
