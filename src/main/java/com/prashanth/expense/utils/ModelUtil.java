package com.prashanth.expense.utils;

import com.prashanth.expense.model.enums.OperationId;
import com.prashanth.expense.model.category.*;
import com.prashanth.expense.model.summary.Expenses;
import com.prashanth.expense.model.transactionfield.CreditCardCategory;
import com.prashanth.expense.model.transactionfield.ExpenseCategory;
import com.prashanth.expense.repository.ExpensesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

import static com.prashanth.expense.constants.Constants.*;
import static com.prashanth.expense.utils.CommonUtils.*;

@Slf4j
@AllArgsConstructor
@Component
public class ModelUtil {

    private ExpensesRepository expensesRepository;
    private ExpenseRepositoryFilterUtil expenseRepositoryFilterUtil;

    public void modelHandler(OperationId operationId, Model model, String... values) {
        CategoryList categoryList = new CategoryList();
        switch (operationId) {
            case SET_CREDIT_CATEGORIES:
                List<String> allCreditCardCategories = categoryList.getAllCreditCardCategories();
                model.addAttribute(CREDIT_CATEGORIES, allCreditCardCategories);
                model.addAttribute(CREDIT_CATEGORY, new CreditCardCategory());
                break;
            case SET_EXPENSE_CATEGORIES:
                List<String> allExpenseCategories = categoryList.getAllBillCategories();
                model.addAttribute(EXPENSE_CATEGORIES, allExpenseCategories);
                model.addAttribute(EXPENSE_CATEGORY, new ExpenseCategory());
                break;
            case SET_MONTH_CATEGORIES:
                List<String> allMonthCategories = categoryList.getAllMonthCategories();
                model.addAttribute(MONTH_CATEGORIES, allMonthCategories);
                model.addAttribute(MONTH_CATEGORY, new MonthCategory());
                break;
            case SET_YEAR_CATEGORIES:
                List<Integer> allYearCategories = categoryList.getAllYearCategories();
                model.addAttribute(YEAR_CATEGORIES, allYearCategories);
                model.addAttribute(YEAR_CATEGORY, new YearCategory());
                break;
            case TOTAL_BALANCE:
                long totalBalance = expenseRepositoryFilterUtil.currentBalance();
                model.addAttribute(BALANCE, totalBalance);
                break;
            case CARD_EXPENSE:
                long cardExpense = expenseRepositoryFilterUtil.aggregateAmountByCard(values[0]);
                model.addAttribute(CARD_EXPENSE_AMOUNT + removeSpaces(values[0]), cardExpense);
                break;
            case DEFAULT_DATE:
                model.addAttribute(TODAY_DATE, getTodayDate());
                break;
            case FILTER_BY_MONTH:
                List<Expenses> filteredByMonth = expensesRepository.findByDateRegex(monthYearRegex(values[0]));
                model.addAttribute(AGGREGATE_AMOUNT, expenseRepositoryFilterUtil.totalExpenseByFilter(filteredByMonth));
                model.addAttribute(FILTER, filteredByMonth);
                break;
            case FILTER_BY_YEAR:
                int year = Integer.parseInt(values[0]);
                List<Expenses> filteredByYear = expensesRepository.findByYear(year);
                model.addAttribute(AGGREGATE_AMOUNT, expenseRepositoryFilterUtil.totalExpenseByFilter(filteredByYear));
                model.addAttribute(FILTER, filteredByYear);
                break;
            case FILTER_BY_BILL:
                List<Expenses> filteredByBill = expensesRepository.findByDateRegexAndBillCategory(monthYearRegex(values[0]), values[1]);
                model.addAttribute(AGGREGATE_AMOUNT, expenseRepositoryFilterUtil.totalExpenseByFilter(filteredByBill));
                model.addAttribute(FILTER, filteredByBill);
                break;
            case FILTER_BY_CARD:
                List<Expenses> filteredByCard = expensesRepository.findByDateRegexAndExpenseSource(monthYearRegex(values[0]), values[1]);
                model.addAttribute(AGGREGATE_AMOUNT, expenseRepositoryFilterUtil.totalExpenseByFilter(filteredByCard));
                model.addAttribute(FILTER, filteredByCard);
                break;
            case FILTER_BY_CARD_AND_EXPENSE:
                List<Expenses> filteredByCardAndExpense = expensesRepository.findByYearAndMonthAndExpenseSourceAndBillCategory(monthYearRegex(values[0]), values[1],values[2]);
                log.info("check1"+monthYearRegex(values[0]));
                log.info("check1"+filteredByCardAndExpense);
                log.info("check2"+values[1]);
                log.info("check3"+values[2]);
                model.addAttribute(AGGREGATE_AMOUNT, expenseRepositoryFilterUtil.totalExpenseByFilter(filteredByCardAndExpense));
                model.addAttribute(FILTER, filteredByCardAndExpense);
                break;

            default:
                throw new IllegalArgumentException("Unsupported operationId: " + operationId);
        }
    }
}
