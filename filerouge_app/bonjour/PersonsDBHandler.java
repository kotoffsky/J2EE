package bonjour;

import persons.SQLPersonsDB;
import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * A class for accessing the database for persons. This class can be used as a bean with (read-only) property "db".
 * Strings host, database, username, password, and table are expected to be found.
 * At any moment there is only one instance of the link to the database, and one instance of the SQLPersonsDB class, maintained by this class.
 * Connector/J is used for accessing the DBMS.
 * @author Kharaim Nikita, Lahbib Amanie
 * @since November, 2015
 */
public class PersonsDBHandler {

    /** The unique link to the database (null if none active). */
    private static Connection link;

    /** The unique instance of class SQLPersonsDB (null if none). */
    private static SQLPersonsDB db;

    /**
     * Builds a new instance, using the strings used in the environment.
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    public PersonsDBHandler () throws NamingException, SQLException {
        if (PersonsDBHandler.db==null) {
            PersonsDBHandler.initialize();
            
        }
    }

    /**
     * Returns the instance of SQLPersonsDB.
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    public SQLPersonsDB getDb () throws NamingException, SQLException {
        if (PersonsDBHandler.db==null) {
            PersonsDBHandler.initialize();
        }
        return PersonsDBHandler.db;
    }

    /**
     * Releases the connection to the database.
     * @throws SQLException if any problem occurs while closing the connection
     */
    public static void close () throws SQLException {
        if (PersonsDBHandler.link!=null) {
            PersonsDBHandler.link.close();
        }
    }

    // Helper methods =====================================================================

    /**
     * Initializes the connection to the database and the instance of SQLPersonsDB.
     * For each of these objects, nothing occurs if it is already initialized.
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    private static void initialize () throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        String host=initialContext.doLookup("java:comp/env/host");
        String database=initialContext.doLookup("java:comp/env/database");
        String username=initialContext.doLookup("java:comp/env/username");
        String password=initialContext.doLookup("java:comp/env/password");
        
        PersonsDBHandler.db=new SQLPersonsDB(PersonsDBHandler.getLink(host,database,username,password),"persons");
        PersonsDBHandler.db.createTables();
    }

    /**
     * Returns the link to the database, which is active.
     * @param host The hostname for the DBMS
     * @param database The name for the database to use in the DBMS
     * @param username The username for connecting to the database
     * @param password The password for connecting to the database
     * @return An active link to the database
     * @throws SQLException if no active link can be established
     */
    private static Connection getLink (String host, String database, String username, String password) throws SQLException {
        if (PersonsDBHandler.link==null) {
            MysqlDataSource ds=new MysqlDataSource();
            ds.setServerName(host);
            ds.setDatabaseName(database);
            PersonsDBHandler.link=ds.getConnection(username,password);
        }
        if (!PersonsDBHandler.link.isValid(0)) {
            throw new SQLException("Failed to initialize a valid connection to database.");
        }
        return PersonsDBHandler.link;
    }

}
