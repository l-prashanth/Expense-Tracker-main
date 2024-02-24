package com.prashanth.expense.repository;

import com.prashanth.expense.model.summary.Expenses;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExpensesRepository extends MongoRepository<Expenses, String> {


    @Query("{'date': {'$regex': ?0, $options: 'i'}}")
    List<Expenses> findByYear(int targetYear);


    @Query("{ 'date': { $regex: ?0, $options: 'i' }, 'expenseSource': { $regex: ?1, $options: 'i' } }")
    List<Expenses> findByYearMonthAndExpense(String yearMonthRegex, String expenseSource);
    /*Dont delete this
    //
   List<Expenses> findByDateRegex(String regex);
   //
   //
*/
    @Query("{ 'date': { $regex: ?0, $options: 'i' } }")
    List<Expenses> findByDateRegex(String regex);

    @Query("{ $and: [ { 'date': { $regex: ?0, $options: 'i' } }, { 'expenseSource': { $regex: ?1, $options: 'i' } } ] }")
    List<Expenses> findByDateRegexAndExpenseSource(String regex, String expenseSource);
    @Query("{ $and: [ { 'date': { $regex: ?0, $options: 'i' } }, { 'billCategory': { $regex: ?1, $options: 'i' } } ] }")
    List<Expenses> findByDateRegexAndBillCategory(String regex, String billCategory);
    List<Expenses> findByBillCategory(String billCategory);
    @Query("{ $and: [ { 'date': { $regex: ?0, $options: 'i' } }, { 'expenseSource': { $regex: ?1, $options: 'i' } }, { 'billCategory': { $regex: ?2, $options: 'i' } } ] }")
    List<Expenses> findByYearAndMonthAndExpenseSourceAndBillCategory(String regex, String expenseSource, String billCategory);

}



