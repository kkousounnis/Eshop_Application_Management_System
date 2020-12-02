package cmdutils;

import java.util.Scanner;

public class Command {
    
    public String getField(Scanner sc, String Message){
        System.out.println(Message);
        return (sc.nextLine());
    }
    
    public Double getDoubleField(Scanner sc, String Message){
        System.out.println(Message);
        return (sc.nextDouble());
    }
    
    public int getIntField(Scanner sc, String Message){
        System.out.println(Message);
        return (sc.nextInt());
    }
    
}
