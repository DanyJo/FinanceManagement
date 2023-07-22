# FinanceManager
## Uni project for the Web Java Technologies course, FMI 2023
*TO DO*  
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

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/db_scheme.png)

## Rest endpoints
When the app is running the rest endpoints can be seen on <http://localhost:8080/swagger-ui/index.html> or <http://localhost:8080/rest-endpoints.html>

### Investment
#### *Endpoints*:

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/Investment-endpoints.png)  

#### *DTO*:   

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/InvestmentDto.png)

### Saving
#### *Endpoints*:

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/saving-endpoints.png)  

#### *DTOes*:   

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/SavingDto.png)  

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/SavingTransactionDto.png)  

### Income
#### *Endpoints*:

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/Income-endpoints.png)  

#### *DTO*:   

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/IncomeDto.png)

### Goal
#### *Endpoints*:

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/Goal-endpoints.png)  

#### *DTOes*:   

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/GoalDto.png)  

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/GoalContributionDto.png)  

### Expense
#### *Endpoints*:

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/Expense-endpoints.png)  

#### *DTO*:   

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/ExpenseDto.png)

### Budget
#### *Endpoints*:

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/Budget-endpoints.png)  

#### *DTOes*:   

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/BudegetDto.png)  

![alt text](https://github.com/RadoSlav220/Personal-Finance-Management-App/blob/docs/BudegetSpendingDto.png)
