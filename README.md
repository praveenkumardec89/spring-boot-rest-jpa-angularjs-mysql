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
* The application also has below things to visual representation 
  * A static index page
  * A CSS file containing the styling
  * A JavaScript file for validation
  * A Spring-MVC `RestController` handling requests:

* The application can simply be started from your IDE, or by using the Maven command ```mvn spring-boot:run``` 