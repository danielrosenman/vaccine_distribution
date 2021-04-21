import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Distributor extends AUser {

  Distributor(String userName, Connection connect){
    super(userName, connect);
  }

  public void updateStock(String manName,int quantity) throws SQLException {
    CallableStatement addStmt = connect.prepareCall("{CALL update_stock(?, ?)}");
    addStmt.setString(1, manName);
    addStmt.setInt(2, quantity);
    addStmt.execute();
    System.out.println("Vaccine stock information successfully updated");
  }

}
