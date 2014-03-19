package db.connecter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static final Connection conn = connector();

    private static Connection connector() {
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/worder";
	String user = "root";
	String password = "root";
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection(url, user, password);
	} catch (ClassNotFoundException exc) {
	    exc.printStackTrace();
	} catch (SQLException exc) {
	    exc.printStackTrace();
	}
	return conn;
    }

    public static Connection getConnection() {
	return conn;
    }

}
