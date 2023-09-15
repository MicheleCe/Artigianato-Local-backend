FROM openjdk:17-alpine
COPY ./target/e-commerce-0.0.1-SNAPSHOT.jar . 
EXPOSE 5000 
ENTRYPOINT ["java","-jar","e-commerce-0.0.1-SNAPSHOT.jar"]