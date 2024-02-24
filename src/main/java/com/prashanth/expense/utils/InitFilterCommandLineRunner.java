package com.prashanth.expense.utils;

import com.prashanth.expense.model.FilterTable;
import com.prashanth.expense.repository.FilterTableRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@AllArgsConstructor
@Slf4j
public class InitFilterCommandLineRunner {

    private FilterTableRepository filterTableRepository;
    @Bean
    CommandLineRunner initFilters(){
        return args -> {
            FilterTable filterTable = new FilterTable();
            filterTable.setYear(0);
            filterTable.setMonth(null);
            filterTable.setCardType(null);
            filterTable.setBillType(null);
            filterTableRepository.save(filterTable);
            log.info("Filter Cleared");
        };
    }
}
