package Server;

import java.io.Serializable;
import java.time.LocalTime;

public class CaringTime implements Serializable {
    private final LocalTime start;
    private final LocalTime stop;
    private final String weekday;

    public CaringTime(String weekday, LocalTime start, LocalTime stop){
        this.weekday = weekday;
        this.start = start;
        this.stop = stop;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public String getDay(){
        return weekday;
    }

}