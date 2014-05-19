package com.ebiz.bp_oracle.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;

public class DateTools {

	public static Date getFirstSatudayOfMonth(int year, int month) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(year, month, 1);

		cal.setFirstDayOfWeek(Calendar.SATURDAY);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		return cal.getTime();
	}

	public static Date getFirstSatudayOfWeek(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.SATURDAY);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		return cal.getTime();
	}

	public static Date changeString2Date(String parm) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (parm == null) {
			return null;
		}
		try {
			date = (Date) fmt.parse(parm);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

	/**
	 * @desc 格式化日期为字符串
	 */
	public static String getStringDate(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = fmt.format(date);
		return dateStr;
	}

	public static Date changeString2DateTime(String parm) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		if (parm == null) {
			return null;
		}
		try {
			date = (Date) fmt.parse(parm);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

	/**
	 * @desc 当月第一天
	 */
	public static Date getFirstDayOfCurMonth() {
		Calendar now = Calendar.getInstance();
		int curtYear = now.get(Calendar.YEAR);
		int curtMonth = now.get(Calendar.MONTH);
		now.set(curtYear, curtMonth, 1);
		Date firstDayOfCurMonth = now.getTime();
		return firstDayOfCurMonth;
	}

	/**
	 * @desc 当月最后一天
	 */
	public static Date getLastDayOfCurMonth() {
		Calendar now = Calendar.getInstance();
		int curtYear = now.get(Calendar.YEAR);
		int curtMonth = now.get(Calendar.MONTH);
		now.set(curtYear, curtMonth + 1, 1);
		Date firstDayOfCurMonth = now.getTime();
		return DateUtils.addDays(firstDayOfCurMonth, -1);
	}
}
