package service;

import java.util.Collection;

public interface IServiceDB {
	public void create (Service p);

	public Collection<Service> retrieveAll();
	

}
