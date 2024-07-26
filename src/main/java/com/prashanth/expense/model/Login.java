package com.prashanth.expense.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("User")
public class Login {
    private int id = 1;
    private String user;
}
