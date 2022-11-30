package Class;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/***
 * This is the TimestampData class! This class takes care of all the time conversion, localdatetime convertion etc.
 */
public class TimestampData {

    /***
     * getStartDateTime
     * The getStartDateTime gets the start time from the add or modify appointment form and format it to LocalDateTime!
     * It then converts it to UTC for the database!
     * @param appointmentStartTimeToString A parameter of appointmentStartTimeToString is passed in!
     * @return This method returns a Timestamp!
     */
    public static Timestamp getStartDateTime(String appointmentStartTimeToString){

        ZoneId zid = ZoneId.systemDefault();

        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        LocalDateTime ldtStart = LocalDateTime.parse(appointmentStartTimeToString, dFormatter);

        ZonedDateTime zdtStart = ldtStart.atZone(zid);
        ZonedDateTime utcStart = zdtStart.withZoneSameInstant(ZoneId.of("UTC"));
        ldtStart = utcStart.toLocalDateTime();

        Timestamp endSqlts = Timestamp.valueOf(ldtStart);

        return endSqlts;

    }


    /***
     * getEndDateTime
     * The getEndDateTime gets the end time from the add or modify appointment form and format it to LocalDateTime!
     * It then converts it to UTC for the database!
     * @param appointmentEndTimeToString A parameter of appointmentEndTimeToString is passed in!
     * @return This method returns a Timestamp!
     */
    public static Timestamp getEndDateTime(String appointmentEndTimeToString){

        ZoneId zid = ZoneId.systemDefault();

        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        LocalDateTime ldtEnd = LocalDateTime.parse(appointmentEndTimeToString, dFormatter);

        ZonedDateTime zdtEnd = ldtEnd.atZone(zid);
        ZonedDateTime utcEnd = zdtEnd.withZoneSameInstant(ZoneId.of("UTC"));
        ldtEnd = utcEnd.toLocalDateTime();

        Timestamp endSqlts = Timestamp.valueOf(ldtEnd);

        return endSqlts;

    }

    /***
     * getCreateDateTime
     * The getCreateDateTime gets the current time format it to LocalDateTime!
     * It then converts it to UTC for the database!
     * @return This method returns a Timestamp!
     */
    public static Timestamp getCreateDateTime(){

        ZoneId zid = ZoneId.systemDefault();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        LocalDateTime now = LocalDateTime.now();
        String createDate = now.toString().replace("T", " ").substring(0, 21);
        LocalDateTime nowFormat = LocalDateTime.parse(createDate, dtf);

        ZonedDateTime zdtNow = nowFormat.atZone(zid);
        ZonedDateTime utcNow = zdtNow.withZoneSameInstant(ZoneId.of("UTC"));
        nowFormat = utcNow.toLocalDateTime();
        Timestamp nowsqlts = Timestamp.valueOf(nowFormat);

        return nowsqlts;

    }

    /***
     * getUpdateDateTime
     * The getUpdateDateTime gets the current time format it to LocalDateTime!
     * It then converts it to UTC for the database!
     * @return This method returns a Timestamp!
     */
    public static Timestamp getUpdateDateTime(){

        ZoneId zid = ZoneId.systemDefault();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        LocalDateTime now = LocalDateTime.now();
        String updateDate = now.toString().replace("T", " ").substring(0, 21);
        LocalDateTime nowFormat = LocalDateTime.parse(updateDate, dtf);

        ZonedDateTime zdtNow = nowFormat.atZone(zid);
        ZonedDateTime utcNow = zdtNow.withZoneSameInstant(ZoneId.of("UTC"));
        nowFormat = utcNow.toLocalDateTime();
        Timestamp updateSqlts = Timestamp.valueOf(nowFormat);

        return updateSqlts;

    }

    /***
     * getMilitaryTimeConversion
     * The getMilitaryTimeConversion gets hour and period from the add of modify appointment form! It then converts it
     * to military time!
     * @param hour A parameter of hour is passed in!
     * @param period A parameter of period is passed in!
     * @return This method returns a String!
     */
    public static String getMilitaryTimeConversion(String hour, String period){

        if(period == "PM" && hour != "12"){

            hour = Integer.toString(Integer.parseInt(hour) + 12);
            return hour;

        }else if(period == "AM" && hour != "12"){

            return hour;

        }else if(period == "AM" && hour == "12") {

            return "00";

        }else if(period == "PM" && hour == "12") {

            return hour;

        }

        return null;

    }

    /***
     * getHour
     * The getHour gets the hour from the timestamp from the database and then converts it to regular time(hour)!
     * @param hour A parameter of hour is passed in!
     * @return This method returns a String!
     */
    public static String getHour(Timestamp hour){

        String hourString = hour.toString().substring(11, 13);

        if(Integer.parseInt(hourString) > 12){

            hourString = String.valueOf(Integer.parseInt(hourString) - 12);

            return hourString;

        }else{

            return hourString;

        }

    }

    /***
     * getPeriod
     * The getPeriod gets the hour from the timestamp from the database determines if the period should be "AM" or "PM"!
     * @param hour A parameter of hour is passed in!
     * @return This method returns a String!
     */
    public static String getPeriod(Timestamp hour){

        String hourString = hour.toString().substring(11, 13);
        String period;

        if(Integer.parseInt(hourString) < 12){

            period = "AM";

            return period;

        }else{

            period = "PM";

            return period;

        }

    }

    /***
     * getEstTime
     * The getEstTime convert the passed in time to EST time zone!
     * @param time A parameter of time is passed in!
     * @return This method returns a String!
     */
    public static Timestamp getEstTime(String time){

        ZoneId oldZone = ZoneId.systemDefault();

        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        LocalDateTime oldDateTime = LocalDateTime.parse(time, dFormatter);

        ZoneId newZone = ZoneId.of("America/New_York");
        LocalDateTime newDateTime = oldDateTime.atZone(oldZone).withZoneSameInstant(newZone).toLocalDateTime();

        Timestamp estTime = Timestamp.valueOf(newDateTime);

        return estTime;

    }

}
