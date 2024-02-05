### Database Setup Instructions

---
## Overview

This document provides the instructions for setting up the MySQL database and tables required for the Boffee application.
It includes steps for creating the database, initializing tables, and seeding the database with initial data.

---
## Prerequisities
Before proceeding with the database setup, ensure you have the following installed:

* MySQL Server
* MySQL Workbench for managing databases

This project was made with 5.7.0 version

---
## MySQL Server Connection and Script Execution
Establish connection with the server.

Open the numerated SQL scripts, starting with '00_00 create-database'.
Each '_01' script creates a table, and each '_02' script inserts data into that table.

---
## Application.properties
# JDBC properties

# Change the following URL to match your MySQL server and database name
spring.datasource.url=jdbc:mysql://localhost:3306/boffee_database

# Set your MySQL username
spring.datasource.username=root

# Set your MySQL password
spring.datasource.password=admin

# Specify the MySQL JDBC driver class
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver