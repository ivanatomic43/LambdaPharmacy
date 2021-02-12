[![Build Status](https://travis-ci.com/katarinaa94/deployment-example.svg?branch=master)](https://travis-ci.com/katarinaa94/deployment-example)
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=katarinaa94_deployment-example)](https://sonarcloud.io/dashboard?id=katarinaa94_deployment-example)


### Installation and prerequisites

1. Download and install Visual Studio Code 
2. Download and install NodeJS (LTS) - https://nodejs.org/en/download/ 
3. Download MySQL Server and MySQL Workbench
5. Download and install Java 8
6. Angular 7

### Setup for pharmacy-backend 

 1. Clone git repository
 2. Open Visual Studio Code and import pharmacy-backend folder
 3. Install dependencies from pom.xml file
 4. Create connection to database and start MySQL server 
 5. Run project as Spring Boot App

### Setup for pharmacy-frontend
 1. Open Visual Studio Code and import pharmacy-frontend folder
 2. Install Angular from terminal
```shell
$npm install -g @angular/cli 
```
 3. Open terminal in './pharmacy-frontend' folder and run commands
 ```shell
$ npm install
$ ng serve 
```
4. Application is running on port 4200.

### Setup for e-mail service
 1. Use e-mail and password from application.yml file to login into google account
 2. If necessary, turn off security protection for this account (turn off access for less secure applications)
