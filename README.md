# Cucumber Project

Welcome to the Cucumber Project! This project serves as a comprehensive example of how to use the basic and advanced concepts of the Cucumber framework for Behavior-Driven Development (BDD). The project demonstrates various features such as hooks, tagged hooks, data tables, tagging, data-driven testing, and more.

## Table of Contents

- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Features Implemented](#features-implemented)
  - [Hooks](#hooks)
  - [Tagged Hooks](#tagged-hooks)
  - [Data Tables](#data-tables)
  - [Tagging](#tagging)
  - [Data-Driven Approach](#data-driven-approach)
- [Setup and Installation](#setup-and-installation)
- [Running the Tests](#running-the-tests)
- [Contributing](#contributing)
- [License](#license)

---

## Introduction

This project is designed to demonstrate the use of Cucumber for creating and executing BDD-style tests. Cucumber allows for writing human-readable Gherkin syntax that bridges the gap between technical and non-technical stakeholders.

---

## Project Structure

The project follows a standard Cucumber folder structure:

```
.
├── src/test/java
│   ├── features
│   │   ├── <feature-files>.feature
│   ├── stepDefinitions
│   │   ├── <step-definition-files>.java
│   ├── hooks
│   │   ├── Hooks.java
│   ├── runners
│   │   ├── TestRunner.java
├── src/test/resources
│   ├── cucumber.properties
│   ├── test-data
│   │   ├── <data-files>.csv
```

---

## Features Implemented

### Hooks
Hooks are blocks of code that are executed before or after each scenario. Common use cases include initializing or cleaning up test data, starting/stopping services, etc.

```java
@Before
public void setUp() {
    System.out.println("Setting up the test environment");
}

@After
public void tearDown() {
    System.out.println("Tearing down the test environment");
}
```

---

### Tagged Hooks
Tagged hooks allow specific hooks to run only for scenarios marked with certain tags.

```java
@Before("@SmokeTest")
public void setUpForSmokeTests() {
    System.out.println("Setting up for smoke tests");
}

@After("@SmokeTest")
public void tearDownForSmokeTests() {
    System.out.println("Tearing down smoke test environment");
}
```

---

### Data Tables
Data tables in Cucumber allow for passing structured data into steps. They are particularly useful for scenarios that require multiple sets of data.

Example Gherkin:
```gherkin
Given the following user details:
  | Name  | Age | Email              |
  | Alice | 30  | alice@example.com  |
  | Bob   | 25  | bob@example.com    |
```

Step Definition:
```java
@Given("the following user details:")
public void userDetails(DataTable dataTable) {
    List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);
    users.forEach(user -> System.out.println(user));
}
```

---

### Tagging
Tags can be used to categorize and selectively run scenarios or features.

Example:
```gherkin
@SmokeTest
Scenario: Verify user login
  Given the user is on the login page
  When the user enters valid credentials
  Then the user is redirected to the dashboard
```

You can run tests with specific tags using the following command:
```sh
mvn test -Dcucumber.filter.tags="@SmokeTest"
```

---

### Data-Driven Approach
The data-driven approach allows running the same test scenario multiple times with different sets of data.

Example Gherkin:
```gherkin
Scenario Outline: Login with valid credentials
  Given the user is on the login page
  When the user enters "<username>" and "<password>"
  Then the user should see the dashboard

  Examples:
    | username   | password    |
    | user1      | pass1       |
    | user2      | pass2       |
```

Step Definition:
```java
@When("the user enters {string} and {string}")
public void login(String username, String password) {
    System.out.println("Logging in with username: " + username + " and password: " + password);
}
```

---

## Setup and Installation

1. Clone the repository to your local machine:
   ```sh
   git clone https://github.com/Furqanbijapur/cucumber-project.git
   ```
2. Navigate to the project directory:
   ```sh
   cd cucumber-project
   ```
3. Install dependencies using Maven:
   ```sh
   mvn clean install
   ```

---

## Running the Tests

To execute the tests, use the following Maven command:
```sh
mvn test
```

To run specific tagged scenarios:
```sh
mvn test -Dcucumber.filter.tags="@SmokeTest"
```

---

## Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests with enhancements or fixes.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
