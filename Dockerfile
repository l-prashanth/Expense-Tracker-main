#FROM openjdk:17
##EXPOSE 8091
#ADD target/expense-docker.jar app.jar
#ENTRYPOINT ["java","-jar","app.jar"]

FROM openjdk:17
WORKDIR /app
COPY ./target/expense-springboot.jar /app
COPY wait-for-it.sh /app
RUN chmod +x /app/wait-for-it.sh
CMD ["./wait-for-it.sh", "0.0.0.0:27017", "--timeout=30", "--", "java", "-jar", "expense-springboot.jar"]

