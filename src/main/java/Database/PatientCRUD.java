package Database;

import com.example.schedulingsystem.ResultSetInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Class.Patient;
import java.sql.ResultSet;
import java.sql.Timestamp;

/***
 * This is the PatientCRUD class! This performs all the insert, update and delete function to the database for the
 * patient table! It also gets data from the database for reports!
 */
public class PatientCRUD {

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
     * getAllPatient
     * The getAllPatient is a method that gets all the patients from the database and adds it to an observable
     * list!
     * @return This method returns an observable list of Patient for all patients!
     * @throws Exception IOException is needed in case of failure!
     */
    public static ObservableList<Patient> getAllPatient() throws Exception{

        ObservableList<Patient> allPatient = FXCollections.observableArrayList();
        JDBC.openConnection();
        ResultSet result = resultSet.result("SELECT * FROM PATIENTS");

        while(result.next()){

            int patientId = result.getInt("Patient_ID");
            String patientName = result.getString("Patient_Name");
            String address = result.getString("Address");
            String postalCode = result.getString("Postal_Code");
            String phone = result.getString("Phone");
            Timestamp createDate = result.getTimestamp("Create_Date");
            String createdBy = result.getString("Created_By");
            Timestamp lastUpdate = result.getTimestamp("Last_Update");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int divisionId = result.getInt("Division_ID");

            Patient patientResult = new Patient(patientId, patientName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId);
            allPatient.add(patientResult);

        }

        JDBC.closeConnection();
        return allPatient;

    }

    /***
     * insertPatient
     * The insertPatient is a method that perform an insert statement to add a valid patient to the database!
     * @param patientId A parameter of patientId is passed in for the patient id!
     * @param patientName A parameter of patientName is passed in for the patient name!
     * @param patientAddress A parameter of address is passed in for the patient's address!
     * @param postalCode A parameter of postalCode is passed in for the patient's postal code!
     * @param phoneNumber A parameter of phoneNumber is passed in for the patient's phone number!
     * @param createDate A parameter of createDate is passed in for the creation date!
     * @param createBy A parameter of createBy is passed in for the user's name!
     * @param lastUpdate A parameter of lastUpdate is passed in for the update date!
     * @param lastUpdatedBy A parameter of lastUpdatedBy is passed in for the user's name!
     * @param divisionId A parameter of divisionId is passed in for the division id!
     */
    public static void insertPatient(int patientId, String patientName, String patientAddress, String postalCode, String phoneNumber, Timestamp createDate, String createBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionId){

        JDBC.openConnection();

        String sqlStatement = "INSERT INTO PATIENTS VALUES(" + patientId + ", '" + patientName + "', '" + patientAddress + "', '" + postalCode + "', '" + phoneNumber + "', '"  + createDate + "', '" + createBy + "', '" + lastUpdate + "', '" + lastUpdatedBy + "', " + divisionId + ")";
        Query.makeQuery(sqlStatement);

        JDBC.closeConnection();

    }

    /***
     * updatePatient
     * The updatePatient is a method that perform an update statement to update a valid patient to the database!
     * @param patientId A parameter of patientId is passed in for the patient id!
     * @param patientName A parameter of patientName is passed in for the patient name!
     * @param address A parameter of address is passed in for the patient's address!
     * @param postalCode A parameter of postalCode is passed in for the patient's postal code!
     * @param phoneNumber A parameter of phoneNumber is passed in for the patient's phone number!
     * @param createDate A parameter of createDate is passed in for the creation date!
     * @param createBy A parameter of createBy is passed in for the user's name!
     * @param lastUpdate A parameter of lastUpdate is passed in for the update date!
     * @param lastUpdatedBy A parameter of lastUpdatedBy is passed in for the user's name!
     * @param divisionId A parameter of divisionId is passed in for the division id!
     */
    public static void updatePatient(int patientId, String patientName, String address, String postalCode, String phoneNumber, Timestamp createDate, String createBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionId){

        JDBC.openConnection();

        String sqlStatement = "UPDATE PATIENTS SET PATIENT_NAME = '" + patientName + "', ADDRESS = '" + address + "', POSTAL_CODE = '" + postalCode + "', PHONE = '"  + phoneNumber + "', CREATE_DATE = '" + createDate + "', CREATED_BY = '" + createBy + "', LAST_UPDATE = '" + lastUpdate + "', LAST_UPDATED_BY = '" + lastUpdatedBy + "', DIVISION_ID = " + divisionId + " WHERE PATIENT_ID = " + patientId;
        Query.makeQuery(sqlStatement);

        JDBC.closeConnection();

    }

    /***
     * deletePatient
     * The deletePatient is a method that perform a deletePatient statement to update a valid patient to the database!
     * @param selectedPatient A parameter of the selected patient is passed in to know which patient to delete!
     * @return This method returns a true or false value!
     * @throws Exception Exception is needed in case of failure!
     */
    public static boolean deletePatient(Patient selectedPatient) throws Exception {

        for(Patient patient : PatientCRUD.getAllPatient()){

            if(patient.getPatient_Id() == selectedPatient.getPatient_Id()){

                JDBC.openConnection();

                String sqlStatement = "DELETE FROM PATIENTS WHERE PATIENT_ID = " + selectedPatient.getPatient_Id();
                Query.makeQuery(sqlStatement);

                JDBC.closeConnection();

            }

        }

        return false;

    }

}
