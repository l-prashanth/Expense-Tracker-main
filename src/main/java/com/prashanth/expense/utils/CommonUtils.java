package com.prashanth.expense.utils;

import com.prashanth.expense.model.summary.Expenses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.prashanth.expense.utils.MonthMapper.getMonthNumberMap;


@Component
@AllArgsConstructor
@Slf4j
public class CommonUtils {
    public static boolean isNullOrEmpty(Object... objects) {
        for (Object o : objects) {
            if (o == null || o == "") {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotNullOrEmpty(Object... objects) {
        for (Object o : objects) {
            if (o instanceof Number number) {
                double numericValue = number.doubleValue();
                if (numericValue == 0) {
                    return false;
                }
            }
            if (o == null || o.equals("")) {
                return false;
            }
        }
        return true;
    }
    // New method to aggregate amounts
    public static long aggregateAmount(List<Expenses> expensesList) {
        return expensesList.stream()
                .mapToLong(Expenses::getAmount)
                .sum();
    }
    public static String getCurrentMonth() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Extract the month from the current date
        int currentMonthValue = currentDate.getMonthValue();

        // Format the result as a string

        return String.format("%02d", currentMonthValue);
    }

        public static String getCurrentYear() {
            Year currentYear = Year.now();
            return String.format("%d", currentYear.getValue());

    }
    public static String getCurrentMonthYear(){
        return getCurrentMonth()+"/"+getCurrentYear();
    }
    public static String getMonthYear(int month,int year){
        String monthPadded=String.format("%02d", month);
        return monthPadded+"/"+year;
    }
    public static String monthYearRegex(String monthYear){
        return "^" + monthYear.substring(0, 2) + "/\\d{2}" + monthYear.substring(2) + "$";
    }
    public static String removeSpaces(String input) {
        if (input == null) {
            return null; // or handle accordingly based on your requirements
        }
        // Use regular expression to remove spaces
        return input.replaceAll("\\s", "");
    }

    public static String getTodayDate(){
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}
