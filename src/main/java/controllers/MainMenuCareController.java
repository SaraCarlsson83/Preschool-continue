package controllers;

import Enums.Weekdays;
import Server.Caregiver;
import Server.CaringTime;
import Server.Child;
import Server.Educator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Created by Sara Carlsson
 * Date: 14/12/2020
 * Time:15:05
 * Project: PreschoolContinue
 * Copywright: MIT
 */
public class MainMenuCareController implements Initializable {



    private Caregiver caregiver;

    @FXML
    private Text helloText;

    @FXML
    private ChoiceBox<String> alternativ;

    @FXML
    private ChoiceBox<String> childrens;

    @FXML
    private Text regAbsenceText;

    @FXML
    private TextArea contactInfo;

    @FXML
    private Pane changeCaretime;

    @FXML
    private ChoiceBox<String> Veckodag;

    @FXML
    private ChoiceBox<LocalTime> leave;

    @FXML
    private ChoiceBox<LocalTime> pickUp;

    @FXML
    private Button saveBtn;

    @FXML
    public Pane changePasswordPane;

    @FXML
    public PasswordField newPasswordField;

    @FXML
    public PasswordField repeatPasswordField;

    @FXML
    public Text presentPasswordText;

    @FXML
    public Text wrongPasswordText;

    @FXML
    void LogOutAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("StartWindow.fxml"));
        Stage primaryStage = (Stage) saveBtn.getScene().getWindow();
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }

    @FXML
    public void savePasswordAction(ActionEvent actionEvent) {
        if (newPasswordField.getText().equals(repeatPasswordField.getText())){
            caregiver.setPassword(newPasswordField.getText());
            changePasswordPane.setVisible(false);
            regAbsenceText.setText("Nu är ditt nya lösenord sparat");
            regAbsenceText.setVisible(true);
            newPasswordField.clear();
            repeatPasswordField.clear();
            wrongPasswordText.setVisible(false);
        }
        else
            wrongPasswordText.setVisible(true);
    }

    @FXML
    void saveAction(ActionEvent event) {
        Child child = AllInfo.personDAO.getChild(childrens.getValue());
        Weekdays[] weekdays = Weekdays.values();
        Weekdays i;
        String dayOfWeek = Veckodag.getValue();
        if (dayOfWeek.equalsIgnoreCase(Weekdays.EVERYDAY.days)){
            for(Weekdays w : weekdays){
                if(w.daysInt == Weekdays.EVERYDAY.daysInt)
                    break;
                child.getCaringTimes().set(w.daysInt, new CaringTime(w.days, leave.getValue(), pickUp.getValue()));
            }
        }
        else {
            for (Weekdays w : weekdays) {
                if (w.days.equalsIgnoreCase(dayOfWeek)) {
                    i = w;
                    child.getCaringTimes().set(i.daysInt, new CaringTime(i.days, leave.getValue(), pickUp.getValue()));
                    break;
                }
            }
        }
        setBottomPaneEmpty();
    }

    @FXML
    void searchAction(ActionEvent event) {
        setBottomPaneEmpty();
        String alt = alternativ.getValue();
        Child child = AllInfo.personDAO.getChild(childrens.getValue());

        switch (alt) {
            case "Registrera frånvaro":
                AllInfo.attendanceDAO.addAbsence(child);
                regAbsenceText.setText("Du har registrerat frånvaro för " + child.getFirstName() +
                        " " + child.getLastName());
                regAbsenceText.setVisible(true);
                break;
            case "Kontaktuppgifter":
                showContactInfo();
                contactInfo.setVisible(true);
                break;
            case "Registrera tider":
                changeCaretime.setVisible(true);
                break;
            case "Visa tider":
                showCaringTimes(child);
                contactInfo.setVisible(true);
                break;
            case "Ändra lösenord":
                presentPasswordText.setText(caregiver.getPassword());
                changePasswordPane.setVisible(true);
                break;
        }
    }

    public void setCaregiver(Caregiver caregiver){
        this.caregiver = caregiver;
    }

    public Caregiver getCaregiver(){
        return caregiver;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCaregiver(AllInfo.caregiver);
        helloText.setText("Välkommen " + getCaregiver().getFirstName() );
        alternativ.getItems().addAll("Registrera frånvaro", "Kontaktuppgifter", "Registrera tider", "Visa tider",
                "Ändra lösenord");
        alternativ.setValue("Registrera frånvaro");

        for(Child child : getCaregiver().getChildren())
            childrens.getItems().add(child.getFirstName() + " " + child.getLastName());
        childrens.setValue(getCaregiver().getChildren().get(0).getFirstName() + " " +
                getCaregiver().getChildren().get(0).getLastName());

        Veckodag.getItems().addAll(Weekdays.EVERYDAY.days, Weekdays.MONDAY.days, Weekdays.TUESDAY.days,
                Weekdays.WEDNESDAY.days, Weekdays.THURSDAY.days, Weekdays.FRIDAY.days);
        Veckodag.setValue(Weekdays.EVERYDAY.days);
        ObservableList<LocalTime> localTimes = setLocalTime();

        leave.getItems().addAll(localTimes);
        leave.setValue(LocalTime.parse("06:30"));
        pickUp.getItems().addAll(localTimes);
        pickUp.setValue(LocalTime.parse("06:30"));
    }

    public void setBottomPaneEmpty(){
        contactInfo.setVisible(false);
        regAbsenceText.setVisible(false);
        changeCaretime.setVisible(false);
        changePasswordPane.setVisible(false);
        contactInfo.clear();
    }

    public ObservableList<LocalTime> setLocalTime(){
        ObservableList<LocalTime> temp = FXCollections.observableArrayList();
        LocalTime start = LocalTime.parse("06:30");
        LocalTime stop = LocalTime.parse("18:00");
        while(true){
            temp.add(start);
            if(start.equals(stop))
                break;
            start = start.plusMinutes(30);
        }
        return temp;
    }

    public void showContactInfo(){
        contactInfo.appendText("Pedagogers kontaktuppgifter\n\n");
        for (Educator educator : AllInfo.personDAO.getEducatorList()) {
            contactInfo.appendText("Namn: " + educator.getFirstName() + " " + educator.getLastName() + "\n");
            contactInfo.appendText(AllInfo.personDAO.getContactInformation(educator));
        }
    }

    public void showCaringTimes(Child child) {
        contactInfo.appendText("Här är " + child.getFirstName() + "s omsorgstider: \n");
        for (CaringTime ct : child.getCaringTimes()) {
            contactInfo.appendText(ct.getDay() + ": \t" + ct.getStart() + " - " + ct.getStop() + "\n");
        }
    }
}
