package com.jtb.taxpayerws.utils;

import com.jtb.taxpayerws.constants.AppConstants;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.jtb.taxpayerws.constants.AppConstants.TIME_ZONE;

public class DateUtil {

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return new Timestamp(date.getTime()).toLocalDateTime();
    }

    public static Timestamp getCurrentSqlDatetime() {
        return new Timestamp(getCurrentDate().getTime());
    }

    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        TimeZone fromTimeZone = calendar.getTimeZone();
        TimeZone toTimeZone = TimeZone.getTimeZone("Africa/Lagos");
        return getDate(calendar, fromTimeZone, toTimeZone);
    }

    public static Date getCurrentDate(String timeZone) {
        Calendar calendar = Calendar.getInstance();
        TimeZone fromTimeZone = calendar.getTimeZone();
        TimeZone toTimeZone = TimeZone.getTimeZone(timeZone);

        return getDate(calendar, fromTimeZone, toTimeZone);
    }

    private static Date getDate(Calendar calendar, TimeZone fromTimeZone, TimeZone toTimeZone) {
        calendar.setTimeZone(fromTimeZone);
        calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
        if (fromTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getDSTSavings() * -1);
        }

        calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
        if (toTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
        }
        return calendar.getTime();
    }

    public static Date addSecondsToDate(int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.getCurrentDate());
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    public static LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of(TIME_ZONE))
                .toLocalDateTime();
    }

    public static LocalDate parseStringToDate(String strDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT);

            return LocalDate.parse(strDate.trim(), formatter);
        } catch (DateTimeParseException ex) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorInfo.INVALID_DATE.getErrorMessage());
        }
    }

    public static boolean isValidDateInterval(LocalDate from, LocalDate to) {
        return from.isBefore(to);
    }
}
