package Server;

import java.util.List;

public interface AttendanceDAO {

    void setAttendance();
    void addAbsence(Child child);
    void addChildInAttendance(Child child);
    void removeChildFromAttendance(Child child);
    void addAttendanceTodayInList(List<Attendance> list);
    List<Attendance> getAttendanceToday();
    List<List<Attendance>> getAttendanceList();

}
