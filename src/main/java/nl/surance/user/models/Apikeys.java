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
@Table(name= "apikeys")
public class Apikeys implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "api_key", unique = true)
    private String api_key;

    @Column(name = "key_creation_date", nullable = true)
    private Date keyCreationDate;

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public Date getKeyCreationDate() {
		return keyCreationDate;
	}

	public void setKeyCreationDate(Date keyCreationDate) {
		this.keyCreationDate = keyCreationDate;
	}

 
  
}