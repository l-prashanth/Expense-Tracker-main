package com.prashanth.expense.model.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@Document(collection = "ExpenseSummaryFeb2023")
@ToString
public class Expenses {
    @Id
    @Generated
    @NotNull
    @JsonProperty("ID")
    private String transactionId;
    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @JsonProperty("DATE")
    private String date;
    @NotNull
    @JsonProperty("SOURCE")
    private String expenseSource;
    @NotNull
    @JsonProperty("AMOUNT")
    private long amount;
    @NotNull
    @JsonProperty("BILL CATEGORY")
    private String billCategory;
    private String remarks;
}
