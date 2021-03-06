package persons;

import java.util.Collection;

/**
 * An interface for databases of persons. The interface essentially declares CRUD operations,
 * and operations to add/check passwords (while these are not stored in class Person).
 * The key for distinguishing two persons from each other is assumed to be the email address.
 * @author Charlotte Lecluze and Bruno Zanuttini, Universit&eacute; de Caen Basse-Normandie, France
 * @since February, 2013
 */
public interface IPersonDB {

    // "C" operations

    /**
     * Adds a person to this database.
     * @param p The person to add
     * @param password The password for p
     * @throws Exception if a person with the same email address already exists in the list or
     * a database access error occurs
     */
    public void create (Person p, String password) throws Exception;

    // "R" operations

    /**
     * Returns the list of all persons in this databse.
     * @return The list of all persons in this databse as an instance of java.util.CollectionPerson.
     * @throws Exception if a database access error occurs
     */
    public Collection<Person> retrieveAll () throws Exception;

    /**
     * Returns the list of all email addresses (i.e., primary keys) in this database.
     * @return The list of all logins in this database as an instance of java.util.CollectionString.
     * @throws Exception if a database access error occurs
     */
    public Collection<String> retrieveAllEmails () throws Exception;

    /**
     * Returns the person with a given email (i.e., primary key).
     * @param email The email to search for
     * @return The person with the given email in the list
     * @throws Exception if no person with the given email exists in the list or a database access error
     * occurs
     */
    public Person retrieve (String email) throws Exception;

    /**
     * Decides whether a given couple (email,password) exists in the list.
     * @return false if either there is no person with the given email in the
     * list or the password is incorrect, true otherwise
     * @throws Exception if a database access error occurs
     */
    public boolean isValid (String email, String password) throws Exception;

    /**
     * Decides whether a person with a given email exists in the list.
     * @return true is the list contains a person with the given email,
     * false otherwise
     * @throws Exception if a database access error occurs
     */
    public boolean exists (String email) throws Exception;

    // "U" operations

    /**
     * Updates the person associated to a given email address in the database.
     * Given that the email address is part of an instance of Person, this method
     * may be safely used to change the email address itself.
     * @param email The email address of the person to update
     * @param person An instance of Person to store in place of the existing one
     * @throws Exception if no person is currently associated to the given email address,
     * or the new instance has an email address which already exists in the database, or a
     * database access error occurs
     */
    public void update (String email, Person person) throws Exception;

    /**
     * Updates a person's password in the database.
     * @param email The email address of the person whose password to update
     * @param password The new password for the person
     * @throws Exception if no person is currently associated to the given email address
     * or a database access error occurs
     */
    public void updatePassword (String email, String password) throws Exception;

    // "D" operations

    /**
     * Removes the person with a given email address from this database.
     * @param email The email address of the person to remove
     * @throws Exception if no person is currently associated to the given email address
     * or a database access error occurs
     */
    public void delete (String email) throws Exception;

}
