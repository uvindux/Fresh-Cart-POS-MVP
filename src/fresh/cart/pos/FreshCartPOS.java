package fresh.cart.pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class FreshCartPOS {

    public static void main(String[] args) {
        try {
            String SQL = "Select * from customer";
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/thogakade";
            String username = "root";
            String password = ""; // leave empty if no password in XAMPP
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stm = conn.createStatement();
            ResultSet rst=stm.executeQuery(SQL);
            while (rst.next()){
                String id=rst.getString("id");
                String name  =rst.getString("name");
                String address=rst.getString("address");
                double salary=rst.getDouble("salary");
                System.out.println(id+"\t"+name+"\t"+address+"\t"+salary);
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver S/W not found");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
}
