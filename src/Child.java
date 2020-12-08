import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-11-30
 * Project: Preeschool
 * Copyright: MIT
 */
public class Child extends Person  {

    List<Caregiver> caregivers = new ArrayList<>();
    List<CaringTime> caringTimes = new ArrayList<>();

    Child(String firstName, String lastName, String personalNumber) {
        super(firstName, lastName, personalNumber);
    }

    public void addCaregiver(Caregiver c){
        caregivers.add(c);
    }

    public void addCaringTime(String inputDate, String inputStartTime, String inputStopTime){
        LocalDate day = LocalDate.parse(inputDate);

        LocalDateTime start = day.atTime(LocalTime.parse(inputStartTime));
        LocalDateTime stop = day.atTime(LocalTime.parse(inputStopTime));
        caringTimes.add(new CaringTime(start, stop));
    }

    public List<Caregiver> getCaregivers() {
        return caregivers;
    }
}
