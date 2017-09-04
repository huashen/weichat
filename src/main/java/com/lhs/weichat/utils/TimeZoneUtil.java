package com.lhs.weichat.utils;

import java.util.Date;
import java.util.TimeZone;

/**
 * TimeZoneUtil
 *
 * @author longhuashen
 * @since 17/9/4
 */
public class TimeZoneUtil {

    /**
     * 判断用户的设备时区是否为东八区（中国）
     * @return
     */
    public static boolean isInEasternEightZones() {
        boolean defaultVaule = true;
        if (TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08"))
            defaultVaule = true;
        else
            defaultVaule = false;
        return defaultVaule;
    }

    /**
     * 根据不同时区，转换时间
     * @return
     */
    public static Date transformTime(Date date, TimeZone oldZone, TimeZone newZone) {
        Date finalDate = null;
        if (date != null) {
            int timeOffset = oldZone.getOffset(date.getTime())
                    - newZone.getOffset(date.getTime());
            finalDate = new Date(date.getTime() - timeOffset);
        }
        return finalDate;
    }
}
