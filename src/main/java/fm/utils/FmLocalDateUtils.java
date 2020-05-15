package fm.utils;

import fm.common.FmConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by HaoHo on 5/15/2020
 */
public class FmLocalDateUtils {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(FmConstants.DATE_PATTERN);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FmConstants.DATE_TIME_PATTERN);

    private static final Logger logger = LogManager.getLogger(FmLocalDateUtils.class);

    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateFormatter.format(dateTime);
    }

    public static String formatDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        return dateTimeFormatter.format(date);
    }

    public static String formatDateWithPattern(LocalDate date, String pattern) {
        if (date == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(date);
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, dateFormatter);
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    public static LocalDate parseDateWithPattern(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, formatter);
    }

    public static LocalDateTime parseDateTimeWithPattern(String dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTime, formatter);
    }
}
