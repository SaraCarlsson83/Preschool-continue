
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lisa Ramel
 * Date: 2020-12-04
 * Time: 14:03
 * Project: Preeschool
 * Copywrite: MIT
 */
public class Main {

    private final Database d = new Database();

    private AttendanceDAO attendanceDAO = d;
    private DatabaseDAO databaseDAO = d;
    private PersonDAO personDAO = d;

    private Scanner scan = new Scanner(System.in);

    private States state;

/*
1. Välj Vårdnadshavare eller Pedagog ex (1,2)

Vårdnadshavare
1. Välj barn
1. Registerara frånvaro
2. Anmäla omsorgstider
3. Visa pedagogers uppgifter

Pedagog
1. Lägga till frånvaro
2. Registrera nytt barn

 */

    public Main() {
        state = States.LOGIN;
        state.output(null);
        int input = scan.nextInt();


        while (true) {
            if (input == 1) {
                caregiverView(input);
                state = States.LOGIN;
                state.output(null);
                input = scan.nextInt();
                //break;
            } else if (input == 2) {
                educatorView(input);
                state = States.LOGIN;
                state.output(null);
                input = scan.nextInt();
                //break;
            } else if (input == 3) {
                state = States.SHUT_DOWN;
                state.output(null);
                saveAllFiles();
                break;
            } else {
                System.out.println("Ogiltigt kommando, var god försök igen.");
                input = scan.nextInt();
            }
        }
    }

    public Caregiver lookupCaregiver(String name) {
        Caregiver caregiver = personDAO.getCaregiver(name);
        while (caregiver == null) {
            System.out.println("Var god försök igen: ");
            name = scan.next();
            caregiver = personDAO.getCaregiver(name);
        }
        return caregiver;
    }

    public void caregiverView(int input) {
        String name;
        //Om användaren valde att logga in som vårdnadshavare (1)

        state = States.USERNAME;
        state.output(null);
        name = scan.next();
        Caregiver caregiver = lookupCaregiver(name);
        while (true) {
            Child child;
            child = caregiver.getChildren().get(0);

            if (caregiver.getChildren().size() > 1) {
                state = States.CAREGIVER;
                state.output(caregiver);
                // väljer barn
                input = scan.nextInt();
                //Om användaren valde ett barn (1)
                if (input <= caregiver.getChildren().size()) {
                    child = caregiver.getChildren().get(input - 1);
                }
            }

            state = States.CHOSE_CHILD;
            state.output(child);

            input = scan.nextInt();

            //Om användaren valde omsorgstider (1)
            if (input == 1) {
                state = States.CHILD_ATTENDANCE;
                state.output(child);
                state.addCaringTime(child, scan);
            }

            //Om användaren valde frånvaro (2)
            else if (input == 2) {
                state = States.CHILD_ABSENCE;
                addAbsenseToday(child);
            }

            //Om användaren valde kontaktuppgifter (3)
            else if (input == 3) {
                state = States.EDUCATOR_INFO;
                List<Educator> educatorList = databaseDAO.getEducatorList();
                state.output(educatorList);
            }
            //Om användaren valde att Logga ut (4)
            else if (input == 4) {
                state = States.LOG_OUT;
                state.output(caregiver);
                break;
            }

            else {
                System.out.println("Okänt kommando, var god försök igen.");
            }
        }

    }


