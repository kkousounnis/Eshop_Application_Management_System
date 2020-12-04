package dbutils;

import cmdutils.Command;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import models.Product;
import models.ProductDTO;

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
     
    public int insertOrder(Scanner sc) {

        int result = 0;
        /*
            Step 1 - Select Customer
            Step 2 - Select Product
            Step 3 - sum products
            Step 4 - Insert order
            Step 5 - Insert Products
            
         */
        int customerId = selectCustomer(sc);
        
        List<ProductDTO> productsIdsQuantities = selectProducts(sc);
        
        System.out.println(productsIdsQuantities);

        double sumPricesOfSelectedProducts = sumProductsPrices(productsIdsQuantities);
        System.out.println(sumPricesOfSelectedProducts);
        // INSERT INTO orders2(`customers_id`, `total_price`, `date`) 
        // VALUES(1, 187.65, "2020-12-03"), (1, 4128.3, "2020-12-03")
        int orders2Id = addOrder(customerId, sumPricesOfSelectedProducts, "orders2");
        
        result = insertOrders2Details(orders2Id, productsIdsQuantities);
 
        
        
        return (result);
    }

    public int selectCustomer(Scanner sc) {
        Command cmd = new Command();
        Customer customer = null;
        int customerId = -1;
        try {
            statement = con.createStatement();

            rs = statement.executeQuery("SELECT * FROM `customers`");
            while (rs.next()) {
                customerId = rs.getInt("id");
                customer = new Customer(rs.getString("first_name"),
                         rs.getString("last_name"),
                         rs.getString("tel"),
                         rs.getString("email"));
                System.out.println(customerId + "." + customer);
            }
            customerId = cmd.getIntField(sc, "Please select the customer");

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return customerId;
    }

    public List<ProductDTO> selectProducts(Scanner sc) {

        List<ProductDTO> productIdsQuantities = new ArrayList<>();
        Command cmd = new Command();
        Product product = null;
        int productId = -1;
         
        try {
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            

            rs = statement.executeQuery("SELECT * FROM `products`");
            while (rs.next()) {
                productId = rs.getInt("id");
                product = new Product(rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
                System.out.println(productId + "." + product);

            }

            int choice = 1;
            while (choice == 1) {
                // product id
                int prId = cmd.getIntField(sc, "Please select a product to add");
                
                
                // ask for quantity for the previous product
                int quant = cmd.getIntField(sc, "Please type the quantity of the product with id: " + prId);
                rs.absolute(prId);
                double price = rs.getDouble("price");
                productIdsQuantities.add(new ProductDTO(prId, quant, price));
                
                // ask if he would like to add one more product
                choice = cmd.getIntField(sc, "Would you like to add 1 more product, "
                                           + "if yes press 1 else press any other number");

            }
            //productId = cmd.getIntField(sc,"Please select the product id");

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return (productIdsQuantities);
    }

    public double sumProductsPrices(List<ProductDTO> products) {
        double result = 0;
        for (ProductDTO product : products) {
            result += product.getPrice() * product.getQuantity();
        }
        return (result);
    }
    public int addOrder(int customerId, double totalPrice, String tableName) {
        // INSERT INTO orders2(`customers_id`, `total_price`, `date`) 
        // VALUES(1, 187.65, "2020-12-03"), (1, 4128.3, "2020-12-03")
        
        int orders2Id = 0;
        int result = 0;
        StringBuilder sb = new StringBuilder();
        
        sb.append("INSERT INTO ");
        sb.append("`"); sb.append(tableName); sb.append("`");
        sb.append("(`customers_id`, `total_price`, `date`)");
        sb.append(" VALUES(");
        sb.append("\""); sb.append(customerId); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(totalPrice); sb.append("\""); sb.append(",");
        sb.append("\""); sb.append(LocalDateTime.now()); sb.append("\"");
        sb.append(")");
        try {
            //        System.out.println(sb.toString());
            statement = con.createStatement();
            result = statement.executeUpdate(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                System.out.println(rs.getString(1));
                orders2Id=rs.getInt(1);
            }
  
            
        } catch (SQLException ex) {
            //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return(orders2Id);
    }
    
    
     public int insertOrders2Details(int orders2Id, List<ProductDTO> productsIdsQuantities) {
         PreparedStatement preparedStatement = null;
         ResultSet rs = null;
         int result = 0;
         for (int i = 0; i < productsIdsQuantities.size(); i++) {
             try {

                 preparedStatement = con.prepareStatement(
                         "INSERT INTO `orders2_details`"
                         + "(`orders2_id`,"
                         + "`products_id`,"
                         + " `price`,"
                         + " `quantity`)"
                         + " VALUES (?,?,?,?)");

                 preparedStatement.setInt(1, orders2Id);
                 preparedStatement.setInt(2, productsIdsQuantities.get(i).getProductId());
                 preparedStatement.setDouble(3, productsIdsQuantities.get(i).getPrice());
                 preparedStatement.setInt(4, productsIdsQuantities.get(i).getQuantity());

                 result = preparedStatement.executeUpdate();

             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
         return (result);
    }

}
