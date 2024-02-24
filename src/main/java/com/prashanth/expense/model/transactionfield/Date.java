package com.prashanth.expense.model.transactionfield;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Date {
    String creditDate;
    String debitDate;
    String creditRepaymentDate;

    String addBalanceDate;

    String editDate;
}
