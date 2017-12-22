# REST APIs WITH SPRING BOOT 

## Stories

* Create an account for a given user *
* Get the current balance on an account *
* Withdraw cash from the account *

## Product backlog

* *As* a customer *I want* to create an account *so that* I can have a savings account.
  * **Acceptance criteria**
    * The user should be able to create an account.
    * should get an error.
* *As* a customer *I want* to check my balance *so that* I can plan.   
  * **Acceptance criteria**
    * given customer account API should return the balance.
    * Error if account does not exist.
* *As* a customer *I want* to withdraw money *so that* can spend.
  * **Acceptance criteria**
    * Update the balance with with drawn amount.

## Application

* The technologies used are:
  * [Spring-Boot](http://projects.spring.io/spring-boot/)
  * [Material Design Light](http://www.getmdl.io/)
  * [Maven](https://maven.apache.org/)
  * [Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
  * [Angular Js](https://angular.io/)
  * [JPA/Hibernate](https://projects.spring.io/spring-data-jpa/)
* The application also has below things to visual representation 
  * A static index page
  * A CSS file containing the styling
  * A Spring-MVC `RestController` handling requests:
## Tables to be Created in Mysql
 * CREATE TABLE customer (customer_id int(11), customer_name VARCHAR(20), customer_age VARCHAR(20),customer_account INT(11), sex CHAR(1), birth DATE, PRIMARY KEY (customer_id));
* CREATE TABLE accounts(customer_account INT(11), account_balance INT(11), account_creation_date DATE, PRIMARY KEY (customer_account));
* CREATE TABLE apikeys(api_key VARCHAR(100), key_creation_date DATE, PRIMARY KEY (api_key));

* The application can simply be started from your IDE, or by using the Maven command ```mvn spring-boot:run``` 
## API Endpoints
* http://localhost:8080/api/user/createAccount
* http://localhost:8080/api/user/getKey
* http://localhost:8080/api/user/17584976?api_key=a3cec6ef-159a-4c3e-a4ec-b6722f54a8ac
* http://localhost:8080/api/user/17584976/235?api_key=a3cec6ef-159a-4c3e-a4ec-b6722f54a8ac
* APIs consume JSON if verbose is not GET.
* For example to createAccount API request is 
```
{ "name":"mj", 
"age":55, 
"id":9,
"api_key":"a3cec6ef-159a-4c3e-a4ec-b6722f54a8ac",
"sex":"M",
"dob":"2017-12-20"
}
```
* Finally Go to http://localhost:8080/ after starting the app to get the API key.
