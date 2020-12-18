package controllers;

import Server.Caregiver;
import controllers.AllInfo;

import java.util.List;

/**
 * Created by Ivona Zoricic
 * Date: 2020-12-04
 * Time: 14:36
 * Project: Preeschool
 * Copywrite: MIT
 */
public class Demo {
    public static void main(String[] args) {


       /* Server.Database d = new Server.Database();

        Server.Caregiver c1 = new Server.Caregiver("Anna","Andersson","198902024785");
        Server.Caregiver c2 = new Server.Caregiver("Eva","Johansson","198801015689");
        Server.Caregiver c3 = new Server.Caregiver("Maria","Karlsson","198703036523");
        Server.Caregiver c4 = new Server.Caregiver("Karin","Nilsson", "198604043256");
        d.addCaregiver(c1);
        d.addCaregiver(c2);
        d.addCaregiver(c3);
        d.addCaregiver(c4);
        c1.setEmailAddress("anna.andersson@gmail.com");
        c1.setPhoneNumber("070 222 333 44");
        c1.setPostAddress("Stockholm");
        c2.setEmailAddress("eva.johansson@gmail.com");
        c2.setPhoneNumber("070 333 444 55");
        c2.setPostAddress("Solna");
        c3.setEmailAddress("maria.karlsson@gmail.com");
        c3.setPhoneNumber("070 444 555 66");
        c3.setPostAddress("Huddinge");
        c4.setEmailAddress("karin.nilsson@gmail.com");
        c4.setPhoneNumber("070 555 666 77");
        c4.setPostAddress("Södertälje");
        c1.setPassword("Anna");
        c2.setPassword("Eva");
        c3.setPassword("Maria");
        c4.setPassword("Karin");
        List<Caregiver> caregiverList = d.getCaregiverList();


        Server.Child b1 = new Server.Child("Alice","Andersson","201502024785");
        Server.Child b2 = new Server.Child("Olivia","Johansson","201501015689");
        Server.Child b3 = new Server.Child("Lucas","Karlsson","201503036523");
        Server.Child b4 = new Server.Child("Liam","Nilsson","201504043256");
        Server.Child b5 = new Server.Child("Astrid","Nilsson","01605053325");

        b1.addCaringTime("måndag", "08:00", "16:00");
        b1.addCaringTime("tisdag", "08:00", "16:00");
        b1.addCaringTime("onsdag", "08:00", "16:00");
        b1.addCaringTime("torsdag", "08:00", "15:00");
        b1.addCaringTime("fredag", "08:00", "15:00");

        b2.addCaringTime("måndag", "08:00", "15:00");
        b2.addCaringTime("tisdag", "08:00", "15:00");
        b2.addCaringTime("onsdag", "08:00", "15:00");
        b2.addCaringTime("torsdag", "08:00", "15:00");
        b2.addCaringTime("fredag", "08:00", "15:00");

        b3.addCaringTime("måndag", "08:00", "15:00");
        b3.addCaringTime("tisdag", "08:00", "15:00");
        b3.addCaringTime("onsdag", "08:00", "15:00");
        b3.addCaringTime("torsdag", "08:00", "15:00");
        b3.addCaringTime("fredag", "08:00", "15:00");

        b4.addCaringTime("måndag", "08:00", "16:30");
        b4.addCaringTime("tisdag", "08:00", "16:30");
        b4.addCaringTime("onsdag", "08:00", "16:30");
        b4.addCaringTime("torsdag", "08:00", "15:00");
        b4.addCaringTime("fredag", "08:00", "15:00");

        b5.addCaringTime("måndag", "08:00", "16:30");
        b5.addCaringTime("tisdag", "08:00", "16:30");
        b5.addCaringTime("onsdag", "08:00", "16:30");
        b5.addCaringTime("torsdag", "08:00", "15:00");
        b5.addCaringTime("fredag", "08:00", "15:00");


        d.addChild(b1);
        d.addChild(b2);
        d.addChild(b3);
        d.addChild(b4);
        d.addChild(b5);
        List<Server.Child> childrenList = d.getChildList();


        c1.addChildren(b1);
        c2.addChildren(b2);
        c3.addChildren(b3);
        c4.addChildren(b4);
        c4.addChildren(b5);

        b1.addCaregiver(c1);
        b2.addCaregiver(c2);
        b3.addCaregiver(c3);
        b4.addCaregiver(c4);
        b5.addCaregiver(c4);

        Server.Educator e = new Server.Educator("Kristina","Eriksson","97807075564");
        d.addEducator(e);
        e.setEmailAddress("kristina.eriksson@gmail.com");
        e.setPhoneNumber("070 123 45 67");
        e.setPostAddress("Stockholm");
        e.setPassword("Kristina");
        List<Server.Educator> educatorList = d.getEducatorList();


        controllers.AllInfo.databaseDAO.serialize(childrenList,"Children.ser");
        controllers.AllInfo.databaseDAO.serialize(educatorList,"Educators.ser");

        */

    }
}
