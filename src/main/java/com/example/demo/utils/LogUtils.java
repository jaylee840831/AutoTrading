package com.example.demo.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * 本地日誌類別
 *
 *
 */
public class LogUtils {


    /**
     * 獲取業務日誌logger
     *
     * @return
     */
    public static Logger getBussinessLogger() {
        return LogManager.getLogger(Logs.BUSSINESS.getCategory());
    }

    /**
     * 獲取平臺日誌logger
     *
     * @return
     */
    public static Logger getPlatformLogger() {
        return LogManager.getLogger(Logs.PLATFORM.getCategory());
    }

    /**
     * 獲取資料庫日誌logger
     *
     * @return
     */
    public static Logger getDBLogger() {
        return LogManager.getLogger(Logs.DB.getCategory());
    }


    /**
     * 獲取異常日誌logger
     *
     * @return
     */
    public static Logger getExceptionLogger() {
        return LogManager.getLogger(Logs.EXCEPTION.getCategory());
    }


}
