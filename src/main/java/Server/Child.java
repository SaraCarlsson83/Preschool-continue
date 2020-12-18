package Server;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Child extends Person {

    List<Caregiver> caregivers = new ArrayList<>();
    List<CaringTime> caringTimes = new ArrayList<>();

    public Child(String firstName, String lastName, String personalNumber) {
        super(firstName, lastName, personalNumber);
    }

    public void addCaregiver(Caregiver c){
        caregivers.add(c);
    }

    public void addCaringTime(String inputDate, String inputStartTime, String inputStopTime){
        LocalTime start = LocalTime.parse(inputStartTime);
        LocalTime stop = LocalTime.parse(inputStopTime);
        caringTimes.add(new CaringTime(inputDate, start, stop));
    }

    public List<CaringTime> getCaringTimes(){return caringTimes; }

    public List<Caregiver> getCaregivers() {
        return caregivers;
    }
}
