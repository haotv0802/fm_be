package fm.utils;

import fm.common.FmConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HaoHo on 5/15/2020
 */
public class FmDateUtils {
    private static final SimpleDateFormat sdfDateTime = new SimpleDateFormat(FmConstants.DATE_TIME_PATTERN);
    private static final SimpleDateFormat sdfDate = new SimpleDateFormat(FmConstants.DATE_PATTERN);

    private static final Logger logger = LogManager.getLogger(FmDateUtils.class);

    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        }
        return sdfDateTime.format(date);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return sdfDate.format(date);
    }

    public static String formatDateWithPattern(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date parseDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }

        try {
            return sdfDate.parse(date);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static Date parseDateTime(String dateTime) {
        if (StringUtils.isEmpty(dateTime)) {
            return null;
        }

        try {
            return sdfDateTime.parse(dateTime);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static Date parseDateWithPattern(String dateTime, String pattern) {
        if (StringUtils.isEmpty(dateTime)) {
            return null;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(dateTime);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static Date getLastDateOfNextYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DATE, 31);

        return calendar.getTime();

    }

    /**
     * Notes: java.sql.Date stores just only Date (not time)
     *
     * @param date
     * @return
     */
    public static java.util.Date toUtilDate(java.sql.Date date) {
        return date != null ? new java.util.Date(date.getTime()) : null;
    }

    /**
     * Notes: Timestamp stores Date & Time
     *
     * @param dateTime
     * @return
     */
    public static java.util.Date toUtilDate(Timestamp dateTime) {
        return dateTime != null ? new java.util.Date(dateTime.getTime()) : null;
    }

    public static java.sql.Date toSqlDate(java.util.Date date) {
        return date != null ? new java.sql.Date(date.getTime()) : null;
    }

    public static java.sql.Timestamp toSqlDateTime(java.util.Date date) {
        return date != null ? new java.sql.Timestamp(date.getTime()) : null;
    }
}
