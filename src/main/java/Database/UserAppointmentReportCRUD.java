package Database;

import com.example.schedulingsystem.ResultSetInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Class.UserAppointmentsReport;

import java.sql.ResultSet;
import java.sql.Timestamp;

/***
 * This is the UserAppointmentReportCRUD class! This class performs a select statement to retrieve all the users and
 * their appointments count by month!
 */
public class UserAppointmentReportCRUD {

	/***
	 * This is a lambda that i created! The code within the lambda executes a query and returns
	 * a resultset! It passes in a string with is the SQL statement! My justification to using
	 * the resultset for my lambda is because there are so queries that needs
	 * to be made and this lambda saves me a lot of time in code writing!
	 */
	static ResultSetInterface resultSet = sql -> {

		Query.makeQuery(sql);
		ResultSet rs = Query.getResult();

		return rs;

	};

	/***
	 * getUserAppointmentsReport
	 * The method getUserAppointmentsReport gets all the user name and their total count of appointments by month!
	 * @param month A parameter month is passed in!
	 * @return This method returns an observable list of UserAppointmentsReport!
	 * @throws Exception Exception is needed in case of failure!
	 */
	public static ObservableList<UserAppointmentsReport> getUserAppointmentsReport(String month) throws Exception{

		ObservableList<UserAppointmentsReport> allUserAppointmentsReport = FXCollections.observableArrayList();
		JDBC.openConnection();
		ResultSet result = resultSet.result("SELECT USER_NAME, COUNT(*) AS COUNT FROM APPOINTMENTS INNER JOIN USERS WHERE MONTHNAME(START) = '" + month + "' AND APPOINTMENTS.USER_ID = USERS.USER_ID GROUP BY USER_NAME");

		while(result.next()){

			String username = result.getString("User_Name");
			int count = result.getInt("Count");

			UserAppointmentsReport userAppointmentsReportResult= new UserAppointmentsReport(username, count);
			allUserAppointmentsReport.add(userAppointmentsReportResult);

		}

		JDBC.closeConnection();
		return allUserAppointmentsReport;

	}

}
