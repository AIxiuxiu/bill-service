package com.dabby.tax.repojo;

import com.dabby.tax.enums.RetCode;
import com.dabby.tax.util.CommonTool;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName RespInfo
 * @Description TODO
 * @Author Aaronchen
 * @Date 2019/3/27 19:22
 **/
@Getter
@Setter
public class RespInfo {
    private Integer code;
    private String msg;
    private Object data;
    private Long timestamp;


    public void setRet(RetCode ret) {
        setRet(ret, true);
    }

    public void setRet(RetCode ret, boolean cover) {
        setRet(ret, ret.getMsg(), cover);
    }

    public void setRet(RetCode ret, String msg) {
        setRet(ret, msg, true);
    }


    public void setRet(RetCode ret, String msg, boolean cover) {
        if (cover) {
            setCode(ret.getCode());
            setMsg(msg);
        } else {
            if (this.code == null) {
                setCode(ret.getCode());
            }
            if (!CommonTool.isEmpty(this.msg)) {
                setMsg(msg);
            }
        }
        setTimestamp(System.currentTimeMillis());
    }
}
