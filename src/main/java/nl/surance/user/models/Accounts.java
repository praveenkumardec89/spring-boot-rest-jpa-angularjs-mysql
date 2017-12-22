package nl.surance.user.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name= "accounts")
public class Accounts implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "customer_account", unique = true)
    private long customer_account;

    @Column(name = "account_balance", nullable = false)
    private int balance;

    @Column(name = "account_creation_date", nullable = true)
    private String creationDate;

    
    
    
	public long getCustomer_account() {
		return customer_account;
	}

	public void setCustomer_account(long customer_account) {
		this.customer_account = customer_account;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
    
    
  
}