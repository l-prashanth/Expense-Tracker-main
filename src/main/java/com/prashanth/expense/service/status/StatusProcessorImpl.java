package com.prashanth.expense.service.status;

import com.prashanth.expense.model.transactionfield.*;
import com.prashanth.expense.model.summary.Expenses;
import com.prashanth.expense.repository.ExpensesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.prashanth.expense.constants.Constants.*;
import static com.prashanth.expense.utils.CommonUtils.isNotNullOrEmpty;

@Service
@AllArgsConstructor
@Slf4j
public class StatusProcessorImpl implements StatusProcessor {

    private ExpensesRepository expensesRepository;


    @Override
    public void addBalance(AmountFrom amountFrom, Date date, Remarks remarks) {
        String addBalanceDate = date.getAddBalanceDate();
        long amount = amountFrom.getAddBalanceAmount();
        String addBalanceRemarks = setBalanceRemarks(remarks,amountFrom);
        Expenses expenses = getExpenses(addBalanceDate, CREDIT_AMOUNT, amount, ADD_BALANCE, addBalanceRemarks);
        expensesRepository.save(expenses);
    }

    @Override
    public void addCreditedAmount(AmountFrom amountFrom, ExpenseCategory expenseCategory, CreditCardCategory creditCardCategory, Date date, Remarks remarks) {
        String addBalanceDate = date.getCreditDate();
        String expenseSource = creditCardCategory.getCardName();
        long amount = amountFrom.getCreditCardAmount();
        String billCategory = expenseCategory.getCreditExpenseType();
        String creditRemarks = setCreditRemarks(remarks, billCategory, amountFrom);
        Expenses expenses = getExpenses(addBalanceDate, expenseSource, amount, billCategory, creditRemarks);
        expensesRepository.save(expenses);
    }

    @Override
    public void addDebitedAmount(AmountFrom amountFrom, ExpenseCategory expenseCategory, Date date, Remarks remarks) {
        String addBalanceDate = date.getDebitDate();
        long amount = amountFrom.getDebitCardAmount();
        String billCategory = expenseCategory.getDebitExpenseType();
        String debitRemarks = setDebitRemarks(remarks, billCategory, amountFrom);
        Expenses expenses = getExpenses(addBalanceDate, DEBIT, amount, billCategory, debitRemarks);
        expensesRepository.save(expenses);
    }

    @Override
    public void addRepaymentAmount(AmountFrom amountFrom, CreditCardCategory creditCardCategory, Date date, Remarks remarks) {
        String addBalanceDate = date.getCreditRepaymentDate();
        long amount = amountFrom.getRepaymentAmount();
        String billCategory = REPAYMENT_TO + creditCardCategory.getCardName();
        String repayRemarks = setRepayRemarks(amountFrom,creditCardCategory);
        Expenses expenses = getExpenses(addBalanceDate, REPAYMENT, amount, billCategory, repayRemarks);
        expensesRepository.save(expenses);
    }

    public String setRepayRemarks(AmountFrom amountFrom,CreditCardCategory creditCardCategory){
        return  REPAID + amountFrom.getRepaymentAmount() + TO + creditCardCategory.getCardName();
    }
    public String setCreditRemarks(Remarks remarks, String expenseCategory, AmountFrom amountFrom) {
        return (isNotNullOrEmpty(amountFrom.getCreditCardAmount()) && isNotNullOrEmpty(remarks.getCreditRemarks()))
                ? SPENT + amountFrom.getCreditCardAmount() + ON + remarks.getCreditRemarks().toUpperCase()
                : SPENT + amountFrom.getCreditCardAmount() + ON + expenseCategory;
    }

    public String setDebitRemarks(Remarks remarks, String expenseCategory, AmountFrom amountFrom) {
        return (isNotNullOrEmpty(amountFrom.getDebitCardAmount()) && isNotNullOrEmpty(remarks.getDebitRemarks()))
                ? SPENT + amountFrom.getDebitCardAmount() + ON + remarks.getDebitRemarks().toUpperCase()
                : SPENT + amountFrom.getDebitCardAmount() + ON + expenseCategory;
    }

    public String setBalanceRemarks(Remarks remarks, AmountFrom amountFrom) {
        return (isNotNullOrEmpty(amountFrom.getAddBalanceAmount()) && isNotNullOrEmpty(remarks.getBalanceRemarks()))
                ? MONEY + amountFrom.getAddBalanceAmount() + CREDITED_AS + remarks.getBalanceRemarks().toUpperCase()
                : MONEY + amountFrom.getAddBalanceAmount() + CREDITED;
    }

    private Expenses getExpenses(String addedBalanceDate, String expenseSource, long addBalance, String billCategory, String remarks) {
        Expenses expenses = new Expenses();
        expenses.setDate(addedBalanceDate);
        expenses.setExpenseSource(expenseSource);
        expenses.setAmount(addBalance);
        expenses.setBillCategory(billCategory);
        expenses.setRemarks(remarks);
        expensesRepository.save(expenses);
        return expenses;
    }
}
