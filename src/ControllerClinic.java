import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ControllerClinic implements IController {

  @Override
  public void run(Scanner scan, Connection conn, String username) {
   //we assign the ids for the clinic since they are auto generated
    ClinicStaff clinic = new ClinicStaff(1, username, conn);
    while (true) {
      System.out.println("Option menu:");
      System.out.println("Press 1 to view all clinic information");
      System.out.println("Press 2 to add new available appointment");
      System.out.println("Press 3 to update patient information");
      System.out.println("Press 4 to Log Out");

      int choice = scan.nextInt();

      switch (choice) {
        case 1:
          try {
            clinic.getClinicInformation();
          } catch (Exception e) {
            System.out.println("Clinic information is not available!");
            continue;
          }

          break;
        case 2:

          Timestamp timestamp;
          while(true) {
            System.out.println("Please enter the appointment date and time in a time stamp format");
            System.out.println("Here is an example: 2021-04-20 23:50:36.717");
            String time = scan.next();
            try {
              SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
              Date parsedDate = dateFormat.parse(time);
              timestamp = new java.sql.Timestamp(parsedDate.getTime());
              break;
            } catch(Exception e) {
              System.out.println("date entered in incorrect fromat!");
            }
          }
          System.out.println("Please enter your employee number ID");
          int id = scan.nextInt();

          try {
            clinic.addAppointmentAvailability(timestamp,id);
            System.out.println("Appointment successfully added to the system");
            continue;
          } catch (Exception e) {
            System.out.println("Couldn't add appointment please review the time and"
                + " stuff ID entered!");
            continue;
          }

        case 3:
          System.out.println("Please enter the social security number of the patient");
          String ssn = scan.next();
          System.out.println("Please a description of the patient's medical history");
          String history = scan.next();


          try {
            clinic.updatePatientInformation(ssn,history);
            System.out.println("Patient information successfully update");
            continue;
          } catch (Exception e) {
            System.out.println("Couldn't update patient information please review "
                + "social security number and medical history entered");
            continue;
          }

        case 4:
          try {
            clinic.logOut(clinic.getCurrentSession(username));
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






