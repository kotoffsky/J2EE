package bonjour;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.type.TimestampType;

import service.Service;

/**
 * Association class of a service to a user
 * @author Nick Kharaim, Amani Lahbib
 *
 */
public class AssServicePerson {
	private Long id;
	private String personID;
	private Service service;
	private String typeService;
	private Long dateDeLimite;
	
	public Long getDateDeLimite() {
		return dateDeLimite;
	}

	public void setDateDeLimite(Long timestamp) {
		this.dateDeLimite = timestamp;
	}

	public AssServicePerson() {
		// TODO Auto-generated constructor stub
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service serivce) {
		this.service = serivce;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeService() {
		return typeService;
	}

	public void setTypeService(String typeService) {
		this.typeService = typeService;
	}
	
}
