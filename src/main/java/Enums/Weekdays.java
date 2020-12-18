package Enums;

/**
 * Created by Sara Carlsson
 * Date: 15/12/2020
 * Time:14:09
 * Project: PreschoolContinue
 * Copywright: MIT
 */
public enum Weekdays {
    MONDAY("Måndag", 0),
    TUESDAY("Tisdag", 1),
    WEDNESDAY("Onsdag", 2),
    THURSDAY("Torsdag", 3),
    FRIDAY("Fredag", 4),
    EVERYDAY("Alla dagar", 5);
    public final String days;
    public final int daysInt;

    Weekdays(String days, int daysInt) {
        this.days = days;
        this.daysInt = daysInt;
    }
}
