package lk.ijse.teleleavesbilling.repository;

import lk.ijse.teleleavesbilling.db.DBConnection;
import lk.ijse.teleleavesbilling.model.TeaSupplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeaSupplierRepo {


    public static boolean delete(String tsId) throws SQLException {
        String sql = "DELETE FROM Tea_Supplier WHERE TS_ID= ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);


        pstm.setObject(1, tsId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(TeaSupplier teasupplier) throws SQLException {
        String sql = "INSERT INTO Tea_Supplier VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, teasupplier.getTS_ID());
        pstm.setObject(2, teasupplier.getName());
        pstm.setObject(3, teasupplier.getAddress());
        pstm.setObject(4, teasupplier.getContact());

        return pstm.executeUpdate() > 0;


    }


    public static boolean update(TeaSupplier teasupplier) throws SQLException {
        String sql = "UPDATE Tea_Supplier SET Name = ?, Address = ?, Contact = ? WHERE TS_ID = ?";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, teasupplier.getName());
        pstm.setObject(2, teasupplier.getAddress());
        pstm.setObject(3, teasupplier.getContact());
        pstm.setObject(4, teasupplier.getTS_ID());

        return pstm.executeUpdate() > 0;

    }
    public static TeaSupplier searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Tea_Supplier WHERE TS_ID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();

        TeaSupplier teaSupplier = null;

        if (resultSet.next()) {
            String ts_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact = resultSet.getString(4);

            teaSupplier = new TeaSupplier(ts_id, name, address, contact);
        }
        return teaSupplier;
    }
    public static List<TeaSupplier> getAll() throws SQLException {
        String sql = "SELECT * FROM Tea_Supplier";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<TeaSupplier> teaSupplierList = new ArrayList<>();
        while (resultSet.next()) {
            String ts_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact = resultSet.getString(4);

            TeaSupplier teasupplier = new TeaSupplier(ts_id, name, address, contact);
            teaSupplierList.add(teasupplier);
        }
return teaSupplierList;
    }


    public static List<String> getIds() throws SQLException {
        String sql = "SELECT TS_ID FROM Tea_Supplier";

        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
return idList;

    }}