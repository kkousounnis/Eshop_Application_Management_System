package cmdutils;

import interfaces.ICustomerCmdUtils;
import java.util.Scanner;

public class Customer implements ICustomerCmdUtils {
    private Scanner sc;
    
    public Customer(Scanner sc){
        this.sc=sc;
    }
    
    @Override
    public models.Customer askDate() {

        Command cmd = new Command();
        models.Customer customer = new models.Customer();
        //firstName, lastName, tel, email
        
        customer.setFirstName(cmd.getField(sc, "Type your First Name."));
        customer.setLastName(cmd.getField(sc, "Type your Last Name."));
        customer.setTel(cmd.getField(sc, "Type your Telephone number."));
        customer.setEmaill(cmd.getField(sc, "Type your Email."));
        return (customer);
    }

}
