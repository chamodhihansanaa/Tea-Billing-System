package lk.ijse.teleleavesbilling.repository;

import lk.ijse.teleleavesbilling.db.DBConnection;
import lk.ijse.teleleavesbilling.model.Collector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollectorRepo {
    public static boolean save(Collector collector) throws SQLException {

        String sql = "INSERT INTO Collector VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, collector.getCollect_ID());
        pstm.setObject(2, collector.getName());
        pstm.setObject(3, collector.getAddress());
        pstm.setObject(4, collector.getContactNo());

        return pstm.executeUpdate() > 0;


    }
    public static boolean update(Collector collector) throws SQLException {
        String sql = "UPDATE Collector SET Name = ?, Address = ?,Contact = ? WHERE Collect_ID = ?";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, collector.getName());
        pstm.setObject(2, collector.getAddress());
        pstm.setObject(3, collector.getContactNo());
        pstm.setObject(4, collector.getCollect_ID());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String collectId) throws SQLException {
        String sql = "DELETE FROM Collector WHERE Collect_ID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, collectId);

        return pstm.executeUpdate() > 0;
    }

    public static Collector searchByCollect_ID(String collectId) throws SQLException {
        String sql = "SELECT * FROM Collector WHERE Collector_ID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, collectId);
        ResultSet resultSet = pstm.executeQuery();

        Collector collector = null;

        if (resultSet.next()) {
            String collectorId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            collector = new Collector(collectId, name, address, tel);
        }
        return collector;
    }

    public static List<Collector> getAll() throws SQLException {
        String sql = "SELECT * FROM Collector";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Collector> collectorList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            Collector collector = new Collector(id, name, address, tel);
            collectorList.add(collector);
        }
        return collectorList;
    }
}

