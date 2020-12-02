package dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import models.Product;

public class DataBase {

    public String server;
    public String username;
    public String password;
    public String database;

    Connection con;
    Statement statement = null;
    PreparedStatement preparedstatement;
    ResultSet rs;

    public DataBase() {

        username = "root";
        password = "password";
        database = "eshop1";
        server = "jdbc:mysql://localhost/" + database + "?useSSL=false&serverTimeZone=Europe/Athens";
        try {
            con = DriverManager.getConnection(server, username, password);
            System.out.println("Connected");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Insert update delete <----- int result
    //SELECT               <----- Result set
    
    
    //insert into customers
    public int insertCustomer(Customer c, String tableName){
        //INSERT INTO `customers`(`first_name`,`last_name`,`tel`,`email`) VALUES ('John','Johnakos','210212121','j@gmail.com');
        int result = 0;
        StringBuilder sb = new StringBuilder();
        
        sb.append("INSERT INTO ");
        sb.append(tableName);
        sb.append("(`first_name`,`last_name`,`tel`,`email`)");
        sb.append(" VALUES (");
        sb.append(" \""); sb.append(c.getFirstName()); sb.append("\"");sb.append(",");
        sb.append(" \"");sb.append(c.getLastName()); sb.append("\"");sb.append(",");
        sb.append(" \"");sb.append(c.getTel()); sb.append("\"");sb.append(",");
        sb.append(" \"");sb.append(c.getEmaill()); sb.append("\"");
        sb.append(" );");
        
        try {
            //System.out.println(sb.toString());

            statement = con.createStatement();
            result = statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        
     
        return result;
    }
    
     public int insertProducts(Product p, String tableName){
        //INSERT INTO `customers`(`name`,`price`,`quantity`) VALUES ('Fix it kit','187','1');
        int result = 0;
        StringBuilder sb = new StringBuilder();
        
        sb.append("INSERT INTO ");
        sb.append(tableName);
        sb.append("(`name`,`price`,`quantity`)");
        sb.append(" VALUES (");
        sb.append(" \""); sb.append(p.getName()); sb.append("\"");sb.append(",");
        sb.append(" \"");sb.append(p.getPrice()); sb.append("\"");sb.append(",");
        sb.append(" \"");sb.append(p.getQuantity()); sb.append("\"");
        sb.append(" );");
        
        try {
            //System.out.println(sb.toString());

            statement = con.createStatement();
            result = statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        
     
        return result;
    }
}
