package com.example.schedulingsystem;

import Database.PatientCRUD;
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
import Class.Patient;
import Class.TimestampData;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.ResourceBundle;

/***
 * This is the add patient controller! This scene contains labels and text fields, combo boxes
 * for the patient's data!
 */
public class ModifyPatientController {

	ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());

	Stage stage;
	Scene scene;
	Parent root;

	Patient patient;

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
	ObservableList<String> Division = FXCollections.observableArrayList();
	ObservableList<String> Country = FXCollections.observableArrayList();

	@FXML
	private Label ModifyPatientAddressLabel;

	@FXML
	private TextField ModifyPatientAddressTxtField;

	@FXML
	private Label ModifyPatientCityLabel;

	@FXML
	private TextField ModifyPatientCityTxtField;

	@FXML
	private ComboBox<String> ModifyPatientrCountryCombo;

	@FXML
	private Label ModifyPatientCountryLabel;

	@FXML
	private TextField ModifyPatientPatientIdTxtField;

	@FXML
	private Label ModifyPatientPatientIdTxtLabel;

	@FXML
	private Label ModifyPatientPatientNameLabel;

	@FXML
	private TextField ModifyPatientPatientNameTxtField;

	@FXML
	private ComboBox<String> ModifyPatientDivisionCombo;

	@FXML
	private Label ModifyPatientDivisionLabel;

	@FXML
	private Label ModifyPatientLabel;

	@FXML
	private Label ModifyPatientPhoneLabel;

	@FXML
	private TextField ModifyPatientPhoneNumberTxtField;

	@FXML
	private Label ModifyPatientPostalCodeLabel;

	@FXML
	private TextField ModifyPatientPostalcodeTxtField1;

	@FXML
	private Label ModifyPatientUserIdLabel;

	@FXML
	private TextField ModifyPatientUserIdTxtField;

	@FXML
	private Button ModifyPatientCloseBtn;

	@FXML
	private Button ModifyPatientSaveBtn;

	/***
	 * ModifyPatientCloseBtn
	 * The ModifyPatientCloseBtn method sets the scene to the MainScene
	 * when the "Close" button is clicked!
	 * @param event Upon the "Close" button is clicked, the code within the ModifyPatientCloseBtn
	 *              gets executed!
	 * @throws IOException IOException is needed in case of failure!
	 */
	@FXML
	void ModifyPatientCloseBtn(ActionEvent event) throws IOException {

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
	 * ModifyPatientCountryCombo
	 * The ModifyPatientCountryCombo sets the available country from the first level division table to the country
	 * combo box!
	 * @param event When the scene loads, the available country from the first level division table to the country
	 * 	 			combo box!
	 * @throws SQLException SQLException is needed in case of SQL query failure!
	 */
	@FXML
	void ModifyPatientCountryCombo(ActionEvent event) throws SQLException {

		ResultSet rs = null;

		Division.clear();
		JDBC.openConnection();

		if(ModifyPatientDivisionCombo.getSelectionModel().getSelectedItem() == null) {

			if (ModifyPatientrCountryCombo.getSelectionModel().getSelectedItem() == "United States") {

				rs = result.result("SELECT DIVISION FROM FIRST_LEVEL_DIVISIONS WHERE COUNTRY_ID = 1");

			} else if (ModifyPatientrCountryCombo.getSelectionModel().getSelectedItem() == "United Kingdom") {

				rs = result.result("SELECT DIVISION FROM FIRST_LEVEL_DIVISIONS WHERE COUNTRY_ID = 2");

			} else if (ModifyPatientrCountryCombo.getSelectionModel().getSelectedItem() == "Canada") {

				rs = result.result("SELECT DIVISION FROM FIRST_LEVEL_DIVISIONS WHERE COUNTRY_ID = 3");

			}

			while (rs.next()) {

				Division.add(rs.getString("Division"));

			}

			JDBC.closeConnection();

		}

	}

	@FXML
	void ModifyPatientDivisionCombo(ActionEvent event) {

	}

	/***
	 * ModifyPatientSaveBtn
	 * The ModifyPatientSaveBtn method sets the scene to the MainScene when the "Save" button is clicked! This method
	 * performs all the input validation for the patient's data, records updated date timestamp value etc then saves it to the database!
	 * @param event Upon the "Save" button is clicked, the code within the ModifyPatientSaveBtn
	 *              gets executed!
	 * @throws IOException IOException is needed in case of failure!
	 */
	@FXML
	void ModifyPatientSaveBtn(ActionEvent event) throws SQLException, IOException {

		if(ModifyPatientPatientNameTxtField.getText() == null || ModifyPatientAddressTxtField.getText() == null || ModifyPatientCityTxtField.getText() == null ||  ModifyPatientDivisionCombo.getSelectionModel().getSelectedItem() == null || ModifyPatientrCountryCombo.getSelectionModel().getSelectedItem() == null || ModifyPatientPostalcodeTxtField1.getText() == null || ModifyPatientPhoneNumberTxtField.getText() == null){

			a.alert(rb.getString("lb70"));

		}else if(!ModifyPatientPatientNameTxtField.getText().matches("[a-zA-Z]*\\s*[a-zA-Z]*\\s*") || ModifyPatientPatientNameTxtField.getText() == ""){

			a.alert(rb.getString("lb75"));

		}else if(!ModifyPatientAddressTxtField.getText().matches("\\d+\\s+[a-zA-Z]*\\s*[a-zA-Z]+\\s[a-zA-Z]*")){

			a.alert(rb.getString("lb76"));

		}else if(!ModifyPatientCityTxtField.getText().matches("[a-zA-Z ]+")){

			a.alert(rb.getString("lb77"));

		} else if(!ModifyPatientPostalcodeTxtField1.getText().matches("[a-zA-Z0-9 ]+")){

			a.alert(rb.getString("lb78"));

		}else if(!ModifyPatientPhoneNumberTxtField.getText().matches("[0-9- ]+")){

			a.alert(rb.getString("lb79"));

		}else if(divisionIdValidation() == null){

			a.alert(ModifyPatientDivisionCombo.getSelectionModel().getSelectedItem() + rb.getString("lb80") + ModifyPatientrCountryCombo.getSelectionModel().getSelectedItem());

		}else{

			int patientId = Integer.parseInt(ModifyPatientPatientIdTxtField.getText());
			String patientName = ModifyPatientPatientNameTxtField.getText();
			String address = ModifyPatientAddressTxtField.getText() + ", " + ModifyPatientCityTxtField.getText();
			String postalCode = ModifyPatientPostalcodeTxtField1.getText();
			String phoneNumber = ModifyPatientPhoneNumberTxtField.getText();
			Timestamp createDateTime = TimestampData.getStartDateTime(patient.getCreate_Date().toString()) ;
			String createdBy = patient.getCreated_By();
			Timestamp lastUpdateDateTime = TimestampData.getUpdateDateTime();
			String lastUpdatedBy = getUserName();
			int divisionId = Integer.parseInt(divisionIdValidation());

			PatientCRUD.updatePatient(patientId, patientName, address, postalCode, phoneNumber, createDateTime, createdBy, lastUpdateDateTime, lastUpdatedBy, divisionId);

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

	/***
	 * setPatient
	 * The setPatient sets the scene with the selected patient's data! All of the selected patient's data
	 * gets preloaded when the scene loads up!
	 * @param patient The parameter patient is set from the main scene so that selected patient's data can
	 *                    be accessed!
	 * @throws SQLException SQLException is needed in case of SQL query failure!
	 */
	public void setPatient(Patient patient) throws SQLException {

		ModifyPatientLabel.setText(rb.getString("lb63"));
		ModifyPatientPatientIdTxtLabel.setText(rb.getString("lb9"));
		ModifyPatientUserIdLabel.setText(rb.getString("lb8"));
		ModifyPatientPatientNameLabel.setText(rb.getString("lb24"));
		ModifyPatientAddressLabel.setText(rb.getString("lb25"));
		ModifyPatientCityLabel.setText(rb.getString("lb26"));
		ModifyPatientDivisionLabel.setText(rb.getString("lb27"));
		ModifyPatientCountryLabel.setText(rb.getString("lb29"));
		ModifyPatientPostalCodeLabel.setText(rb.getString("lb31"));
		ModifyPatientPhoneLabel.setText(rb.getString("lb32"));
		ModifyPatientSaveBtn.setText(rb.getString("lb22"));
		ModifyPatientCloseBtn.setText(rb.getString("lb4"));
		ModifyPatientPatientIdTxtField.setText(rb.getString("lb7"));
		ModifyPatientDivisionCombo.setPromptText(rb.getString("lb28"));
		ModifyPatientrCountryCombo.setPromptText(rb.getString("lb30"));

		this.patient = patient;

		ModifyPatientPatientIdTxtField.setText(String.valueOf(patient.getPatient_Id()));
		ModifyPatientUserIdTxtField.setText(String.valueOf(getCurrentUserId()));
		ModifyPatientPatientNameTxtField.setText(patient.getPatient_Name());
		if(patient.getAddress().contains(",")){

			ModifyPatientAddressTxtField.setText(patient.getAddress().substring(0, patient.getAddress().indexOf(",")));
			ModifyPatientCityTxtField.setText(patient.getAddress().substring(patient.getAddress().indexOf(",") + 2));

		}else{

			ModifyPatientAddressTxtField.setText(patient.getAddress());
			ModifyPatientCityTxtField.setText("N/A");

		}

		try {

			JDBC.openConnection();
			ResultSet rs = result.result("SELECT DIVISION FROM FIRST_LEVEL_DIVISIONS");

			while(rs.next()){

				Division.add(rs.getString("Division"));

			}

			ModifyPatientDivisionCombo.setItems(Division);

			ResultSet rsCountry = result.result("SELECT DISTINCT COUNTRY_ID FROM FIRST_LEVEL_DIVISIONS");

			while(rsCountry.next()){

				if(rsCountry.getString("Country_ID").contains("1")){

					Country.add("United States");

				}else if(rsCountry.getString("Country_ID").contains("2")){

					Country.add("United Kingdom");

				}else if(rsCountry.getString("Country_ID").contains("3")){

					Country.add("Canada");

				}

			}

			JDBC.closeConnection();

			ModifyPatientrCountryCombo.setItems(Country);

		} catch (SQLException e) {

			e.printStackTrace();

		}

		JDBC.openConnection();

		ResultSet rs = result.result("SELECT DIVISION, COUNTRY_ID FROM FIRST_LEVEL_DIVISIONS WHERE DIVISION_ID = " + patient.getDivision_Id());

		while(rs.next()) {

			if (Integer.parseInt(rs.getString("COUNTRY_ID")) == 1) {

				ModifyPatientrCountryCombo.setValue("United States");
				ModifyPatientDivisionCombo.setValue(rs.getString("DIVISION"));

			} else if (Integer.parseInt(rs.getString("COUNTRY_ID")) == 2) {

				ModifyPatientrCountryCombo.setValue("United Kingdom");
				ModifyPatientDivisionCombo.setValue(rs.getString("DIVISION"));

			} else if (Integer.parseInt(rs.getString("COUNTRY_ID")) == 3) {

				ModifyPatientrCountryCombo.setValue("Canada");
				ModifyPatientDivisionCombo.setValue(rs.getString("DIVISION"));

			}

		}

		JDBC.closeConnection();

		ModifyPatientPostalcodeTxtField1.setText(patient.getPostal_Code());
		ModifyPatientPhoneNumberTxtField.setText(patient.getPhone());

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
		ModifyPatientUserIdTxtField.setText(String.valueOf(currentUserId));

	}

	/***
	 * divisionIdValidation
	 * The divisionIdValidation is a validation method! It checks if the division ID exist based on the City and
	 * Country selected and gets the division ID! If the combination does not exist, an alert will show up!
	 * @return This method returns a String of the division ID!
	 * @throws SQLException SQLException is needed in case of SQL query failure!
	 */
	public String divisionIdValidation() throws SQLException {

		ResultSet rs = null;

		JDBC.openConnection();

		if(ModifyPatientrCountryCombo.getSelectionModel().getSelectedItem() == "United States") {

			rs = result.result("SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE DIVISION = '" + ModifyPatientDivisionCombo.getSelectionModel().getSelectedItem() + "' AND COUNTRY_ID = 1");

		}else if(ModifyPatientrCountryCombo.getSelectionModel().getSelectedItem() == "United Kingdom"){

			rs = result.result("SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE DIVISION = '" + ModifyPatientDivisionCombo.getSelectionModel().getSelectedItem() + "' AND COUNTRY_ID = 2");

		}else if(ModifyPatientrCountryCombo.getSelectionModel().getSelectedItem() == "Canada"){

			rs = result.result("SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE DIVISION = '" + ModifyPatientDivisionCombo.getSelectionModel().getSelectedItem() + "' AND COUNTRY_ID = 3");

		}

		String divisionIdResult = null;

		if(rs.next()){

			divisionIdResult = rs.getString("DIVISION_ID");

		}

		JDBC.closeConnection();

		return divisionIdResult;

	}

	/***
	 * getUserName
	 * The method getUserName gets the user name of the user that is logged in!
	 * @return The method returns a string value of the username after the SQL query is performed!
	 * @throws SQLException SQLException is needed in case of SQL query failure!
	 */
	public String getUserName() throws SQLException {

		int userId = Integer.parseInt(ModifyPatientUserIdTxtField.getText());
		String userName;

		JDBC.openConnection();

		ResultSet rs = result.result("SELECT User_Name FROM USERS WHERE USER_ID = " + userId);

		rs.next();
		userName = rs.getString("User_Name");

		JDBC.closeConnection();

		return userName;

	}

}
