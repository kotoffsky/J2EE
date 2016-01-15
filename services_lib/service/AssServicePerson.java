package service;

public class AssServicePerson {
	private Long id;
	private int personID;
	private Service service;
	
	public AssServicePerson() {
		// TODO Auto-generated constructor stub
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
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
	
}
