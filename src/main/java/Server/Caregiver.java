package Server;

import java.util.ArrayList;
import java.util.List;

public class Caregiver extends Person implements IContactInformation {
    private String password;
    private String eMailAddress;
    private String phoneNumber;
    private String postAddress;
    List<Child> children;

    public Caregiver(String firstName, String lastName, String personalNumber) {
        super(firstName, lastName, personalNumber);
        children = new ArrayList<>();
    }

    public void addChildren(Child child){
        children.add(child);
    }

    public Child getChild(Child child){
        for(Child c: children){
            if(c.equals(child)){
                return c;
            }
        } return null;
    }

    public List<Child> getChildren(){
        return children;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.eMailAddress = emailAddress;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    @Override
    public String getEmailAddress() {
        return eMailAddress;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getPostAddress() {
        return postAddress;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
