package Database;

import com.example.schedulingsystem.ResultSetInterface;
import javafx.collections.FXCollections;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Class.Appointment;
import Class.VirtualAppointment;
import Class.InOfficeAppointment;
import Class.AppointmentReport;
import javafx.collections.ObservableList;

/***
 * This is the AppointmentCRUD class! This class performs all the insert, update and delete function to the database for
 * the appointments! It also gets data from the database for reports!
 */
public class AppointmentCRUD {

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
     * getAllAppointment
     * The getAllAppointment is a method that gets all the appointments from the database and adds it to an observable
     * list!
     * @return This method returns an observable list of Appointment for all appointments!
     * @throws Exception Exception is needed in case of failure!
     */
    public static ObservableList<Appointment> getAllAppointment() throws Exception{

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        JDBC.openConnection();
        ResultSet result = resultSet.result("SELECT * FROM APPOINTMENTS");

        while(result.next()){

            int appointmentId = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String appointmentMethod = result.getString("Appointment_Method");
            String type = result.getString("Type");
            Timestamp start = result.getTimestamp("Start");
            Timestamp end = result.getTimestamp("End");
            Timestamp createDate = result.getTimestamp("Create_Date");
            String createdBy = result.getString("Created_By");
            Timestamp lastUpdate = result.getTimestamp("Last_Update");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int patientId = result.getInt("Patient_ID");
            int userId = result.getInt("User_ID");
            int therapistId = result.getInt("Therapist_ID");

            if(appointmentMethod.equals("In Office")){

                InOfficeAppointment appointmentResult = new InOfficeAppointment(appointmentId, title, description, appointmentMethod, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, patientId, userId, therapistId);
                allAppointments.add(appointmentResult);

            } else if(appointmentMethod.equals("Virtual")) {

                VirtualAppointment appointmentResult = new VirtualAppointment(appointmentId, title, description, appointmentMethod, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, patientId, userId, therapistId);
                allAppointments.add(appointmentResult);

            }

        }

        JDBC.closeConnection();
        return allAppointments;

    }

