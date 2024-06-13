# BankApp
A springboot application for managing bank details and transactions for a user

## Description
Here I have built a simple springboot server using postgres as database. there are a total of 3 models and 5 endpoints.

## Features
- add user and bank details
- create transactions
- query user and bank details
- query transaction details in a range and sorted

## Models
- User
- UserBankAccount
- Transaction

## Data relationships
- User has one-to-one relationship with UserBankAccount
- User has ont-to-many relationship with Transaction

## Acknowledgments
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)