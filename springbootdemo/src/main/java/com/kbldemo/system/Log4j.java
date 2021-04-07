package com.kbldemo.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kbl on 2016/12/1.
 */
public class Log4j {
    private static final Logger logger = LoggerFactory.getLogger(new Exception().getStackTrace()[1].getClassName());
    public static void showLogInfo(String logInfo){
        logger.info(logInfo);
    }
    public static void showLogInfo(String logInfo,Throwable t){
        logger.info(logInfo,t);
    }
}