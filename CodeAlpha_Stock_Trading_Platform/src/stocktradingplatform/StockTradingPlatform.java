/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stocktradingplatform;
import java.sql.*;
import java.time.*;
import java.util.Scanner;
/**
 *
 * @author sami
 */
public class StockTradingPlatform {
    
    private static Integer userId=null;
    
    static Scanner sc = new Scanner(System.in);
    
    public static void greetingMessage() {
        int hour=LocalTime.now().getHour();
        if (hour<12) System.out.println(">Good Morning...");
        else if (hour>=12 && hour<17) System.out.println(">Good Afternon...");
        else if (hour>=17) System.out.println(">Good Evening...");
    }
    
    private static void loginOrRegister() {
        System.out.println(">Choose : 1-Login, 2-Register, 3-Cancel");
        System.out.print(">Operation : ");
        int operation=sc.nextInt();
        switch (operation) {
            case 1 -> {
                    System.out.print("\n>Enter user Id : ");
                    int username=sc.nextInt();
                    sc.nextLine();
                    System.out.print("\n>Enter password : ");
                    String pass=sc.nextLine();
                    try {
                        boolean check=login(username,pass);
                        if (check) {
                            userId=username;
                            System.out.println(">Logged Successfully...");
                        }
                        else System.out.println("Login unsuccessful, Error...");
                    } catch (SQLException ex) {
                        System.getLogger(StockTradingPlatform.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }    
                    break;
                }
            case 2 -> {
                    System.out.print("\n>Enter Name : ");
                    String name=sc.nextLine();
                    sc.nextLine();
                    System.out.print("\n>Enter Passowrd : ");
                    String pass=sc.next();
                    User u=new User();
                    u.setuName(name);
                    u.setuPass(pass);
                    try {
                        boolean check=register(u);
                        if (check) {
                            System.out.println(">Registered Successfully...");
                            System.out.println(">Your userId is : "+userId);
                        } else System.out.println("Registeration unsuccessful, Error...");
                    } catch (SQLException ex) {
                        System.getLogger(ex.getMessage());
                    } 
                    break;
                }
            case 3 -> {
                System.out.println("Operation cancelled...");
                break;
            }
            default -> {
                break;
            }
        }
    }
    public static void main(String[] args) {
        greetingMessage();
        
        loginOrRegister();
        
        while (userId==null) {
            System.out.println(">Login OR Register first...");
            loginOrRegister();
        }
        
        int operation=0;
        do {
            System.out.println(">Operations are 1-Portfolio, 2-Buy/Sell Stocks, 3-Show Stocks, 4-Exit");
            System.out.print(">Enter operation : ");
            operation=sc.nextInt();
            switch(operation) {
                case 1:
                    portFolio();
                    break;
                case 2:
                    buyOrSellStocks();
                    break;
                case 3:
                    showStocks();
                    break;
            }
        }while (operation!=4);
        System.out.println(">Thank you for choosing us...");
    }

    private static void portFolio() {
        try{
            Class.forName("org.postgresql.Driver");
            Connection con=null;
            Statement st=null;
            ResultSet rs=null;
            try {
                con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/projects","sami","sami@*123#");
                st=con.createStatement();
                rs = st.executeQuery("select t.tId,t.tType,t.tQuantity,t.tTotalValue,t.tPricePerShare,t.ldt from transactions t where t.uId=" + userId);

                System.out.print(">User : "+userId+"\n\n");
                System.out.println(">Transactions : ");
                System.out.println("-------------------------------------------------------------------------------------------");
                System.out.println("Transcation Id   Transaction Type   Quantity   Total Value   Price Per Share   Time & Date");
                while (rs.next()) {
                    System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getDouble(4)+"\t"+rs.getDouble(5)+"\t"+rs.getTimestamp(6));
                }
                System.out.print("-------------------------------------------------------------------------------------------\n\n");
                rs=st.executeQuery("select uTotInvestment from users where uId="+userId);
                rs.next();
                System.out.println(">Total Invested : "+rs.getDouble(1));
            } catch (SQLException se) {
               System.out.println(">No logical persistance state...");
            } catch (Exception e) {
               System.out.println(e.getMessage());
            } finally {
                if (rs!=null) rs.close();
                if (st!=null) st.close();
                if (con!=null) con.close();
            }
        } catch (ClassNotFoundException ce) {
            System.out.println(">No logical persistance state.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean sellStocks(Transaction t) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con=null;
            PreparedStatement pst=null;
            ResultSet rs=null;
            try {
                con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/projects","sami","sami@*123#");
                pst=con.prepareStatement("insert into transactions(uId,tType,tQuantity,tTotalValue,tPricePerShare,ldt) values(?,?,?,?,?,?)");
                pst.setInt(1,t.getuId());
                pst.setString(2,t.gettType());
                pst.setInt(3,t.gettQuantity());
                pst.setDouble(4,t.gettTotalValue());
                pst.setDouble(5,t.gettPricePerShare());
                pst.setTimestamp(6,Timestamp.valueOf(LocalDateTime.now()));
                pst.executeUpdate();
                
                System.out.println(">Transcation registered successfully...");
                
                System.out.println(">Updating user investment value...");
                
                pst=con.prepareStatement("update users set uTotInvestment=uTotInvestment-? where uId=?");
                pst.setDouble(1,t.gettTotalValue());
                pst.setInt(2,t.getuId());
                pst.executeUpdate();
                
                System.out.println(">User investment value updated successfully...");
            } catch (SQLException se) {
                System.out.println(">No logical persistance state...");
                return false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            } finally {
                if (rs!=null) rs.close();
                if (pst!=null) pst.close();
                if (con!=null) con.close();
            }
        } catch (ClassNotFoundException ce) {
            System.out.println(">No logical persistance state.");  
            return false;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private static boolean buyStocks(Transaction t) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con=null;
            PreparedStatement pst=null;
            ResultSet rs=null;
            try {
                con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/projects","sami","sami@*123#");
                pst=con.prepareStatement("insert into transactions(uId,tType,tQuantity,tTotalValue,tPricePerShare,ldt) values(?,?,?,?,?,?)");
                pst.setInt(1,t.getuId());
                pst.setString(2,t.gettType());
                pst.setInt(3,t.gettQuantity());
                pst.setDouble(4,t.gettTotalValue());
                pst.setDouble(5,t.gettPricePerShare());
                pst.setTimestamp(6,Timestamp.valueOf(LocalDateTime.now()));
                pst.executeUpdate();
                
                System.out.println(">Transcation registered successfully...");
                
                System.out.println(">Updating user investment value...");
                
                pst=con.prepareStatement("update users set uTotInvestment=uTotInvestment+? where uId=?");
                pst.setDouble(1,t.gettTotalValue());
                pst.setInt(2,t.getuId());
                pst.executeUpdate();
                
                System.out.println(">User investment value updated successfully...");
                
            } catch (SQLException se) {
                System.out.println(">No logical persistance state...");
                System.out.println(se.getMessage());
                return false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            } finally {
                if (rs!=null) rs.close();
                if (pst!=null) pst.close();
                if (con!=null) con.close();
            }
        } catch (ClassNotFoundException ce) {
            System.out.println(">No logical persistance state."); 
            return false;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private static void showStocks() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con=null;
            Statement st=null;
            ResultSet rs=null;
            try {
                con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/projects","sami","sami@*123#");
                st=con.createStatement();
                rs=st.executeQuery("select * from Stocks;");
                System.out.println("SYMBOL\t\tNAME\t\tPRICE");
                while (rs.next()) {
                    System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getInt(3));
                }
                rs.close();
                st.close();
                con.close();
            } catch (SQLException se) {
                System.out.println(">No logical persistance state...");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (rs!=null) rs.close();
                if (st!=null) st.close();
                if (con!=null) con.close();
            }
        } catch (ClassNotFoundException ce) {
            System.out.println(">No logical persistance state.");      
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
    
    public static boolean login(Integer uId,String pass) throws SQLException {
        try {
            Connection con=null;
            PreparedStatement pst=null;
            ResultSet rs=null;
            Class.forName("org.postgresql.Driver");
            try {
                con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/projects","sami","sami@*123#");
                pst=con.prepareStatement("select upass from Users where uId=?");
                pst.setInt(1,uId);
                rs = pst.executeQuery();
                if (!rs.next()) return false;
                if (!rs.getString(1).equals(pass)) return false;
            }catch (SQLException e) {
                System.out.println(e.getMessage());
                if (rs!=null) rs.close();
                if (pst!=null) pst.close();
                if (con!=null) con.close();
                return false;
            }catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (rs!=null) rs.close();
                if (pst!=null) pst.close();
                if (con!=null) con.close();
            }
        } catch (ClassNotFoundException ce) {
            System.out.println(">Internal db error : No connection found...");
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public static boolean register(User user) throws SQLException {
        try {
            Connection con=null;
            PreparedStatement pst=null;
            ResultSet rs=null;
            Class.forName("org.postgresql.Driver");
            try {
                con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/projects","sami","sami@*123#");
                pst=con.prepareStatement("insert into Users(uName,uPass) values(?,?)");
                pst.setString(1,user.getuName());
                pst.setString(2,user.getuPass());
                pst.executeUpdate();
                pst=con.prepareStatement("select uId from users where uName=? and uPass=?");
                pst.setString(1,user.getuName());
                pst.setString(2,user.getuPass());
                rs=pst.executeQuery();
                rs.next();
                userId=rs.getInt(1);
            }catch (SQLException e) {
                System.out.println(e.getMessage());
                if (rs!=null) rs.close();
                if (pst!=null) pst.close();
                if (con!=null) con.close();
                return false;
            }catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (rs!=null) rs.close();
                if (pst!=null) pst.close();
                if (con!=null) con.close();
            }
        } catch (ClassNotFoundException ce) {
            System.out.println(">Internal db error : No connection found...");
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private static void buyOrSellStocks() {
        Transaction t=new Transaction();
        t.setuId(userId);
        System.out.println(">Choose : 1-Buy, 2-Sell, 3-Cancel");
        System.out.print(">Operation : ");
        int operation=sc.nextInt();
        System.out.print(">Enter the stock symbol : ");
        String sSymbol=sc.next();
        System.out.print(">Enter the stock quantity : ");
        int quantity=sc.nextInt();
        Stock s = getStockDetails(sSymbol);
        if (s == null) {
            System.out.println(">Invalid stock symbol");
            return;
        }
        switch (operation) {
            case 1 -> {
                    t.settPricePerShare(s.getPrice()); t.settQuantity(quantity); t.settTotalValue(s.getPrice()*quantity);
                    t.setLdt(LocalDateTime.now()); t.settType("BUY");
                    boolean check=buyStocks(t);
                        if (check) System.out.println(">Buy Successfully...");
                        else System.out.println("Buying unsuccessful, Error...");   
                    break;
                }
            case 2 -> {
                    t.settPricePerShare(s.getPrice()); t.settQuantity(quantity); t.settTotalValue(s.getPrice()*quantity);
                    t.setLdt(LocalDateTime.now()); t.settType("SELL");
                    boolean check=sellStocks(t);
                        if (check) System.out.println(">Sell Successfully...");
                        else System.out.println("Selling unsuccessful, Error...");   
                    break;
                }
            case 3 -> {
                System.out.println("Operation cancelled...");
                break;
            }
            default -> {
                break;
            }
        }
    }

    private static Stock getStockDetails(String sSymbol) {
        Stock st=new Stock();
        try {
            Connection con=null;
            PreparedStatement pst=null;
            ResultSet rs=null;
            Class.forName("org.postgresql.Driver");
            try {
                con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/projects","sami","sami@*123#");
                pst=con.prepareStatement("select sname,price from Stocks where ssymbol=?");
                pst.setString(1,sSymbol);
                rs = pst.executeQuery();
                if (!rs.next()) return null;
                st.setsSymbol(sSymbol);
                st.setsName(rs.getString(1));
                st.setPrice(rs.getDouble(2));
            }catch (SQLException e) {
                System.out.println(">No Stock Found...");
                if (rs!=null) rs.close();
                if (pst!=null) pst.close();
                if (con!=null) con.close();
                return null;
            }catch (Exception e) {
                System.out.println(">Unexpected internal error");
            } finally {
                if (rs!=null) rs.close();
                if (pst!=null) pst.close();
                if (con!=null) con.close();
            }
        } catch (ClassNotFoundException ce) {
            System.out.println(">Internal db error : No connection found...");
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return st;
    }
}
