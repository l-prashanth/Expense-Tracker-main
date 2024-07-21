FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar BudgetTracket.jar
ENTRYPOINT ["java","-jar","/BudgetTracket.jar"]
