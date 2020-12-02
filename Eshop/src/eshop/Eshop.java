/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop;

import dbutils.DataBase;
import java.util.Scanner;
import models.Customer;

/**
 *
 * @author root
 */
public class Eshop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Customer customer = new Customer();
        cmdutils.Customer cmdCustomer = new cmdutils.Customer(sc);
        customer = cmdCustomer.askDate();

        DataBase db = new DataBase();
        db.insertCustomer(customer, "customers");
    }
}
