//package com.prashanth.expense.utils.ext;
//
//import com.prashanth.expense.model.summary.Expenses;
//import com.prashanth.expense.model.summary.Status;
//import com.prashanth.expense.utils.ext.AggregateAmountRepository;
//import com.prashanth.expense.repository.ExpensesRepository;
//import com.prashanth.expense.repository.StatusRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.ui.Model;
//
//import java.util.List;
//import java.util.Optional;
//
//@AllArgsConstructor
//public class ExtUtils {
//    private ExpensesRepository expensesRepository;
//    private AggregateAmountRepository aggregateAmountRepository;
//    private StatusRepository statusRepository;
//    public void allData(Model model){
//        Iterable<Expenses> expenses = expensesRepository.findAll();
//        model.addAttribute("expenses", expenses);
//        model.addAttribute("sumAllAmounts", aggregateAmountRepository.sumAllAmounts());
//    }
//    private void setStatusModel(Model model) {
//        Optional<Status> getStatus = statusRepository.findById(1);
//        if (getStatus.isPresent()) {
//            long balance = getStatus.get().getOutstandingAmount();
//            long oneCardDue = getStatus.get().getOneCardDue();
//            long hdfcSwiggyDue = getStatus.get().getHdfcSwiggyDue();
//            long axisAceDue = getStatus.get().getAxisAceDue();
//            long axisFlipkartDue = getStatus.get().getAxisFlipkartDue();
//
//            model.addAttribute("balance", balance);
//            model.addAttribute("oneCard", oneCardDue);
//            model.addAttribute("hdfcSwiggy", hdfcSwiggyDue);
//            model.addAttribute("axisAce", axisAceDue);
//            model.addAttribute("axisFlipkart", axisFlipkartDue);
//        }
//    }
//    private void setAllDataCategoryModel(Model model) {
//        List<Expenses> allData = expensesRepository.findAll();
//        model.addAttribute("filteredData", allData);
//    }
//}
