package com.dabby.tax.controller;

import com.alibaba.fastjson.JSONObject;
import com.dabby.tax.repojo.RespInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Aaronchen
 * @Date 2019/3/27 19:34
 **/
@RestController
public class TestController {

    @RequestMapping("/test")
    public RespInfo test(@RequestBody  String test){
        RespInfo respInfo = new RespInfo();
        JSONObject json = JSONObject.parseObject(test);
        respInfo.setCode(0);
        return respInfo;
    }
}
