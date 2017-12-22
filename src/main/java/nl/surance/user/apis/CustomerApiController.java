package nl.surance.user.apis;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nl.surance.user.errors.CustomErrorType;
import nl.surance.user.models.Accounts;
import nl.surance.user.models.Apikeys;
import nl.surance.user.models.Customer;
import nl.surance.user.service.UserService;
 
@RestController
@RequestMapping("/api/user")
public class CustomerApiController {
 
	 // Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("mqwork");
    public static final Logger logger = LoggerFactory.getLogger(CustomerApiController.class);
 
    @Autowired
    UserService userService; //Service which will do all data retrieval/manipulation work
 
   
    
    
    
 
    /**
     * Method to with draw balance
     * @param customerAccount
     * @param amount
     * @return Account
     * 
     */
    @RequestMapping(value = "/{customer_account}/{amount}", method = RequestMethod.PUT)
    public ResponseEntity<?> withdraw(@PathVariable("customer_account") long customerAccount, @PathVariable("amount") int amount, @RequestParam String api_key) {
    	
    	//key check 
        if (!verifyKey(api_key)) {
            logger.error("Invalid Key {} ", api_key);
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("unable to get balance " +  customerAccount + " "
            		+ " Invalid API_KEY"),HttpStatus.BAD_REQUEST);
        }       
        
    	//balance process
    	
        logger.info("Fetching the account details for  {}", customerAccount);
        logger.info("deducting the amount of  {}", amount);
        Accounts account = userService.withdraw(customerAccount, amount);
        if (account == null) {
            logger.error("account  {} not found.", customerAccount);
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("account with id " + customerAccount 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Accounts>(account, HttpStatus.OK);
    }
  
    
    
    
   
 
    /**
     * method to create account
     * @param customer
     * @param api_key
     * @return Account
     */
    @RequestMapping(value = "/createAccount", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<?> createCustomerAccount(@RequestBody Customer customer) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        logger.info("Creating User : {}", customer);
        //check if the API_KEY is valid
        transaction = manager.getTransaction();
        // Begin the transaction
        transaction.begin();
        // Get the Student object
        Apikeys api = manager.find(Apikeys.class, customer.getApi_key());
        System.out.println(api);
        if (null ==api ) {
            logger.error("Unable to create. A User with name {} ", customer.getName());
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Unable to create. A User with name " +  customer.getName() + " "
            		+ " Invalid API_KEY"),HttpStatus.BAD_REQUEST);
        }else {
            //    create(user.getId(), user.getName(), user.getAge());
         //   userService.saveUser(customer);
            
            HttpHeaders headers = new HttpHeaders();

            return new ResponseEntity<Accounts>(userService.saveUser(customer), HttpStatus.CREATED);
        }
        
    }
 
    
 
    /**
     * Method request balance
     * @param accountNumber
     * @param api_key
     * @return Accounts
     */
    @RequestMapping(value = "/{customer_account}", method = RequestMethod.GET)
    public ResponseEntity<?> getBalance(@PathVariable("customer_account") long accountNumber, @RequestParam String api_key) {
        logger.info("Requesting Balance for account {}", accountNumber);
        Accounts accountBalance = userService.getBalance(accountNumber);
 
        if (!verifyKey(api_key)) {
            logger.error("Invalid Key {} ", api_key);
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Unable to create. A User with name " +  accountNumber + " "
            		+ " Invalid API_KEY"),HttpStatus.BAD_REQUEST);
        }       
        
        
        if (accountBalance == null) {
            logger.error("Unable to get balance : {} not found.", accountNumber);
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Unable to get balance : " + accountNumber + " not found."),
                    HttpStatus.NOT_FOUND);
        }else {
        	return new ResponseEntity<Accounts>(accountBalance, HttpStatus.OK);
        }
        
    }

    /**
     * Method request balance
     * @param accountNumber
     * @param api_key
     * @return Accounts
     */
    @RequestMapping(value = "/getKey", method = RequestMethod.GET)
    public ResponseEntity<Apikeys> getKey() {
        logger.info("Requesting Balance for key ");
    //    Accounts accountBalance = userService.getBalance(accountNumber);
         	return new ResponseEntity<Apikeys>(userService.getApiKey(), HttpStatus.OK);
        
        
    }




	private boolean verifyKey(String api_key) {
		 EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
	        EntityTransaction transaction = null;
	        logger.info("verifying key : {}", api_key);
	        //check if the API_KEY is valid
	        transaction = manager.getTransaction();
	        // Begin the transaction
	        transaction.begin();
	        // Get the Student object
	        Apikeys key = manager.find(Apikeys.class, api_key);
	        System.out.println(key);
		return key!=null;
	}
 

 
}