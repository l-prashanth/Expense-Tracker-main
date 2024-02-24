package com.prashanth.expense.repository;

import com.prashanth.expense.model.FilterTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterTableRepository extends CrudRepository<FilterTable,Integer> {
}
