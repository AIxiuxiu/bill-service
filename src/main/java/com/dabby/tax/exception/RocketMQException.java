package com.dabby.tax.exception;

import com.dabby.tax.enums.RetCode;

/**
 * @ClassName RocketMQException
 * @Description RocketMQ异常类
 * @Author Aaronchen
 * @Date 2019/3/27 18:03
 **/
public class RocketMQException extends GlobalException{
    /**
     * 无参构造函数
     */
    public RocketMQException() {
        super();
    }
    public RocketMQException(Throwable e) {
        super(e);
    }
    public RocketMQException(RetCode retCode) {
        super(retCode);
    }

    public RocketMQException(RetCode retCode, String ... errMsg) {
        super(retCode, errMsg);
    }
    /**
     * 封装异常
     * @param retCode
     * @param errMsg
     * @param isTransfer 是否转换异常信息，如果为false,则直接使用errMsg信息
     */
    public RocketMQException(RetCode retCode, String errMsg,Boolean isTransfer) {
        super(retCode, errMsg,isTransfer);
    }

    public RocketMQException(RetCode retCode, Throwable cause,String ... errMsg) {
        super(retCode,cause, errMsg);
    }
}
