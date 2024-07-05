package lk.ijse.teleleavesbilling.repository;

import lk.ijse.teleleavesbilling.db.DBConnection;
import lk.ijse.teleleavesbilling.model.Collector;
import lk.ijse.teleleavesbilling.model.Items;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsRepo {

        public static boolean save(Items items) throws SQLException {

            String sql = "INSERT INTO Items VALUES(?, ?, ?, ?)";
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

            pstm.setObject(1, items.getI_ID());
            pstm.setObject(2, items.getDescription());
            pstm.setObject(3, items.getPrice());


            return pstm.executeUpdate() > 0;


        }

        public static boolean delete(String i_id) throws SQLException {
            String sql = "DELETE FROM Items WHERE I_ID = ?";
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

            pstm.setObject(1, i_id);

            return pstm.executeUpdate() > 0;
        }

        public static Items searchByI_ID(String i_id) throws SQLException {
            String sql = "SELECT * FROM Items WHERE I_ID = ?";
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

            pstm.setObject(1, i_id);
            ResultSet resultSet = pstm.executeQuery();

            Items items = null;

            if (resultSet.next()) {
                String iid = resultSet.getString(1);
                String description = resultSet.getString(2);
                String price = resultSet.getString(3);


                items = new Items(iid, description, price);
            }
            return items;
        }

        public static List<Items> getAll() throws SQLException {
            String sql = "SELECT * FROM Items";

            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();

            List<Items> itemsList = new ArrayList<>();
            while (resultSet.next()) {
                String i_id = resultSet.getString(1);
                String description = resultSet.getString(2);
                String price = resultSet.getString(3);


                Items items = new Items(i_id, description, price);
                itemsList.add(items);
            }
            return itemsList;
        }

    public static boolean update(Items items) throws SQLException {
        String sql = "UPDATE Items SET Description = ?, Price = ?WHERE I_ID = ?";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, items.getI_ID());
        pstm.setObject(2, items.getDescription());
        pstm.setObject(3, items.getPrice());


        return pstm.executeUpdate() > 0;
    }
}
