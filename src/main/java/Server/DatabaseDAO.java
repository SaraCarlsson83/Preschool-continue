package Server;

import java.util.List;

public interface DatabaseDAO {
    void addChild(Child child);
    void deleteChild(Child child);
    void addCaregiver(Caregiver caregiver);
    void deleteCaregiver(Caregiver caregiver);
    void addEducator(Educator educator);
    void deleteEducator(Educator educator);
    <T> List<T> deSerialize(String fileName);
    <T> void serialize(List <T> list, String fileName);



}
