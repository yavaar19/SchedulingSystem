package com.example.schedulingsystem;

import Database.PatientCRUD;
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
import java.util.Locale;
import java.util.ResourceBundle;
import Class.TimestampData;

/***
 * This is the add patient controller! This scene contains labels and text fields, combo boxes
 * for the patient's data!
 */
public class AddPatientController implements Initializable {

	ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());

	Stage stage;
	Scene scene;
	Parent root;

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
	private Button AddPatientCloseBtn;

	@FXML
	private Button AddPatientSaveBtn;

	@FXML
	private Label AddPatientAddressLabel;

	@FXML
	private TextField AddPatientAddressTxtField;

	@FXML
	private ComboBox<String> AddPatientDivisionCombo;

	@FXML
	private Label AddPatientDivisionLabel;

	@FXML
	private Label AddPatientCountryLabel;

	@FXML
	private ComboBox<String> AddPatientCountryCombo;

	@FXML
	private Label AddPatientCityLabel;

	@FXML
	private TextField AddPatientCityTxtField;

	@FXML
	private Label AddPatientPatientIdLabel;

	@FXML
	private TextField AddPatientPatientIdTxtField;

	@FXML
	private Label AddPatientPatientNameLabel;

	@FXML
	private Label AddPatientLabel;

	@FXML
	private Label AddPatientPatientIdTxtLabel;

	@FXML
	private TextField AddPatientPatientNameTxtField;

	@FXML
	private Label AddPatientPhoneLabel;

	@FXML
	private TextField AddPatientPhoneNumberTxtField;

	@FXML
	private Label AddPatientPostalCodeLabel;

	@FXML
	private TextField AddPatientPostalcodeTxtField1;

	@FXML
	private Label AddPatientUserIdLabel;

	@FXML
	private TextField AddPatientUserIdTxtField;

	/***
	 * AddPatientCloseBtn
	 * The AddPatientCloseBtn method sets the scene to the MainScene
	 * when the "Close" button is clicked!
	 * @param event Upon the "Close" button is clicked, the code within the AddPatientCloseBtn
	 *              gets executed!
	 * @throws IOException IOException is needed in case of failure!
	 */
	@FXML
	void AddPatientCloseBtn(ActionEvent event) throws IOException {

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
	 * AddPatientCountryCombo
	 * The AddPatientCountryCombo sets the available country from the first level division table to the country
	 * combo box!
	 * @param event When the scene loads, the available country from the first level division table to the country
	 * 	 			combo box!
	 * @throws SQLException SQLException is needed in case of SQL query failure!
	 */
	@FXML
	void AddPatientCountryCombo(ActionEvent event) throws SQLException {

		ResultSet rs = null;

		Division.clear();
		JDBC.openConnection();

		if(AddPatientDivisionCombo.getSelectionModel().getSelectedItem() == null) {

			if (AddPatientCountryCombo.getSelectionModel().getSelectedItem() == "United States") {

				rs = result.result("SELECT DIVISION FROM FIRST_LEVEL_DIVISIONS WHERE COUNTRY_ID = 1");

			} else if (AddPatientCountryCombo.getSelectionModel().getSelectedItem() == "United Kingdom") {

				rs = result.result("SELECT DIVISION FROM FIRST_LEVEL_DIVISIONS WHERE COUNTRY_ID = 2");

			} else if (AddPatientCountryCombo.getSelectionModel().getSelectedItem() == "Canada") {

				rs = result.result("SELECT DIVISION FROM FIRST_LEVEL_DIVISIONS WHERE COUNTRY_ID = 3");

			}

			while (rs.next()) {

				Division.add(rs.getString("Division"));

			}

			JDBC.closeConnection();

		}

	}

	@FXML
	void AddPatientDivisionCombo(ActionEvent event) throws SQLException {


	}

	/***
	 * AddPatientSaveBtn
	 * The AddPatientSaveBtn method sets the scene to the MainScene when the "Save" button is clicked! This method
	 * performs all the input validation for the Patient's data, records created date timestamp value etc then saves it to the database!
	 * @param event Upon the "Save" button is clicked, the code within the AddPatientSaveBtn
	 *              gets executed!
	 * @throws IOException IOException is needed in case of failure!
	 */
	@FXML
	void AddPatientSaveBtn(ActionEvent event) throws SQLException, IOException {

		if(AddPatientPatientNameTxtField.getText() == null || AddPatientAddressTxtField.getText() == null || AddPatientCityTxtField.getText() == null ||  AddPatientDivisionCombo.getSelectionModel().getSelectedItem() == null || AddPatientCountryCombo.getSelectionModel().getSelectedItem() == null || AddPatientPostalcodeTxtField1.getText() == null || AddPatientPhoneNumberTxtField.getText() == null){

			a.alert(rb.getString("lb70"));

		}else if(!AddPatientPatientNameTxtField.getText().matches("[a-zA-Z]*\\s*[a-zA-Z]*\\s*") || AddPatientPatientNameTxtField.getText() == ""){

			a.alert(rb.getString("lb75"));

		}else if(!AddPatientAddressTxtField.getText().matches("\\d+\\s+[a-zA-Z]*\\s*[a-zA-Z]+\\s[a-zA-Z]*")){

			a.alert(rb.getString("lb76"));

		}else if(!AddPatientCityTxtField.getText().matches("[a-zA-Z ]+")){

			a.alert(rb.getString("lb77"));

		} else if(!AddPatientPostalcodeTxtField1.getText().matches("[a-zA-Z0-9 ]+")){

			a.alert(rb.getString("lb78"));

		}else if(!AddPatientPhoneNumberTxtField.getText().matches("[0-9- ]+")){

			a.alert(rb.getString("lb79"));

		}else if(divisionIdValidation() == null){

			a.alert(AddPatientDivisionCombo.getSelectionModel().getSelectedItem() + rb.getString("lb80") + AddPatientCountryCombo.getSelectionModel().getSelectedItem());

		}else{

			int patientId = Integer.parseInt(AddPatientPatientIdTxtField.getText());
			String patientName = AddPatientPatientNameTxtField.getText();
			String address = AddPatientAddressTxtField.getText() + ", " + AddPatientCityTxtField.getText();
			String postalCode = AddPatientPostalcodeTxtField1.getText();
			String phoneNumber = AddPatientPhoneNumberTxtField.getText();
			Timestamp createDateTime = TimestampData.getCreateDateTime();
			String createdBy = getUserName();
			Timestamp lastUpdateDateTime = TimestampData.getUpdateDateTime();
			String lastUpdatedBy = createdBy;
			int divisionId = Integer.parseInt(divisionIdValidation());

			PatientCRUD.insertPatient(patientId, patientName, address, postalCode, phoneNumber, createDateTime, createdBy, lastUpdateDateTime, lastUpdatedBy, divisionId);

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
		AddPatientUserIdTxtField.setText(String.valueOf(currentUserId));

	}

	/***
	 * initialize
	 * The method initialize the Add Patient Scene! It sets all the labels and combo boxes!
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		AddPatientLabel.setText(rb.getString("lb23"));
		AddPatientPatientIdTxtLabel.setText(rb.getString("lb9"));
		AddPatientUserIdLabel.setText(rb.getString("lb8"));
		AddPatientPatientNameLabel.setText(rb.getString("lb24"));
		AddPatientAddressLabel.setText(rb.getString("lb25"));
		AddPatientCityLabel.setText(rb.getString("lb26"));
		AddPatientDivisionLabel.setText(rb.getString("lb27"));
		AddPatientCountryLabel.setText(rb.getString("lb29"));
		AddPatientPostalCodeLabel.setText(rb.getString("lb31"));
		AddPatientPhoneLabel.setText(rb.getString("lb32"));
		AddPatientSaveBtn.setText(rb.getString("lb22"));
		AddPatientCloseBtn.setText(rb.getString("lb4"));
		AddPatientPatientIdTxtField.setText(rb.getString("lb7"));
		AddPatientDivisionCombo.setPromptText(rb.getString("lb28"));
		AddPatientCountryCombo.setPromptText(rb.getString("lb30"));



		try {

			AddPatientPatientIdTxtField.setText(String.valueOf(generateId()));
			JDBC.openConnection();
			ResultSet rs = result.result("SELECT DIVISION FROM FIRST_LEVEL_DIVISIONS");

			while(rs.next()){

				Division.add(rs.getString("Division"));

			}

			AddPatientDivisionCombo.setItems(Division);

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

			AddPatientCountryCombo.setItems(Country);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/***
	 * generateId
	 * The generateId method uses a sorting algorithm to generate the next patient's ID! It does so by getting all
	 * the existing patient IDs, sorts them and sets the next patient ID to the highest patient ID plus 1!
	 * @return The method returns the an integer of the next patient ID!
	 * @throws SQLException SQLException is needed in case of SQL query failure!
	 */
	public int generateId() throws SQLException {

		int index = 0;
		int id;
		boolean sorted = false;
		int temp;
		int count = 1;

		JDBC.openConnection();
		ResultSet rs = result.result("SELECT * FROM PATIENTS");

		while (rs.next()) {

			count++;

		}
/*
		if(rs.next() == false){

			count = 1;

		}
*/
		int[] idArray = new int[count];

		ResultSet rsA = result.result("SELECT * FROM PATIENTS");

		while(rsA.next()){

			idArray[index] = rsA.getInt("Patient_ID");
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

		int userId = Integer.parseInt(AddPatientUserIdTxtField.getText());
		String userName;

		JDBC.openConnection();

		ResultSet rs = result.result("SELECT User_Name FROM USERS WHERE USER_ID = " + userId);

		rs.next();
		userName = rs.getString("User_Name");

		JDBC.closeConnection();

		return userName;

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

		if(AddPatientCountryCombo.getSelectionModel().getSelectedItem() == "United States") {

			rs = result.result("SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE DIVISION = '" + AddPatientDivisionCombo.getSelectionModel().getSelectedItem() + "' AND COUNTRY_ID = 1");

		}else if(AddPatientCountryCombo.getSelectionModel().getSelectedItem() == "United Kingdom"){

			rs = result.result("SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE DIVISION = '" + AddPatientDivisionCombo.getSelectionModel().getSelectedItem() + "' AND COUNTRY_ID = 2");

		}else if(AddPatientCountryCombo.getSelectionModel().getSelectedItem() == "Canada"){

			rs = result.result("SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE DIVISION = '" + AddPatientDivisionCombo.getSelectionModel().getSelectedItem() + "' AND COUNTRY_ID = 3");

		}

		String divisionIdResult = null;

		if(rs.next()){

			divisionIdResult = rs.getString("DIVISION_ID");

		}

		JDBC.closeConnection();

		return divisionIdResult;

	}

}
