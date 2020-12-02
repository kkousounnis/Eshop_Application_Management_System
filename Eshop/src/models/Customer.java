package models;

public class Customer {
    private String firstName;
    private String lastName;
    private String tel;
    private String emaill;
    
    public Customer(){
        
    }

    public Customer(String firstName, String lastName, String tel, String emaill) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.emaill = emaill;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer{firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", tel=").append(tel);
        sb.append(", emaill=").append(emaill);
        sb.append('}');
        return sb.toString();
    }

    
    
    
}
