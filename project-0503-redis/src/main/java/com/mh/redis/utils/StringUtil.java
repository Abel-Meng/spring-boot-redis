package com.mh.redis.utils;

import org.springframework.stereotype.Component;

/**
 * @author 孟浩
 * @date 2018/5/14  14:13.
 */
@Component
public class StringUtil {

    static final int DEFAULT_LENGTH = 3;
    /**
     * 得到3位的序列号,长度不足3位,前面补0
     *
     * @param seq
     * @return
     */
    public static String getSequence(long seq) {
        String str = String.valueOf(seq);
        int len = str.length();
        if (len >= DEFAULT_LENGTH) {// 取决于业务规模
            return str;
        }
        int rest = DEFAULT_LENGTH - len;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rest; i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }
}
