package Class;

import java.sql.Timestamp;

/***
 * This is the appointment class! This class holds each appointment's data such as appointment id, title, description,
 * location, type, start timestamp, end timestamp, create data timestamp etc.
 */
public abstract class Appointment {

    private int appointment_Id;
    private String title;
    private String description;
    private String appointmentMethod;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private Timestamp create_Date;
    private String created_By;
    private Timestamp last_Update;
    private String last_Updated_By;
    private int patient_Id;
    private int user_Id;
    private int therapist_Id;

    public String getAppointmentMethod() {

        return appointmentMethod;

    }

    public void setAppointmentMethod(String appointmentMethod) {

        this.appointmentMethod = appointmentMethod;

    }

    /***
     * Appointment
     * The Appointment() is the constructor for the Appointment class!
     * @param appointment_Id A parameter of appointment_Id is passed in for the appointment id!
     * @param title A parameter of title is passed in for the title!
     * @param description A parameter of description is passed in for the description!
     * @param appointmentMethod A parameter of location is passed in for the location!
     * @param type A parameter of type is passed in for the type!
     * @param start A parameter of start is passed in for the start timestamp!
     * @param end A parameter of end is passed in for the end timestamp!
     * @param create_Date A parameter of create_Date is passed in for the create timestamp!
     * @param created_By A parameter of created_By is passed in for the user name!
     * @param last_Update A parameter of last_Update is passed in for the last_Update timestamp!
     * @param last_Updated_By A parameter of last_Updated_By is passed in for the user name!
     * @param patient_Id A parameter of customer_Id is passed in for the customer id!
     * @param user_Id A parameter of user_Id is passed in for the user id!
     * @param therapist_Id A parameter of therapist_Id is passed in for the therapist id!
     */
    public Appointment(int appointment_Id, String title, String description, String appointmentMethod, String type, Timestamp start, Timestamp end, Timestamp create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int patient_Id, int user_Id, int therapist_Id) {
        this.appointment_Id = appointment_Id;
        this.title = title;
        this.description = description;
        this.appointmentMethod = appointmentMethod;
        this.type = type;
        this.start = start;
        this.end = end;
        this.create_Date = create_Date;
        this.created_By = created_By;
        this.last_Update = last_Update;
        this.last_Updated_By = last_Updated_By;
        this.patient_Id = patient_Id;
        this.user_Id = user_Id;
        this.therapist_Id = therapist_Id;

    }

    /***
     * getAppointment_Id
     * The getAppointment_Id method is a getter for the appointment id!
     * @return This method returns an integer for the appointment id!
     */
    public int getAppointment_Id() {
        return appointment_Id;
    }

    /***
     * setAppointment_Id
     * The setAppointment_Id method is a setter for the appointment id!
     * @param appointment_Id A parameter appointment_Id is passed in for the appointment id!
     */
    public void setAppointment_Id(int appointment_Id) {
        this.appointment_Id = appointment_Id;
    }

    /***
     * getTitle
     * The getTitle method is a getter for the appointment title!
     * @return This method returns a String for the appointment title!
     */
    public String getTitle() {
        return title;
    }

    /***
     * setTitle
     * The setTitle method is a setter for the appointment title!
     * @param title A parameter title is passed in for the appointment title!
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /***
     * getDescription
     * The getDescription method is a getter for the appointment description!
     * @return This method returns a String for the appointment description!
     */
    public String getDescription() {
        return description;
    }

    /***
     * setDescription
     * The setDescription method is a setter for the appointment description!
     * @param description A parameter description is passed in for the appointment description!
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /***
     * getType
     * The getType method is a getter for the appointment type!
     * @return This method returns a String for the appointment type!
     */
    public String getType() {
        return type;
    }

    /***
     * setType
     * The setType method is a setter for the appointment type!
     * @param type A parameter type is passed in for the appointment type!
     */
    public void setType(String type) {
        this.type = type;
    }

    /***
     * getStart
     * The getStart method is a getter for the appointment start Timestamp!
     * @return This method returns a Timestamp for the appointment start time!
     */
    public Timestamp getStart() {
        return start;
    }

