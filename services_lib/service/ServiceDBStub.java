package service;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * A stub for interface IServiceDB, which does provide RAM persistency but no long-term persistency.
 * @author Lahbib, Kharaim
 * @since Novembre, 2015
 */
public class ServiceDBStub implements IServiceDB {

    /** The list of services */
    protected List<Service> services;

    /**
     * Builds a new, empty list of services.
     */
    public ServiceDBStub () {
        this.services=new ArrayList<Service> ();
    }

    @Override
    public void create (Service p) {
        this.services.add(p);
    }

    @Override
    public Collection<Service> retrieveAll () {
        return this.services;
    }
//
//    @Override
//    public Collection<String> retrieveAllEmails () {
//        Collection<String> res=new ArrayList<String> ();
//        for (Person p: this.persons) {
//            res.add(p.getEmail());
//        }
//        return res;
//    }
//
//    @Override
//    public Person retrieve (String email) throws IndexOutOfBoundsException {
//        for (Person p: this.persons) {
//            if (p.getEmail().equals(email)) {
//                return p;
//            }
//        }
//        throw new IndexOutOfBoundsException("No person with email "+email);
//    }
//
//    @Override
//    public boolean isValid (String email, String password) {
//        int i;
//        for (i=0; i<this.persons.size(); i++) {
//            if (this.persons.get(i).getEmail().equals(email)) {
//                break;
//            }
//        }
//        if (i==this.persons.size()) {
//            return false;
//        }
//        return this.passwords.get(i).equals(password);
//    }
//
//    @Override
//    public boolean exists (String email) {
//        for (Person p: this.persons) {
//            if (p.getEmail().equals(email)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void update (String email, Person person) throws IndexOutOfBoundsException {
//        int index=-1;
//        for (int i=0; i<this.persons.size(); i++) {
//            if (this.persons.get(i).getEmail().equals(email)) {
//                index=i;
//                break;
//            }
//        }
//        if (index==-1) {
//            throw new IndexOutOfBoundsException("No person with email "+email);
//        }
//        this.persons.set(index,person);
//    }
//
//    @Override
//    public void updatePassword (String email, String password) throws IndexOutOfBoundsException {
//        int index=-1;
//        for (int i=0; i<this.persons.size(); i++) {
//            if (this.persons.get(i).getEmail().equals(email)) {
//                index=i;
//                break;
//            }
//        }
//        if (index==-1) {
//            throw new IndexOutOfBoundsException("No person with email "+email);
//        }
//        this.passwords.set(index,password);
//    }
//
//    @Override
//    public void delete (String email) throws IndexOutOfBoundsException {    
//        int index=-1;
//        for (int i=0; i<this.persons.size(); i++) {
//            if (this.persons.get(i).getEmail().equals(email)) {
//                index=i;
//                break;
//            }
//        }
//        if (index==-1) {
//            throw new IndexOutOfBoundsException("No person with email "+email);
//        }
//        this.persons.remove(index);
//    }

}