    public void educatorView(int input) {

        String name;
        String firstName;

        //Om användaren valde att logga in som pedagog (2)

        state = States.USERNAME;
        state.output(null);

        name = scan.next();
        Educator educator = personDAO.getEducator(name);
        while (educator == null) {
            System.out.println("Var god försök igen: ");
            name = scan.next();
            educator = personDAO.getEducator(name);
        }

        while (true) {
            state = States.EDUCATOR;
            state.output(educator);
            input = scan.nextInt();

            //Om användaren valde att registrera frånvaro för ett barn
            if (input == 1) {
                state = States.EDUCATOR_ABSENCE;
                List<Child> childList = databaseDAO.getChildList();
                state.output(databaseDAO.getChildList());
                input = scan.nextInt();

                //Registrerar frånvaro på barn
                if (input <= childList.size()) {
                    state = States.CHILD_ABSENCE;
                    Child child = childList.get(input - 1);
                    addAbsenseToday(child);
                }


                //Om användaren vill lägga till ett barn
            } else if (input == 2) {

                state = States.REGISTER_CHILD;
                state.output(null);
                firstName = scan.next();
                boolean foundCaregiver = false;

                //Om vårdnadshavaren redan finns i systemet, läggs
                //ett nytt barn läggs till till den redan exsisterande vårdnadshavaren
                for (Caregiver caregiver : databaseDAO.getCaregiverList()) {
                    if (caregiver.getFirstName().equalsIgnoreCase(firstName)) {
                        System.out.println("Det nya barnet kommer att registreras på den redan " +
                                "\nexisterande vårdnadshavaren " + caregiver.getFirstName() + " " + caregiver.getLastName());

                        Child child = state.registerNewChild(scan);
                        databaseDAO.addChild(child);

                        child.addCaregiver(caregiver);
                        caregiver.addChildren(child);

                        attendanceDAO.addChildInAttendance(child);
                        foundCaregiver = true;
                    }
                }

                //Om det är en ny vårdnadshavare så adderas en ny vårdnadshavare
                //och ett nytt barn läggs till och kopplas till den nya vårdnadshavaren
                if (!foundCaregiver) {

                    Caregiver caregiver = state.addCaregiverToNewChild(scan, firstName);
                    databaseDAO.addCaregiver(caregiver);

                    Child child = state.registerNewChild(scan);
                    databaseDAO.addChild(child);

                    child.addCaregiver(caregiver);
                    caregiver.addChildren(child);
                    attendanceDAO.addChildInAttendance(child);

                }

                saveAllFiles();

                //TODO test om barn lagts till:
                System.out.println(d.getChildList().size());
                System.out.println(d.getCaregiverList().size());

            }
            //Om användaren vill skriva ut närvarolistor
            else if (input == 3) {
                List<Attendance> attendanceList = d.deSerialize(SerFiles.ATTENDANCE.serFiles);
                state = States.ATTENDANCE;
                state.output(null);
                input = scan.nextInt();
                if (input == 1) {

                    state = States.PRINT_ALL;
                    state.output(attendanceList);
                } else if (input == 2) {
                    state = States.PRINT_PRESENT;
                    state.output(attendanceList);
                } else if (input == 3) {
                    state = States.PRINT_ABSENT;
                    state.output(attendanceList);
                }
            }
             
            else if (input == 4) {
                state = States.CAREGIVER_INFO;
                state.output(null);
                name = scan.next();
                List<Child> childList = databaseDAO.getChildList();
                for (Child child : childList) {
                    if (name.equalsIgnoreCase(child.getFirstName()) || name.equalsIgnoreCase(child.getLastName())) {
                        state = States.CAREGIVER_INFO_PRINT;
                        state.output(child);
                    }
                }
            }
            //Om användaren valde att Logga ut (5)
            else if (input == 5) {
                state = States.LOG_OUT;
                state.output(educator);
                break;

            }

            else {
                System.out.println("Okänt kommando, var god försök igen.");
            }

        }
    }
    public void addAbsenseToday(Child child){
        state.output(child);
        attendanceDAO.addAbsence(child);
        d.serialize(d.getAttendanceToday(), SerFiles.ATTENDANCE.serFiles);
    }

    public void saveAllFiles(){
        d.addAttendanceTodayInList(d.getAttendanceToday());
        d.serialize(d.getAttendanceList(), SerFiles.LIST_OF_ATTENDANCES.serFiles);
        d.serialize(d.getAttendanceToday(), SerFiles.ATTENDANCE.serFiles);
        d.serialize(d.getChildList(), SerFiles.CHILDREN.serFiles);
        d.serialize(d.getEducatorList(), SerFiles.EDUCATOR.serFiles);
    }

    public static void main(String[] args) {
        new Main();
    }
}



