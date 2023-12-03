# Testing_QTRIP_APIs

This project involves the testing of REST APIs using RESTArrured.

## About the Project

> ***The RESTAssured script is designed to evaluate the functionality of APIs for QTRIP_dynamic (a dummy travel web application). The script performs various tests, checking all the functionalities of the APIs.***

## Features

- Check the working of different functionalities
- Using GET and POST requests 

# Setup

This project requires the following software and dependencies:

- **Java JDK 17.x.x:** Ensure you have Java Development Kit version 17 or above installed. You can download it [here](https://www.oracle.com/java/technologies/javase-downloads.html).

- **Gradle 8.x.x:** Make sure you have Gradle version 8 or above installed. You can download it [here](https://gradle.org/install/).

- **TestNG Framework:** This project uses TestNG for test execution and reporting. Add the TestNG dependency to your project. Information can be found [here](https://testng.org/doc/).

- **RESTAssusred:** Uses RESTAssured library to test the REST APIs. Details can be found [here](https://mvnrepository.com/artifact/io.rest-assured/rest-assured).
  
- **JSON:** Use to convert the data in json form [here](https://mvnrepository.com/artifact/org.json/json).

- **JSON-schema-validator** Use to validate the json-schema [here](https://mvnrepository.com/artifact/io.rest-assured/json-schema-validator).

### Example Gradle Dependency Configuration:

```gradle
dependencies {
    testImplementation 'org.testng:testng:7.5'
    // This dependency is used by the application.
    implementation 'com.google.guava:guava:31.1-jre'
    testImplementation group: 'io.rest-assured', name: 'rest-assured', version: '5.3.2'
    implementation group: 'org.json', name: 'json', version: '20231013'
    implementation group: 'io.rest-assured', name: 'json-schema-validator', version: '5.3.2'
}
```

> [!NOTE]
> Some of the dependencies may not work in furure. Update to their latest version

# Instructions

Clone/Download the code to your local machine. Pull the code stubs/Unpack the file. Open your terminal/shell. Navigate to the project folder in your terminal.

> ***For Windows:*** Execute the command in the terminal (command prompt or powershell).
```
gradle build
```
> ***For Mac/Linux:*** Execute this command in the bash terminal.
```
./gradlew build
```

Wait for some time and bingo! you will have the output in the terminal.
