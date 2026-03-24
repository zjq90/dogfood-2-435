package cn.edu.nuc.shop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * IP时间戳工具类
 * 用于生成唯一的文件名，避免文件名冲突
 */
public class IPTimeStamp {

    private static final Logger logger = LoggerFactory.getLogger(IPTimeStamp.class);

    private SimpleDateFormat sdf = null;
    private String ip = null;

    /**
     * 默认构造方法
     */
    public IPTimeStamp() {
    }

    /**
     * 带IP参数的构造方法
     * @param ip IP地址
     */
    public IPTimeStamp(String ip) {
        this.ip = ip;
        logger.debug("IPTimeStamp工具类初始化，IP: {}", ip);
    }

    /**
     * 生成IP+时间戳+随机数的唯一字符串
     * @return 唯一字符串
     */
    public String getIPTimeRand() {
        StringBuffer buf = new StringBuffer();
        if (this.ip != null) {
            String s[] = this.ip.split("\\.");
            for (int i = 0; i < s.length; i++) {
                buf.append(this.addZero(s[i], 3));
            }
        }
        buf.append(this.getTimeStamp());
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            buf.append(r.nextInt(10));
        }
        String result = buf.toString();
        logger.debug("生成唯一文件名: {}", result);
        return result;
    }

    /**
     * 获取时间戳字符串（格式：yyyyMMddHHmmssSSS）
     * @return 时间戳字符串
     */
    public String getTimeStamp() {
        this.sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = this.sdf.format(new Date());
        logger.debug("生成时间戳: {}", timestamp);
        return timestamp;
    }

    /**
     * 字符串补零
     * @param str 原始字符串
     * @param len 目标长度
     * @return 补零后的字符串
     */
    private String addZero(String str, int len) {
        StringBuffer s = new StringBuffer();
        s.append(str);
        while (s.length() < len) {
            s.insert(0, "0");
        }
        return s.toString();
    }
}
