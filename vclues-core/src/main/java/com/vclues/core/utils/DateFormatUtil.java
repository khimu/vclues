package com.vclues.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateFormatUtil {
	private final static Logger logger = LoggerFactory.getLogger(DateFormatUtil.class);

	/*
	 * Calendar.DAY_OF_YEAR = 6
	 * Calendar.MONTH = 2
	 * 
	 */
	public static String moveBy(int unit, int calendarDateType) {
		SimpleDateFormat dayDate = new SimpleDateFormat("yyyyMMdd");

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(calendarDateType, unit);
		
		String result = dayDate.format(c.getTime());
		logger.info("Move by calendarDateType: " + calendarDateType + " with frequency: " + unit + " result is " + result);
		return result;
	}

}
