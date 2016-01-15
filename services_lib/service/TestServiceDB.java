package service;

import java.util.Collection;
import java.util.Date;

/**
 * A class for running some basic tests on classes which implement interface IServiceDB.
 * @author Lahbib, Kharaim
 * @since Novembre, 2015
 */
public class TestServiceDB {

    /**
     * Runs a series of tests on an instance of a class which implements IServiceDB.
     * The instance is assumed to represent an empty database of persons when passed to
     * this method. If tests go well, the database is empty again when the method exits.
     * The method uses assertions to run tests.
     * @param instance An instance of the class to be tested, representing an empty
     * database of persons
     * @throws Exception if an unexpected error occurs
     */
    public static void test (IServiceDB instance) throws Exception {
        instance.create(new Service("Service1","Desc1","Infomatique",new Date()));
        instance.create(new Service("Service1","Desc1","Infomatique",new Date()));
        
        // Testing "R" methods
        Collection<Service> all=instance.retrieveAll();
        assert all.size()==2:"non 2";
        
    }

}

