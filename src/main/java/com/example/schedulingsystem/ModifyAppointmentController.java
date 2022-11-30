package com.example.schedulingsystem;

import Database.AppointmentCRUD;
import Database.JDBC;
import Database.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Class.Appointment;
import javafx.stage.Stage;

import java.io.IOException;
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
 * This is the modify appointment controller! This scene contains labels and text fields, combo boxes and a datepicker
 * for the appointment's data!
 */
public class ModifyAppointmentController {

	ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());

	Stage stage;
	Scene scene;
	Parent root;

	Appointment appointment;

	private int currentUserId;

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
	ResultSetInterface result = sql -> {

		Query.makeQuery(sql);
		ResultSet rs = Query.getResult();

		return rs;

	};

	/***
	 * Bellow are observable list for combo boxes!
	 */
	ObservableList<String> patientId = FXCollections.observableArrayList();
	ObservableList<String> therapistName = FXCollections.observableArrayList();
	ObservableList<String> startHour = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
	ObservableList<String> startMinute = FXCollections.observableArrayList("00", "15", "30", "45");
	ObservableList<String> startPeriod = FXCollections.observableArrayList("AM", "PM");
	ObservableList<String> endHour = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
	ObservableList<String> endMinute = FXCollections.observableArrayList("00", "15", "30", "45");
	ObservableList<String> endPeriod = FXCollections.observableArrayList("AM", "PM");
	ObservableList<String> appointmentMethod = FXCollections.observableArrayList("In Office", "Virtual");


	@FXML
	private Label ModifyAppointmentAppointmentLabel;

	@FXML
	private Label ModifyAppointmentAppointmentIdLabel;

	@FXML
	private TextField ModifyAppointmentAppointmentIdTxtField;

	@FXML
	private ComboBox<String> ModifyAppointmentContactIdCombo;

	@FXML
	private ComboBox<String> ModifyAppointmentMethodCombo;

	@FXML
	private Label ModifyAppointmentContactIdLabel;

	@FXML
	private ComboBox<String> ModifyAppointmentPatientIdCombo;

	@FXML
	private Label ModifyAppointmentPatientIdLabel;

	@FXML
	private DatePicker ModifyAppointmentDateDatePicker;

	@FXML
	private Label ModifyAppointmentDateLabel;

	@FXML
	private Label ModifyAppointmentDescriptionLabel;

	@FXML
	private TextField ModifyAppointmentDescriptionTxtField;

	@FXML
	private ComboBox<String> ModifyAppointmentEndHourCombo;

	@FXML
	private ComboBox<String> ModifyAppointmentEndMinuteCombo;

	@FXML
	private ComboBox<String> ModifyAppointmentEndPeriodCombo;

	@FXML
	private Label ModifyAppointmentEndTimeLabel;

	@FXML
	private Label ModifyAppointmentMethodLabel;

	@FXML
	private ComboBox<String> ModifyAppointmentStartHourCombo;

	@FXML
	private ComboBox<String> ModifyAppointmentStartMinuteCombo;

	@FXML
	private ComboBox<String> ModifyAppointmentStartPeriodCombo;

	@FXML
	private Label ModifyAppointmentStartTimeLabel;

	@FXML
	private Label ModifyAppointmentTitleLabel;

	@FXML
	private TextField ModifyAppointmentTitleTxtField;

	@FXML
	private Label ModifyAppointmentTypeLabel;

	@FXML
	private TextField ModifyAppointmentTypeTxtField;

	@FXML
	private Label ModifyAppointmentUserIdLabel;

	@FXML
	private TextField ModifyAppointmentUserIdTxtField;

	@FXML
	private Button ModifyAppointmentSaveBtn;

	@FXML
	private Button ModifyAppointmentCloseBtn;

	/***
	 * ModifyAppointmentCloseBtn
	 * The ModifyAppointmentCloseBtn method sets the scene to the MainScene
	 * when the "Close" button is clicked!
	 * @param event Upon the "Close" button is clicked, the code within the ModifyAppointmentCloseBtn
	 *              gets executed!
	 * @throws IOException IOException is needed in case of failure!
	 */
	@FXML
	void ModifyAppointmentCloseBtn(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
		root = loader.load();
		stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		scene = new Scene(root, 913, 621);
		stage.setTitle(rb.getString("lb51"));
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
		MainSceneController currentUser = loader.getController();
		currentUser.setCurrentUserId(appointment.getUser_Id());

	}

	/***
	 * ModifyAppointmentSaveBtn
	 * The ModifyAppointmentSaveBtn method sets the scene to the MainScene when the "Save" button is clicked! This method
	 * performs all the input validation for the appointment's data, converts the combo boxes values and date picker to
	 * a timestamp's value and then save it to the database!
	 * @param event Upon the "Save" button is clicked, the code within the ModifyAppointmentSaveBtn
	 *              gets executed!
	 * @throws IOException IOException is needed in case of failure!
	 */
	@FXML
	void ModifyAppointmentSaveBtn(ActionEvent event) throws SQLException, IOException {

		if(ModifyAppointmentPatientIdCombo.getSelectionModel().getSelectedItem() == null || ModifyAppointmentMethodCombo.getSelectionModel().getSelectedItem() == null || ModifyAppointmentTitleTxtField.getText() == null || ModifyAppointmentDescriptionTxtField.getText() == null || ModifyAppointmentContactIdCombo.getSelectionModel().getSelectedItem() == null || ModifyAppointmentTypeTxtField.getText() == null || ModifyAppointmentStartHourCombo.getSelectionModel().getSelectedItem() == null || ModifyAppointmentStartMinuteCombo.getSelectionModel().getSelectedItem() == null || ModifyAppointmentStartPeriodCombo.getSelectionModel().getSelectedItem() == null || ModifyAppointmentEndHourCombo.getSelectionModel().getSelectedItem() == null || ModifyAppointmentEndMinuteCombo.getSelectionModel().getSelectedItem() == null || ModifyAppointmentEndPeriodCombo.getSelectionModel().getSelectedItem() == null || ModifyAppointmentTitleTxtField.getText() == "" || ModifyAppointmentDescriptionTxtField.getText() == "" || ModifyAppointmentTypeTxtField.getText() == "" ){

			a.alert(rb.getString("lb70"));

		}else if(ModifyAppointmentDateDatePicker.getValue().compareTo(LocalDate.now())<0) {

			a.alert(rb.getString("lb71"));

		}else {

			int appointmentId = Integer.parseInt(ModifyAppointmentAppointmentIdTxtField.getText());
			int userId = Integer.parseInt(ModifyAppointmentUserIdTxtField.getText());
			int patientId = Integer.parseInt(ModifyAppointmentPatientIdCombo.getSelectionModel().getSelectedItem());
			String title = ModifyAppointmentTitleTxtField.getText();
			String description = ModifyAppointmentDescriptionTxtField.getText();
			String appointmentMethod = ModifyAppointmentMethodCombo.getSelectionModel().getSelectedItem();
			String type = ModifyAppointmentTypeTxtField.getText();
			LocalDate appointmentDate = ModifyAppointmentDateDatePicker.getValue();
			String startHour = TimestampData.getMilitaryTimeConversion(ModifyAppointmentStartHourCombo.getSelectionModel().getSelectedItem(), ModifyAppointmentStartPeriodCombo.getSelectionModel().getSelectedItem());
			String startMinute = ModifyAppointmentStartMinuteCombo.getSelectionModel().getSelectedItem();
			String endHour = TimestampData.getMilitaryTimeConversion(ModifyAppointmentEndHourCombo.getSelectionModel().getSelectedItem(), ModifyAppointmentEndPeriodCombo.getSelectionModel().getSelectedItem());
			String endMinute = ModifyAppointmentEndMinuteCombo.getSelectionModel().getSelectedItem();
			String createdBy = appointment.getCreated_By();
			String lastUpdatedBy = getUserName();
			String startTimeStamp = appointmentDate + " " + startHour + ":" + startMinute + ":" + "00.0";
			String endTimeStamp = appointmentDate + " " + endHour + ":" + endMinute + ":" + "00.0";
			Timestamp start = TimestampData.getStartDateTime(startTimeStamp);
			Timestamp end = TimestampData.getEndDateTime(endTimeStamp);
			Timestamp createDateTime = TimestampData.getStartDateTime(appointment.getCreated_Date().toString());
			Timestamp lastUpdateDateTime = TimestampData.getUpdateDateTime();
			String openHours = appointmentDate + " " + "08" + ":" + "00" + ":" + "00.0";
			String closeHours = appointmentDate + " " + "22" + ":" + "00" + ":" + "00.0";

			JDBC.openConnection();

			ResultSet rs1 = result.result("SELECT * FROM THERAPISTS WHERE THERAPIST_NAME = '" + ModifyAppointmentContactIdCombo.getSelectionModel().getSelectedItem() + "'");
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
				ResultSet rs = result.result("SELECT * FROM APPOINTMENTS WHERE User_ID = " + userId + " and APPOINTMENT_ID !=" + appointmentId + " AND " + "End > '" + start + "' AND Start < '" + end + "'");

				if (rs.next()) {

					a.alert(rb.getString("lb74"));

				} else {

					AppointmentCRUD.updateAppointment(appointmentId, userId, patientId, title, description, appointmentMethod, therapistId, type, start, end, createDateTime, createdBy, lastUpdateDateTime, lastUpdatedBy);
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
					currentUser.setCurrentUserId(appointment.getUser_Id());

				}

			}

		}

	}

	/***
	 * setAppointment
	 * The setAppointment sets the scene with the selected appointment's data! All of the selected appointment's data
	 * gets preloaded when the scene loads up!
	 * @param appointment The parameter appointment is set from the main scene so that selected appointment's data can
	 *                    be accessed!
	 * @throws SQLException SQLException is needed in case of SQL query failure!
	 */
	public void setAppointment(Appointment appointment) throws SQLException {

		ModifyAppointmentAppointmentLabel.setText(rb.getString("lb62"));
		ModifyAppointmentAppointmentIdLabel.setText(rb.getString("lb5"));
		ModifyAppointmentUserIdLabel.setText(rb.getString("lb8"));
		ModifyAppointmentPatientIdLabel.setText(rb.getString("lb9"));
		ModifyAppointmentTitleLabel.setText(rb.getString("lb11"));
		ModifyAppointmentDescriptionLabel.setText(rb.getString("lb12"));
		ModifyAppointmentMethodLabel.setText(rb.getString("lb101"));
		ModifyAppointmentContactIdLabel.setText(rb.getString("lb14"));
		ModifyAppointmentTypeLabel.setText(rb.getString("lb16"));
		ModifyAppointmentDateLabel.setText(rb.getString("lb17"));
		ModifyAppointmentStartTimeLabel.setText(rb.getString("lb18"));
		ModifyAppointmentEndTimeLabel.setText(rb.getString("lb19"));
		ModifyAppointmentStartHourCombo.setPromptText(rb.getString("lb20"));
		ModifyAppointmentStartMinuteCombo.setPromptText(rb.getString("lb21"));
		ModifyAppointmentEndHourCombo.setPromptText(rb.getString("lb20"));
		ModifyAppointmentEndMinuteCombo.setPromptText(rb.getString("lb21"));
		ModifyAppointmentSaveBtn.setText(rb.getString("lb22"));
		ModifyAppointmentCloseBtn.setText(rb.getString("lb4"));
		ModifyAppointmentPatientIdCombo.setPromptText(rb.getString("lb10"));
		ModifyAppointmentContactIdCombo.setPromptText(rb.getString("lb15"));
		ModifyAppointmentAppointmentIdTxtField.setPromptText(rb.getString("lb7"));

		this.appointment = appointment;

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

		ModifyAppointmentAppointmentIdTxtField.setText(String.valueOf(appointment.getAppointment_Id()));
		ModifyAppointmentUserIdTxtField.setText(String.valueOf(appointment.getUser_Id()));
		ModifyAppointmentPatientIdCombo.setValue(String.valueOf(appointment.getPatient_Id()));

		ModifyAppointmentTitleTxtField.setText(appointment.getTitle());
		ModifyAppointmentDescriptionTxtField.setText(appointment.getDescription());
		ModifyAppointmentMethodCombo.setValue(appointment.getAppointmentMethod());

		JDBC.openConnection();
		ResultSet rs2 = result.result("SELECT * FROM THERAPISTS WHERE THERAPIST_ID = '" + appointment.getTherapist_Id() +"'");

		while (rs2.next()) {

			ModifyAppointmentContactIdCombo.setValue(rs2.getString("Therapist_Name"));

		}

		JDBC.closeConnection();

		ModifyAppointmentTypeTxtField.setText(appointment.getType());

		ModifyAppointmentStartHourCombo.setValue(TimestampData.getHour(appointment.getStart()));
		ModifyAppointmentStartMinuteCombo.setValue(appointment.getStart().toString().substring(14, 16));
		ModifyAppointmentStartPeriodCombo.setValue(TimestampData.getPeriod(appointment.getStart()));

		ModifyAppointmentEndHourCombo.setValue(TimestampData.getHour(appointment.getEnd()));
		ModifyAppointmentEndMinuteCombo.setValue(appointment.getEnd().toString().substring(14, 16));
		ModifyAppointmentEndPeriodCombo.setValue(TimestampData.getPeriod(appointment.getEnd()));

		ModifyAppointmentPatientIdCombo.setItems(patientId);
		ModifyAppointmentContactIdCombo.setItems(therapistName);
		ModifyAppointmentStartHourCombo.setItems(startHour);
		ModifyAppointmentStartMinuteCombo.setItems(startMinute);
		ModifyAppointmentStartPeriodCombo.setItems(startPeriod);
		ModifyAppointmentEndHourCombo.setItems(endHour);
		ModifyAppointmentEndMinuteCombo.setItems(endMinute);
		ModifyAppointmentEndPeriodCombo.setItems(endPeriod);
		ModifyAppointmentMethodCombo.setItems(appointmentMethod);
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = appointment.getStart().toString().substring(0, 10);
		LocalDate datePicker = LocalDate.parse(date, df);
		ModifyAppointmentDateDatePicker.setValue(datePicker);

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

	}

	/***
	 * getUserName
	 * The method getUserName gets the user name of the user that is logged in!
	 * @return The method returns a string value of the username after the SQL query is performed!
	 * @throws SQLException SQLException is needed in case of SQL query failure!
	 */
	public String getUserName() throws SQLException {

		String userName;

		JDBC.openConnection();

		ResultSet rs = result.result("SELECT User_Name FROM USERS WHERE USER_ID = " + getCurrentUserId());

		rs.next();
		userName = rs.getString("User_Name");

		JDBC.closeConnection();

		return userName;

	}

}
