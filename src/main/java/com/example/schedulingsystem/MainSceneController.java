package com.example.schedulingsystem;

import Database.AppointmentCRUD;
import Database.PatientCRUD;
import Database.JDBC;
import Database.Query;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Class.Appointment;
import Class.Patient;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


/***
 * This is the main scene controller! This scene contains a tableview for the appointments,
 * a table view for the patients, buttons to add, modify and delete both appointments and
 * patients! It also contains all the required labels as well as buttons to view customized
 * reports!
 */
public class MainSceneController implements Initializable {

    ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());

    Stage stage;
    Scene scene;
    Parent root;

    Appointment appointment;
    Patient patient;

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
     *
     */
    ResultSetInterface resultSet = sql -> {

        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();

        return rs;

    };

    private int currentUserId;

    @FXML
    private TableColumn<Appointment, String> AppointmentTVTitleClm;

    @FXML
    private TableColumn<Appointment, Integer> AppointmentsTVUserIdClm;

    @FXML
    private TableColumn<Appointment, Integer> AppointmentsTVPatientIdClm;

    @FXML
    private TableColumn<Appointment, Integer> AppointmentsTVAppointmentIdClm;

    @FXML
    private TableColumn<Appointment, Timestamp> AppointmentsTVEndClm;

    @FXML
    private TableColumn<Appointment, String> AppointmentsTVMethodClm;

    @FXML
    private TableColumn<Appointment, Timestamp> AppointmentsTVStartClm;

    @FXML
    private TableColumn<Patient, Integer> PatientsTVDivisionIdClm;

    @FXML
    private TableColumn<Patient, Integer> PatientsTVPatientIdClm;

    @FXML
    private TableColumn<Patient, String> PatientsTVPatientNameClm;

    @FXML
    private Label MainSceneAppointmentsLabel;

    @FXML
    private TableView<Appointment> MainSceneAppointmentsTV;

    @FXML
    private TableView<Patient> MainScenePatientsTV;

    @FXML
    private Label MainSceneSchedulingSystemLabel;

    @FXML
    private Label MainScenePatientLabel;

    @FXML
    private Button MainSceneAddAppointmentBtn;

    @FXML
    private Button MainSceneModifyAppointmentsBtn;

    @FXML
    private Button MainSceneDeleteAppointmentBtn;

    @FXML
    private Button MainSceneAddPatientsBtn;

    @FXML
    private Button MainSceneModifyPatientsBtn;

    @FXML
    private Button MainSceneDeletePatientsBtn;

    @FXML
    private Button MainSceneViewAppointmentsBtn;

    @FXML
    private Button MainSceneAppointmentTypesReportBtn;

    @FXML
    private Button MainSceneContactScheduleReportBtn;

    @FXML
    private Button MainSceneUserAppointmentsReportBtn;

    @FXML
    private Button MainSceneLogOutBtn;

    @FXML
    private TextField AppointmentSearch;

    @FXML
    private TextField PatientSearch;

    public MainSceneController() throws Exception {
    }

    /***
     * MainSceneUserAppointmentsReportBtn
     * The MainSceneUserAppointmentsReportBtn method sets the scene to the UserAppointmentsReport
     * when the "User Appointment Report" button is clicked!
     * @param event Upon the "User Appointment Report" button is clicked, the code within the
     *              MainSceneUserAppointmentsReportBtn gets executed!
     * @throws IOException IOException is needed in case of failure!
     */



    @FXML
    void MainSceneUserAppointmentsReportBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserAppointmentsReport.fxml"));
        root = loader.load();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle(rb.getString("lb51"));
        stage.show();
        UserAppointmentsReportController currentUser = loader.getController();
        currentUser.setCurrentUserId(getCurrentUserId());

    }

    /***
     * MainSceneAddAppointmentBtn
     * The MainSceneAddAppointmentBtn method sets the scene to the AddAppointment
     * when the "Add" button is clicked!
     * @param event Upon the "Add" button is clicked, the code within the MainSceneAddAppointmentBtn
     *              gets executed!
     * @throws IOException IOException is needed in case of failure!
     */
    @FXML
    void MainSceneAddAppointmentBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAppointment.fxml"));
        root = loader.load();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 519, 566);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle(rb.getString("lb51"));
        stage.show();
        AddAppointmentController currentUser = loader.getController();
        currentUser.setCurrentUserId(getCurrentUserId());

    }

    /***
     * MainSceneAddPatientsBtn
     * The MainSceneAddPatientsBtn method sets the scene to the AddPatient
     * when the "Add" button is clicked!
     * @param event Upon the "Add" button is clicked, the code within the MainSceneAddPatientsBtn
     *              gets executed!
     * @throws IOException IOException is needed in case of failure!
     */
    @FXML
    void MainSceneAddPatientsBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPatient.fxml"));
        root = loader.load();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 519, 566);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle(rb.getString("lb51"));
        stage.show();
        AddPatientController currentUser = loader.getController();
        currentUser.setCurrentUserId(getCurrentUserId());

    }

    /***
     * MainSceneAppointmentTypesBtn
     * The MainSceneAppointmentTypesBtn method sets the  scene to the AppointmentTypesReport when the "Appointment Types Report"
     * button is clicked!
     * @param event Upon the "Appointment Types Report" button is clicked, the code within the
     *              MainSceneAppointmentTypesBtn gets executed!
     * @throws IOException IOException is needed in case of failure!
     */
    @FXML
    void MainSceneAppointmentTypesBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentTypesReport.fxml"));
        root = loader.load();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle(rb.getString("lb51"));
        stage.show();
        AppointmentTypesReportController currentUser = loader.getController();
        currentUser.setCurrentUserId(getCurrentUserId());

    }

    /***
     * MainSceneDeleteAppointmentBtn
     * The MainSceneDeleteAppointmentBtn methods takes care of deleting a selected appointment from the main scene
     * appointment's table! The code within the MainSceneDeleteAppointmentBtn is executed when the "Delete" button
     * is clicked! The method also contains alerts in case no appointment is selected!
     * @param event Upon the "Delete" button is clicked, the code within the
     *              MainSceneDeleteAppointmentBtn gets executed!
     * @throws Exception IOException is needed in case of failure!
     */
    @FXML
    void MainSceneDeleteAppointmentBtn(ActionEvent event) throws Exception {

        if(MainSceneAppointmentsTV.getSelectionModel().getSelectedItem() == null){

            a.alert(rb.getString("lb83"));

        }else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(rb.getString("lb84"));
            alert.setContentText(rb.getString("lb85"));
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK){

                appointment = MainSceneAppointmentsTV.getSelectionModel().getSelectedItem();
                AppointmentCRUD.deleteAppointment(appointment);
                MainSceneAppointmentsTV.setItems(AppointmentCRUD.getAllAppointment());

                Alert customAlert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(rb.getString("lb93"));
                alert.setContentText(rb.getString("lb5") + " " + appointment.getAppointment_Id()+ " " + rb.getString("lb95") + " '" + appointment.getType() + "' " + rb.getString("lb94"));
                alert.showAndWait();

            }

        }

    }

    /***
     * MainSceneDeletePatientsBtn
     * The MainSceneDeletePatientsBtn methods takes care of deleting a selected patient from the main scene
     * patient's table! The code within the MainSceneDeletePatientsBtn is executed when the "Delete" button
     * is clicked! The method also contains alerts in case no patient is selected! If a patient has an appointment,
     * the delete will not be processed and an alert will show up!
     * @param event Upon the "Delete" button is clicked, the code within the
     *              MainSceneDeletePatientsBtn gets executed!
     * @throws Exception IOException is needed in case of failure!
     */
    @FXML
    void MainSceneDeletePatientsBtn(ActionEvent event) throws Exception {

        if(MainScenePatientsTV.getSelectionModel().getSelectedItem() == null){

            a.alert(rb.getString("lb86"));

        }else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(rb.getString("lb84"));
            alert.setContentText(rb.getString("lb87"));
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK){

                patient = MainScenePatientsTV.getSelectionModel().getSelectedItem();

                JDBC.openConnection();

                ResultSet rs = resultSet.result("SELECT * FROM APPOINTMENTS WHERE PATIENT_ID = " + patient.getPatient_Id());

                if(rs.next()){

                    a.alert(rb.getString("lb88"));

                }else {

                    PatientCRUD.deletePatient(patient);
                    MainScenePatientsTV.setItems(PatientCRUD.getAllPatient());

                    Alert customAlert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(rb.getString("lb93"));
                    alert.setContentText(rb.getString("lb24") + " " + patient.getPatient_Name() + " " + rb.getString("lb94"));
                    alert.showAndWait();

                }

            }

            JDBC.closeConnection();

        }

    }

    /***
     * MainSceneLogOutBtn
     * The MainSceneLogOutBtn method returns the user back to the LogIn scene when the "Log Out" button is clicked!
     * @param event Upon the "Log Out" button is clicked, the code within the
     *              MainSceneLogOutBtn gets executed!
     * @throws IOException IOException is needed in case of failure!
     */
    @FXML
    void MainSceneLogOutBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
        root = loader.load();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    /***
     * MainSceneModifyAppointmentsBtn
     * The MainSceneModifyAppointmentsBtn method sets the user to the ModifyAppointment scene! When the button
     * "Modify" is clicked, the method sets the controller with the selected appointment's data and then loads
     * the scene with the appointment's data! The method also contains alert such as input validation!
     * @param event Upon the "Modify" button is clicked, the code within the
     *              MainSceneModifyAppointmentsBtn gets executed!
     * @throws IOException IOException is needed in case of failure!
     * @throws SQLException SQLException is needed in case of SQL query failure!
     */
    @FXML
    void MainSceneModifyAppointmentsBtn(ActionEvent event) throws IOException, SQLException {

        if(MainSceneAppointmentsTV.getSelectionModel().getSelectedItem() == null){

            a.alert(rb.getString("lb89"));

        }else{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyAppointment.fxml"));
            root = loader.load();
            stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 519, 566);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle(rb.getString("lb51"));
            stage.show();
            ModifyAppointmentController controller = loader.getController();
            appointment = MainSceneAppointmentsTV.getSelectionModel().getSelectedItem();
            controller.setAppointment(appointment);
            controller.setCurrentUserId(getCurrentUserId());

        }

    }

    /***
     * MainSceneModifyPatientsBtn
     * The MainSceneModifyPatientsBtn method sets the user to the ModifyPatient scene! When the button
     * "Modify" is clicked, the method sets the controller with the selected patient's data and then loads
     * the scene with the patient's data! The method also contains alert such as input validation!
     * @param event Upon the "Modify" button is clicked, the code within the
     *              MainSceneModifyPatientrsBtn gets executed!
     * @throws IOException IOException is needed in case of failure!
     * @throws SQLException SQLException is needed in case of SQL query failure!
     */
    @FXML
    void MainSceneModifyPatientsBtn(ActionEvent event) throws IOException, SQLException {

        if(MainScenePatientsTV.getSelectionModel().getSelectedItem() == null){

            a.alert(rb.getString("lb90"));

        }else{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPatient.fxml"));
            root = loader.load();
            stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 519, 566);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle(rb.getString("lb51"));
            stage.show();
            ModifyPatientController controller = loader.getController();
            patient = MainScenePatientsTV.getSelectionModel().getSelectedItem();
            controller.setPatient(patient);
            controller.setCurrentUserId(getCurrentUserId());

        }

    }

    /***
     * MainSceneContactScheduleReportBtn
     * The MainSceneContactScheduleReportBtn method sets the  scene to the ContactScheduleReport when the "Contact Schedule Report"
     * button is clicked!
     * @param event Upon the "Contact Schedule Report" button is clicked, the code within the
     *              MainSceneContactScheduleReportBtn gets executed!
     * @throws IOException IOException is needed in case of failure!
     */
    @FXML
    void MainSceneContactScheduleReportBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("TherapistScheduleReport.fxml"));
        root = loader.load();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle(rb.getString("lb51"));
        stage.show();
        TherapistScheduleReportController currentUser = loader.getController();
        currentUser.setCurrentUserId(getCurrentUserId());

    }

    /***
     * MainSceneViewAppointmentsBtn
     * The MainSceneViewAppointmentsBtn method sets the  scene to the ViewAppointments when the "View Appointments"
     * button is clicked!
     * @param event Upon the "View Appointments" button is clicked, the code within the
     *              MainSceneViewAppointmentsBtn gets executed!
     * @throws IOException IOException is needed in case of failure!
     */
    @FXML
    void MainSceneViewAppointmentsBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAppointments.fxml"));
        root = loader.load();
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle(rb.getString("lb51"));
        stage.show();
        ViewAppointmentsController currentUser = loader.getController();
        currentUser.setCurrentUserId(getCurrentUserId());

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
     * @param currentUserId The parameter currentUserId is the user ID's value that gets passed in from the Log In
     *                      controller!
     */
    public void setCurrentUserId(int currentUserId) {

        this.currentUserId = currentUserId;

    }

    /***
     * initialize
     * This method initialize the main scene! It sets all the table and columns, adds all appointments and patients
     * from an observable list!
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MainSceneSchedulingSystemLabel.setText(rb.getString("lb51"));
        MainSceneAppointmentsLabel.setText(rb.getString("lb52"));
        MainScenePatientLabel.setText(rb.getString("lb56"));
        AppointmentsTVAppointmentIdClm.setText(rb.getString("lb5"));
        AppointmentsTVUserIdClm.setText(rb.getString("lb8"));
        AppointmentsTVPatientIdClm.setText(rb.getString("lb9"));
        AppointmentTVTitleClm.setText(rb.getString("lb11"));
        AppointmentsTVStartClm.setText(rb.getString("lb48"));
        AppointmentsTVEndClm.setText(rb.getString("lb49"));
        AppointmentsTVMethodClm.setText(rb.getString("lb101"));
        MainSceneAddAppointmentBtn.setText(rb.getString("lb53"));
        MainSceneModifyAppointmentsBtn.setText(rb.getString("lb54"));
        MainSceneDeleteAppointmentBtn.setText(rb.getString("lb55"));
        PatientsTVPatientIdClm.setText(rb.getString("lb9"));
        PatientsTVPatientNameClm.setText(rb.getString("lb24"));
        PatientsTVDivisionIdClm.setText(rb.getString("lb97"));
        MainSceneAddPatientsBtn.setText(rb.getString("lb53"));
        MainSceneModifyPatientsBtn.setText(rb.getString("lb54"));
        MainSceneDeletePatientsBtn.setText(rb.getString("lb55"));
        MainSceneViewAppointmentsBtn.setText(rb.getString("lb57"));
        MainSceneAppointmentTypesReportBtn.setText(rb.getString("lb58"));
        MainSceneContactScheduleReportBtn.setText(rb.getString("lb59"));
        MainSceneUserAppointmentsReportBtn.setText(rb.getString("lb60"));
        MainSceneLogOutBtn.setText(rb.getString("lb61"));

        try {

            MainSceneAppointmentsTV.setItems(AppointmentCRUD.getAllAppointment());
            AppointmentsTVAppointmentIdClm.setCellValueFactory(new PropertyValueFactory<>("appointment_Id"));
            AppointmentTVTitleClm.setCellValueFactory(new PropertyValueFactory<>("title"));
            AppointmentsTVMethodClm.setCellValueFactory(new PropertyValueFactory<>("AppointmentMethod"));
            AppointmentsTVStartClm.setCellValueFactory(new PropertyValueFactory<>("start"));
            AppointmentsTVEndClm.setCellValueFactory(new PropertyValueFactory<>("end"));
            AppointmentsTVUserIdClm.setCellValueFactory(new PropertyValueFactory<>("user_Id"));
            AppointmentsTVPatientIdClm.setCellValueFactory(new PropertyValueFactory<>("patient_Id"));

            MainScenePatientsTV.setItems(PatientCRUD.getAllPatient());
            PatientsTVPatientIdClm.setCellValueFactory(new PropertyValueFactory<>("patient_Id"));
            PatientsTVPatientNameClm.setCellValueFactory(new PropertyValueFactory<>("patient_Name"));
            PatientsTVDivisionIdClm.setCellValueFactory(new PropertyValueFactory<>("division_Id"));

            FilteredList<Appointment> filteredData = new FilteredList<>(AppointmentCRUD.getAllAppointment(), b -> true);

            AppointmentSearch.textProperty().addListener((observable, oldValue, newValue) -> {

                filteredData.setPredicate(appointment -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null){

                        return true;

                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (Integer.toString(appointment.getAppointment_Id()).toLowerCase().indexOf(searchKeyword) > -1){

                        return true;

                    } else if(Integer.toString(appointment.getUser_Id()).toLowerCase().indexOf(searchKeyword) > -1){

                        return true;

                    } else if(Integer.toString(appointment.getPatient_Id()).toLowerCase().indexOf(searchKeyword) > -1){

                        return true;

                    } else if(appointment.getTitle().toLowerCase().indexOf(searchKeyword) > -1){

                        return true;

                    } else if(appointment.getAppointmentMethod().toLowerCase().indexOf(searchKeyword) > -1){

                        return true;

                    } else

                        return false;

                });

            });

            SortedList<Appointment> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(MainSceneAppointmentsTV.comparatorProperty());

            MainSceneAppointmentsTV.setItems(sortedData);

            FilteredList<Patient> filteredPatientData = new FilteredList<>(PatientCRUD.getAllPatient(), b -> true);

            PatientSearch.textProperty().addListener((observable, oldValue, newValue) -> {

                filteredPatientData.setPredicate(patient -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null){

                        return true;

                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (Integer.toString(patient.getPatient_Id()).toLowerCase().indexOf(searchKeyword) > -1){

                        return true;

                    } else if(patient.getPatient_Name().toLowerCase().indexOf(searchKeyword) > -1){

                        return true;

                    } else if(Integer.toString(patient.getDivision_Id()).toLowerCase().indexOf(searchKeyword) > -1){

                        return true;

                    } else

                        return false;

                });

            });

            SortedList<Patient> sortedPatientData = new SortedList<>(filteredPatientData);

            sortedPatientData.comparatorProperty().bind(MainScenePatientsTV.comparatorProperty());

            MainScenePatientsTV.setItems(sortedPatientData);


        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
