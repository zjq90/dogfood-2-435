package com.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * IP时间戳工具类
 * 用于生成唯一的文件名
 * 
 * @author shop
 */
public class IPTimeStamp {

    private SimpleDateFormat sdf;
    private String ip;

    public IPTimeStamp() {
    }

    public IPTimeStamp(String ip) {
        this.ip = ip;
    }

    public String getIPTimeRand() {
        StringBuilder buf = new StringBuilder();
        if (this.ip != null) {
            String[] s = this.ip.split("\\.");
            for (int i = 0; i < s.length; i++) {
                buf.append(this.addZero(s[i], 3));
            }
        }
        buf.append(this.getTimeStamp());
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            buf.append(r.nextInt(10));
        }
        return buf.toString();
    }

    public String getTimeStamp() {
        this.sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return this.sdf.format(new Date());
    }

    private String addZero(String str, int len) {
        StringBuilder s = new StringBuilder();
        s.append(str);
        while (s.length() < len) {
            s.insert(0, "0");
        }
        return s.toString();
    }
}
