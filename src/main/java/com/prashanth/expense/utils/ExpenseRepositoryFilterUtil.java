package com.prashanth.expense.utils;

import com.prashanth.expense.model.summary.Expenses;
import com.prashanth.expense.repository.ExpensesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class ExpenseRepositoryFilterUtil {
    private ExpensesRepository expensesRepository;


    public long totalAddedBalance() {
        List<Expenses> allExpenses = expensesRepository.findAll();
        // Calculate total amount for ADD BALANCE
        return allExpenses.stream()
                .filter(expense -> "ADD BALANCE".equals(expense.getBillCategory()))
                .mapToLong(Expenses::getAmount)
                .sum();
    }

    public long totalRepaidBalance() {
        List<Expenses> allExpenses = expensesRepository.findAll();
        // Calculate total amount for ADD BALANCE
        return allExpenses.stream()
                .filter(expense -> expense.getBillCategory().contains("REPAYMENT"))
                .mapToLong(Expenses::getAmount)
                .sum();
    }

    public long totalDebitedBalance() {
        List<Expenses> allExpenses = expensesRepository.findAll();
        // Calculate total amount for ADD BALANCE
        return allExpenses.stream()
                .filter(expense -> expense.getExpenseSource().contains("DEBIT"))
                .mapToLong(Expenses::getAmount)
                .sum();
    }

    public long totalRepaidBalanceByType(String card) {
        List<Expenses> allExpenses = expensesRepository.findAll();
        // Calculate total amount for ADD BALANCE
        return allExpenses.stream()
                .filter(expense -> expense.getBillCategory().contains("REPAYMENT TO " + card))
                .mapToLong(Expenses::getAmount)
                .sum();
    }

    public long allTotalExpense() {
        List<Expenses> allExpenses = expensesRepository.findAll();

        long totalAllExpensesAmount = aggregateAmount(allExpenses);

        return totalAllExpensesAmount - (totalAddedBalance() + totalRepaidBalance());
    }

    public long totalExpenseByFilter(List<Expenses> expensesList) {
        return aggregateExpense(expensesList);
    }

    public long currentBalance() {
        return totalAddedBalance() - totalRepaidBalance() - totalDebitedBalance();
    }

    public static long aggregateAmount(List<Expenses> expensesList) {
        return expensesList.stream()
                .mapToLong(Expenses::getAmount)
                .sum();
    }

    public static long aggregateExpense(List<Expenses> expensesList) {
        List<String> card = Arrays.asList("ONE CARD", "AXIS ACE", "AXIS FLIPKART", "HDFC SWIGGY","DEBIT");
        return expensesList.stream()
                .filter(expense -> card.contains(expense.getExpenseSource()))
                .mapToLong(Expenses::getAmount)
                .sum();
    }


    public long aggregateAmountByCard(String card) {
        List<Expenses> allExpenses = expensesRepository.findAll();
        long cardExpense = allExpenses.stream()
                .filter(expense -> card.equals(expense.getExpenseSource()))
                .mapToLong(Expenses::getAmount)
                .sum();
        return cardExpense - totalRepaidBalanceByType(card);
    }


    public long calculateTotalAmountForAddBalance() {
        List<Expenses> expensesList = expensesRepository.findByBillCategory("ADD BALANCE");
        // Calculate total amount
        return expensesList.stream()
                .mapToLong(Expenses::getAmount)
                .sum();
    }

//    public List<Expenses> filterBy(int year, String month, String expenseSource, String billCategory) {
//        return expensesRepository.findByYearAndMonthAndExpenseSourceAndBillCategory(year, month, expenseSource, billCategory);
//    }
}
