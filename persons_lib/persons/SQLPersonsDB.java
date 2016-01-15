package persons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class for SQL storage of persons in a database. The name of the person is
 * taken to be its primary key.
 * 
 * @author Lahbib, Kharaim
 * @since Novembre, 2015
 */
public class SQLPersonsDB implements IPersonDB {

	/** The name for the SQL table where to store persons. */
	protected String table;

	/** A prepared statement for creations. */
	private PreparedStatement createPersonStatement;

	/** A prepared statement for retrieval of one person. */
	private PreparedStatement retrievePersonStatement;

	/** A link to the database. */
	protected Connection link;

	/**
	 * Builds a new instance.
	 * 
	 * @param link
	 *            An active connection to an SQL database
	 * @param table
	 *            The name of the table where to store persons
	 * @throws SQLException
	 *             if a database access error occurs
	 */
	public SQLPersonsDB(Connection link, String table) throws SQLException {
		this.link = link;
		this.table = table;
		String query = null;
		query = "INSERT INTO `" + this.table + "` VALUES(?,?,?,?)";
		this.createPersonStatement = this.link.prepareStatement(query);
		query = "SELECT * FROM `" + this.table + "` WHERE email=?";
		this.retrievePersonStatement = this.link.prepareStatement(query);
	}

	// Methods

	/**
	 * Resets the link to the database. This method can be used in case the
	 * connection breaks down.
	 * 
	 * @param link
	 *            An active link to the database
	 */
	public void setLink(Connection link) {
		this.link = link;
	}

	/**
	 * Creates the necessary table in the database. Nothing occurs if the table
	 * already exists.
	 * 
	 * @throws SQLException
	 *             if a database access error occurs
	 */
	public void createTables() throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS `" + this.table + "` (";
		query += "`name` VARCHAR(100) NOT NULL, ";
		query += "`firstName` VARCHAR(100) NOT NULL, ";
		query += "`email` VARCHAR(100) NOT NULL, ";
		query += "`password` VARCHAR(100) NOT NULL, ";
		query += "PRIMARY KEY (`email`) ";
		query += ")";
		Statement statement = this.link.createStatement();
		statement.execute(query);
	}

	/**
	 * Stores a new person in the database.
	 * 
	 * @param p
	 *            The person to store
	 * @throws SQLException
	 *             if a database access error occurs
	 */
	public void create(Person p, String password) throws Exception {
		this.createPersonStatement.setString(1, p.getName());
		this.createPersonStatement.setString(2, p.getFirstName());
		this.createPersonStatement.setString(3, p.getEmail());
		this.createPersonStatement.setString(4, password);
		this.createPersonStatement.execute();
	}

	/**
	 * Retrieves all the persons in the database.
	 * 
	 * @return A list of all persons in the database
	 * @throws SQLException
	 *             if a database access error occurs
	 */
	public List<Person> retrieveAll() throws SQLException {
		String query = "SELECT * FROM `" + this.table + "`";
		ResultSet rs = null;
		Statement statement = this.link.createStatement();
		rs = statement.executeQuery(query);
		List<Person> res = new ArrayList<Person>();
		while (rs.next()) {
			res.add(new Person(rs.getString("name"), rs.getString("firstName"),
					rs.getString("email")));
		}
		return res;
	}

	/**
	 * Retrieves a person in the database.
	 * 
	 * @param email
	 *            The email of the person
	 * @return A product, or null if none with the given name exists in the
	 *         database
	 * @throws SQLException
	 *             if a database access error occurs
	 */
	public Person retrieve(String email) throws SQLException {
		this.retrievePersonStatement.setString(1, email);
		ResultSet rs = this.retrievePersonStatement.executeQuery();
		if (!rs.next()) {
			return null;
		}
		return new Person(rs.getString("name"), rs.getString("firstName"),
				rs.getString("email"));
	}

	/**
	 * Drops the table from the database. Nothing occurs if the table does not
	 * exist.
	 * 
	 * @throws SQLException
	 *             if a database access error occurs
	 */
	public void deleteTables() throws SQLException {
		String query = "DROP TABLE IF EXISTS `" + this.table + "`";
		Statement statement = this.link.createStatement();
		statement.execute(query);
	}

	/**
	 * Deletes a person. Nothing occurs in case the person does not exist in the
	 * database.
	 * 
	 * @param person
	 *            The person
	 * @throws SQLException
	 *             if a database access error occurs
	 */
	public void delete(Person person) throws SQLException {
		String query = "DELETE FROM `" + this.table + "` WHERE email=\""
				+ person.getEmail() + "\"";
		Statement statement = this.link.createStatement();
		statement.execute(query);
	}

	@Override
	public Collection<String> retrieveAllEmails() throws Exception {
		String query = "SELECT email FROM `" + this.table + "`";
		ResultSet rs = null;
		Statement statement = this.link.createStatement();
		rs = statement.executeQuery(query);
		List<String> res = new ArrayList<String>();
		while (rs.next()) {
			res.add(rs.getString("email"));
		}
		return res;
	}

	@Override
	public boolean isValid(String email, String password) throws Exception {
		String query = "SELECT email FROM `" + this.table + "` where email='"+email+ "' AND password='" + password+"'";
		System.out.println(query);
		ResultSet rs = null;
		Statement statement = this.link.createStatement();
		rs = statement.executeQuery(query);
		if (rs.next()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean exists(String email) throws Exception {
		String query = "SELECT email FROM `" + this.table + "` WHERE email='" + email + "'";
		ResultSet rs = null;
		Statement statement = this.link.createStatement();
		rs = statement.executeQuery(query);
		List<String> res = new ArrayList<String>();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	@Override
	public void update(String email, Person person) throws Exception {
		String query = "UPDATE `" + this.table + "` WHERE email=`" + email + "` VALUES (?,?,?)";
		PreparedStatement statement = this.link.prepareStatement(query);
		statement.setString(1, person.getName());
		statement.setString(2, person.getFirstName());
		statement.setString(3, person.getEmail());
		statement.execute();
	}

	@Override
	public void updatePassword(String email, String password) throws Exception {
		String query = "UPDATE `" + this.table + "` WHERE email=`" + email + "` VALUES (?)";
		PreparedStatement statement = this.link.prepareStatement(query);
		statement.setString(1, password);
		statement.execute();
	}

	@Override
	public void delete(String email) throws Exception {
		String query = "DELETE FROM `" + this.table + "` WHERE email=?";
		PreparedStatement statement = this.link.prepareStatement(query);
		statement.setString(1, email);
		statement.execute();
	}

}
