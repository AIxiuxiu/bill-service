package com.dabby.tax.billservice.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @ClassName CommonTool
 * @Description TODO
 * @Author Aaronchen
 * @Date 2019/3/27 19:21
 **/
@Slf4j
public class CommonTool {
    public final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE };

    public static int sizeOfInt(int x) {
        for (int i = 0;; i++)
            if (x <= sizeTable[i])
                return i + 1;
    }
    /**
     * 将文件转为byte[]
     * @param filePath 文件路径
     * @return
     */
    public static byte[] getBytes(String filePath){
        File file = new File(filePath);
        ByteArrayOutputStream out = null;
        try {
            FileInputStream in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) != -1) {

                out.write(b, 0, b.length);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage(),e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage(),e);
        }
        byte[] s = out.toByteArray();
        return s;

    }

    /**
     * 检查参数是否为空或null,是则返回true
     * @param content
     * @return 为空或null返回true
     */
    public static boolean isEmpty(String content){
        boolean flag = false;
        if(null == content || "".equals(content.trim())){
            flag = true;
        }
        return flag;
    }
}
