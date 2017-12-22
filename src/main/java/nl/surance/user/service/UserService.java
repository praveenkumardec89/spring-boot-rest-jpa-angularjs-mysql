package nl.surance.user.service;
 
 
import org.springframework.stereotype.Component;

import nl.surance.user.models.Accounts;
import nl.surance.user.models.Apikeys;
import nl.surance.user.models.Customer;
 

@Component
public interface UserService {
     
	Accounts getBalance(long id);
     
    Accounts saveUser(Customer user);
    
    Apikeys getApiKey();
    
    Accounts withdraw(long id, int amount);
    
    boolean verifyApiKey(String api_key);
     

     
}