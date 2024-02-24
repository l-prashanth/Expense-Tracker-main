package com.prashanth.expense.service.datamanipulation;

import com.prashanth.expense.model.FilterTable;
import com.prashanth.expense.repository.FilterTableRepository;
import com.prashanth.expense.utils.ModelUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

import static com.prashanth.expense.model.enums.OperationId.*;
import static com.prashanth.expense.utils.CommonUtils.*;
import static com.prashanth.expense.utils.CommonUtils.isNotNullOrEmpty;
import static com.prashanth.expense.utils.MonthMapper.getMonthNumberMap;

@Service
@AllArgsConstructor
@Slf4j
public class FilterProcessorImpl implements FilterProcessor {

    private FilterTableRepository filterTableRepository;
    private ModelUtil modelUtil;

    @Override
    public void storeFilteredData(FilterTable filterTable) {
        filterTable.setYear(filterTable.getYear());
        filterTable.setMonth(filterTable.getMonth());
        filterTable.setCardType(filterTable.getCardType());
        filterTable.setBillType(filterTable.getBillType());
        filterTableRepository.save(filterTable);
    }

    @Override
    public void handleFilteredDataModel(Model model) {
        modelUtil.modelHandler(SET_MONTH_CATEGORIES, model);
        modelUtil.modelHandler(SET_YEAR_CATEGORIES, model);
        Optional<FilterTable> filterTable = filterTableRepository.findById(1);
        if (filterTable.isPresent()) {
            int year = filterTable.get().getYear();
            String month = filterTable.get().getMonth();
            String billType = filterTable.get().getBillType();
            String card = filterTable.get().getCardType();
            String monthYear = getMonthYear(getMonthNumberMap(month), year);
            if (isNotNullOrEmpty(year)) {
                if (isNotNullOrEmpty(month)) {
                    if(isNotNullOrEmpty(billType) && isNotNullOrEmpty(card)){
                        modelUtil.modelHandler(FILTER_BY_CARD_AND_EXPENSE, model, monthYear,card,billType);
                        return;
                    }
                    if (isNotNullOrEmpty(billType)) {
                        modelUtil.modelHandler(FILTER_BY_BILL, model, monthYear, billType);
                        return;
                    }
                    if (isNotNullOrEmpty(card)) {
                        modelUtil.modelHandler(FILTER_BY_CARD, model, monthYear, card);
                        return;
                    }
                    modelUtil.modelHandler(FILTER_BY_MONTH, model, monthYear);
                    return;
                }
                modelUtil.modelHandler(FILTER_BY_YEAR, model, String.valueOf(year));
            }
        }
    }
}