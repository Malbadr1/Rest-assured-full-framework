# REST Assured Full Framework

A complete API testing framework using:
- Java 21
- Maven
- JUnit 5
- REST Assured
- Allure for reporting
- CLI test menu
- Assertions with detailed checks

## Run all tests
```bash
mvn clean test
```

## Run menu
```bash
mvn clean test-compile exec:java 
```

## Generate Allure Report
```bash
allure serve target/allure-results
```

## Features
- Covers 200, 201, 204, 400, 404, 500 status codes
- Asserts JSON values from responses
- Uses external JSON for POST/PUT
- Interactive test runner
