package Class;

import java.sql.Timestamp;

public class InOfficeAppointment extends Appointment{
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
     * @param AppointmentMethod A parameter of AppointmentMethod is passed in for the therapist id!
     */

    private String appointmentMethod;

    public InOfficeAppointment(int appointment_Id, String title, String description, String appointmentMethod, String type, Timestamp start, Timestamp end, Timestamp create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int patient_Id, int user_Id, int therapist_Id) {
        super(appointment_Id, title, description, appointmentMethod, type, start, end, create_Date, created_By, last_Update, last_Updated_By, patient_Id, user_Id, therapist_Id);

        this.appointmentMethod = appointmentMethod;

    }

    @Override
    public String getAppointmentMethod() {

        return appointmentMethod;

    }

    @Override
    public void setAppointmentMethod(String appointmentMethod) {

        appointmentMethod = appointmentMethod;

    }

}
