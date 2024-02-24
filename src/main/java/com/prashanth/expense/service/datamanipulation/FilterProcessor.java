package com.prashanth.expense.service.datamanipulation;

import com.prashanth.expense.model.FilterTable;
import com.prashanth.expense.model.category.CategoryList;
import com.prashanth.expense.model.summary.Expenses;
import org.springframework.ui.Model;

import java.util.List;

public interface FilterProcessor {


     void storeFilteredData(FilterTable filterTable);
     void handleFilteredDataModel(Model model);
}
