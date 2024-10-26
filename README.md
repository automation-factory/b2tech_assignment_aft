# Automated Testing Project

###### This project is designed for automated testing using Java, TestNG, Selenide, Allure and Lombok. It supports running tests both locally and in a Continuous Integration (CI) environment and parallel execution.


## Prerequisites
##### Make sure the following tools are installed on your machine:

- Java Development Kit (JDK): Version 17 or higher
- Gradle: Version 8.6 or higher
- IntelliJ IDEA (recommended) or any other Java-compatible IDE

## Clone the Repository
#### To start, clone the repository and navigate into it:
```bash
git clone https://github.com/automation-factory/b2tech_assignment_aft.git
```

## Lombok Configuration
This project uses the Lombok library. 
Ensure the Lombok plugin is installed and enabled in your IDE to prevent any compilation issues.

## Environment Configuration
#### Local Environment Setup
To set up environment variables and differentiate between local and CI environments:

IntelliJ IDEA Setup
#### Local Execution from IDEA

1. Go to Run > Edit Configurations.
2. Select Templates > TestNG.
3. In Environment variables, add:

#### Name / Value
- test.environment = Environment type (ex: local or ci)
- base.url = Base URL for the application under test (ex: https://s.gsb.co.zm/) 
- is.headless = Headless mode (ex: true or false)
- timeout = Timeout duration in milliseconds (ex: 30000, 30sec)


## Running Tests
#### Local Execution from console
To run tests locally with specific parameters, use the following command:
```bash
./gradlew clean test -'Dtest.environment'='local' -'Dbase.url'='https://s.gsb.co.zm/'  -'Dis.headless'='true' -'Dtimeout'='5000'
```

## Parallel execution
edit build.gradle file row with maxParallelForks
```bash
maxParallelForks = System.getProperty('max.parallel.forks', '4').toInteger()
```
in console just add additional parameter
```bash
-'Dmax.parallel.forks'='6'
```

## Generating and Viewing Allure Reports

Generate Reports: After running tests, you can generate the Allure report:
```bash
./gradlew allureServe
```