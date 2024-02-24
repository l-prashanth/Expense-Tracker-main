package com.prashanth.expense.service.transaction;

import com.prashanth.expense.model.transactionfield.*;
import com.prashanth.expense.model.FilterTable;
import org.springframework.ui.Model;

public interface TransactionProcessor {

    void processIncomingRequest(Model model, Remarks remarks, FilterTable filterTable, AmountFrom amountFrom,
                                ExpenseCategory expenseCategory,
                                CreditCardCategory creditCardCategory,
                                Date date);
    void handelModels(FilterTable filterTable, Model model);

}
