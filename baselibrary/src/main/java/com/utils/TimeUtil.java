package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	/**
	 * 格式化时间
	 * @param milliseconds
	 * @return "HH:mm:ss"类型的时间
	 */
	public static String formatTimeHMS(long milliseconds){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date(milliseconds);
		return sdf.format(date);
	}
	/**
	 * 格式化时间
	 * @param milliseconds
	 * @return "mm:ss"类型的时间
	 */
	public static String formatTimeMS(long milliseconds){
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
		Date date = new Date(milliseconds);
		return sdf.format(date);
	}
	/**
	 * 格式化时间
	 * @param milliseconds
	 * @return "yyyy:MM:dd"类型的时间
	 */
	public static String formatTimeYMD(long milliseconds){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(milliseconds);
		return sdf.format(date);
	}
	/**
	 * 格式化时间
	 * @param milliseconds
	 * @return "HH:mm:ss yyyy:MM:dd"类型的时间
	 */
	public static String formatTimeYMDandHMS(long milliseconds){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(milliseconds);
		return sdf.format(date);
	}

    /**
     * 格式化时间
     * @param milliseconds
     * @return "HH:mm yyyy:MM:dd"类型的时间
     */
    public static String formatTimeYMDandHM(long milliseconds){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(milliseconds*1000);
        return sdf.format(date);
    }
	
	/**
	 * 格式化时间
	 * @param milliseconds
	 * @return "HH:mm:ss yyyy:MM:dd"类型的时间
	 */
	public static String formatTimeYMDHS(String milliseconds){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(milliseconds);
		return sdf.format(date);
	}

}
