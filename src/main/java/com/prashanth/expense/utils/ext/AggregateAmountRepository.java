//package com.prashanth.expense.utils.ext;
//
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.aggregation.Aggregation;
//import org.springframework.data.mongodb.core.aggregation.AggregationResults;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.stereotype.Repository;
//
//import java.math.BigDecimal;
//
//@Repository
//public class AggregateAmountRepository {
//
//    private final MongoTemplate mongoTemplate;
//
//    @Autowired
//    public AggregateAmountRepository(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }
//
//    public BigDecimal sumAllAmounts() {
//        // The aggregation pipeline to calculate the sum of amounts
////        Aggregation aggregation = Aggregation.newAggregation(
////                Aggregation.group().sum("amount").as("totalAmount")
////        );
//        Aggregation aggregation = Aggregation.newAggregation(
//                Aggregation.match(Criteria.where("billCategory").ne("REPAYMENT to ONE CARD")), // Filter documents where Transaction != repayment
//                Aggregation.group().sum("amount").as("totalAmount")
//        );
//
//        // Executing the aggregation query
//        AggregationResults<TotalAmountProjection> result = mongoTemplate.aggregate(aggregation, "ExpenseSummaryFeb2023", TotalAmountProjection.class);
//
//        // Extracting the result
//        TotalAmountProjection totalAmountProjection = result.getUniqueMappedResult();
//
//        return totalAmountProjection != null ? totalAmountProjection.getTotalAmount() : BigDecimal.ZERO;
//
//    }
//
//    // Projection class to map the result of the aggregation
//    @Getter
//    private static class TotalAmountProjection {
//        private BigDecimal totalAmount;
//
//    }
//}
//
