
#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build 
WORKDIR /app
COPY . .
RUN mvn clean package -D skipTests

#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /app/target/MarketPlace-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT=8081
EXPOSE 8081
ENTRYPOINT ["java","-jar","demo.jar"]