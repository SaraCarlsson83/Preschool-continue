package Server;

import java.util.List;

public interface PersonDAO {

   String getContactInformation(IContactInformation person);
   Child getChild(String name);
   Caregiver getCaregiver(String username, String password);
   Caregiver getCaregiver(String name);
   Educator getEducator(String username, String password);
   List<Child> getChildList();
   List<Educator> getEducatorList();
   List<Caregiver> getCaregiverList();
   void removeChildFromCaregiver(Child child);
}
