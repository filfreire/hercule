# hercule 🕵️

[![Build Status](https://travis-ci.org/filfreire/hercule.svg?branch=master)](https://travis-ci.org/filfreire/hercule)

## About

`hercule` is a (somewhat aggressive) boilerplate starter project for a selenium based UI test framework.

What makes `hercule` distinct is:

- It's use of an "aggressive" code quality/static analysis build check with [qulice](https://www.qulice.com/), makes it maintainable across large teams, preventing any "lazy" "quick-win" code from being added to the codebase;
- Enforced documented code and uniform code style (because of the the agressive static analysis);
- Script to raise test execution "infrastructure", with docker containers and selenium standalone packages, so it's usable in your typical CI slave machines (Ex.: Jenkins, Travis, CircleCi, etc.);
- Plain JUnit tests (no "BDD"-style tests, at least for now);
- Tests can run locally and be debugged locally on an IDE, but the main purpose is that there is already setup work so they're ready run them headlessly on docker containers;
- Distinction between Testing profiles for differen test executions (Example: Integration test profile vs. Unit test profile, etc.)

Curiosity: The project's name comes from an all-time favourite (fictional) detective, Hercule Poirot.

## TO-DO

`hercule` is still a work in progress, in the future I plan to include:

- streamline reporting of JUnit test execution results;
- Add API integration boilerplate examples;
- add Cucumber + Cucumber reports boilerplate;

Feel free to submit an issue on Github if you'd like to see something added to the project.

## Requirements

- Maven 3 (using mvn 3.5.2 version)
- Java 8  (using 1.8.0\_131)
- Docker 17.12.0-ce (tested on Mac OS)

## How to build

In terminal run command:
```
mvn clean install
```

## How to run tests

Simply use this comment, to raise containers, and run tests:
```
./test.sh
```


Or run mvn test individually (and create your own script that raises docker and does other stuff):
```
mvn clean test -P integration -DremoteHost=<REMOTE_WEBDRIVER_HOST>
```

### To run a single test locally

Replace
```
final WebDriver driver = new LocalRemoteDriver(
    System.getProperty("remoteHost")
).instance();
```

with:
```
final WebDriver driver = new LocalFirefoxDriver(
    "/Users/filipefreire/Downloads/geckodriver"
).instance();
```
