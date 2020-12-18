package controllers;

import Enums.Weekdays;
import Server.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Sara Carlsson
 * Date: 14/12/2020
 * Time:15:05
 * Project: PreschoolContinue
 * Copywright: MIT
 */
public class MainMenuEduController implements Initializable {

    private Educator educator;
    private Child child;

    @FXML
    public Button newCaregiverBtn;

    @FXML
    public Button registerCaregiverBtn;

    @FXML
    public Button saveChildBtn;

    @FXML
    public VBox careGiverVbox;

    @FXML
    private Text helloText;

    @FXML
    private ChoiceBox<String> alternativ;

    @FXML
    private ComboBox<String> childrens;

    @FXML
    private TextArea contactInfo;

    @FXML
    private Text regAbsenceText;

    @FXML
    private Pane showAttendancePane;

    @FXML
    private TextArea attendanceText;

    @FXML
    private TextArea absenceText;

    @FXML
    private TextArea presentText;

    @FXML
    private Pane registerNewChildPane;

    @FXML
    private TextField firstNameChild;

    @FXML
    private TextField lastNameChild;

    @FXML
    private TextField PersonNrChild;

    @FXML
    private ChoiceBox<String> listOfParents;

    @FXML
    private Pane registerNewParentPane;

    @FXML
    private TextField firstNameCare;

    @FXML
    private TextField lastNameCare;

    @FXML
    private TextField personNrCare;

    @FXML
    private TextField emailCare;

    @FXML
    private TextField phoneCare;

    @FXML
    private TextField adressCare;

    @FXML
    public TextField passwordCare;

    @FXML
    public Pane askNewChildPane;

    @FXML
    public Pane askRemoveChildPane;

    @FXML
    private Button yesRemoveBtn;

    @FXML
    private Button noRemoveBtn;

    @FXML
    private Button yesAddBtn;

    @FXML
    private Button noAddBtn;


