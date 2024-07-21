package com.prashanth.expense.config;

import com.prashanth.expense.service.DownloadTransactions;
import com.prashanth.expense.service.InitFilterCommandLineRunner;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Slf4j
public class InitData {

    private InitFilterCommandLineRunner initFilterCommandLineRunner;
    private DownloadTransactions downloadTransactions;

        @Bean
        CommandLineRunner initFilters(){
        return args -> {
            initFilterCommandLineRunner.initFilterTable();
//            downloadTransactions.getAllDataAsJson();
        };
    }
}
