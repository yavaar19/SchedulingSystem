package com.example.schedulingsystem;

import Database.TypeCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Class.Type;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/***
 * This is the appointment types controller! This scene contains a tableview for the appointment types, and count!
 * IT also contains radio buttons for the month!
 */
public class AppointmentTypesReportController implements Initializable {

	ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());

	Stage stage;
	Scene scene;
	Parent root;

	private int currentUserId;

	@FXML
	private Button AppointmentTypesReportCloseBtn;

	@FXML
	private Label AppointmentTypesReportLabel;

	@FXML
	private TableView<Type> AppointmentTypesReportTV;

	@FXML
	private RadioButton AprilRadioBtn;

	@FXML
	private RadioButton AugRadioBtn;

	@FXML
	private TableColumn<Type, Integer> CountClm;

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
	private TableColumn<Type, String> TypeClm;

	/***
	 * AppointmentTypesReportCloseClm
	 * The AppointmentTypesReportCloseClm method sets the scene to the MainScene
	 * when the "Close" button is clicked!
	 * @param event Upon the "Close" button is clicked, the code within the AppointmentTypesReportCloseClm
	 *              gets executed!
	 * @throws IOException IOException is needed in case of failure!
	 */
	@FXML
	void AppointmentTypesReportCloseClm(ActionEvent event) throws IOException {

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
	 * AprilRadioBtn
	 * The method AprilRadioBtn sets the table view with the observable list from the getAllTypes for the report for the month of
	 * April!
	 * @param event When radio button "April" is selected, the tableview is set to the getAllTypes observable list for
	 *              April!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void AprilRadioBtn(ActionEvent event) throws Exception {

		AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("APRIL"));

	}

	/***
	 * AugRadioBtn
	 * The method AugRadioBtn sets the table view with the observable list from the getAllTypes for the report for the month of
	 * August!
	 * @param event When radio button "August" is selected, the tableview is set to the getAllTypes observable list for
	 *              August!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void AugRadioBtn(ActionEvent event) throws Exception {

		AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("AUGUST"));

	}

	/***
	 * DecRadioBtn
	 * The method DecRadioBtn sets the table view with the observable list from the getAllTypes for the report for the month of
	 * December!
	 * @param event When radio button "December" is selected, the tableview is set to the getAllTypes observable list for
	 *              December!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void DecRadioBtn(ActionEvent event) throws Exception {

		AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("DECEMBER"));

	}

	/***
	 * FebRadioBtn
	 * The method FebRadioBtn sets the table view with the observable list from the getAllTypes for the report for the month of
	 * February!
	 * @param event When radio button "February" is selected, the tableview is set to the getAllTypes observable list for
	 *              February!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void FebRadioBtn(ActionEvent event) throws Exception {

		AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("FEBRUARY"));

	}

	/***
	 * JanRadioBtn
	 * The method JanRadioBtn sets the table view with the observable list from the getAllTypes for the report for the month of
	 * January!
	 * @param event When radio button "January" is selected, the tableview is set to the getAllTypes observable list for
	 *              January!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void JanRadioBtn(ActionEvent event) throws Exception {

		AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("JANUARY"));

	}

	/***
	 * JulyRadioBtn
	 * The method JulyRadioBtn sets the table view with the observable list from the getAllTypes for the report for the month of
	 * July!
	 * @param event When radio button "July" is selected, the tableview is set to the getAllTypes observable list for
	 *              July!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void JulyRadioBtn(ActionEvent event) throws Exception {

		AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("JULY"));

	}

	/***
	 * JuneRadioBtn
	 * The method JuneRadioBtn sets the table view with the observable list from the getAllTypes for the report for the month of
	 * June!
	 * @param event When radio button "June" is selected, the tableview is set to the getAllTypes observable list for
	 *              June!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void JuneRadioBtn(ActionEvent event) throws Exception {

		AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("JUNE"));

	}

	/***
	 * MarRadioBtn
	 * The method MarRadioBtn sets the table view with the observable list from the getAllTypes for the report for the month of
	 * March!
	 * @param event When radio button "March" is selected, the tableview is set to the getAllTypes observable list for
	 *              March!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void MarRadioBtn(ActionEvent event) throws Exception {

		AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("MARCH"));

	}

	/***
	 * MayRadioBtn
	 * The method MayRadioBtn sets the table view with the observable list from the getAllTypes for the report for the month of
	 * May!
	 * @param event When radio button "May" is selected, the tableview is set to the getAllTypes observable list for
	 *              May!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void MayRadioBtn(ActionEvent event) throws Exception {

		AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("MAY"));

	}

	/***
	 * NovRadioBtn
	 * The method NovRadioBtn sets the table view with the observable list from the getAllTypes for the report for the month of
	 * November!
	 * @param event When radio button "November" is selected, the tableview is set to the getAllTypes observable list for
	 *              November!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void NovRadioBtn(ActionEvent event) throws Exception {

		AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("NOVEMBER"));

	}

	/***
	 * OctRadioBtn
	 * The method OctRadioBtn sets the table view with the observable list from the getAllTypes for the report for the month of
	 * October!
	 * @param event When radio button "October" is selected, the tableview is set to the getAllTypes observable list for
	 *              October!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void OctRadioBtn(ActionEvent event) throws Exception {

		AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("OCTOBER"));

	}

	/***
	 * SepRadioBtn
	 * The method SepRadioBtn sets the table view with the observable list from the getAllTypes for the report for the month of
	 * September!
	 * @param event When radio button "September" is selected, the tableview is set to the getAllTypes observable list for
	 *              September!
	 * @throws Exception Exception is needed in case of failure!
	 */
	@FXML
	void SepRadioBtn(ActionEvent event) throws Exception {

		AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("SEPTEMBER"));

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
	 * The method initialize the Appointment Types Scene! It sets all the labels and radio buttons!
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		AppointmentTypesReportLabel.setText(rb.getString("lb33"));
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
		AppointmentTypesReportCloseBtn.setText(rb.getString("lb4"));
		TypeClm.setText(rb.getString("lb16"));
		CountClm.setText(rb.getString("lb46"));

		try {

			AppointmentTypesReportTV.setItems(TypeCRUD.getAllTypes("MAY"));
			TypeClm.setCellValueFactory(new PropertyValueFactory<>("type"));
			CountClm.setCellValueFactory(new PropertyValueFactory<>("count"));

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}
