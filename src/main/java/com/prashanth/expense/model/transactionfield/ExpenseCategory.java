package com.prashanth.expense.model.transactionfield;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExpenseCategory {

    private String creditExpenseType;
    private String debitExpenseType;
}
