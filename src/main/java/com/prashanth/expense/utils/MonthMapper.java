package com.prashanth.expense.utils;

import java.util.HashMap;
import java.util.Map;
public class MonthMapper {
    private static final Map<String, Integer> MONTH_MAP = new HashMap<>();
    static {
        MONTH_MAP.put("JANUARY", 1);
        MONTH_MAP.put("FEBRUARY", 2);
        MONTH_MAP.put("MARCH", 3);
        MONTH_MAP.put("APRIL", 4);
        MONTH_MAP.put("MAY", 5);
        MONTH_MAP.put("JUNE", 6);
        MONTH_MAP.put("JULY", 7);
        MONTH_MAP.put("AUGUST", 8);
        MONTH_MAP.put("SEPTEMBER", 9);
        MONTH_MAP.put("OCTOBER", 10);
        MONTH_MAP.put("NOVEMBER", 11);
        MONTH_MAP.put("DECEMBER", 12);
    }

    public static int getMonthNumberMap(String monthName) {
        if (monthName != null && !monthName.isEmpty()) {
            return MONTH_MAP.getOrDefault(monthName.toUpperCase(), 0);
        } else {
            return 0;
        }
    }

}
