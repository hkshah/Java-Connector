package com.rapidvalue.master.common.util;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

/**
 * Util class for defining common functions.
 * 
 * @author eldho.alias
 * @since Apr 12, 2016
 */
public class Util {

	private static final Logger LOG = Logger.getLogger(Util.class);

	/**
	 * Private constructor to hide the implicit public one.
	 */
	private Util() {

	}

	/**
	 * Convert BigDecimal to Integer.
	 * 
	 * @param objArray
	 *            {@link BigDecimal} value
	 * @return {@link Integer}
	 */
	public static Integer convertBigDecimalToInt(Object objArray) {

		Integer value = null;
		if (objArray != null) {
			value = ((BigDecimal) objArray).intValue();
		}

		return value;
	}

	public static BigDecimal convertIntegerToBigDecimal(final Integer intValue) {

		BigDecimal value = null;
		if (intValue != null) {
			value = BigDecimal.valueOf(intValue);
		}

		return value;
	}

	public static BigDecimal convertDoubleToBigDecimal(final Double longValue) {

		BigDecimal value = null;
		if (longValue != null) {
			value = BigDecimal.valueOf(longValue);
		}

		return value;
	}

	/**
	 * Convert BigDecimal to Double.
	 * 
	 * @param objArray
	 *            {@link BigDecimal} value
	 * @return {@link Double}
	 */
	public static double convertBigDecimalToDouble(Object objArray) {

		double value = 0.0;
		if (objArray != null) {
			value = ((BigDecimal) objArray).doubleValue();
		}

		return value;
	}

	/**
	 * Convert Time stamp to Date
	 * 
	 * @param timestamp
	 *            {@link Timestamp}
	 * @return {@link Date}
	 */
	public static Date convertTimestampToDate(Timestamp timestamp) {

		Date date = null;
		if (timestamp != null) {
			date = new Date(timestamp.getTime());
		}

		return date;
	}

	public static Date formatDate(String dateString, String currentFormat) {

		SimpleDateFormat format = new SimpleDateFormat(currentFormat);
		Date date = null;
		try {
			date = new Date(format.parse(dateString).getTime());
		} catch (ParseException e) {
			LOG.error("Failed to parse date::" + e);
		}

		return date;
	}

	public static String formatDateToString(java.util.Date date,
			String currentFormat) {

		String dateString = null;
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(currentFormat);
			dateString = df.format(date);
		}
		return dateString;
	}

	public static String convertClobToString(Clob clob) throws SQLException,
			IOException {
		String clobString = null;
		if (clob != null) {
			StringBuffer sb = new StringBuffer((int) clob.length());
			Reader r = clob.getCharacterStream();
			char[] cbuf = new char[2048];
			int n = 0;
			while ((n = r.read(cbuf, 0, cbuf.length)) != -1) {
				if (n > 0) {
					sb.append(cbuf, 0, n);
				}
			}
			clobString = sb.toString();
		}
		return clobString;
	}
}
