/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stocktradingplatform;
import java.sql.*;
/**
 *
 * @author sami
 */
public class loginOrRegister {
    public static boolean login(String username,String pass) throws SQLException {
        Connection con=null;
        PreparedStatement pst=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","sami@*123#");
            pst=con.prepareStatement("select pass from User where uUsername=?");
            pst.setString(1,username);
            ResultSet rs=pst.executeQuery();
            rs.next();
            if (!rs.getString(1).equals(pass)) return false; 
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            if (pst!=null) pst.close();
            if (con!=null) con.close();
            return false;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static boolean register(User user) throws SQLException {
        Connection con=null;
        PreparedStatement pst=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","sami@*123#");
            pst=con.prepareStatement("insert into User(uName,uUsername,uPass) values(?,?,?)");
            pst.setString(2,user.getuName());
            pst.setString(3,user.getuUserName());
            pst.setString(4,user.getuPass());
            pst.executeUpdate();
            pst.close();
            con.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            if (pst!=null) pst.close();
            if (con!=null) con.close();
            return false;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}