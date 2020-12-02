/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmdutils;

import interfaces.IProductCmdutils;
import java.util.Scanner;

/**
 *
 * @author root
 */
public class Product implements IProductCmdutils{
    private Scanner sc;
    
     public Product(Scanner sc){
        this.sc=sc;
    }
     
     
    @Override
    public models.Product askData(){
        Command cmd = new Command();
        models.Product product = new models.Product();
        
        
        product.setName(cmd.getField(sc, "Type Product Name."));
        product.setPrice(cmd.getDoubleField(sc, "Type Product Price"));
        product.setQuantity(cmd.getIntField(sc, "Type quantity"));
        return (product);
    }

}
