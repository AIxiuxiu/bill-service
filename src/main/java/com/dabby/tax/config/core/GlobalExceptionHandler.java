package com.dabby.tax.config.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.dabby.tax.enums.RetCode;
import com.dabby.tax.exception.GlobalException;
import com.dabby.tax.repojo.RespInfo;
import com.dabby.tax.util.CommonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author Aaronchen
 * @Date 2019/3/27 19:01
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理所有控制类（Controller）的异常
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public String allExceptionHandler(HttpServletRequest request,Exception e) throws Exception{
        if (CommonTool.isEmpty(WebApiInfo.getFlag())) {
            WebApiInfo.set(request.getServletPath());
        }
        RespInfo respInfo = new RespInfo();
        if (e instanceof GlobalException) {
            GlobalException tmp = (GlobalException) e;
            respInfo.setRet(tmp.getRetCode(), tmp.getErrMsg());
        } else if (e instanceof BindException) {
            BindException tmp = (BindException) e;
            if (tmp.getFieldError() != null) {
                respInfo.setRet(RetCode.ParamError, tmp.getFieldError().getDefaultMessage());
            } else {
                respInfo.setRet(RetCode.ParamError);
            }
            log.warn("参数异常：[{}], [{}]", getParamString(request), getParamJson(request));
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException tmp = (MethodArgumentNotValidException) e;

            if (tmp.getBindingResult().getFieldError() != null) {
                respInfo.setRet(RetCode.ParamError, tmp.getBindingResult().getFieldError().getDefaultMessage());
            } else {
                respInfo.setRet(RetCode.ParamError);
            }
            log.warn("参数异常：[{}], [{}]", getParamString(request), getParamJson(request));
        } else if (e instanceof HttpMessageNotReadableException) {
            log.error("exception= {}", e.getMessage(), e);
            respInfo.setRet(RetCode.ParamFormatError);
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            respInfo.setRet(RetCode.ParamError, "参数错误：" + e.getMessage());
        } else if (e instanceof JSONException) {
            log.error("exception= {}", e.getMessage(), e);
            respInfo.setRet(RetCode.ParamFormatError);
        } else {
            respInfo.setRet(RetCode.Failed);
            log.error("exception= {}", e.getMessage(), e);
        }
        log.info("response data = {}", respInfo);
        WebApiInfo.remove();
        return JSON.toJSONString(respInfo);
    }

    private static String getParamString(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        StringBuilder sb = new StringBuilder();
        String reqBody = null;
        try {
            for (Map.Entry<String, String[]> e : map.entrySet()) {
                sb.append(e.getKey()).append("=");
                String[] value = e.getValue();
                if (value != null && value.length == 1) {
                    sb.append(value[0]).append("&");
                } else {
                    sb.append(Arrays.toString(value)).append("\n");
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            reqBody = URLDecoder.decode(sb.toString(), "UTF-8");
        } catch (Exception e) {
            log.error("参数解析异常", e);
        }
        return reqBody;
    }

    private static String getParamJson(HttpServletRequest request) {
        try (InputStreamReader isr = new InputStreamReader(request.getInputStream());
             BufferedReader br = new BufferedReader(isr)) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("参数解析异常", e);
            return null;
        }
    }
}
