import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

public class ClinicStaff extends AUser {

  int clinicID;

  ClinicStaff(int id){
    this.clinicID = id;
  }


  public void getClinicInformation() throws SQLException {
    CallableStatement addStmt = connect.prepareCall("{CALL view_clinic_information(?)}");
    addStmt.setInt(1, this.clinicID);
    ResultSet result = addStmt.executeQuery();
    System.out.println("Appointment added successfuly");

    while (result.next()) {
      System.out.println("Clinic number: " + result.getInt(1) +
          ", " + "Clinic Name: " + result.getString(2) + "Street:" + ", "
          + result.getString(3) + ", "
          + "City:" + result.getString(4) + ", "
          + "Zip Code:" + result.getString(5));
    }

  }

  public void addAppointmentAvailability(Timestamp stamp, int staffID) throws SQLException {
    CallableStatement addStmt = connect.prepareCall("{CALL add_appt_availability(?, ?, ?)}");
    addStmt.registerOutParameter(1, Types.VARCHAR, this.clinicID);
    addStmt.setTimestamp(2, stamp);
    addStmt.setInt(3, staffID);
    addStmt.execute();
    System.out.println("Appointment added successfuly");
  }

  public void updatePatientInformation(String ssn){

  }

}
