package Class;

import java.sql.Timestamp;

public class TherapistSchedule {

	private int appointmentId;
	private int patientId;
	private String title;
	private String description;
	private String type;
	private Timestamp start;
	private Timestamp end;

	public TherapistSchedule(int appointmentId, String title, String type, String description, Timestamp start, Timestamp end, int patientId) {

		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.title = title;
		this.description = description;
		this.type = type;
		this.start = start;
		this.end = end;

	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
