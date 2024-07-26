package com.prashanth.expense.repository;

import com.prashanth.expense.model.FilterTable;
import com.prashanth.expense.model.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login,Integer> {
}
