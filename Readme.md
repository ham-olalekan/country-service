## Introduction

This project is a backend application of built for Klasha java developer assessment.

## Setup

Make sure to follow all these steps exactly as explained below. Do not miss any steps or you won't be able to run this application.

### Start the Server with Docker
Run the following Docker commands in the project root directory to run the application
- `docker build -t country-service . `
-
then run

- `docker run -p 8080:8080 country-service `
  NB: you can bind to any other free port in case 9090 is already in use on your machine


### Documentation
- Database tables = http://localhost:8080/h2-console/
- Postman collection = https://documenter.getpostman.com/view/6597143/2s9YJdUMei

### DATABASE
- DB = H2 in memory
- migration = Liquibase