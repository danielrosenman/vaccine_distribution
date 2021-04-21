import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ControllerGov implements IController {

  @Override
  public void run(Scanner scan, Connection conn, String username) {
//we assign the ids for the clinic
   GovOfficial gov = new GovOfficial(username, conn);
    while (true) {
      System.out.println("Option menu:");
      System.out.println("Press 1 to view Vaccine supply information");
      System.out.println("Press 2 to view the vaccine doses a certain citizen has received");
      System.out.println("Press 3 to Log Out");

      int choice = scan.nextInt();

      switch (choice) {
        case 1:
          try {
           gov.viewVaccineSupply();
          } catch (Exception e) {
            System.out.println("Cant view supply!");
            continue;
          }

          break;
        case 2:
          System.out.println("Please enter the social security number of the citizen you are "
              + "seeking vaccine information for");
          String ssn = scan.next();
          try {
            gov.viewCitizenDoses(ssn);
            continue;
          } catch (Exception e) {
            System.out.println("Invalid social security number entered!");
            continue;
          }

          case 3:
          try {
            gov.logOut(gov.getCurrentSession(username));
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
