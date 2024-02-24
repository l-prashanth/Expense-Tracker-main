package com.prashanth.expense.controller;

import com.prashanth.expense.repository.ExpensesRepository;
import com.prashanth.expense.service.datamanipulation.FilterProcessorImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Slf4j

public class DataManipulationController {

    FilterProcessorImpl dataManipulationProcessor;
    ExpensesRepository expensesRepository;

@GetMapping(value = "/delete/{id}")
    public String handleDelete(@PathVariable String id) {
        expensesRepository.deleteById(id);
        return "redirect:/budget";
    }
}
