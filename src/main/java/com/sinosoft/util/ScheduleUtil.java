package com.sinosoft.util;

import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ScheduleUtil {

    /** 日志对象 */
    Logger LOGGER = LoggerFactory.getLogger(ScheduleUtil.class);

    public static TriggerKey getTriggerKey(String triggerName, String groupNname) {

        return TriggerKey.triggerKey(triggerName, groupNname);
    }



}
