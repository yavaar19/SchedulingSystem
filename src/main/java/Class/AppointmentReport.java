package Class;

import java.sql.Timestamp;

public class AppointmentReport {

	private int appointment_Id;
	private int user_Id;
	private int patient_Id;
	private String title;
	private String description;
	private String appointmentMethod;
	private String type;
	private String therapist;
	private Timestamp start;
	private Timestamp end;

	public AppointmentReport(int appointment_Id, int user_Id, int patient_Id, String title, String description, String appointmentMethod, String type, String therapist, Timestamp start, Timestamp end) {

		this.appointment_Id = appointment_Id;
		this.user_Id = user_Id;
		this.patient_Id = patient_Id;
		this.title = title;
		this.description = description;
		this.appointmentMethod = appointmentMethod;
		this.type = type;
		this.therapist = therapist;
		this.start = start;
		this.end = end;

	}

	public int getAppointment_Id() {
		return appointment_Id;
	}

	public void setAppointment_Id(int appointment_Id) {
		this.appointment_Id = appointment_Id;
	}

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

	public int getPatient_Id() {
		return patient_Id;
	}

	public void setPatient_Id(int patient_Id) {
		this.patient_Id = patient_Id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAppointmentMethod() {
		return appointmentMethod;
	}

	public void setAppointmentMethod(String type) {
		this.appointmentMethod = appointmentMethod;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTherapist() {
		return therapist;
	}

	public void setTherapist(String therapist) {
		this.therapist = therapist;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}
}
