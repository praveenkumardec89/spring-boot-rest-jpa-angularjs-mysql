package nl.surance.user.impl;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import nl.surance.user.models.Accounts;
import nl.surance.user.models.Apikeys;
import nl.surance.user.models.Constants;
import nl.surance.user.models.Customer;
import nl.surance.user.service.UserService;
 
 
 @Transactional
@Service("userService")
public class UserServiceImpl implements UserService{
	 public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public static final AtomicLong counter = new AtomicLong();
    // Create an EntityManagerFactory when you start the application.
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("mqwork");


    /**
     * with draw balance.
     * 
     * @param accountNumber
     * @param amount
     * @param Account
     */
    public Accounts withdraw(long accountNumber, int amount) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Accounts account = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
             account = manager.find(Accounts.class, accountNumber);
             if (null ==account ) {
                 logger.error("Unable to get the balance for account {} ", accountNumber);
                 
                 }else {
                                
                 // Change the values
                 account.setBalance(account.getBalance() - amount);
                 // Update the student
                 manager.persist(account);
                 // Commit the transaction
                 transaction.commit();
             }
             
             

        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        
		return account;
    } 
    
    
    
    
    
    /* (non-Javadoc)
     * retrieves the balance
     * @see nl.surance.user.models.UserService#getBalance(long)
     * @param account number
     * 
     * @ return accounts
     * 
     */
    public Accounts getBalance(long accountNumber) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Accounts acc = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
            acc = manager.find(Accounts.class, accountNumber);
            
            if (null ==acc ) {
                logger.error("Unable to get the balance for account {} ", accountNumber);
                }else {
                logger.info("blance in the account is {} ", acc.getBalance());
            }
            
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
		
		return acc;
	}
     
     
    /* (non-Javadoc)
     * @see nl.surance.user.models.UserService#saveUser(nl.surance.user.models.Customer)
     * requests the customer creation
     */
    public Accounts saveUser(Customer user) {
    	System.out.println("insde impl");
    	create(user);
    	Accounts account = createAccountOf(user);
    	return account;
    }
 
    /**
     * creates account for the customer
     * @param user
     * @return account
     */
    public static Accounts createAccountOf(Customer user) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Accounts acc = new Accounts();
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
           acc.setBalance(Constants.MINIMUM_BALANCE);
           acc.setCreationDate(new SimpleDateFormat("MM-dd-yyyy").format(new Date()));
           acc.setCustomer_account(Long.parseLong(generateAccountNumber()+""+generateAccountNumber()));
            manager.persist(acc);
            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
		
		return acc;
	}

	private static long generateAccountNumber() {
		  return   (long) (Math.random()*10000);
		
	}


    /**
     * creates customer
     * @param customer
     */
    public static void create(Customer customer) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
           transaction.begin();
            // Save the customer object
            manager.persist(customer);
            
            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }

    /*
     * 
     * (non-Javadoc)
     * @see nl.surance.user.models.UserService#getApiKey()
     * generates api key and persists in DB
     */
    
    
	@Override
	public Apikeys getApiKey() {
		UUID uuid = UUID.randomUUID();

        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Apikeys keys = new Apikeys();
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
            
            keys.setApi_key(uuid.toString());
            keys.setKeyCreationDate(new Date());
            manager.persist(keys);
            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
		
		return keys;
	}



	@Override
	public boolean verifyApiKey(String api_key) {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean valid=false;
        Apikeys key=null;
        logger.info("verifying key  : {}", api_key);
        //check if the API_KEY is valid
        transaction = manager.getTransaction();
        // Begin the transaction
        transaction.begin();
        // Get the Student object
         key = manager.find(Apikeys.class, api_key);
        System.out.println(key+""+api_key);
        if (key == null ) {
            logger.error("Invalid API Key  {} ", api_key);
            valid=false;
        }else {
         valid = true;
         logger.error(" API Key  {} is valid!!", api_key);
        }
		return valid;
	}    
    
}