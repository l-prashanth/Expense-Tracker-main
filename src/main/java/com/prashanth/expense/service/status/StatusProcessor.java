package com.prashanth.expense.service.status;

import com.prashanth.expense.model.transactionfield.*;

public interface StatusProcessor {
    void addBalance(AmountFrom amountFrom, Date date, Remarks remarks);

    void addCreditedAmount(AmountFrom amountFrom, ExpenseCategory expenseCategory, CreditCardCategory creditCardCategory, Date date, Remarks remarks);

    void addDebitedAmount(AmountFrom amountFrom, ExpenseCategory expenseCategory, Date date, Remarks remarks);

    void addRepaymentAmount(AmountFrom amountFrom, CreditCardCategory creditCardCategory, Date date, Remarks remarks);
}
