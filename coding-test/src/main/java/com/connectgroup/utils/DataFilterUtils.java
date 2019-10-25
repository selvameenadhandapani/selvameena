package com.connectgroup.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.connectgroup.config.DataFilterConstants;

public class DataFilterUtils {
	
	
	public static String getFormattedDate(String dataPart) {
		Date date = new Date(Long.valueOf(dataPart) * 1000L);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		return format.format(date);
	}
}