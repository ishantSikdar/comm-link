package com.ctrlcreate.commlink.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TimeUtil {

    TimeUtil() {}


    public static Date now() {
        return new Date();
    }

    // current time in string pattern applied
    public static String now(String pattern) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(currentDate);
    }

    // given time in pattern conversion
    public static String formattedDate(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    // converts UTC (Instant) date to IST (Date) time
    public static Date instantToIST(Instant instantDate) {
        ZonedDateTime istDate = instantDate.atZone(ZoneId.of("Asia/Kolkata"));
        return Date.from(istDate.toInstant());
    }

    // returns current year
    public static String getCurrentYear() {
        return String.valueOf(Year.now().getValue());
    }

    public static String getCurrentHexTimestampMillis() {
        return Long.toHexString(System.currentTimeMillis()).toUpperCase();
    }

    public static String getCurrentHexTimestampSec() {
        return Long.toHexString(System.currentTimeMillis() / 1000).toUpperCase();
    }

    public static Long secondsToMinute(Long seconds) {
        return seconds / 60;
    }

    public static Long secondsToHour(Long seconds) {
        return seconds / 3600;
    }

    public static Long secondsToDays(Long seconds) {
        return seconds / 86400;
    }

    public static Long secondsToWeeks(Long seconds) {
        return seconds / 604800;
    }

    public static String secondsToWords(Long seconds) {
        if (seconds <= 60 && seconds >= 0) {
            return "Now";

            // minutes
        } else if (seconds <= 3600 && seconds > 60) {
            return secondsToMinute(seconds) + "m ago";

            // hours
        } else if (seconds <= 86400 && seconds > 3600) {
            return secondsToHour(seconds) + "h ago";

            // days
        } else if (seconds <= 604800 && seconds > 86400) {
            return secondsToDays(seconds) + "d ago";

        }   // weeks
        return secondsToWeeks(seconds) + "w ago";
    }

    /**
     Parses the given timeStamp String to milliseconds
     */
    public static long convertTimeStringToSeconds(String timeString) {
        String[] parts = splitStringTime(timeString);
        try {

            long value = Long.parseLong(parts[0]);
            String unit = parts[1];

            switch (unit) {
                case "Now":
                    return value;
                case "m":
                    return value * 60000;
                case "h":
                    return value * 3600000;
                case "d":
                    return value * 86400000;
                case "w":
                    return value * 604800000;
                default:
                    throw new IllegalArgumentException("Invalid time unit");
            }
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Invalid time value");
        }
    }

    private static String[] splitStringTime(String timeString) {

        Pattern pattern = Pattern.compile("(\\d+)([a-zA-Z]+)\\s(.+)");
        Matcher matcher = pattern.matcher(timeString);

        if (matcher.matches()) {
            return new String[]{matcher.group(1), matcher.group(2), matcher.group(3)};
        } else {
            throw new IllegalArgumentException("Time String is Invalid"); // Invalid format
        }
    }
}