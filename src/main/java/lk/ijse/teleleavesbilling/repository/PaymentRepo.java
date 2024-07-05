package lk.ijse.teleleavesbilling.repository;

import lk.ijse.teleleavesbilling.db.DBConnection;
import lk.ijse.teleleavesbilling.model.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {

    public static boolean update(Payment payment)throws SQLException {

    String sql = "UPDATE Payment SET Payment_ID = ?, Payment_method = ? WHERE Address = ?";

    PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, payment.getPayment_ID());
        pstm.setObject(2, payment.getPayment_method());
        pstm.setObject(3, payment.getAddress());


        return pstm.executeUpdate() > 0;
}
    public static boolean save(Payment payment) throws SQLException {

    String sql = "INSERT INTO Payment VALUES(?, ?, ?, ?)";
    PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

            pstm.setObject(1, payment.getPayment_ID());
                    pstm.setObject(2, payment.getPayment_method());
                    pstm.setObject(3, payment.getAddress());


                    return pstm.executeUpdate() > 0;

    }
    public static boolean delete(String payment_id) throws SQLException {

        String sql = "DELETE FROM Payment WHERE Payment_ID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, payment_id);

        return pstm.executeUpdate() > 0;
        }
public static Payment searchByPayment_ID(String payment_id) throws SQLException {
        String sql = "SELECT * FROM Payment WHERE Payment_ID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, payment_id);



        ResultSet resultSet = pstm.executeQuery();


        Payment payment = null;

        if (resultSet.next()) {
        String pay_id = resultSet.getString(1);
        String payment_method = resultSet.getString(2);
        String address = resultSet.getString(3);


        payment = new Payment(pay_id, payment_method, address);
        }
        return payment;
        }
public static List<Payment> getAll() throws SQLException {
        String sql = "SELECT * FROM Payment";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Payment> paymentList = new ArrayList<>();
        while (resultSet.next()) {
        String payment_id = resultSet.getString(1);
        String payment_method = resultSet.getString(2);
        String address = resultSet.getString(3);


        Payment payment = new Payment(payment_id, payment_method, address);
        paymentList.add(payment);
        }
        return paymentList;
        }
}
