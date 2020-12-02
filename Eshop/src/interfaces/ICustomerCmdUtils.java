package interfaces;

import models.Customer;

public interface ICustomerCmdUtils {

    /**
     * ask from the cmd the customer's data and return new customer
     */
    public Customer askData();
}
