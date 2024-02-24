package com.prashanth.expense.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "FilterTable")
@ToString
public class FilterTable {
    private int id = 1;
    private int year;
    private String month;
    private String cardType;
    private String billType;
}
