package Server;

import java.io.Serializable;
import java.time.LocalDate;

public class Attendance implements Serializable {

    private final Child child;
    private final LocalDate date;
    private Boolean isPresent;

    public Attendance(Child child) {
        this.child = child;
        this.date = LocalDate.now();
        this.isPresent = true;
    }

    public Child getChild() {
        return child;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public LocalDate getDate() {
        return date;
    }
}
