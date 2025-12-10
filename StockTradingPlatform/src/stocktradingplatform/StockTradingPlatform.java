/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stocktradingplatform;
import java.sql.SQLException;
import java.time.*;
import java.util.Scanner;
/**
 *
 * @author sami
 */
public class StockTradingPlatform {
    private static User user=null; 
    public static void greetingMessage() {
        int hour=LocalTime.now().getHour();
        if (hour<12) System.out.println(">Good Morning...");
        else if (hour>=12 && hour<17) System.out.println(">Good Afternon...");
        else if (hour>=17) System.out.println(">Good Evening...");
    }
    private static void loginOrRegister() {
        System.out.println(">Choose : 1-Login, 2-Register, 3-Cancel");
        System.out.print(">Operation : ");
        Scanner sc=new Scanner(System.in);
        int operation=sc.nextInt();
        if (operation==1) {
            System.out.print("\n>Enter username : ");
            String username=sc.nextLine();
            System.out.print("\n>Enter password : ");
            String pass=sc.nextLine();
            try {
                boolean check=loginOrRegister.login(username,pass);
                if (check) System.out.println(">Logged Successfully...");
                else System.out.println("Login unsuccessful, Error...");
            } catch (SQLException ex) {
                System.getLogger(StockTradingPlatform.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else if (operation==2) {
            System.out.print("\n>Enter Name : ");
            String name=sc.nextLine();
            System.out.print("\n>Enter Username : ");
            String username=sc.nextLine();
            System.out.println("\n>Enter Passowrd : ");
            String pass=sc.nextLine();
            User u=new User();
            u.setuName(name);u.setuUserName(username);u.setuPass(pass);
            try {
                boolean check=loginOrRegister.register(u);
                if (check) System.out.println(">Logged Successfully...");
                else System.out.println("Login unsuccessful, Error...");
            } catch (SQLException ex) {
                System.getLogger(StockTradingPlatform.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else if (operation==3) {
            System.out.println("Operation cancelled...");
            return;
        }
    }
    public static void main(String[] args) {
        greetingMessage();
        
        loginOrRegister();
        
        int operation=0;
        do {
            System.out.println(">Operations are 1-Portfolio, 2-Buy Stocks, 3-Sell Stocks, 4-Show stocks, 5-Exit");
            System.out.print(">Enter operation : ");
            Scanner sc=new Scanner(System.in);
            operation=sc.nextInt();
            switch(operation) {
                case 1:
                    portFolio();
                    break;
                case 2:
                    buyStocks();
                    break;
                case 3:
                    sellStocks();
                    break;
                case 4:
                    showStocks();
                    break;
            }
        }while (operation!=5);
        System.out.println(">Thank you for choosing us...");
    }

    private static void portFolio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void sellStocks() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void buyStocks() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void showStocks() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }    
}
