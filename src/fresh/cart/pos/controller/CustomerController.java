package fresh.cart.pos.controller;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import fresh.cart.pos.db.DbConnection;
import fresh.cart.pos.model.Customer;

public class CustomerController {

    public static boolean addCustomer(Customer customer) {
        String SQL = "INSERT INTO Customer (id, name,address ,salary) VALUES ('"
                + customer.getId() + "', '"
                + customer.getName() + "', '"
                + customer.getLocation() + "', '"
                + customer.getSalary() + "');";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();

            int res = stm.executeUpdate(SQL); // ✅ execute the insert query
            return res > 0; // ✅ returns true if insert succeeded

        } catch (SQLException e) {
            e.printStackTrace(); // ✅ log error
            return false;
        }
    }

    public static int CustomerSearch(String id) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM Customer WHERE id = '" + id + "'";

        Connection connection = DbConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();

        ResultSet rst = stm.executeQuery(SQL);

        if (rst.next()) {
            return 1;
        } else {
            return 0;
        }

    }

    public static int CustomerDelete(String id) throws SQLException {
       if (DbConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM Customer WHERE id = '" + id + "'")>0){
           return 1;   
       }
       return 1;
    }
    
  public static boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        // SQL query to update customer
        String SQL = "UPDATE Customer SET name = ?, address = ?, salary = ? WHERE id = ?";

        // Get database connection
        Connection connection = DbConnection.getInstance().getConnection();

        // Prepare the statement
        PreparedStatement stm = connection.prepareStatement(SQL);

        // Set values
        stm.setObject(1, customer.getName());
        stm.setObject(2, customer.getLocation()); // make sure Customer has getAddress()
        stm.setObject(3, customer.getSalary());
        stm.setObject(4, customer.getId());

        // Execute update and return true if at least one row was updated
        return stm.executeUpdate() > 0;
    }

   
}

