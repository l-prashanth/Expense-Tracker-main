package com.prashanth.expense.controller;


import com.prashanth.expense.model.transactionfield.*;
import com.prashanth.expense.model.FilterTable;
import com.prashanth.expense.repository.ExpensesRepository;
import com.prashanth.expense.service.transaction.TransactionProcessorImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
@Slf4j
public class BudgetController {
    TransactionProcessorImpl transactionProcessor;
    ExpensesRepository expensesRepository;

//    @GetMapping("/budget")
//    public String showForm(FilterTable filterTable, Model model) {
//        log.info("");
//        transactionProcessor.handelModels(filterTable, model);
//        return "budget";
//    }
//@GetMapping("/login")
//public String login() {
//    return "login"; // Return the login view
//}
    @GetMapping("/budget")
    public String showForm(FilterTable filterTable, Model model, HttpServletRequest request) {
        log.info("Accessing budget page");

        // Process your models here
        transactionProcessor.handelModels(filterTable, model);

        // Check if the request is from a mobile device
        String userAgent = request.getHeader("User-Agent");
        boolean isMobile = userAgent != null && userAgent.toLowerCase().contains("mobile");
        System.out.println(isMobile);
        // Return different views based on device type
        if (isMobile) {
            return "mobileBudget"; // Return mobile-specific HTML
        } else {
            return "budget"; // Return desktop HTML
        }
    }

    @PostMapping("/processForm")
    public String submitCreditCard(Model model, Remarks remarks, FilterTable filterTable, AmountFrom amountFrom,
                                   ExpenseCategory expenseCategory,
                                   CreditCardCategory creditCardCategory,
                                   Date date) {
        filterTable.setYear(filterTable.getYear());
        transactionProcessor.processIncomingRequest(model,remarks, filterTable, amountFrom, expenseCategory, creditCardCategory, date);
        return "redirect:/budget";
    }
}

