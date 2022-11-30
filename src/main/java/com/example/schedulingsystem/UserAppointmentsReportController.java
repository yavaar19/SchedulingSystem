package com.example.schedulingsystem;

import Database.TypeCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Class.UserAppointmentsReport;
import Database.UserAppointmentReportCRUD;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/***
 * This is the user appointment controller! This scene contains a tableview for the user, and count!
 * IT also contains radio buttons for the month! This is my chosen custom report! It shows all the user's appointments
 * count by month!
 */
public class UserAppointmentsReportController implements Initializable {

	ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());

	Stage stage;
	Scene scene;
	Parent root;

	private int currentUserId;

	@FXML
	private RadioButton AprilRadioBtn;

	@FXML
	private RadioButton AugRadioBtn;

	@FXML
	private TableColumn<UserAppointmentsReport, Integer> CountClm;

	@FXML
	private RadioButton DecRadioBtn;

	@FXML
	private RadioButton FebRadioBtn;

	@FXML
	private RadioButton JanRadioBtn;

	@FXML
	private RadioButton JulyRadioBtn;

	@FXML
	private RadioButton JuneRadioBtn;

	@FXML
	private RadioButton MarRadioBtn;

	@FXML
	private RadioButton MayRadioBtn;

	@FXML
	private RadioButton NovRadioBtn;

	@FXML
	private RadioButton OctRadioBtn;

	@FXML
	private ToggleGroup RadioBtn;

	@FXML
	private RadioButton SeptRadioBtn;

	@FXML
	private TableColumn<UserAppointmentsReport, String> UserClm;

	@FXML
	private Button UserAppointmentsReportCloseBtn;

	@FXML
	private Label UserAppointmentsReportLabel;

	@FXML
	private TableView<UserAppointmentsReport> UserAppointmentsReportTV;

	/***
	 * AprilRadioBtn
	 * The method AprilRadioBtn sets the table view with the observable list from the getUserAppointmentsReport for the
	 * report for the month of April!
	 * @param event When radio button "April" is selected, the tableview is set to the getUserAppointmentsReport
	 *              observable list for April!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void AprilRadioBtn(ActionEvent event) throws Exception {

		UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("APRIL"));

	}

	/***
	 * AugRadioBtn
	 * The method AugRadioBtn sets the table view with the observable list from the getUserAppointmentsReport for the
	 * report for the month of August!
	 * @param event When radio button "August" is selected, the tableview is set to the getUserAppointmentsReport
	 *              observable list for August!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void AugRadioBtn(ActionEvent event) throws Exception {

		UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("AUGUST"));

	}

	/***
	 * DecRadioBtn
	 * The method DecRadioBtn sets the table view with the observable list from the getUserAppointmentsReport for the
	 * report for the month of December!
	 * @param event When radio button "December" is selected, the tableview is set to the getUserAppointmentsReport
	 *              observable list for December!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void DecRadioBtn(ActionEvent event) throws Exception {

		UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("DECEMBER"));

	}

	/***
	 * FebRadioBtn
	 * The method FebRadioBtn sets the table view with the observable list from the getUserAppointmentsReport for the
	 * report for the month of February!
	 * @param event When radio button "February" is selected, the tableview is set to the getUserAppointmentsReport
	 *              observable list for February!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void FebRadioBtn(ActionEvent event) throws Exception {

		UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("FEBRUARY"));

	}

	/***
	 * JanRadioBtn
	 * The method JanRadioBtn sets the table view with the observable list from the getUserAppointmentsReport for the
	 * report for the month of January!
	 * @param event When radio button "January" is selected, the tableview is set to the getUserAppointmentsReport
	 *              observable list for January!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void JanRadioBtn(ActionEvent event) throws Exception {

		UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("JANUARY"));

	}

	/***
	 * JulyRadioBtn
	 * The method JulyRadioBtn sets the table view with the observable list from the getUserAppointmentsReport for the
	 * report for the month of July!
	 * @param event When radio button "July" is selected, the tableview is set to the getUserAppointmentsReport
	 *              observable list for July!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void JulyRadioBtn(ActionEvent event) throws Exception {

		UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("JULY"));

	}

	/***
	 * JuneRadioBtn
	 * The method JuneRadioBtn sets the table view with the observable list from the getUserAppointmentsReport for the
	 * report for the month of June!
	 * @param event When radio button "June" is selected, the tableview is set to the getUserAppointmentsReport
	 *              observable list for June!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void JuneRadioBtn(ActionEvent event) throws Exception {

		UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("JUNE"));

	}

	/***
	 * MarRadioBtn
	 * The method MarRadioBtn sets the table view with the observable list from the getUserAppointmentsReport for the
	 * report for the month of March!
	 * @param event When radio button "March" is selected, the tableview is set to the getUserAppointmentsReport
	 *              observable list for March!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void MarRadioBtn(ActionEvent event) throws Exception {

		UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("MARCH"));

	}

	/***
	 * MayRadioBtn
	 * The method MarRadioBtn sets the table view with the observable list from the getUserAppointmentsReport for the
	 * report for the month of May!
	 * @param event When radio button "May" is selected, the tableview is set to the getUserAppointmentsReport
	 *              observable list for May!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void MayRadioBtn(ActionEvent event) throws Exception {

		UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("MAY"));

	}

	/***
	 * NovRadioBtn
	 * The method NovRadioBtn sets the table view with the observable list from the getUserAppointmentsReport for the
	 * report for the month of November!
	 * @param event When radio button "November" is selected, the tableview is set to the getUserAppointmentsReport
	 *              observable list for November!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void NovRadioBtn(ActionEvent event) throws Exception {

		UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("NOVEMBER"));

	}

	/***
	 * OctRadioBtn
	 * The method OctRadioBtn sets the table view with the observable list from the getUserAppointmentsReport for the
	 * report for the month of October!
	 * @param event When radio button "October" is selected, the tableview is set to the getUserAppointmentsReport
	 *              observable list for October!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void OctRadioBtn(ActionEvent event) throws Exception {

		UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("OCTOBER"));

	}

	/***
	 * SepRadioBtn
	 * The method SepRadioBtn sets the table view with the observable list from the getUserAppointmentsReport for the
	 * report for the month of September!
	 * @param event When radio button "September" is selected, the tableview is set to the getUserAppointmentsReport
	 *              observable list for September!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void SepRadioBtn(ActionEvent event) throws Exception {

		UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("SEPTEMBER"));

	}

	/***
	 * UserAppointmentsReportCloseBtn
	 * The UserAppointmentsReportCloseBtn method sets the scene to the MainScene
	 * when the "Close" button is clicked!
	 * @param event Upon the "Close" button is clicked, the code within the UserAppointmentsReportCloseBtn
	 *              gets executed!
	 * @throws IOException IOException is needed in case of failure!
	 */
	@FXML
	void UserAppointmentsReportCloseBtn(ActionEvent event) throws IOException {

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
	 * The method initialize the User Appointments Report Scene! It sets all the labels and radio buttons!
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		UserAppointmentsReportLabel.setText(rb.getString("lb64"));
		JanRadioBtn.setText(rb.getString("lb34"));
		FebRadioBtn.setText(rb.getString("lb35"));
		MarRadioBtn.setText(rb.getString("lb36"));
		AprilRadioBtn.setText(rb.getString("lb37"));
		MayRadioBtn.setText(rb.getString("lb38"));
		JuneRadioBtn.setText(rb.getString("lb39"));
		JulyRadioBtn.setText(rb.getString("lb40"));
		AugRadioBtn.setText(rb.getString("lb41"));
		SeptRadioBtn.setText(rb.getString("lb42"));
		OctRadioBtn.setText(rb.getString("lb43"));
		NovRadioBtn.setText(rb.getString("lb44"));
		DecRadioBtn.setText(rb.getString("lb45"));
		UserClm.setText(rb.getString("lb65"));
		CountClm.setText(rb.getString("lb46"));
		UserAppointmentsReportCloseBtn.setText(rb.getString("lb4"));

		try {

			UserAppointmentsReportTV.setItems(UserAppointmentReportCRUD.getUserAppointmentsReport("MAY"));
			UserClm.setCellValueFactory(new PropertyValueFactory<>("username"));
			CountClm.setCellValueFactory(new PropertyValueFactory<>("count"));

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}
