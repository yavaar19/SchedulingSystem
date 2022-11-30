package com.example.schedulingsystem;

import Database.AppointmentCRUD;
import Database.JDBC;
import Database.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import Class.TimestampData;

/***
 * This is the add appointment controller! This scene contains labels and text fields, combo boxes and a datepicker
 * for the appointment's data!
 */
public class AddAppointmentController implements Initializable {

    Stage stage;
    Scene scene;
    Parent root;

    ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());

    /***
     * This is a lambda that i created! The code within the lambda executes an alert box!
     * It passed a string which is the customs message for the alert! My justification to using
     * the alert for my lambda is because there are so many alerts and validation that needs
     * to be checked and this lambda saves me a lot of time in code writing!
     */
    AlertInterface a = s -> {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(rb.getString("lb93"));
        alert.setContentText(s);
        alert.showAndWait();

    };

    /***
     * This is a lambda that i created! The code within the lambda executes a query and returns
     * a resultset! It passes in a string with is the SQL statement! My justification to using
     * the resultset for my lambda is because there are so queries that needs
     * to be made and this lambda saves me a lot of time in code writing!
     */
    ResultSetInterface result = sql -> {

        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();

        return rs;

    };

    /***
     * Bellow are observable list for combo boxes!
     */
    private int currentUserId;
    ObservableList<String> patientId = FXCollections.observableArrayList();
    ObservableList<String> therapistName = FXCollections.observableArrayList();
    ObservableList<String> appointmentMethod = FXCollections.observableArrayList("In Office", "Virtual");
    ObservableList<String> startHour = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
    ObservableList<String> startMinute = FXCollections.observableArrayList("00", "15", "30", "45");
    ObservableList<String> startPeriod = FXCollections.observableArrayList("AM", "PM");
    ObservableList<String> endHour = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
    ObservableList<String> endMinute = FXCollections.observableArrayList("00", "15", "30", "45");
    ObservableList<String> endPeriod = FXCollections.observableArrayList("AM", "PM");

    @FXML
    private Label AddAppointmentAppointmentIdLabel;

    @FXML
    private Label AddAppointmentAppointmentLabel;

    @FXML
    private Button AddAppointmentCloseBtn;

    @FXML
    private Button AddAppointmentSaveBtn;

    @FXML
    private TextField AddAppointmentAppointmentIdTxtField;

    @FXML
    private ComboBox<String> AddAppointmentContactIdCombo;

    @FXML
    private ComboBox<String> AddAppointmentMethodCombo;

    @FXML
    private Label AddAppointmentContactIdLabel;

    @FXML
    private ComboBox<String> AddAppointmentPatientIdCombo;

    @FXML
    private Label AddAppointmentPatientIdLabel;

    @FXML
    private DatePicker AddAppointmentDateDatePicker;

    @FXML
    private Label AddAppointmentDateLabel;

    @FXML
    private Label AddAppointmentDescriptionLabel;

    @FXML
    private TextField AddAppointmentDescriptionTxtField;

    @FXML
    private ComboBox<String> AddAppointmentEndHourCombo;

    @FXML
    private ComboBox<String> AddAppointmentEndMinuteCombo;

    @FXML
    private ComboBox<String> AddAppointmentEndPeriodCombo;

    @FXML
    private Label AddAppointmentEndTimeLabel;

    @FXML
    private Label AddAppointmentMethodLabel;

    @FXML
    private ComboBox<String> AddAppointmentStartHourCombo;

    @FXML
    private ComboBox<String> AddAppointmentStartMinuteCombo;

    @FXML
    private ComboBox<String> AddAppointmentStartPeriodCombo;

    @FXML
    private Label AddAppointmentStartTimeLabel;

    @FXML
    private Label AddAppointmentTitleLabel;

    @FXML
    private TextField AddAppointmentTitleTxtField;

    @FXML
    private Label AddAppointmentTypeLabel;

    @FXML
    private TextField AddAppointmentTypeTxtField;

    @FXML
    private Label AddAppointmentUserIdLabel;

    @FXML
    private TextField AddAppointmentUserIdTxtField;

    /***
     * AddAppointmentCloseBtn
     * The AddAppointmentCloseBtn method sets the scene to the MainScene
     * when the "Close" button is clicked!
     * @param event Upon the "Close" button is clicked, the code within the AddAppointmentCloseBtn
     *              gets executed!
     * @throws IOException IOException is needed in case of failure!
     */
    @FXML
    void AddAppointmentCloseBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        root = loader.load();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 913, 621);
        stage.setTitle(rb.getString("lb51"));
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        MainSceneController currentUser = loader.getController();
        currentUser.setCurrentUserId(getCurrentUserId());

    }

    /***
     * AddAppointmentSaveBtn
     * The AddAppointmentSaveBtn method sets the scene to the MainScene when the "Save" button is clicked! This method
     * performs all the input validation for the appointment's data, converts the combo boxes values and date picker to
     * a timestamp's value and then saves it to the database!
     * @param event Upon the "Save" button is clicked, the code within the AddAppointmentSaveBtn
     *              gets executed!
     * @throws IOException IOException is needed in case of failure!
     */
    @FXML
    void AddAppointmentSaveBtn(ActionEvent event) throws SQLException, IOException {

        if(AddAppointmentPatientIdCombo.getSelectionModel().getSelectedItem() == null || AddAppointmentTitleTxtField.getText() == null || AddAppointmentDescriptionTxtField.getText() == null || AddAppointmentContactIdCombo.getSelectionModel().getSelectedItem() == null || AddAppointmentMethodCombo.getSelectionModel().getSelectedItem() == null ||AddAppointmentTypeTxtField.getText() == null || AddAppointmentStartHourCombo.getSelectionModel().getSelectedItem() == null || AddAppointmentStartMinuteCombo.getSelectionModel().getSelectedItem() == null || AddAppointmentStartPeriodCombo.getSelectionModel().getSelectedItem() == null || AddAppointmentEndHourCombo.getSelectionModel().getSelectedItem() == null || AddAppointmentEndMinuteCombo.getSelectionModel().getSelectedItem() == null || AddAppointmentEndPeriodCombo.getSelectionModel().getSelectedItem() == null || AddAppointmentDescriptionTxtField.getText() == "" || AddAppointmentTitleTxtField.getText() == "" || AddAppointmentTypeTxtField.getText() == "") {

            a.alert(rb.getString("lb70"));

        }else if(AddAppointmentDateDatePicker.getValue().compareTo(LocalDate.now())<0) {

            a.alert(rb.getString("lb71"));

        }else {

            int appointmentId = Integer.parseInt(AddAppointmentAppointmentIdTxtField.getText());
            int userId = Integer.parseInt(AddAppointmentUserIdTxtField.getText());
            int patientId = Integer.parseInt(AddAppointmentPatientIdCombo.getSelectionModel().getSelectedItem());
            String title = AddAppointmentTitleTxtField.getText();
            String description = AddAppointmentDescriptionTxtField.getText();
            String appointmentMethod = AddAppointmentMethodCombo.getSelectionModel().getSelectedItem();
            String type = AddAppointmentTypeTxtField.getText();
            LocalDate appointmentDate = AddAppointmentDateDatePicker.getValue();
            String startHour = TimestampData.getMilitaryTimeConversion(AddAppointmentStartHourCombo.getSelectionModel().getSelectedItem(), AddAppointmentStartPeriodCombo.getSelectionModel().getSelectedItem());
            String startMinute = AddAppointmentStartMinuteCombo.getSelectionModel().getSelectedItem();
            String endHour = TimestampData.getMilitaryTimeConversion(AddAppointmentEndHourCombo.getSelectionModel().getSelectedItem(), AddAppointmentEndPeriodCombo.getSelectionModel().getSelectedItem());
            String endMinute = AddAppointmentEndMinuteCombo.getSelectionModel().getSelectedItem();
            String createdBy = getUserName();
            String lastUpdatedBy = createdBy;
            String startTimeStamp = appointmentDate + " " + startHour + ":" + startMinute + ":" + "00.0";
            String endTimeStamp = appointmentDate + " " + endHour + ":" + endMinute + ":" + "00.0";
            Timestamp start = TimestampData.getStartDateTime(startTimeStamp);
            Timestamp end = TimestampData.getEndDateTime(endTimeStamp);
            Timestamp createDateTime = TimestampData.getCreateDateTime();
            Timestamp lastUpdateDateTime = TimestampData.getUpdateDateTime();
            String openHours = appointmentDate + " " + "08" + ":" + "00" + ":" + "00.0";
            String closeHours = appointmentDate + " " + "22" + ":" + "00" + ":" + "00.0";

            JDBC.openConnection();

            ResultSet rs1 = result.result("SELECT * FROM THERAPISTS WHERE THERAPIST_NAME = '" + AddAppointmentContactIdCombo.getSelectionModel().getSelectedItem() + "'");
            rs1.next();
            int therapistId = rs1.getInt("Therapist_ID");

            JDBC.closeConnection();

            DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
            LocalDateTime openBusinessHours = LocalDateTime.parse(openHours, dFormatter);
            LocalDateTime closeBusinessHours = LocalDateTime.parse(closeHours, dFormatter);

            Timestamp openingHours = Timestamp.valueOf(openBusinessHours);
            Timestamp closingHours = Timestamp.valueOf(closeBusinessHours);

            if (start.compareTo(end) > -1) {

                a.alert(rb.getString("lb72"));

            }else if(TimestampData.getEstTime(startTimeStamp).compareTo(closingHours) > 0 || TimestampData.getEstTime(endTimeStamp).compareTo(closingHours) > 0 || TimestampData.getEstTime(startTimeStamp).compareTo(openingHours) < 0 || TimestampData.getEstTime(endTimeStamp).compareTo(openingHours) < 0) {

                a.alert(rb.getString("lb73"));

            }else{

                    JDBC.openConnection();
                    ResultSet rs = result.result("SELECT * FROM APPOINTMENTS WHERE User_ID = " + userId + " AND " + "End > '" + start + "' AND Start < '" + end + "'");

                    if (rs.next()) {

                        a.alert(rb.getString("lb74"));

                    } else {



                        AppointmentCRUD.insertAppointment(appointmentId, userId, patientId, title, description, appointmentMethod, therapistId, type, start, end, createDateTime, createdBy, lastUpdateDateTime, lastUpdatedBy);
                        JDBC.closeConnection();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
                        root = loader.load();
                        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
                        scene = new Scene(root, 913, 621);
                        stage.setTitle(rb.getString("lb51"));
                        stage.setScene(scene);
                        stage.centerOnScreen();
                        stage.show();
                        MainSceneController currentUser = loader.getController();
                        currentUser.setCurrentUserId(getCurrentUserId());

                    }

            }

        }

    }

    /***
     * getCurrentUserId
     * The getCurrentUserId method hold the user ID for the user that is currently logged in!
     * @return - This method returns the user's ID of the user that is logged in!
     */
    public int getCurrentUserId() {

        return currentUserId;

    }

    /***
     * setCurrentUserId
     * The method setCurrentUserId sets the user ID of the user that id currently logged in!
     * @param currentUserId The parameter currentUserId is the user ID's value that gets passed in from the Main Scene
     *                      controller!
     */
    public void setCurrentUserId(int currentUserId) {

        this.currentUserId = currentUserId;
        AddAppointmentUserIdTxtField.setText(String.valueOf(currentUserId));

    }

    /***
     * initialize
     * The method initialize the Add Appointment Scene! It sets all the labels and combo boxes!
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AddAppointmentAppointmentLabel.setText(rb.getString("lb6"));
        AddAppointmentAppointmentIdLabel.setText(rb.getString("lb5"));
        AddAppointmentUserIdLabel.setText(rb.getString("lb8"));
        AddAppointmentPatientIdLabel.setText(rb.getString("lb9"));
        AddAppointmentTitleLabel.setText(rb.getString("lb11"));
        AddAppointmentDescriptionLabel.setText(rb.getString("lb12"));
        AddAppointmentMethodLabel.setText(rb.getString("lb101"));
        AddAppointmentContactIdLabel.setText(rb.getString("lb14"));
        AddAppointmentTypeLabel.setText(rb.getString("lb16"));
        AddAppointmentDateLabel.setText(rb.getString("lb17"));
        AddAppointmentStartTimeLabel.setText(rb.getString("lb18"));
        AddAppointmentEndTimeLabel.setText(rb.getString("lb19"));
        AddAppointmentStartHourCombo.setPromptText(rb.getString("lb20"));
        AddAppointmentMethodCombo.setPromptText(rb.getString("lb101"));
        AddAppointmentStartMinuteCombo.setPromptText(rb.getString("lb21"));
        AddAppointmentEndHourCombo.setPromptText(rb.getString("lb20"));
        AddAppointmentEndMinuteCombo.setPromptText(rb.getString("lb21"));
        AddAppointmentSaveBtn.setText(rb.getString("lb22"));
        AddAppointmentCloseBtn.setText(rb.getString("lb4"));
        AddAppointmentPatientIdCombo.setPromptText(rb.getString("lb10"));
        AddAppointmentContactIdCombo.setPromptText(rb.getString("lb15"));
        AddAppointmentAppointmentIdTxtField.setPromptText(rb.getString("lb7"));

        try {

            AddAppointmentAppointmentIdTxtField.setText(String.valueOf(generateId()));
            AddAppointmentPatientIdCombo.setItems(patientId);
            AddAppointmentContactIdCombo.setItems(therapistName);
            AddAppointmentStartHourCombo.setItems(startHour);
            AddAppointmentMethodCombo.setItems(appointmentMethod);
            AddAppointmentStartMinuteCombo.setItems(startMinute);
            AddAppointmentStartPeriodCombo.setItems(startPeriod);
            AddAppointmentEndHourCombo.setItems(endHour);
            AddAppointmentEndMinuteCombo.setItems(endMinute);
            AddAppointmentEndPeriodCombo.setItems(endPeriod);
            AddAppointmentDateDatePicker.setValue(LocalDate.now());

            JDBC.openConnection();

            ResultSet rs = result.result("SELECT * FROM PATIENTS");

            while (rs.next()) {

                patientId.add(String.valueOf(rs.getInt("Patient_ID")));

            }

            JDBC.closeConnection();

            JDBC.openConnection();

            ResultSet results = result.result("SELECT * FROM THERAPISTS");

            while (results.next()) {

                therapistName.add(results.getString("Therapist_Name"));

            }

            JDBC.closeConnection();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    /***
     * generateId
     * The generateId method uses a sorting algorithm to generate the next appointment's ID! It does so by getting all
     * the existing appointment IDs, sorts them and sets the next appointment ID to the highest appointment ID plus 1!
     * @return The method returns the an integer of the next appointment ID!
     * @throws SQLException SQLException is needed in case of SQL query failure!
     */
    public int generateId() throws SQLException {

        int index = 0;
        int id;
        boolean sorted = false;
        int temp;
        int count = 1;

        JDBC.openConnection();
        ResultSet rs = result.result("SELECT * FROM APPOINTMENTS");

        while (rs.next()) {

            count++;

        }
/*
        if(rs.next() == false){

            count = 1;

        }
*/
        int[] idArray = new int[count];

        ResultSet rsA = result.result("SELECT * FROM APPOINTMENTS");

        while(rsA.next()){

            idArray[index] = rsA.getInt("Appointment_ID");
            index++;

        }

        JDBC.closeConnection();

        while(!sorted) {

            sorted = true;

            for (int i = 0; i < idArray.length - 1; i++) {

                if (idArray[i] > idArray[i+1]) {

                    temp = idArray[i];
                    idArray[i] = idArray[i+1];
                    idArray[i+1] = temp;
                    sorted = false;

                }

            }

        }

        id = idArray[idArray.length-1] + 1;

        return id;

    }

    /***
     * getUserName
     * The method getUserName gets the user name of the user that is logged in!
     * @return The method returns a string value of the username after the SQL query is performed!
     * @throws SQLException SQLException is needed in case of SQL query failure!
     */
    public String getUserName() throws SQLException {

        int userId = Integer.parseInt(AddAppointmentUserIdTxtField.getText());
        String userName;

        JDBC.openConnection();

        ResultSet rs = result.result("SELECT User_Name FROM USERS WHERE USER_ID = " + userId);

        rs.next();
        userName = rs.getString("User_Name");

        JDBC.closeConnection();

        return userName;

    }

}
