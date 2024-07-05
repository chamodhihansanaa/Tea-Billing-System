package lk.ijse.teleleavesbilling.repository;

import lk.ijse.teleleavesbilling.db.DBConnection;
import lk.ijse.teleleavesbilling.model.Collector;
import lk.ijse.teleleavesbilling.model.Delivery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRepo {
    public static boolean save(Delivery delivery) throws SQLException {

        String sql = "INSERT INTO Delivery VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, delivery.getCode());
        pstm.setObject(2, delivery.getPrice());
        pstm.setObject(3, delivery.getDate());
        pstm.setObject(4, delivery.getAddress());

        return pstm.executeUpdate() > 0;


    }
    public static boolean update(Delivery delivery) throws SQLException {
        String sql = "UPDATE delivery SET Code = ?, Price = ?, Date = ? WHERE Address = ?";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, delivery.getCode());
        pstm.setObject(2, delivery.getPrice());
        pstm.setObject(3, delivery.getDate());
        pstm.setObject(4, delivery.getAddress());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String code) throws SQLException {
        String sql = "DELETE FROM Delivery WHERE Code = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, code);

        return pstm.executeUpdate() > 0;
    }

    public static Delivery searchByCode(String code) throws SQLException {
        String sql = "SELECT * FROM Delivery WHERE Code = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, code);
        ResultSet resultSet = pstm.executeQuery();

        Delivery delivery = null;

        if (resultSet.next()) {
            String Delcode = resultSet.getString(1);
            String price = resultSet.getString(2);
            String date = resultSet.getString(3);
            String address = resultSet.getString(4);

            delivery = new Delivery(code, price, date, address);
        }
        return delivery;
    }

    public static List<Delivery> getAll() throws SQLException {
        String sql = "SELECT * FROM Delivery";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Delivery> deliveryList = new ArrayList<>();
        while (resultSet.next()) {
            String code = resultSet.getString(1);
            String price = resultSet.getString(2);
            String date = resultSet.getString(3);
            String address = resultSet.getString(4);

            Delivery delivery = new Delivery(code, price, date, address);
            deliveryList.add(delivery);
        }
        return deliveryList;
    }
}
