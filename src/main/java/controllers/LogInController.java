package controllers;

import Enums.SerFiles;
import Server.Caregiver;
import Server.Educator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {
    @FXML
    public Button quitBtnEdu;

    @FXML
    public Button quitBtnCare;

    @FXML
    private Tab educatorPane;

    @FXML
    private TextField userNameEdu;

    @FXML
    private PasswordField passwordEdu;

    @FXML
    private Button logInBtnEdu;


    @FXML
    private Text wrongTextEdu;

    @FXML
    private Tab caregiverPane;

    @FXML
    private TextField userNameCare;

    @FXML
    private PasswordField passwordCare;

    @FXML
    private Button logInBtnCare;

    @FXML
    private Text wrongTextCare;

    public void quitAction(ActionEvent actionEvent) {
        saveAllFiles();
        System.exit(0);
    }

    @FXML
    void logInCareAction(ActionEvent event) throws IOException {
        Caregiver caregiver = AllInfo.personDAO.getCaregiver(userNameCare.getText(), passwordCare.getText());
        if(caregiver==null){
            wrongTextCare.setVisible(true);
        }
        else{
            AllInfo allinfo = new AllInfo();
            allinfo.setCaregiver(caregiver);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainMenuCare.fxml"));
            Stage primaryStage = (Stage) logInBtnCare.getScene().getWindow();
            primaryStage.setScene(new Scene(root, 640, 480));
            primaryStage.show();
        }
    }

    @FXML
    void logInEduAction(ActionEvent event) throws IOException {
        Educator educator = AllInfo.personDAO.getEducator(userNameEdu.getText(), passwordEdu.getText());
        if(educator==null){
            wrongTextEdu.setVisible(true);
        }
        else{
            AllInfo allinfo = new AllInfo();
            allinfo.setEducator(educator);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainMenuEdu.fxml"));
            Stage primaryStage = (Stage) logInBtnEdu.getScene().getWindow();
            primaryStage.setScene(new Scene(root, 640, 480));
            primaryStage.show();
        }
    }

    public void saveAllFiles() {
        AllInfo.attendanceDAO.addAttendanceTodayInList(AllInfo.attendanceDAO.getAttendanceToday());
        AllInfo.databaseDAO.serialize(AllInfo.attendanceDAO.getAttendanceList(), SerFiles.LIST_OF_ATTENDANCES.serFiles);
        AllInfo.databaseDAO.serialize(AllInfo.attendanceDAO.getAttendanceToday(), SerFiles.ATTENDANCE.serFiles);
        AllInfo.databaseDAO.serialize(AllInfo.personDAO.getChildList(), SerFiles.CHILDREN.serFiles);
        AllInfo.databaseDAO.serialize(AllInfo.personDAO.getEducatorList(), SerFiles.EDUCATOR.serFiles);
    }
}
