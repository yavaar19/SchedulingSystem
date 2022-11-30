package com.example.schedulingsystem;

import Database.JDBC;
import Database.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Class.TherapistSchedule;
import Database.TherapistScheduleCRUD;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.ResourceBundle;

/***
 * This is the therapist schedule report controller! This scene contains a tableview for the appointments, and a
 * combo box to choose the therapist name!
 */
public class TherapistScheduleReportController implements Initializable {

	ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());

	ObservableList<String> therapist = FXCollections.observableArrayList();

	Stage stage;
	Scene scene;
	Parent root;

	private int currentUserId;

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

	@FXML
	private TableColumn<TherapistSchedule, Integer> AppointmentsIdClm;

	@FXML
	private Label ContactScheduleReportAppointmentsLabel;

	@FXML
	private Button ContactScheduleReportCloseBtn;

	@FXML
	private ComboBox<String> ContactScheduleReportCombo;

	@FXML
	private TableColumn<TherapistSchedule, Integer> PatientIdClm;

	@FXML
	private TableColumn<TherapistSchedule, String> DescriptionClm;

	@FXML
	private TableColumn<TherapistSchedule, Timestamp> EndClm;

	@FXML
	private TableColumn<TherapistSchedule, Timestamp> StartClm;

	@FXML
	private TableColumn<TherapistSchedule, String> TitleClm;

	@FXML
	private TableColumn<TherapistSchedule, String> TypeClm;

	@FXML
	private TableView<TherapistSchedule> ViewAppointmentsAppointmentsTV;

	@FXML
	private TextField AppointmentSearch;

	/***
	 * ContactScheduleReportCloseBtn
	 * The ContactScheduleReportCloseBtn method sets the scene to the MainScene
	 * when the "Close" button is clicked!
	 * @param event Upon the "Close" button is clicked, the code within the ContactScheduleReportCloseBtn
	 *              gets executed!
	 * @throws IOException IOException is needed in case of failure!
	 */
	@FXML
	void ContactScheduleReportCloseBtn(ActionEvent event) throws IOException {

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
	 * ContactScheduleReportCombo
	 * The method ContactScheduleReportCombo sets the table view to the observable list of getContactSchedule with
	 * the selected therapist name! All of the appointments for that therapist name is then populated!
	 * @param event When the therapist name is selected from the combo box, all of the appointments for
	 *              that therapist name is then populated!
	 * @throws Exception
	 */
	@FXML
	void ContactScheduleReportCombo(ActionEvent event) throws Exception {

		AppointmentSearch.clear();
		ViewAppointmentsAppointmentsTV.setItems(TherapistScheduleCRUD.getContactSchedule(ContactScheduleReportCombo.getSelectionModel().getSelectedItem()));
		AppointmentsIdClm.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
		PatientIdClm.setCellValueFactory(new PropertyValueFactory<>("patientId"));
		DescriptionClm.setCellValueFactory(new PropertyValueFactory<>("description"));
		EndClm.setCellValueFactory(new PropertyValueFactory<>("end"));
		StartClm.setCellValueFactory(new PropertyValueFactory<>("start"));
		TitleClm.setCellValueFactory(new PropertyValueFactory<>("title"));
		TypeClm.setCellValueFactory(new PropertyValueFactory<>("type"));

		FilteredList<TherapistSchedule> filteredData = new FilteredList<>(TherapistScheduleCRUD.getContactSchedule(ContactScheduleReportCombo.getSelectionModel().getSelectedItem()), b -> true);

		AppointmentSearch.textProperty().addListener((observable, oldValue, newValue) -> {

			filteredData.setPredicate(therapistSchedule -> {

				if (newValue.isEmpty() || newValue.isBlank() || newValue == null){

					return true;

				}

				String searchKeyword = newValue.toLowerCase();

				if (Integer.toString(therapistSchedule.getAppointmentId()).toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(Integer.toString(therapistSchedule.getPatientId()).toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(therapistSchedule.getTitle().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(therapistSchedule.getDescription().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(therapistSchedule.getType().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else

					return false;

			});

		});

		SortedList<TherapistSchedule> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(ViewAppointmentsAppointmentsTV.comparatorProperty());

		ViewAppointmentsAppointmentsTV.setItems(sortedData);

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
	 * initialize
	 * The method initialize the Contact Schedule Report Scene! It sets the label, columns, and add all existing
	 * therapist's name to the combo box!
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		ContactScheduleReportAppointmentsLabel.setText(rb.getString("lb47"));
		AppointmentsIdClm.setText(rb.getString("lb5"));
		PatientIdClm.setText(rb.getString("lb9"));
		TitleClm.setText(rb.getString("lb11"));
		DescriptionClm.setText(rb.getString("lb12"));
		TypeClm.setText(rb.getString("lb16"));
		StartClm.setText(rb.getString("lb48"));
		EndClm.setText(rb.getString("lb49"));
		ContactScheduleReportCombo.setPromptText(rb.getString("lb15"));
		ContactScheduleReportCloseBtn.setText(rb.getString("lb4"));

		try {

		ContactScheduleReportCombo.setItems(therapist);

		JDBC.openConnection();

		ResultSet rs = result.result("SELECT THERAPIST_NAME FROM THERAPISTS");

		while(rs.next()) {

			therapist.add(rs.getString("THERAPIST_NAME"));

		}

		JDBC.closeConnection();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

}
