/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * CDDatabase.java, 2017/09/25 SangLT
 */
package testjavacore_bai2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Class connect to database mysql and manipulates with database mysql
 * 
 * @author SangLT
 *
 */
public class CDDatabase {
	/**
	 * Method creates a connection to database
	 * 
	 * @param hostName
	 * @param dbName
	 * @param userName
	 * @param passwd
	 * @return con
	 */
	@SuppressWarnings("finally")
	public static Connection getConnection(String hostName, String dbName, String userName, String passwd) {
		// Initialize connection
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Set connection url
			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName
					+ "?useUnicode=yes&characterEncoding=UTF-8";
			// Connect to mysql server
			con = DriverManager.getConnection(connectionURL, userName, passwd);
		} catch (Exception e) {
			// Print error message
			System.out.println("Connection error!");
		} finally {
			return con;
		}
	}

	/**
	 * Method inserts a CD object into table `CDs`
	 * 
	 * @param insertedObj
	 * 
	 */
	public static void insertCD(CD insertedObj) {
		// Create necessary objects, variables
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Connect to database
			con = getConnection("localhost", "CDs", "root", "");
			// Query using preparedStatement
			ps = con.prepareStatement("INSERT INTO CDs VALUES (?, ?)");
			ps.setString(1, insertedObj.getArtist());
			ps.setString(2, insertedObj.getTitle());
			int i = ps.executeUpdate();
			// Result message
			if (i != 0) {
				System.out.println("Inserted.");
			} else {
				System.out.println("Not inserted.");
			}
		} catch (Exception e) {
			// Print error message and change return result
			System.out.println("Insert error.");
		} finally {
			// Close connection and return
			closeConnection(con, ps);
		}
	}

	/**
	 * Method removes a CD object from table `CDs`
	 * 
	 * @param removedObjs
	 * 
	 */
	@SuppressWarnings("finally")
	public static void removeCD(CD removedObjs) {
		// Create necessary objects, variables
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Connect to database
			con = getConnection("localhost", "CDs", "root", "");
			// Query using preparedStatement
			ps = con.prepareStatement("DELETE FROM CDs WHERE artist = ? AND title = ?");
			ps.setString(1, removedObjs.getArtist());
			ps.setString(2, removedObjs.getTitle());
			int i = ps.executeUpdate();
			// Result message
			if (i != 0) {
				System.out.println("Removed.");
			} else {
				System.out.println("Not removed.");
			}
		} catch (Exception e) {
			// Print error and change return result
			System.out.println("Remove error.");
		} finally {
			// Close connection and return
			closeConnection(con, ps);
		}
	}

	/**
	 * Method gets record(s) of table `CDs` by title field which
	 * contain @param::conditionInput
	 * 
	 * @param conditionInput
	 * @return resultObjs
	 */
	@SuppressWarnings("finally")
	public static ArrayList<CD> findByTitle(String conditionInput) {
		// Create necessary objects
		ArrayList<CD> resultObjs = new ArrayList<CD>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Connect to database
			con = getConnection("localhost", "CDs", "root", "");
			// Query using preparedStatement
			ps = con.prepareStatement("SELECT * FROM CDs WHERE title LIKE ?");
			ps.setString(1, "%" + formatCondSearch(conditionInput) + "%");
			// System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			// Fetch result record(s)
			while (rs.next()) {
				CD obj = new CD(rs.getString(1), rs.getString(2));
				resultObjs.add(obj);
			}
		} catch (Exception e) {
			// Print error message
			System.out.println("Error in fetching database.");
		} finally {
			// Close connection and return record objects
			closeConnection(con, ps);
			return resultObjs;
		}
	}

	/**
	 * Method gets record(s) of table `CDs` by artist field which
	 * contain @param::conditionInput
	 * 
	 * @param conditionInput
	 * @return resultObjs
	 */
	@SuppressWarnings("finally")
	public static ArrayList<CD> findByArtist(String conditionInput) {
		// Create necessary objects
		ArrayList<CD> resultObjs = new ArrayList<CD>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Connect to database
			con = getConnection("localhost", "CDs", "root", "");
			// Query using preparedStatement
			ps = con.prepareStatement("SELECT * FROM CDs WHERE artist = ?");
			ps.setString(1, conditionInput);
			// System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			// Fetch result record(s)
			while (rs.next()) {
				CD obj = new CD(rs.getString(1), rs.getString(2));
				resultObjs.add(obj);
			}
		} catch (Exception e) {
			// Print error message
			System.out.println("Error in fetching database.");
		} finally {
			// Close connection and return record objects
			closeConnection(con, ps);
			return resultObjs;
		}
	}

	/**
	 * Method closes connection and preparedStatement
	 * 
	 * @param con
	 * @param ps
	 */
	public static void closeConnection(Connection con, PreparedStatement ps) {
		try {
			ps.close();
			con.close();
		} catch (Exception e) {
			// Print error message
			System.out.println("Close connection failed!");
		}
	}

	/**
	 * Method checks valid input string which are inserted into database
	 * 
	 * @param inputString
	 * @return true | false
	 */
	public static boolean isValidInput(String inputString) {
		return ((inputString.length() > 0) && (inputString.length() <= 255));
	}

	/**
	 * Method format condition search, format special character in ioString
	 * 
	 * @param ioString
	 * @return ioString
	 */
	public static String formatCondSearch(String ioString) {
		// Declare special characters array
		final String[] specialChars = { "\\", "%", "_", "[", "]", "-", "!" };
		// Add slash(es) in front of each special character
		int specialCharsLeng = specialChars.length;
		for (int i = 0; i < specialCharsLeng; i++) {
			if (ioString.contains(specialChars[i])) {
				ioString = ioString.replace(specialChars[i], "\\" + specialChars[i]);
			}
		}
		return ioString;
	}
}
