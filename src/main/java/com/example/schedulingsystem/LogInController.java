package com.example.schedulingsystem;

import Database.JDBC;
import Database.Query;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/***
 * This is the log in controller! This scene contains a text field for the username and a password field for the
 * password! It also contains a log in button!
 */
public class LogInController implements Initializable {

    ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());

    Stage stage;
    Scene scene;
    Parent root;

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

    ResultSetInterface secondResult = sql -> {

        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();

        return rs;

    };

    @FXML
    private PasswordField LogInPasswordTxtField;

    @FXML
    private TextField LogInUsernameTxtField;

    @FXML
    private Label LogInZoneIdLabel;

    @FXML
    private Label LogInLogInLabel;

    @FXML
    private Button LogInCloseBtn;

    @FXML
    private Button LogInLoginBtn;

    /***
     * LogInCloseBtn
     * The LogInCloseBtn method closes any database connection and exist the program!
     * @param event When the "Close" button is clicked, the program exists!
     */
    @FXML
    void LogInCloseBtn(ActionEvent event) {

        JDBC.closeConnection();
        System.exit(0);

    }

    /***
     * LogInLoginBtn
     * The LogInLoginBtn method performs all the username and password validation! A select query is performed to see
     * if a username with the provided password exist! If it does, then the scene is set to the main scene and log in
     * is successful! If not, then an alert will show up! Password is case sensitive! After each attempt, the log in
     * attempt is save in a file called "login_activity.txt"! The method also performs a check to see if the user has
     * any appointments within the next 15 minutes and pops up an alert accordingly!
     * @param event Upon the "Log In" button is clicked, all validation is performed and Log in is either successful or
     *              failed!
     * @throws SQLException SQLException is needed in case of SQL query failure!
     * @throws IOException IOException is needed in case of failure!
     */
    @FXML
    void LogInLoginBtn(ActionEvent event) throws SQLException, IOException {

        String userName = LogInUsernameTxtField.getText();
        String password = LogInPasswordTxtField.getText();

        JDBC.openConnection();

        ResultSet rs = result.result("SELECT * FROM USERS WHERE USER_NAME= '" + userName + "' AND BINARY PASSWORD= '" + password + "'");

        ResultSet secondRs = secondResult.result("SELECT * FROM USERS WHERE USER_NAME= '" + userName + "'");

        if(userName.equals("") || password.equals("")){

            a.alert(rb.getString("lb81"));

        }else if(rs.next()){

            ZoneId zid = ZoneId.systemDefault();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime nowPlusFithteen = LocalDateTime.now().plusMinutes(15);

            String nowString = now.toString().replace("T", " ").substring(0, 21);
            LocalDateTime nowFormat = LocalDateTime.parse(nowString, dtf);

            String nowPlusFithteenString = nowPlusFithteen.toString().replace("T", " ").substring(0, 21);
            LocalDateTime nowPlusFithteenFormat = LocalDateTime.parse(nowPlusFithteenString, dtf);

            ZonedDateTime zdtNow = nowFormat.atZone(zid);
            ZonedDateTime utcNow = zdtNow.withZoneSameInstant(ZoneId.of("UTC"));
            nowFormat = utcNow.toLocalDateTime();
            Timestamp nowsqlts = Timestamp.valueOf(nowFormat);

            ZonedDateTime zdtNowPlusFifteen = nowPlusFithteenFormat.atZone(zid);
            ZonedDateTime utcNowPlusFifteen = zdtNowPlusFifteen.withZoneSameInstant(ZoneId.of("UTC"));
            nowPlusFithteenFormat = utcNowPlusFifteen.toLocalDateTime();
            Timestamp nowsqltsPlusFifteen = Timestamp.valueOf(nowPlusFithteenFormat);

            secondRs.next();

            ResultSet nextAppointmentResult = result.result("SELECT * FROM APPOINTMENTS WHERE START BETWEEN '" + nowsqlts + "' AND '" + nowsqltsPlusFifteen + "' and USER_ID = " + secondRs.getInt("USER_ID"));

            if(nextAppointmentResult.next()){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(rb.getString("lb93"));
                alert.setContentText(rb.getString("lb98") + " " + nextAppointmentResult.getString("Appointment_ID") + " " + rb.getString("lb99") + " " + nextAppointmentResult.getTimestamp("Start") + "!");
                alert.showAndWait();

            }else{

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(rb.getString("lb93"));
                alert.setContentText(rb.getString("lb96"));
                alert.showAndWait();

            }


            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
            root = loader.load();
            stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 913, 621);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle(rb.getString("lb51"));
            stage.show();
            MainSceneController currentUser = loader.getController();
            currentUser.setCurrentUserId(rs.getInt("User_ID"));
            userActivity(rs.getInt("User_ID"), "Login Successful!");

            JDBC.closeConnection();

        }else if(secondRs.next()) {

            userActivity(secondRs.getInt("User_ID"), "Login Attempt Failed!");
            a.alert(rb.getString("lb81"));

        }else{

            a.alert(rb.getString("lb81"));

        }

        JDBC.closeConnection();

    }

    /***
     * initialize
     * The method initialize the Log In Scene! It sets the text field and password field!
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LogInZoneIdLabel.setText(String.valueOf(ZoneId.systemDefault()));
        LogInLogInLabel.setText(rb.getString("lb1"));
        LogInUsernameTxtField.setPromptText(rb.getString("lb2"));
        LogInPasswordTxtField.setPromptText(rb.getString("lb3"));
        LogInLoginBtn.setText(rb.getString("lb91"));
        LogInCloseBtn.setText(rb.getString("lb4"));

    }

    /***
     * userActivity
     * The method userActivity records all user log in attempt and writes it to a file called "login_activity.txt"!
     * If the file does not exist, it will create a new one! However if the file does exist, it will append to it!
     * The method writes to the file the User ID, Whether attempt was successful or failure and a timestamp!
     * @param userId The user ID of the user trying to log in is passed in as a parameter!
     * @param message A string message of whether a successful of failed attempt is passed in as a parameter!
     * @throws IOException IOException is needed in case of failure!
     */
    public void userActivity(int userId, String message) throws IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        LocalDateTime now = LocalDateTime.now();
        String createDate = now.toString().replace("T", " ").substring(0, 21);
        LocalDateTime nowFormat = LocalDateTime.parse(createDate, dtf);
        Timestamp nowsqlts = Timestamp.valueOf(nowFormat);

        File file = new File("login_activity.txt");

        if(!file.exists()){

            File newFile = new File("login_activity.txt");
            FileWriter fw = new FileWriter(newFile);
            PrintWriter pw = new PrintWriter(fw);

            pw.println("User ID: " + userId + " - " + message + " - " + nowsqlts);
            pw.close();

        }else{

            try(FileWriter fw = new FileWriter("login_activity.txt", true);
                PrintWriter pw = new PrintWriter(fw)) {

                pw.println("User ID: " + userId + " - " + message + " - " + nowsqlts);

            }

        }

    }
	
}
