# FinanceManager
## Uni project for the Web Java Technologies course, FMI 2023
**The application is implemented using Java 17, Spring Boot 3.0.5 and PostgreSQL**  
 
Finance Managemr helps with managing your finanaces by tracking different savings, budgets, financial goals, incomes, expenses and investments by allowing you to:  
 + store information about all of the above, plus contributions made toward goals, spendings that are made from budgets and deposits/withdraws made into/from savings 
 + perform CRUD operations on all of the above
 + monitor progress on goals/budgets
 + filter contributions toward goals by date
 + filter savings by creation date
 + filter deposits/withdraws from the transactions that were made on spendings
 + filter expenses by category i.e. rent, subscriptions, grocery shopping, etc

## How to run the application
1. Alter the **application.properies** file as follows 
  + Change {db name} to the name of the database that you intend to use
  + Change {db port} to the port on which postgresql is runnig on your machine
        
     ```
      spring.datasource.url=jdbc:postgresql://localhost:{port}/{db name}
     ```
  + Change {username} and {password} to your postgres username and password
    
    ```
    spring.datasource.username={username}    
    spring.datasource.password={password}
    ```
2. Run `mvn clean install`
3. Run the app

# Overview
## Database scheme

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/db_scheme.png)

## Rest endpoints
When the app is running the rest endpoints can be seen on <http://localhost:8080/swagger-ui/index.html> or <http://localhost:8080/rest-endpoints.html>

### Saving
#### *Endpoints*:

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/saving-endpoints.png) 

#### *DTO*:   

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/saving-dto.png)  

### Transaction  
#### *Endpoints*:  

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/transaction-endpoints.png)  

#### *DTO*:  

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/transaction-dto.png)  

### Goal
#### *Endpoints*:

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/goal-endpoints.png)  

#### *DTO*:   

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/goal-dto.png)  

### Contribution
#### *Endpoints*:  

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/contribution-endpoints.png)  

#### *DTO*:  
![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/contribution-dto.png)  

### Budget
#### *Endpoints*:

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/budget-endpoints.png)  

#### *DTO*:   

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/budget-dto.png)  

### Saving
#### *Endpoints*:  

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/saving-endpoints.png)  

#### *DTO*:   

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/saving-dto.png)  

### Expense
#### *Endpoints*:

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/expense-endpoints.png)  

#### *DTO*:   

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/expense-dto.png)  

### Income
#### *Endpoints*:

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/income-endpoints.png)  

#### *DTO*:   

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/income-dto.png)  

### Investment
#### *Endpoints*:

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/investment-endpoints.png)  

#### *DTO*:   

![alt text](https://github.com/DanyJo/FinanceManager/blob/docs/investment-dto.png)  

