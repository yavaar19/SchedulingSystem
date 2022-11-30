package Database;

import com.example.schedulingsystem.ResultSetInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Class.TherapistSchedule;

import java.sql.ResultSet;
import java.sql.Timestamp;

/***
 * This is the TherapistScheduleCRUD class! This class performs a select statement to retrieve appointments from a
 * specific contact's name and then returns the appointments to an observable list!
 */
public class TherapistScheduleCRUD {

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
	 * getContactSchedule
	 * The method getContactSchedule gets appointments from a selected contact's name!
	 * @param therapist The parameter contact name is passed in!
	 * @return This method returns an observable list of TherapistSchedule!
	 * @throws Exception Exception is needed in case of failure!
	 */
	public static ObservableList<TherapistSchedule> getContactSchedule(String therapist) throws Exception{

		ObservableList<TherapistSchedule> allTherapist = FXCollections.observableArrayList();
		JDBC.openConnection();
		ResultSet result = resultSet.result("SELECT APPOINTMENT_ID, TITLE, TYPE, DESCRIPTION, START, END, PATIENT_ID FROM APPOINTMENTS INNER JOIN THERAPISTS WHERE APPOINTMENTS.THERAPIST_ID = THERAPISTS.THERAPIST_ID AND THERAPISTS.THERAPIST_NAME = '" + therapist + "'");

		while(result.next()){

			int appointmentId = result.getInt("Appointment_Id");
			String title = result.getString("Title");
			String type = result.getString("Type");
			String description = result.getString("Description");
			Timestamp start = result.getTimestamp("Start");
			Timestamp end = result.getTimestamp("End");
			int patientId = result.getInt("Patient_Id");

			TherapistSchedule therapistResult= new TherapistSchedule(appointmentId, title, type, description, start, end, patientId);
			allTherapist.add(therapistResult);

		}

		JDBC.closeConnection();
		return allTherapist;

	}

}
