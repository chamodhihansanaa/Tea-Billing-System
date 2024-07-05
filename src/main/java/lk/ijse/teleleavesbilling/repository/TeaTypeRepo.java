package lk.ijse.teleleavesbilling.repository;

import lk.ijse.teleleavesbilling.db.DBConnection;
import lk.ijse.teleleavesbilling.model.Collector;
import lk.ijse.teleleavesbilling.model.TeaType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeaTypeRepo {

    public static boolean update(TeaType teaType) throws SQLException {

        String sql = "UPDATE TeaType SET Tea_ID = ?, Price = ?, Tea_Category = ? WHERE Tea_ID= ?";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, teaType.getTea_ID());
        pstm.setObject(2, teaType.getPrice());
        pstm.setObject(3, teaType.getTea_Category());




        return pstm.executeUpdate() > 0;
    }

    public static boolean save(TeaType teatype) throws SQLException {
        String sql = "INSERT INTO Collector VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, teatype.getTea_ID());
        pstm.setObject(2, teatype.getPrice());
        pstm.setObject(3, teatype.getTea_Category());

        return pstm.executeUpdate() > 0;

    }

    public static boolean delete(String teaid) throws SQLException {

        String sql = "DELETE FROM TeaType WHERE Tea_ID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, teaid);

        return pstm.executeUpdate() > 0;
    }

    public static TeaType searchByTeaID(String teaid) throws SQLException {


        String sql = "SELECT * FROM TeaType WHERE Tea_ID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, teaid);
        ResultSet resultSet = pstm.executeQuery();

        TeaType teatype = null;

        if (resultSet.next()) {
            String tea_id = resultSet.getString(1);
            String price = resultSet.getString(2);
            String tea_category = resultSet.getString(3);


            teatype = new TeaType(tea_id, price, tea_category);
        }
        return teatype;
    }


    public static List<TeaType> getAll() throws SQLException {
        String sql = "SELECT * FROM TeaType";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<TeaType> teaTypeList = new ArrayList<>();
        while (resultSet.next()) {
            String tea_id = resultSet.getString(1);
            String price = resultSet.getString(2);
            String tea_category = resultSet.getString(3);


            TeaType teatype = new TeaType(tea_id, price, tea_category);
            teaTypeList.add(teatype);
        }
        return teaTypeList;
    }}