package com.dabby.tax;

import com.dabby.tax.utilservice.redisservcie.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillServiceApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    public void contextLoads() {
        String key = "springbootdddd111111";
        redisService.set(key,"ok",30000l);
        System.out.println("v="+redisService.get(key));
    }

}