    @FXML
    public void yesAction(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(yesAddBtn)){
            askNewChildPane.setVisible(false);
            registerNewChildPane.setVisible(true);
        }
        else if (actionEvent.getSource().equals(yesRemoveBtn)){
            regAbsenceText.setText("Nu är " + this.child.getFirstName() + " och hens föräldrar " +
                    "borttagna från förskolan");
            for(Caregiver caregiver : this.child.getCaregivers())
                AllInfo.databaseDAO.deleteCaregiver(caregiver);
            regAbsenceText.setVisible(true);
            askRemoveChildPane.setVisible(false);
        }

    }

    @FXML
    public void noAction(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(noAddBtn)){
            askNewChildPane.setVisible(false);
        }
        else if(actionEvent.getSource().equals(noRemoveBtn)){
            regAbsenceText.setText("Nu är " + this.child.getFirstName() + "borttagen från förskolan");
            regAbsenceText.setVisible(true);
            askRemoveChildPane.setVisible(false);
        }
    }

    @FXML
    void LogOutAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("StartWindow.fxml"));
        Stage primaryStage = (Stage) newCaregiverBtn.getScene().getWindow();
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }


    @FXML
    void saveCaregiverAction(ActionEvent event) {
        Caregiver caregiver = new Caregiver(firstNameCare.getText(), lastNameCare.getText(), personNrCare.getText());
        caregiver.setEmailAddress(emailCare.getText());
        caregiver.setPassword(passwordCare.getText());
        caregiver.setPhoneNumber(phoneCare.getText());
        caregiver.setPostAddress(adressCare.getText());
        AllInfo.databaseDAO.addCaregiver(caregiver);
        listOfParents.getItems().add(caregiver.getFirstName() + " " + caregiver.getLastName());
        registerNewParentPane.setVisible(false);
        registerNewChildPane.setVisible(true);
    }

    @FXML
    public void saveChildAction(ActionEvent actionEvent) {
        Child child = new Child(firstNameChild.getText(), lastNameChild.getText(), PersonNrChild.getText());
        this.child = child;
        AllInfo.databaseDAO.addChild(child);
        AllInfo.attendanceDAO.addChildInAttendance(child);
        childrens.getItems().add(child.getFirstName() + " " + child.getLastName());
        setCaringTime(child, "07:00", "16:00");
        careGiverVbox.setVisible(true);
    }

    @FXML
    public void newCaregiverAction(ActionEvent actionEvent) {
        registerNewChildPane.setVisible(false);
        registerNewParentPane.setVisible(true);
    }

    @FXML
    public void registerCaregiverAction(ActionEvent actionEvent) {
        Caregiver caregiver = AllInfo.personDAO.getCaregiver(listOfParents.getValue());
        this.child.addCaregiver(caregiver);
        caregiver.addChildren(this.child);
        registerNewChildPane.setVisible(false);
        askNewChildPane.setVisible(true);
    }

    @FXML
    public void searchAction(ActionEvent actionEvent) {
        setBottomPaneEmpty();
        String alt = alternativ.getValue();

        if (alt.equals("Se närvaro idag")) {
            printAttendance();
            showAttendancePane.setVisible(true);
        }
        else if (alt.equals("Registrera nytt barn")) {
            for (Caregiver caregiver : AllInfo.personDAO.getCaregiverList())
                listOfParents.getItems().add(caregiver.getFirstName() + " " + caregiver.getLastName());
            registerNewChildPane.setVisible(true);
        }
        else {
            Child child = AllInfo.personDAO.getChild(childrens.getValue());
            this.child = child;
            switch (alt) {
                case "Registrera frånvaro":
                    AllInfo.attendanceDAO.addAbsence(child);
                    regAbsenceText.setText("Du har registrerat frånvaro för " + child.getFirstName() +
                            " " + child.getLastName());
                    regAbsenceText.setVisible(true);
                    break;
                case "Se ett barns omsorgstider":
                    showCaringTimes(child);
                    contactInfo.setVisible(true);
                    break;
                case "Se vårdnadshavares kontaktuppgifter":
                    showContactInfo(child);
                    contactInfo.setVisible(true);
                    break;
                case "Ta bort barn":
                    AllInfo.databaseDAO.deleteChild(child);
                    childrens.getItems().remove(child.getFirstName() + " " + child.getLastName());
                    askRemoveChildPane.setVisible(true);
                    break;
            }
        }
    }


    public void setEducator(Educator educator) {
        this.educator = educator;
    }

    public Educator getEducator() {
        return educator;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setEducator(AllInfo.educator);
        helloText.setText("Välkommen " + getEducator().getFirstName());
        alternativ.getItems().addAll("Registrera frånvaro", "Se närvaro idag", "Se ett barns omsorgstider",
                "Se vårdnadshavares kontaktuppgifter", "Registrera nytt barn", "Ta bort barn");
        alternativ.setValue("Registrera frånvaro");

        for (Child child : AllInfo.personDAO.getChildList())
            childrens.getItems().add(child.getFirstName() + " " + child.getLastName());
    }

    public void setBottomPaneEmpty(){
        contactInfo.setVisible(false);
        regAbsenceText.setVisible(false);
        showAttendancePane.setVisible(false);
        registerNewChildPane.setVisible(false);
        registerNewParentPane.setVisible(false);
        careGiverVbox.setVisible(false);
        attendanceText.clear();
        absenceText.clear();
        presentText.clear();
        contactInfo.clear();
    }

    public void showCaringTimes(Child child) {
        contactInfo.appendText("Här är " + child.getFirstName() + "s omsorgstider:\n");
        for (CaringTime ct : child.getCaringTimes()) {
            contactInfo.appendText(ct.getDay() + ": \t" + ct.getStart() + " - " + ct.getStop() + "\n");
        }
    }
    public void showContactInfo(Child child){
        contactInfo.appendText("Vårdnadhavares kontaktuppgifter\n\n");
        for (Caregiver caregiver: child.getCaregivers()) {
            contactInfo.appendText("Namn: " + caregiver.getFirstName() + " " + caregiver.getLastName() + "\n");
            contactInfo.appendText(AllInfo.personDAO.getContactInformation(caregiver));
        }
    }

    public void printAttendance(){
        List<Attendance> attendanceList = AllInfo.attendanceDAO.getAttendanceToday();
        String present;
        attendanceText.appendText("NÄRVARO " + LocalDate.now() + "\n");
        presentText.appendText("NÄRVARANDE " + LocalDate.now()+ "\n");
        absenceText.appendText("FRÅNVARANDE " + LocalDate.now()+ "\n");
        for (Attendance a : attendanceList) {
            if (!a.getPresent()) {
                absenceText.appendText(a.getChild().getFirstName() + " " + a.getChild().getLastName()+ "\n");
                present = "Frånvarande";
            }
            else {
                presentText.appendText(a.getChild().getFirstName() + " " + a.getChild().getLastName()+ "\n");
                present = "Närvarande";
            }
            attendanceText.appendText(a.getChild().getFirstName() + " " + a.getChild().getLastName() +
                    "\t " + present + "\n");
        }
        System.out.println();
    }

    public void setCaringTime(Child child, String start, String stop){
        Weekdays[] weekdays = Weekdays.values();
        for(Weekdays w : weekdays){
            if(w.daysInt == Weekdays.EVERYDAY.daysInt)
                break;
            child.addCaringTime(w.days, start, stop);
        }
    }
}
