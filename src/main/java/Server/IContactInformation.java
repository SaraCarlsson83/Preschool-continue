package Server;


public interface IContactInformation {

    void setPassword(String password);
    void setEmailAddress(String emailAddress);
    void setPhoneNumber(String phoneNumber);
    void setPostAddress(String postAddress);
    String getEmailAddress();
    String getPhoneNumber();
    String getPostAddress();
    String getPassword();

}
