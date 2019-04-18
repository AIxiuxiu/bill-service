package com.dabby.tax.config.core;
//import brave.Tracer;
import lombok.Data;

import java.util.UUID;

/**
 * @ClassName WebApiInfo
 * @Description TODO
 * @Author Aaronchen
 * @Date 2019/3/27 18:47
 **/
public class WebApiInfo {
    private final static ThreadLocal<ApiInfo> LOCAL = new ThreadLocal<>();

    public static String getFlag() {
        return LOCAL.get() == null ? null : "METHOD=" + LOCAL.get().getMethod() + " | " + LOCAL.get().getUuid() + " :";
    }

    public static void set(String method) {
        LOCAL.set(new ApiInfo(method));
    }

    public static void setFlag(String flag) {
        try {
            flag = flag.replaceAll("METHOD=", "").replaceAll(" :", "");
            String[] str =  flag.split(" \\| ");
            LOCAL.set(new ApiInfo(str[1], str[0]));
        } catch (Exception e) {
            //who cares
        }
    }
    public static void remove() {
        LOCAL.remove();
    }

    @Data
    public static class ApiInfo {
        private String uuid;
        private String method;

        public ApiInfo(String method) {
//            this.uuid = ApplicationContextHolder.getBean(Tracer.class).currentSpan().context().toString();
            this.uuid = UUID.randomUUID().toString();
            this.method = method;
        }
        public ApiInfo(String uuid, String method) {
            this.uuid = uuid;
            this.method = method;
        }
        public ApiInfo() {
        }
    }
}
