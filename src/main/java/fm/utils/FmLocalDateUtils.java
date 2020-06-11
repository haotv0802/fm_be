package fm.utils;

import fm.common.FmConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
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

    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTimeFormatter.format(dateTime);
    }

    public static String format(LocalDate date) {
        if (date == null) {
            return "";
        }
        return dateFormatter.format(date);
    }

    public static String formatWithPattern(LocalDate date, String pattern) {
        if (date == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(date);
    }

    public static String formatWithPattern(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(dateTime);
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, dateFormatter);
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    public static LocalDate parseDateWithPattern(String date, String pattern) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, formatter);
    }

    public static LocalDateTime parseDateTimeWithPattern(String dateTime, String pattern) {
        if (StringUtils.isEmpty(dateTime)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static LocalDate getLastDateOfNextYear() {
        return LocalDate.of(LocalDate.now().getYear() + 1, 12, 31);
    }

    /**
     * Notes: Timestamp stores Date & Time
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime toLocalDateTime(Timestamp dateTime) {
        return dateTime != null ? dateTime.toLocalDateTime() : null;
    }

    public static LocalDate toLocalDate(java.sql.Date date) {
        return date != null ? date.toLocalDate() : null;
    }
}
