package com.buildupchao.concurrent;

import java.time.*;
import java.util.Date;

/**
 * @author buildupchao
 * @date 2019/10/24 22:11
 * @since JDK 1.8
 */
public class DateTimeTest {

    public static void main(String[] args) {
        Date date = new Date();

        long time1 = date.getTime();
        LocalDateTime localTime = LocalDateTime.now().withYear(2019).withMonth(10).withDayOfMonth(24);
        LocalDateTime start = localTime.withHour(13).withMinute(0).withSecond(0);
        LocalDateTime end = localTime.withHour(18).withMinute(0).withSecond(0);

        long startTime = start.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long endTime = end.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        System.out.println(time1);
        System.out.println(startTime);
        System.out.println(endTime);
    }
}
