package com.prashanth.expense.service.transaction;

import com.prashanth.expense.model.transactionfield.*;
import com.prashanth.expense.model.FilterTable;
import com.prashanth.expense.service.datamanipulation.FilterProcessorImpl;
import com.prashanth.expense.service.status.StatusProcessorImpl;
import com.prashanth.expense.utils.ModelUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import static com.prashanth.expense.constants.Constants.*;
import static com.prashanth.expense.model.enums.OperationId.*;
import static com.prashanth.expense.utils.CommonUtils.getCurrentMonthYear;
import static com.prashanth.expense.utils.CommonUtils.isNotNullOrEmpty;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionProcessorImpl implements TransactionProcessor {

    private StatusProcessorImpl statusProcessor;
    private FilterProcessorImpl filterProcessor;
    ModelUtil modelUtil;

    @Override
    public void processIncomingRequest(Model model, Remarks remarks, FilterTable filterTable, AmountFrom amountFrom,
                                       ExpenseCategory expenseCategory,
                                       CreditCardCategory creditCardCategory,
                                       Date date) {
        processAmountIfNotEmpty(amountFrom.getCreditCardAmount(), () -> statusProcessor.addCreditedAmount(amountFrom, expenseCategory, creditCardCategory, date, remarks));
        processAmountIfNotEmpty(amountFrom.getDebitCardAmount(), () -> statusProcessor.addDebitedAmount(amountFrom, expenseCategory, date, remarks));
        processAmountIfNotEmpty(amountFrom.getRepaymentAmount(), () -> statusProcessor.addRepaymentAmount(amountFrom, creditCardCategory, date, remarks));
        processAmountIfNotEmpty(amountFrom.getAddBalanceAmount(), () -> statusProcessor.addBalance(amountFrom, date, remarks));
        filterProcessor.storeFilteredData(filterTable);
    }

    @Override
    public void handelModels(FilterTable filterTable, Model model) {
        modelUtil.modelHandler(DEFAULT_DATE,model);
        modelUtil.modelHandler(FILTER_BY_MONTH, model, getCurrentMonthYear());
        modelUtil.modelHandler(TOTAL_BALANCE, model);
        modelUtil.modelHandler(CARD_EXPENSE, model, ONE_CARD);
        modelUtil.modelHandler(CARD_EXPENSE, model, AXIS_ACE);
        modelUtil.modelHandler(CARD_EXPENSE, model, HDFC_SWIGGY);
        modelUtil.modelHandler(CARD_EXPENSE, model, AXIS_FLIPKART);
        modelUtil.modelHandler(SET_CREDIT_CATEGORIES, model);
        modelUtil.modelHandler(SET_EXPENSE_CATEGORIES, model);
        modelUtil.modelHandler(SET_EXPENSE_CATEGORIES, model);
        filterProcessor.handleFilteredDataModel(model);
    }

    private void processAmountIfNotEmpty(long amount, Runnable action) {
        if (isNotNullOrEmpty(amount)) {
            action.run();
        }
    }

}

