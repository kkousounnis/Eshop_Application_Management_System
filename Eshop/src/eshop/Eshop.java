/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop;

import dbutils.DataBase;
import java.util.Scanner;
import models.Customer;
import models.Product;

/**
 *
 * @author root
 */
public class Eshop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataBase db = new DataBase();
        Customer customer = new Customer();
        Product product = new Product();

        cmdutils.Customer cmdCustomer = new cmdutils.Customer(sc);
        customer = cmdCustomer.askData();
        cmdutils.Product cmdProduct = new cmdutils.Product(sc);
        product = cmdProduct.askData();

        System.out.println("Insert" + db.insertCustomer(customer, "customers")
                + "record");
        System.out.println("Insert" + db.insertProducts(product, "products")
                + "record");
        db.insertOrder(sc);
    }
}
