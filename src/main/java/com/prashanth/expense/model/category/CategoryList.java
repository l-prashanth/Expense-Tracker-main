package com.prashanth.expense.model.category;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class CategoryList {
    List<String> allCreditCardCategories = Arrays.asList("ONE CARD", "AXIS ACE", "AXIS FLIPKART", "HDFC SWIGGY");
    List<String> allBillCategories = Arrays.asList("PETROL", "INSTAMART", "OUTING", "BILL", "MISCELLENOUS");
    List<Integer> allYearCategories = Arrays.asList(2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
    List<String> allMonthCategories = Arrays.asList("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER");
    List<String> allTransactionCategories = Arrays.asList("option1", "option2", "option3");
}
