package controllers;

import Server.*;

/**
 * Created by Sara Carlsson
 * Date: 14/12/2020
 * Time:14:38
 * Project: PreschoolContinue
 * Copywright: MIT
 */
public class AllInfo {
    private static final Database d = new Database();

    static final AttendanceDAO attendanceDAO = d;
    static final DatabaseDAO databaseDAO = d;
    static final PersonDAO personDAO = d;

    public static Caregiver caregiver;
    public static Educator educator;

    public void setCaregiver(Caregiver caregiver){
        AllInfo.caregiver = caregiver;
    }

    public void setEducator(Educator educator){ AllInfo.educator = educator; }

}
