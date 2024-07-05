package lk.ijse.teleleavesbilling.repository;

import lk.ijse.teleleavesbilling.db.DBConnection;
import lk.ijse.teleleavesbilling.model.Collector;
import lk.ijse.teleleavesbilling.model.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepo {
    public static boolean save(Orders orders) throws SQLException {

        String sql = "INSERT INTO Orders VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, orders.getOR_ID());
        pstm.setObject(2, orders.getDate());
        pstm.setObject(3, orders.getAddress());
        pstm.setObject(4, orders.getQuantity());

        return pstm.executeUpdate() > 0;


    }
    public static boolean update(Orders orders) throws SQLException {
        String sql = "UPDATE Orders SET OR_ID = ?, Date = ?, Address = ? WHERE Quantity = ?";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, orders.getOR_ID());
        pstm.setObject(2, orders.getDate());
        pstm.setObject(3, orders.getAddress());
        pstm.setObject(4, orders.getQuantity());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String or_id) throws SQLException {
        String sql = "DELETE FROM Orders WHERE OR_ID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, or_id);

        return pstm.executeUpdate() > 0;
    }

    public static Orders searchByOrderID(String or_id) throws SQLException {
        String sql = "SELECT * FROM Orders WHERE OR_ID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, or_id);
        ResultSet resultSet = pstm.executeQuery();

        Orders orders = null;

        if (resultSet.next()) {
            String ordId = resultSet.getString(1);
            String date = resultSet.getString(2);
            String address = resultSet.getString(3);
            String qty = resultSet.getString(4);

            orders = new Orders(ordId, date, address, qty);
        }
        return orders;
    }

    public static List<Orders> getAll() throws SQLException {
        String sql = "SELECT * FROM Orders";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Orders> ordersList = new ArrayList<>();
        while (resultSet.next()) {
            String or_id = resultSet.getString(1);
            String date = resultSet.getString(2);
            String address = resultSet.getString(3);
            String quantity = resultSet.getString(4);

            Orders orders = new Orders(or_id, date, address, quantity);
            ordersList.add(orders);
        }
        return ordersList;
    }
}

