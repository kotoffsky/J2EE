package tests;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

// import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import persons.Person;
import persons.SQLPersonsDB;
import persons.TestPersonDB;


public class TestSQLPersonsDB {

    // Arguments: DBMS host, database, username, and password, resp., in args[0], ..., args[3]
    public static void main (String [] args) {
        if (args.length!=4) {
            System.err.println("You must specify DB host, database, username, and password as arguments");
            System.exit(1);
        }
        System.out.print("Testing class SQLProductsDB... ");
        System.out.flush();
        SQLPersonsDB db=null;
        Connection link=null;
        try {
            link=TestSQLPersonsDB.createLink(args[0],args[1],args[2],args[3]);
            db=new SQLPersonsDB(link,"tablePersonsTest");
            System.out.println(db);
            TestSQLPersonsDB.test(db);
        } catch (SQLException e) {
            System.out.println("Exception: "+e);
        } catch (AssertionError e) {
            System.out.println("AssertionError e: "+e);
        } finally {
            try {
                db.deleteTables();
            } catch (SQLException e) {
                System.out.println("Exception: "+e);
            }
            try {
                link.close();
            } catch (SQLException e) {
                System.out.println("Exception: "+e);
            }
        }
        System.out.println("OK");
    }

    protected static Connection createLink (String host, String database, String username, String password) throws SQLException {
        MysqlDataSource ds=new MysqlDataSource();
        ds.setServerName(host);
        ds.setDatabaseName(database);
        Connection link=ds.getConnection(username,password);
        if (!link.isValid(0)) {
            throw new SQLException("Failed to initialize a valid connection to database.");
        }
        return link;
    }

    protected static void test (SQLPersonsDB db) throws SQLException, AssertionError {
        db.createTables();
        try {
			db.create(new Person("Dupont","Marie","marie.dupont@mail.fr"),"eiram");
			db.create(new Person("Martin","Jean","jean.martin@mail.com"),"naej");
	        db.create(new Person("Durand","Nicolas","nicolas.durand@mail.com"),"salocin");
	        db.create(new Person("Lefevre","Emilie","emilie.lefevre@mail.fr"),"eilime");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        List<Person> res=db.retrieveAll();
        System.out.println(res);
        assert res.size()==4 : "diff de 4";
        boolean personExist=false;
        String personName="";
        String firstName="";
        for (Person p: res) {
            if ("marie.dupont@mail.fr".equals(p.getEmail())) {
            	personExist=true;
                personName=p.getName();
                firstName=p.getFirstName();
                break;
            }
        }
        assert personExist : "personne n'existe pas";
        assert personName.equals("Dupont") : "dupont n'existe pas";
        assert firstName.equals("Marie") : "marie n'existe pas";
        Person p=db.retrieve("jean.martin@mail.com");
        assert "jean.martin@mail.com".equals(p.getEmail()): "email n'existe pas";
        assert p.getName().equals("Martin") : "martin n'existe pas";
        assert p.getFirstName().equals("Jean") : "jean n'existe pas";
        db.delete(p);
        assert db.retrieveAll().size()==3 : "diff de 3";
        assert db.retrieve("jean.martin@mail.com")==null : "non null";
    }

}