    /***
     * start
     * The start method is a setter for the appointment start timestamp!
     * @param start A parameter start is passed in for the appointment start timestamp!
     */
    public void start(Timestamp start) {
        this.start = start;
    }

    /***
     * getEnd
     * The getEnd method is a getter for the appointment end Timestamp!
     * @return This method returns a Timestamp for the appointment end time!
     */
    public Timestamp getEnd() {
        return end;
    }

    /***
     * setEnd
     * The setEnd method is a setter for the appointment End timestamp!
     * @param end A parameter end is passed in for the appointment end timestamp!
     */
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /***
     * getCreated_Date
     * The getCreated_Date method is a getter for the appointment create date Timestamp!
     * @return This method returns a Timestamp for the appointment create date time!
     */
    public Timestamp getCreated_Date() {
        return create_Date;
    }

    /***
     * create_Date
     * The create_Date method is a setter for the appointment create date timestamp!
     * @param create_Date A parameter create_Date is passed in for the appointment create date timestamp!
     */
    public void create_Date(Timestamp create_Date) {
        this.create_Date = create_Date;
    }

    /***
     * getCreated_By
     * The getCreated_By method is a getter for the user name!
     * @return This method returns a String for the user name!
     */
    public String getCreated_By() {
        return created_By;
    }

    /***
     * setCreated_By
     * The setCreated_By method is a setter for the appointment user name!
     * @param setCreated_By A parameter setCreated_By is passed in for the appointment user name!
     */
    public void setCreated_By(String setCreated_By) {
        this.created_By = created_By;
    }

    /***
     * getLast_Update
     * The getLast_Update method is a getter for the appointment update date Timestamp!
     * @return This method returns a Timestamp for the appointment update date Timestamp!
     */
    public Timestamp getLast_Update() {
        return last_Update;
    }

    /***
     * setLast_Update
     * The setLast_Update method is a setter for the appointment update timestamp!
     * @param last_Update A parameter last_Update is passed in for the appointment update timestamp!
     */
    public void setLast_Update(Timestamp last_Update) {
        this.last_Update = last_Update;
    }

    /***
     * getLast_Updated_By
     * The getLast_Updated_By method is a getter for the appointment user name!
     * @return This method returns a String for the appointment user name!
     */
    public String getLast_Updated_By() {
        return last_Updated_By;
    }

    /***
     * setLast_Updated_By
     * The setLast_Updated_By method is a setter for the appointment user name!
     * @param last_Updated_By A parameter last_Updated_By is passed in for the appointment user name!
     */
    public void setLast_Updated_By(String last_Updated_By) {
        this.last_Updated_By = last_Updated_By;
    }

    /***
     * getPatient_Id
     * The getPatient_Id method is a getter for the appointment customer id!
     * @return This method returns a String for the appointment customer id!
     */
    public int getPatient_Id() {
        return patient_Id;
    }

    /***
     * setPatient_Id
     * The setPatient_Id method is a setter for the appointment customer id!
     * @param patient_Id A parameter customer_Id is passed in for the appointment customer id!
     */
    public void setPatient_Id(int patient_Id) {
        this.patient_Id = patient_Id;
    }

    /***
     * getUser_Id
     * The getUser_Id method is a getter for the appointment user id!
     * @return This method returns a Integer for the appointment user id!
     */
    public int getUser_Id() {
        return user_Id;
    }

    /***
     * setUser_Id
     * The setUser_Id method is a setter for the appointment user id!
     * @param user_Id A parameter user_Id is passed in for the appointment user id!
     */
    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    /***
     * getTherapist_Id
     * The getTherapist_Id method is a getter for the appointment therapist id!
     * @return This method returns a Integer for the appointment therapist id!
     */
    public int getTherapist_Id() {
        return therapist_Id;
    }

    /***
     * setTherapist_Id
     * The setTherapist_Id method is a setter for the appointment therapist id!
     * @param therapist_Id A parameter therapist_Id is passed in for the appointment therapist id!
     */
    public void setTherapist_Id(int therapist_Id) {
        this.therapist_Id = therapist_Id;
    }

}
