import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lisa Ramel
 * Date: 2020-12-04
 * Time: 16:27
 * Project: Preeschool
 * Copywrite: MIT
 */
public enum States {

    LOGIN {
        @Override
        public void output(Object o) {
            System.out.println("Välkommen till förskolan!" + "\nLOGGA IN SOM"
                    + "\n 1. Vårdnadshavare" + "\n 2. Pedagog");
        }
    },

    USERNAME {
        @Override
        public void output(Object o) {
            System.out.println("Skriv ditt namn: ");

        }
    },

    CAREGIVER {
        @Override
        public void output(Object o) {
            Caregiver caregiver = (Caregiver) o;
            System.out.println("Välj barn:");
            int counter = 1;
            for (Child child : caregiver.getChildren()) {
                System.out.println(counter + " " + child.getFirstName());
                counter++;

            }
        }
    },

    CHOSE_CHILD {
        @Override
        public void output(Object o) {
            Child child = (Child)o;
            System.out.println("Välkommen till sidan för " + child.getFirstName() +
                    "\n 1. Ange omsorgstider" +
                    "\n 2. Registrera frånvaro" +
                    "\n 3. Visa pedagogernas uppgifter");
        }
    },

    CHILD_ABSENCE {
        @Override
        public void output(Object o) {
            Child child = (Child)o;
            System.out.println("Registrerat frånvaro för " + child.getFirstName() + " " + LocalDate.now());

        }
    },

    CHILD_ATTENDANCE {
        @Override
        public void output(Object o) {
            Child child = (Child)o;
            System.out.println("Var god ange omsorgstider för " + child.getFirstName() );
        }
    },

    EDUCATOR_INFO {
        @Override
        public void output(Object o) {
            List<Educator> educatorList = (List<Educator>) o;
            System.out.println("Kontaktuppgifter till pedagogerna:");
            for (Educator educator : educatorList){
                System.out.println(educator.getFirstName() + " " + educator.getLastName()+
                        "\n" + educator.getEmailAddress()+
                        "\n" + educator.getPhoneNumber());
            }
        }
    },

    EDUCATOR {
        @Override
        public void output(Object o) {
            Educator educator = (Educator)o;
            System.out.println("\nVälkommen " + educator.getFirstName() + "!" +
                    "\n 1. Ange frånvaro" +
                    "\n 2. Registrera ett nytt barn till förskolan" +
                    "\n 3. Se närvaro idag" +
                    "\n 4. Se vårdnadshavares kontaktuppgifter" +
                    "\n 5. Avsluta");
        }
    },

    EDUCATOR_ABSENCE {
        @Override
        public void output(Object o) {
            List<Child> childList = (List<Child>) o;
            System.out.println("Ange frånvaro för: ");
            int counter = 1;
            for(Child child : childList) {
                System.out.println(counter + " " + child.getFirstName());
                counter ++;
            }
        }
    },

    REGISTER_CHILD {
        @Override
        public void output(Object o) {
            System.out.println("Registrera nytt barn");
        }
    },

    PRINT_ATTENDANCE{
        @Override
        public void output(Object o) {
            System.out.println("Vilken lista vill du skriva ut?");
            System.out.println(" 1. Alla barn" +
                    "\n 2. Närvarande barn" +
                    "\n 3. Frånvarande barn");
        }
    },

    PRINT_ALL{
        @Override
        public void output(Object o) {
            List<Attendance> attendanceList = (List<Attendance>) o;
            String present;
            System.out.println("Närvaro " + LocalDate.now());
            for(Attendance a: attendanceList){
                if(!a.getPresent())
                    present = "Frånvarande";
                else
                    present = "Närvarande";
                System.out.println(a.getChild().getFirstName() + " " + a.getChild().getLastName() +
                        " " + present );
            }
            System.out.println();
        }
    },

    PRINT_ABSENT{
        @Override
        public void output(Object o) {
            List<Attendance> attendanceList = (List<Attendance>) o;
            System.out.println("Frånvarande " + LocalDate.now());
            for(Attendance a: attendanceList) {
                if (!a.getPresent())
                    System.out.println(a.getChild().getFirstName() + " " + a.getChild().getLastName());
            }
        }
    },

    PRINT_PRESENT {
        @Override
        public void output(Object o) {
            List<Attendance> attendanceList = (List<Attendance>) o;
            System.out.println("Närvarande " + LocalDate.now());
            for(Attendance a: attendanceList) {
                if (a.getPresent())
                    System.out.println(a.getChild().getFirstName() + " " + a.getChild().getLastName());
            }
        }
    },

    CAREGIVER_INFO{
        @Override
        public void output(Object o) {
            System.out.println("Vilket barn?");
        }
    },

    CAREGIVER_INFO_PRINT{
        @Override
        public void output(Object o) {
            Child child = (Child) o;
            List<Caregiver> caregiverList = child.getCaregivers();
            for(Caregiver caregiver : caregiverList){
                System.out.println(caregiver.getFirstName() + " " + caregiver.getLastName()+
                        "\n" + caregiver.getPhoneNumber() +
                        "\n" + caregiver.getEmailAddress());
            }
        }
    },

    SHUT_DOWN{
        @Override
        public void output(Object o) {
            System.out.println("Programmet är avslutat");
        }
    };

    public abstract void output(Object o);

}
