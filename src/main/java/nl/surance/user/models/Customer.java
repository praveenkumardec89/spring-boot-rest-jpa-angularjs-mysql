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
@Table(name= "customer")
public class Customer implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "customer_id", unique = true)
    private int id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "customer_age", nullable = false)
    private int age;

    @Column(name = "customer_account", nullable = true)
    private int account;
    
    public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	@Column(name = "sex", nullable = false)
    private char sex;
    
    @Column(name = "birth", nullable = true)
    private Date dob;
    
    @Column(name = "api_key", unique = false)
    private String api_key;
    
    
    public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getId() {
        return id;
    }

    public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return id + "\t" + name + "\t" + age;
    }
}