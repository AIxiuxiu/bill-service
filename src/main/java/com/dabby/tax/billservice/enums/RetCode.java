package com.dabby.tax.billservice.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName RetCode
 * @Description 返回信息枚举类
 * @Author Aaronchen
 * @Date 2019/3/27 17:20
 **/
@Getter
public enum RetCode {

    // 请求成功
    SUCEESS(0,"成功"),
    Failed(1, "失败"),
    PARAMM_NULL(001,"参数为空"),
    ParamError(002,"参数错误"),
    ParamFormatError(003, "参数格式错误")
    ;


    private int code;
    private String msg;


    RetCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
