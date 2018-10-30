# Fenix Forward Page Backend
[![Build Status](https://travis-ci.org/FenixForwardsIT/FenixPageBackend.svg?branch=master)](https://travis-ci.org/FenixForwardsIT/FenixPageBackend)

## Contributors:
- [David Cingolani](https://www.linkedin.com/in/dcingolani/)
- [Ivan Meyer](https://www.linkedin.com/in/ivan-meyer-355ab715a/)
- [Walter Finkbeiner](https://www.linkedin.com/in/walter-finkbeiner-72129b9b/)

## Running locally:
##### Preconditions:
- JDK must be installed
- Maven must be installed
##### Steps:
1) Open a command prompt and navigate to the directory in which we want to have the project in
```sh
$ cd myDirectory
```
2) Clone the repository
```sh
$ git clone git@github.com:FenixForwardsIT/FenixPageBackend.git
```
3) Move to FenixPageBackend directory
```sh
$ cd FenixPageBackend
```
4) Run mvn clean install -DskipTests
```sh
$ mvn clean install -DskipTests
```
5) Launch the application
```sh
$ mvn mvn spring-boot:run
```
The application will start running on **localhost:8080**. This can be tested by making a **GET** request to **http://localhost:8080/project** (It won't show anything because there are no projects, but you should get a 200 response)

The API documentation can be accessed from **http://localhost:8080/swagger-ui.html**


