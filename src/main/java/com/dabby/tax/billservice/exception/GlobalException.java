package com.dabby.tax.billservice.exception;

import com.dabby.tax.billservice.enums.RetCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName GlobalException
 * @Description 全局异常信息处理
 * @Author Aaronchen
 * @Date 2019/3/27 17:17
 **/
@Getter
@Setter
public class GlobalException extends RuntimeException{

    /**
     * 错误编码枚举
     */
    protected RetCode retCode;
    /**
     * 错误信息
     */
    protected String errMsg;
    /**
     * 无参构造函数
     */
    public GlobalException() {
        super();
    }
    public GlobalException(Throwable e) {
        super(e);
    }

    public GlobalException(RetCode retCode, String... errMsg) {
        super(retCode.getMsg());
        this.retCode = retCode;
        setErrMsg(errMsg,true);
    }

    public GlobalException(RetCode retCode, String errMsg,Boolean isTransfer) {
        super(errMsg);
        this.retCode = retCode;
        setErrMsg(new String[]{errMsg},isTransfer);
    }

    /**
     * 构造函数
     *
     * @param cause 异常
     */
    public GlobalException(RetCode retCode, Throwable cause, String... errMsg) {
        super(retCode.getCode() + retCode.getMsg(), cause);
        this.retCode = retCode;
        setErrMsg(errMsg,true);
    }

    public void setErrMsg(String[] errMsg,Boolean isTransfer) {

        if (null != errMsg &&errMsg.length>0) {
            if(retCode.getMsg().contains("%s") && isTransfer){
                this.errMsg = String.format(retCode.getMsg(), errMsg);
            }else{
                StringBuffer sf = new StringBuffer();
                for (String msg : errMsg) {
                    sf.append(msg+";");
                }
                this.errMsg = sf.toString();
            }
        }else{
            this.errMsg = retCode.getMsg();
        }

    }

}
