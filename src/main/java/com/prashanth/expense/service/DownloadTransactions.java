package com.prashanth.expense.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prashanth.expense.model.summary.Expenses;
import com.prashanth.expense.repository.ExpensesRepository;
import com.prashanth.expense.repository.FilterTableRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
@Slf4j
public class DownloadTransactions {

    private ExpensesRepository expensesRepository;
    private ObjectMapper objectMapper;
    public void getAllDataAsJson() {
        try {
            // Fetch all data from MongoDB
            List<Expenses> expenses = expensesRepository.findAll();

            // Generate current date formatted as dd-MM-yyyy
            String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

            // Define file path
            String filePath = "C:\\MyFiles\\Documents\\Bills\\Budget\\";

            // Create directory if it doesn't exist
            File directory = new File(filePath);
            boolean isDirectoryCreated = directory.mkdirs(); // Create directories including any necessary but nonexistent parent directories.

            if (!isDirectoryCreated) {
                // Log a warning if directory creation failed or already existed
                log.warn("Directory {} already exists or could not be created.", directory.getAbsolutePath());
            }

            // Create file with current date as part of the name
            String fileName = filePath + "expenses_" + currentDate + ".json";
            File jsonFile = new File(fileName);

            // Write expenses as JSON array to the file
            try (FileWriter fileWriter = new FileWriter(jsonFile)) {
                fileWriter.write("[\n");
                for (int i = 0; i < expenses.size(); i++) {
                    Expenses expense = expenses.get(i);
                    String jsonLine = String.format("{ \"_id\": { \"$oid\": \"%s\" }, \"date\": \"%s\", \"expenseSource\": \"%s\", \"amount\": %d, \"billCategory\": \"%s\", \"remarks\": \"%s\", \"_class\": \"%s\" }",
                            expense.getTransactionId(),
                            expense.getDate(),
                            expense.getExpenseSource(),
                            expense.getAmount(),
                            expense.getBillCategory(),
                            expense.getRemarks(),
                            "com.prashanth.expense.model.summary.Expenses");

                    fileWriter.write(jsonLine);
                    if (i < expenses.size() - 1) {
                        fileWriter.write(",\n"); // Add comma and newline after each JSON object (except the last one)
                    } else {
                        fileWriter.write("\n"); // Ensure no trailing comma after the last object
                    }
                }
                fileWriter.write("]\n");
            }

            log.info("Successfully downloaded expenses to file: {}", jsonFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("Error while downloading expenses as JSON", e);
        }
    }

//    public void getAllData() {
//        try {
//            // Fetch all data from repository
//            List<Expenses> expenses = expensesRepository.findAll();
//
//            // Generate current date formatted as dd-MM-yyyy
//            String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//
//            // Define file path
//            String filePath = "C:\\MyFiles\\Documents\\Bills\\Budget\\";
//
//            // Create directory if it doesn't exist
//            File directory = new File(filePath);
//            boolean isDirectoryCreated = directory.mkdirs(); // Create directories including any necessary but nonexistent parent directories.
//
//            if (!isDirectoryCreated) {
//                // Log a warning if directory creation failed or already existed
//                log.warn("Directory {} already exists or could not be created.", directory.getAbsolutePath());
//            }
//
//            // Create file with current date as part of the name
//            String fileName = filePath + "expenses_" + currentDate + ".json";
//            String fileNameCsv = filePath + "expenses_" + currentDate + ".csv";
//            File jsonFile = new File(fileName);
//            File jsonFileCsv = new File(fileNameCsv);
//
//            // Write expenses as JSON to the file
//            objectMapper.writeValue(jsonFile, expenses);
//            objectMapper.writeValue(jsonFileCsv, expenses);
//
//            log.info("Successfully downloaded expenses to file: {}", jsonFile.getAbsolutePath());
//        } catch (IOException e) {
//            log.error("Error while downloading expenses as JSON", e);
//        }
//
//    }


//        log.info("ALL Data/n"+expensesRepository.findAll());
//    }
}
