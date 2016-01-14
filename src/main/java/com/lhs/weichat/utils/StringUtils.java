package com.lhs.weichat.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Date;

/**
 * StringUtils
 *
 * @author longhuashen
 * @since 15/11/9
 */
public class StringUtils {
    /**
     * 获取年龄
     * @param birthday
     * @return
     */
    public static int getAge(Date birthday) {
        if (birthday == null) {
            return 0;
        }

        DateTime nowTime = new DateTime();
        return Days.daysBetween(new DateTime(birthday), nowTime).getDays();
    }
}
