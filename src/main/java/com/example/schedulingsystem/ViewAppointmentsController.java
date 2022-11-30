package com.example.schedulingsystem;

import Database.AppointmentCRUD;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Class.AppointmentReport;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.ResourceBundle;

/***
 * This is the view appointments controller! This scene contains a tableview for all appointments!
 * It also contains radio buttons if the user wants to view all appointments, weekly appointments or monthly
 * appointments.
 */
public class ViewAppointmentsController implements Initializable {

	ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());

	Stage stage;
	Scene scene;
	Parent root;

	private int currentUserId;

	@FXML
	private RadioButton AllRadioBtn;

	@FXML
	private TableColumn<AppointmentReport, Integer> AppointmentsIdClm;

	@FXML
	private TableColumn<AppointmentReport, Integer> TypeClm;

	@FXML
	private TableColumn<AppointmentReport, String> ContactClm;

	@FXML
	private TableColumn<AppointmentReport, Integer> PatientIdClm;

	@FXML
	private TableColumn<AppointmentReport, String> DescriptionClm;

	@FXML
	private TableColumn<AppointmentReport, Timestamp> EndClm;

	@FXML
	private TableColumn<AppointmentReport, String> AppointmentMethodClm;

	@FXML
	private RadioButton MonthlyRadioBtn;

	@FXML
	private ToggleGroup RadioBtn;

	@FXML
	private TableColumn<AppointmentReport, Timestamp> StartClm;

	@FXML
	private TableColumn<AppointmentReport, String> TitleClm;

	@FXML
	private TableColumn<AppointmentReport, Integer> UserIdClm;

	@FXML
	private Label ViewAppointmentsAppointmentsLabel;

	@FXML
	private TableView<AppointmentReport> ViewAppointmentsAppointmentsTV;

	@FXML
	private Button ViewAppointmentsCloseBtn;

	@FXML
	private RadioButton WeeklyRadioBtn;

	@FXML
	private TextField AppointmentSearch;

	/***
	 * AllRadioBtn
	 * The method AllRadioBtn sets the table view with the observable list from the getAppointmentReport for the
	 * report for all appointments!
	 * @param event When radio button "All Appointments" is selected, the tableview is set to the getAppointmentReport
	 *              observable list for all the appointments!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void AllRadioBtn(ActionEvent event) throws Exception {

		AppointmentSearch.clear();
		ViewAppointmentsAppointmentsTV.setItems(AppointmentCRUD.getAppointmentReport("ALL"));

		FilteredList<AppointmentReport> filteredData = new FilteredList<>(AppointmentCRUD.getAppointmentReport("ALL"), b -> true);

		AppointmentSearch.textProperty().addListener((observable, oldValue, newValue) -> {

			filteredData.setPredicate(appointmentReport -> {

				if (newValue.isEmpty() || newValue.isBlank() || newValue == null){

					return true;

				}

				String searchKeyword = newValue.toLowerCase();

				if (Integer.toString(appointmentReport.getAppointment_Id()).toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(Integer.toString(appointmentReport.getUser_Id()).toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(Integer.toString(appointmentReport.getPatient_Id()).toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getTitle().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getDescription().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getAppointmentMethod().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getType().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getTherapist().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				}else

					return false;

			});

		});

		SortedList<AppointmentReport> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(ViewAppointmentsAppointmentsTV.comparatorProperty());

		ViewAppointmentsAppointmentsTV.setItems(sortedData);

	}

	/***
	 * MonthlyRadioBtn
	 * The method MonthlyRadioBtn sets the table view with the observable list from the getAppointmentReport for the
	 * report for appointments in the current month!
	 * @param event When radio button "Monthly Appointments" is selected, the tableview is set to the getAppointmentReport
	 *              observable list for appointments for the current month!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void MonthlyRadioBtn(ActionEvent event) throws Exception {

		AppointmentSearch.clear();
		ViewAppointmentsAppointmentsTV.setItems(AppointmentCRUD.getAppointmentReport("MONTHLY"));

		FilteredList<AppointmentReport> filteredData = new FilteredList<>(AppointmentCRUD.getAppointmentReport("MONTHLY"), b -> true);

		AppointmentSearch.textProperty().addListener((observable, oldValue, newValue) -> {

			filteredData.setPredicate(appointmentReport -> {

				if (newValue.isEmpty() || newValue.isBlank() || newValue == null){

					return true;

				}

				String searchKeyword = newValue.toLowerCase();

				if (Integer.toString(appointmentReport.getAppointment_Id()).toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(Integer.toString(appointmentReport.getUser_Id()).toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(Integer.toString(appointmentReport.getPatient_Id()).toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getTitle().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getDescription().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getAppointmentMethod().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getType().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getTherapist().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				}else

					return false;

			});

		});

		SortedList<AppointmentReport> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(ViewAppointmentsAppointmentsTV.comparatorProperty());

		ViewAppointmentsAppointmentsTV.setItems(sortedData);

	}

	/***
	 * ViewAppointmentsCloseBtn
	 * The ViewAppointmentsCloseBtn method sets the scene to the MainScene
	 * when the "Close" button is clicked!
	 * @param event Upon the "Close" button is clicked, the code within the ViewAppointmentsCloseBtn
	 *              gets executed!
	 * @throws IOException IOException is needed in case of failure!
	 */
	@FXML
	void ViewAppointmentsCloseBtn(ActionEvent event) throws IOException {

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
	 * WeeklyRadioBtn
	 * The method WeeklyRadioBtn sets the table view with the observable list from the getAppointmentReport for the
	 * report for appointments in the current week!
	 * @param event When radio button "Weekly Appointments" is selected, the tableview is set to the getAppointmentReport
	 *              observable list for appointments for the current week!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void WeeklyRadioBtn(ActionEvent event) throws Exception {

		AppointmentSearch.clear();
		ViewAppointmentsAppointmentsTV.setItems(AppointmentCRUD.getAppointmentReport("WEEKLY"));

		FilteredList<AppointmentReport> filteredData = new FilteredList<>(AppointmentCRUD.getAppointmentReport("WEEKLY"), b -> true);

		AppointmentSearch.textProperty().addListener((observable, oldValue, newValue) -> {

			filteredData.setPredicate(appointmentReport -> {

				if (newValue.isEmpty() || newValue.isBlank() || newValue == null){

					return true;

				}

				String searchKeyword = newValue.toLowerCase();

				if (Integer.toString(appointmentReport.getAppointment_Id()).toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(Integer.toString(appointmentReport.getUser_Id()).toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(Integer.toString(appointmentReport.getPatient_Id()).toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getTitle().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getDescription().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getAppointmentMethod().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getType().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				} else if(appointmentReport.getTherapist().toLowerCase().indexOf(searchKeyword) > -1){

					return true;

				}else

					return false;

			});

		});

		SortedList<AppointmentReport> sortedData = new SortedList<>(filteredData);

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
	 * The method initialize the View Appointments Scene! It sets all the labels and radio buttons, tableview and columns!
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		ViewAppointmentsAppointmentsLabel.setText(rb.getString("lb47"));
		AppointmentsIdClm.setText(rb.getString("lb5"));
		UserIdClm.setText(rb.getString("lb8"));
		PatientIdClm.setText(rb.getString("lb9"));
		TitleClm.setText(rb.getString("lb11"));
		DescriptionClm.setText(rb.getString("lb12"));
		AppointmentMethodClm.setText(rb.getString("lb101"));
		TypeClm.setText(rb.getString("lb16"));
		ContactClm.setText(rb.getString("lb14"));
		StartClm.setText(rb.getString("lb48"));
		EndClm.setText(rb.getString("lb49"));
		WeeklyRadioBtn.setText(rb.getString("lb66"));
		MonthlyRadioBtn.setText(rb.getString("lb67"));
		AllRadioBtn.setText(rb.getString("lb68"));
		ViewAppointmentsCloseBtn.setText(rb.getString("lb4"));

		try {

			ViewAppointmentsAppointmentsTV.setItems(AppointmentCRUD.getAppointmentReport("ALL"));
			AppointmentsIdClm.setCellValueFactory(new PropertyValueFactory<>("appointment_Id"));
			UserIdClm.setCellValueFactory(new PropertyValueFactory<>("user_Id"));
			PatientIdClm.setCellValueFactory(new PropertyValueFactory<>("patient_Id"));
			TitleClm.setCellValueFactory(new PropertyValueFactory<>("title"));
			DescriptionClm.setCellValueFactory(new PropertyValueFactory<>("description"));
			AppointmentMethodClm.setCellValueFactory(new PropertyValueFactory<>("appointmentMethod"));
			TypeClm.setCellValueFactory(new PropertyValueFactory<>("type"));
			ContactClm.setCellValueFactory(new PropertyValueFactory<>("therapist"));
			StartClm.setCellValueFactory(new PropertyValueFactory<>("start"));
			EndClm.setCellValueFactory(new PropertyValueFactory<>("end"));

			FilteredList<AppointmentReport> filteredData = new FilteredList<>(AppointmentCRUD.getAppointmentReport("ALL"), b -> true);

			AppointmentSearch.textProperty().addListener((observable, oldValue, newValue) -> {

				filteredData.setPredicate(appointmentReport -> {

					if (newValue.isEmpty() || newValue.isBlank() || newValue == null){

						return true;

					}

					String searchKeyword = newValue.toLowerCase();

					if (Integer.toString(appointmentReport.getAppointment_Id()).toLowerCase().indexOf(searchKeyword) > -1){

						return true;

					} else if(Integer.toString(appointmentReport.getUser_Id()).toLowerCase().indexOf(searchKeyword) > -1){

						return true;

					} else if(Integer.toString(appointmentReport.getPatient_Id()).toLowerCase().indexOf(searchKeyword) > -1){

						return true;

					} else if(appointmentReport.getTitle().toLowerCase().indexOf(searchKeyword) > -1){

						return true;

					} else if(appointmentReport.getDescription().toLowerCase().indexOf(searchKeyword) > -1){

						return true;

					} else if(appointmentReport.getAppointmentMethod().toLowerCase().indexOf(searchKeyword) > -1){

						return true;

					} else if(appointmentReport.getType().toLowerCase().indexOf(searchKeyword) > -1){

						return true;

					} else if(appointmentReport.getTherapist().toLowerCase().indexOf(searchKeyword) > -1){

						return true;

					}else

						return false;

				});

			});

			SortedList<AppointmentReport> sortedData = new SortedList<>(filteredData);

			sortedData.comparatorProperty().bind(ViewAppointmentsAppointmentsTV.comparatorProperty());

			ViewAppointmentsAppointmentsTV.setItems(sortedData);


		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