    /***
     * getAppointmentReport
     * The getAppointmentReport is a method that gets specific appointments from the database and adds it to an observable
     * list!
     * @return This method returns an observable list of AppointmentReport for specific appointments for reports!
     * @throws Exception IOException is needed in case of failure!
     */
    public static ObservableList<AppointmentReport> getAppointmentReport(String period) throws Exception{

        ResultSet result = null;

        ObservableList<AppointmentReport> allAppointments = FXCollections.observableArrayList();
        JDBC.openConnection();

        if(period == "ALL"){

            result = resultSet.result("SELECT APPOINTMENT_ID, USER_ID, PATIENT_ID, TITLE, DESCRIPTION, APPOINTMENT_METHOD, TYPE, THERAPIST_NAME, START, END FROM APPOINTMENTS INNER JOIN THERAPISTS ON APPOINTMENTS.THERAPIST_ID = THERAPISTS.THERAPIST_ID ORDER BY APPOINTMENT_ID ASC");

        }else if(period == "WEEKLY"){

            result = resultSet.result("SELECT APPOINTMENT_ID, USER_ID, PATIENT_ID, TITLE, DESCRIPTION, APPOINTMENT_METHOD, TYPE, THERAPIST_NAME, START, END FROM APPOINTMENTS INNER JOIN THERAPISTS ON APPOINTMENTS.THERAPIST_ID = THERAPISTS.THERAPIST_ID WHERE YEARWEEK(START) = YEARWEEK(NOW()) ORDER BY APPOINTMENT_ID ASC");

        }else if(period == "MONTHLY"){

            result = resultSet.result("SELECT APPOINTMENT_ID, USER_ID, PATIENT_ID, TITLE, DESCRIPTION, APPOINTMENT_METHOD, TYPE, THERAPIST_NAME, START, END FROM APPOINTMENTS INNER JOIN THERAPISTS ON APPOINTMENTS.THERAPIST_ID = THERAPISTS.THERAPIST_ID WHERE MONTH(START) = MONTH(NOW()) ORDER BY APPOINTMENT_ID ASC");

        }

        while(result.next()){

            int appointmentId = result.getInt("Appointment_ID");
            int userId = result.getInt("User_ID");
            int patientId = result.getInt("Patient_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String appointmentMethod = result.getString("Appointment_Method");
            String type = result.getString("Type");
            String therapist = result.getString("Therapist_NAME");
            Timestamp start = result.getTimestamp("Start");
            Timestamp end = result.getTimestamp("End");

            AppointmentReport appointmentResult= new AppointmentReport(appointmentId, userId, patientId, title, description, appointmentMethod, type, therapist, start, end);
            allAppointments.add(appointmentResult);

        }

        JDBC.closeConnection();
        return allAppointments;

    }

    /***
     * insertAppointment
     * The insertAppointment is a method that perform an insert statement to add a valid appointment to the database!
     * @param appointmentId A parameter of appointmentId is passed in for the appointment id!
     * @param userId A parameter of userId is passed in for the user id!
     * @param patientId A parameter of patientId is passed in for the patient id!
     * @param title A parameter of title is passed in for the title!
     * @param description A parameter of description is passed in for the description!
     * @param therapistId A parameter of therapistId is passed in for the therapist id!
     * @param type A parameter of type is passed in for the type!
     * @param start A parameter of start is passed in for the start timestamp!
     * @param end A parameter of end is passed in for the end timestamp!
     * @param createDate A parameter of createDate is passed in for the end creation timestamp!
     * @param createdBy A parameter of createdBy is passed in for the user's name!
     * @param lastUpdate A parameter of lastUpdate is passed in for the update timestamp!
     * @param lastUpdatedBy A parameter of lastUpdatedBy is passed in for the user's name!
     */
    public static void insertAppointment(int appointmentId, int userId, int patientId, String title, String description, String appointmentMethod, int therapistId, String type, Timestamp start, Timestamp end, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy){

        JDBC.openConnection();

        String sqlStatement = "INSERT INTO APPOINTMENTS VALUES('" + appointmentId + "', '" + title + "', '" + description + "', '" + appointmentMethod + "', '" + type + "', '"  + start + "', '" + end + "', '" + createDate + "', '" + createdBy + "', '" + lastUpdate + "', '" + lastUpdatedBy + "', " + patientId + ", " + userId + ", " + therapistId + ")";
        Query.makeQuery(sqlStatement);

        JDBC.closeConnection();

    }

    /***
     * updateAppointment
     * The updateAppointment is a method that perform an update statement to update a valid appointment to the database!
     * @param appointmentId A parameter of appointmentId is passed in for the appointment id!
     * @param userId A parameter of userId is passed in for the user id!
     * @param patientId A parameter of patientId is passed in for the patient id!
     * @param title A parameter of title is passed in for the title!
     * @param description A parameter of description is passed in for the description!
     * @param therapistId A parameter of therapistId is passed in for the therapist id!
     * @param type A parameter of type is passed in for the type!
     * @param start A parameter of start is passed in for the start timestamp!
     * @param end A parameter of end is passed in for the end timestamp!
     * @param createDate A parameter of createDate is passed in for the end creation timestamp!
     * @param createdBy A parameter of createdBy is passed in for the user's name!
     * @param lastUpdate A parameter of lastUpdate is passed in for the update timestamp!
     * @param lastUpdatedBy A parameter of lastUpdatedBy is passed in for the user's name!
     */
    public static void updateAppointment(int appointmentId, int userId, int patientId, String title, String description, String appointmentMethod, int therapistId, String type, Timestamp start, Timestamp end, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy){

        JDBC.openConnection();

        String sqlStatement = "UPDATE APPOINTMENTS SET TITLE = '" + title + "', DESCRIPTION = '" + description + "', APPOINTMENT_METHOD = '" + appointmentMethod + "', TYPE = '" + type + "', START = '"  + start + "', END = '" + end + "', CREATE_DATE = '" + createDate + "', CREATED_BY = '" + createdBy + "', LAST_UPDATE = '" + lastUpdate + "', LAST_UPDATED_BY = '" + lastUpdatedBy + "', PATIENT_ID = " + patientId + ", USER_ID = " + userId + ", THERAPIST_ID = " + therapistId + " WHERE APPOINTMENT_ID = " + appointmentId;
        Query.makeQuery(sqlStatement);

        JDBC.closeConnection();

    }

    /***
     * deleteAppointment
     * The deleteAppointment is a method that perform a delete statement to delete an appointment to the database!
     * @param selectedAppointment A parameter of the selectedAppointment is passed in to know which appointment to delete!
     * @return This method returns a true or false value!
     * @throws Exception Exception is needed in case of failure!
     */
    public static boolean deleteAppointment(Appointment selectedAppointment) throws Exception {

        for(Appointment appointment : AppointmentCRUD.getAllAppointment()){

            if(appointment.getAppointment_Id() == selectedAppointment.getAppointment_Id()){

                JDBC.openConnection();

                String sqlStatement = "DELETE FROM APPOINTMENTS WHERE APPOINTMENT_ID = " + selectedAppointment.getAppointment_Id();
                Query.makeQuery(sqlStatement);

                JDBC.closeConnection();

            }

        }

        return false;

    }

}
