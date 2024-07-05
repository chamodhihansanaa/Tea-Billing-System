package lk.ijse.teleleavesbilling.repository;

import lk.ijse.teleleavesbilling.db.DBConnection;
import lk.ijse.teleleavesbilling.model.Price;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriceRepo {


    private static String price;

    public static boolean delete(String price) throws SQLException {

        String sql = "DELETE FROM Price WHERE Duration = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);


        Object category;
        pstm.setObject(1, price);

        return pstm.executeUpdate() > 0;

    }

    public static boolean save(Price price) throws SQLException {

        String sql = "INSERT INTO Price VALUES(?, ?, ?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, price.getCategory());
        pstm.setObject(2, price.getDuration());
        pstm.setObject(3, price.getKG_Price());


        return pstm.executeUpdate() > 0;


    }

    public static boolean update(Price price) throws SQLException {

        String sql = "UPDATE price SET Category = ?, Duration = ?WHERE KG_Price = ?";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, price.getCategory());
        pstm.setObject(2, price.getDuration());
        pstm.setObject(3, price.getKG_Price());


        return pstm.executeUpdate() > 0;
    }

    public static Price searchByPrice(String price) throws SQLException {



        String sql = "SELECT * FROM Price WHERE Category= ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, price);
        ResultSet resultSet = pstm.executeQuery();

        Price price1 = null;

        if (resultSet.next()) {
            String category = resultSet.getString(1);
            String duration = resultSet.getString(2);
            String kg_price = resultSet.getString(3);


             price1 = new Price(category, duration, kg_price);
        }
        return price1;
    }


    public static List<Price> getAll() throws SQLException {
        String sql = "SELECT * FROM Price";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Price> priceList = new ArrayList<>();
        while (resultSet.next()) {
            String category = resultSet.getString(1);
            String duration = resultSet.getString(2);
            String kg_price = resultSet.getString(3);


           Price price = new Price(category, duration, kg_price);
           priceList.add(price);
        }
        return priceList;
    }
}
