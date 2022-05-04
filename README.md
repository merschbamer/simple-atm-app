# Getting Started

### What
This is a simple interface app for an ATM developed in Java. The application reads user inputs from the command line and executes an action based on the users input.

### Prerequisites
In order to build and run the app you must have the following installed on your machine:
1. Maven 3.8.1+
2. Java 17

### Requirements
* A customer can login to the ATM account by providing a 4 digit pin number
* A customer can view their current balance
* A customer can deposit money
* A customer can withdraw money

### Build
Build and run tests using the following command:
``` mvn clean install ```

Build without running tests using the following command:
``` mvn clean install -DskipTests ```

### Test
Run tests using the following command:
``` mvn test ```

### Run
Run the application using the following command from within the target directory: 
```java -jar AtmApplication.jar```