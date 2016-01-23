package bonjour;

import service.Service;

public class AssServicePerson {
	private Long id;
	private String personID;
	private Service service;
	private String typeService;
	
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
