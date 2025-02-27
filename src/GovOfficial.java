import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GovOfficial extends AUser{


  GovOfficial(String userName, Connection connect){
    super(userName, connect);
  }


public void viewVaccineSupply() throws SQLException {
  CallableStatement addStmt = connect.prepareCall("{CALL view_vaccine_supply ()}");
  ResultSet result = addStmt.executeQuery();

  while (result.next()) {
    System.out.println("Vial Number: " + result.getInt(1) +
        ", " + "Manufacturer Name: " + result.getString(2) +
        "Interval Between Doses In Days :" + ", "
        + result.getInt(3) + ", "
        + "Number of Injections: " + result.getInt(4));
  }

}


  public void viewCitizenDoses(String ssn) throws SQLException {
    CallableStatement addStmt = connect.prepareCall("{CALL view_citizen_doses( ?)}");
    addStmt.setString(1, ssn);
    ResultSet result = addStmt.executeQuery();

    while (result.next()) {
      System.out.println("Social Security Number: " + result.getString(1) +
          ", " + "Citizen Name: " + result.getString(2) + "Date Of Birth:" + ", "
          + result.getDate(3) + ", "
          + "Gender: " + result.getString(4)
          + "Health History: " + result.getString(5)
          + ", " + "Inoculation Number: " + result.getInt(6));
    }

  }

}
