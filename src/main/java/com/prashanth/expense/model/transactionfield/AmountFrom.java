package com.prashanth.expense.model.transactionfield;

import lombok.AllArgsConstructor;
import lombok.Data;

//@AllArgsConstructor
@Data
public class AmountFrom {

    long creditCardAmount;
    long debitCardAmount;
    long repaymentAmount;
    long addBalanceAmount;
    long editAmount;
}
