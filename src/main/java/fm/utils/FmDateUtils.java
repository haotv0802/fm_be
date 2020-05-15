package fm.utils;

import fm.common.FmConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        try {
            return sdfDate.parse(date);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static Date parseDateTime(String dateTime) {
        try {
            return sdfDateTime.parse(dateTime);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
