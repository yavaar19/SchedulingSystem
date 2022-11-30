package Class;

import java.sql.Timestamp;

/***
 * This is the customer class! This class holds each customer's data such as customer id, customer name, address,
 * postal code, phone, create data timestamp etc.
 */
public class Patient {

    private int customer_Id;
    private String customer_Name;
    private String address;
    private String postal_Code;
    private String phone;
    private Timestamp create_Date;
    private String created_By;
    private Timestamp last_Update;
    private String last_Updated_By;
    private int division_Id;

    /***
     * Customer
     * The Customer() is the constructor for the Customer's class!
     * @param customer_Id A parameter of appointment_Id is passed in for the appointment id!
     * @param customer_Name A parameter of customer_Name is passed in for the customer name!
     * @param address A parameter of address is passed in for the customer address!
     * @param postal_Code A parameter of postal_Code is passed in for the customer postal code!
     * @param phone A parameter of phone is passed in for the customer phone!
     * @param create_Date A parameter of create_Date is passed in for the create date timestamp!
     * @param created_By A parameter of created_By is passed in for the user name!
     * @param last_Update A parameter of last_Update is passed in for the last update timestamp!
     * @param last_Updated_By A parameter of last_Updated_By is passed in for the user name!
     * @param division_Id A parameter of division_Id is passed in for the division id!
     */
    public Patient(int customer_Id, String customer_Name, String address, String postal_Code, String phone, Timestamp create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int division_Id) {
        this.customer_Id = customer_Id;
        this.customer_Name = customer_Name;
        this.address = address;
        this.postal_Code = postal_Code;
        this.phone = phone;
        this.create_Date = create_Date;
        this.created_By = created_By;
        this.last_Update = last_Update;
        this.last_Updated_By = last_Updated_By;
        this.division_Id = division_Id;
    }

    /***
     * getPatient_Id
     * The getPatient_Id method is a getter for the customer id!
     * @return This method returns an integer for the customer id!
     */
    public int getPatient_Id() {
        return customer_Id;
    }

    /***
     * setPatient_Id
     * The setPatient_Id method is a setter for the customer id!
     * @param setCustomer_Id A parameter setPatient_Id is passed in for the customer id!
     */
    public void setCustomer_Id(int setCustomer_Id) {
        this.customer_Id = customer_Id;
    }

    /***
     * getCustomer_Name
     * The getCustomer_Name method is a getter for the customer name!
     * @return This method returns a String for the customer name!
     */
    public String getPatient_Name() {
        return customer_Name;
    }

    /***
     * setCustomer_Name
     * The setCustomer_Name method is a setter for the customer name!
     * @param customer_Name A parameter customer_Name is passed in for the customer name!
     */
    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    /***
     * getAddress
     * The getAddress method is a getter for the customer address!
     * @return This method returns a String for the customer address!
     */
    public String getAddress() {
        return address;
    }

    /***
     * setAddress
     * The setAddress method is a setter for the customer address!
     * @param address A parameter address is passed in for the customer address!
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /***
     * getPostal_Code
     * The getPostal_Code method is a getter for the customer postal code!
     * @return This method returns a String for the customer postal code!
     */
    public String getPostal_Code() {
        return postal_Code;
    }

    /***
     * setPostal_Code
     * The setPostal_Code method is a setter for the customer postal code!
     * @param postal_Code A parameter postal_Code is passed in for the postal code!
     */
    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    /***
     * getPhone
     * The getPhone method is a getter for the customer phone!
     * @return This method returns an String for the customer phone!
     */
    public String getPhone() {
        return phone;
    }

    /***
     * setPhone
     * The setPhone method is a setter for the customer phone!
     * @param phone A parameter phone is passed in for the phone!
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /***
     * getCreate_Date
     * The getCreate_Date method is a getter for the create date timestamp!
     * @return This method returns a Timestamp for the create date timestamp!
     */
    public Timestamp getCreate_Date() {
        return create_Date;
    }

    /***
     * setCreate_Date
     * The setCreate_Date method is a setter for the creation timestamp!
     * @param setCreate_Date A parameter setCreate_Date is passed in for the creation timestamp!
     */
    public void setCreate_Date(Timestamp setCreate_Date) {
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
     * The setCreated_By method is a setter for the user name!
     * @param created_By A parameter created_By is passed in for the user name!
     */
    public void setCreated_By(String created_By) {
        this.created_By = created_By;
    }

    /***
     * getLast_Update
     * The getLast_Update method is a getter for the update timestamp!
     * @return This method returns a Timestamp for the update timestamp!
     */
    public Timestamp getLast_Update() {
        return last_Update;
    }

    /***
     * setLast_Update
     * The setLast_Update method is a setter for the update timestamp!
     * @param setLast_Update A parameter setLast_Update is passed in for the update timestamp!
     */
    public void setLast_Update(Timestamp setLast_Update) {
        this.last_Update = last_Update;
    }

    /***
     * getLast_Updated_By
     * The getLast_Updated_By method is a getter for the user name!
     * @return This method returns a String for the user name!
     */
    public String getLast_Updated_By() {
        return last_Updated_By;
    }

    /***
     * setLast_Updated_By
     * The setLast_Updated_By method is a setter for the user name!
     * @param last_Updated_By A parameter last_Updated_By is passed in for the user name!
     */
    public void setLast_Updated_By(String last_Updated_By) {
        this.last_Updated_By = last_Updated_By;
    }

    /***
     * getDivision_Id
     * The getDivision_Id method is a getter for the customer division id!
     * @return This method returns an integer for the customer division id!
     */
    public int getDivision_Id() {
        return division_Id;
    }

    /***
     * setDivision_Id
     * The setDivision_Id method is a setter for the division id!
     * @param division_Id A parameter division_Id is passed in for the division id!
     */
    public void setDivision_Id(int division_Id) {
        this.division_Id = division_Id;
    }
}
