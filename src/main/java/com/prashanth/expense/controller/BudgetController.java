package com.prashanth.expense.controller;


import com.prashanth.expense.model.transactionfield.*;
import com.prashanth.expense.model.FilterTable;
import com.prashanth.expense.repository.ExpensesRepository;
import com.prashanth.expense.service.transaction.TransactionProcessorImpl;
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

    @GetMapping("/budget")
    public String showForm(FilterTable filterTable, Model model) {
        log.info("");
        transactionProcessor.handelModels(filterTable, model);
        return "budget";
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

