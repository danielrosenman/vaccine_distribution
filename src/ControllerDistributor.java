import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ControllerDistributor implements IController {

  @Override
  public void run(Scanner scan, Connection conn, String username) {

    Distributor dist = new Distributor(username, conn);
    while (true) {
      System.out.println("Option menu:");
      System.out.println("Press 1 to update stock information");
      System.out.println("Press 2 to Log Out");

      int choice = scan.nextInt();

      switch (choice) {
        case 1:
          System.out.println("Please enter manufacturer name");
          String man = scan.next();
          System.out.println("Please enter added amount of doses");
          int qua = scan.nextInt();
          try {
            dist.updateStock(man, qua);
            System.out.println("Stock was successfuly updated");

          } catch (Exception e) {
            System.out.println("Entered invalid manufacturer name or amount");
            continue;
          }
          break;
        case 2:
          try {
            dist.logOut(dist.getCurrentSession(username));
            System.out.println("Successfuly logged out!");
            System.exit(0);

          } catch (Exception e) {
            System.out.println("Failed to log out!");
            continue;
          }
        default:

          System.out.println("Invalid input!");

      }

    }

  }

}
