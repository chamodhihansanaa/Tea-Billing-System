package lk.ijse.teleleavesbilling.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.teleleavesbilling.Tm.OrdersTm;
import lk.ijse.teleleavesbilling.Tm.PaymentTm;
import lk.ijse.teleleavesbilling.model.Collector;
import lk.ijse.teleleavesbilling.model.Items;
import lk.ijse.teleleavesbilling.model.Orders;
import lk.ijse.teleleavesbilling.model.Payment;
import lk.ijse.teleleavesbilling.repository.ItemsRepo;
import lk.ijse.teleleavesbilling.repository.OrdersRepo;
import lk.ijse.teleleavesbilling.repository.PaymentRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentFormController {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPayId;

    @FXML
    private TextField txtPayMethod;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colPaymentMethod;

    @FXML
    private TableColumn<?, ?> colPayment_ID;

    @FXML
    private TableView<PaymentTm> tblPayment;
    private List<Payment> paymentList = new ArrayList<>();

    public void initialize() {
        this.paymentList = getAllPayment();
        setCellValueFactory();
        loadPaymentTable();
    }

    private List<Payment> getAllPayment() {
        List<Payment> paymentList = null;
        try {
            paymentList = PaymentRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paymentList;
    }

    private void setCellValueFactory() {
        colPayment_ID.setCellValueFactory(new PropertyValueFactory<>("Payment_ID"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("PaymentMethod"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));

    }

    private void loadPaymentTable() {
        ObservableList<PaymentTm> tmList = FXCollections.observableArrayList();

        for (Payment payment : paymentList) {
           PaymentTm paymentTm= new PaymentTm(
                   payment.getPayment_ID(),
                    payment.getPayment_method(),
                    payment.getAddress()

            );

            tmList.add(paymentTm);
        }
        tblPayment.setItems(tmList);
        PaymentTm selectedItem = tblPayment.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }



    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtPayId.setText("");
        txtAddress.setText("");
        txtPayMethod.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String paymentid = txtPayId.getText();


        boolean isDeleted = PaymentRepo.delete(paymentid);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String paymentid = txtPayId.getText();
        String paymentmethod = txtPayMethod.getText();
        String address = txtAddress.getText();


        Payment payment = new Payment(paymentid, paymentmethod, address);

        boolean isSaved = PaymentRepo.save(payment);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String paymentid = txtPayId.getText();
        String paymentmethod = txtPayMethod.getText();
        String address = txtAddress.getText();


        Payment payment = new Payment(paymentid, paymentmethod, address);

        boolean isUpdated = PaymentRepo.update(payment);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
        }
    }

@FXML
    public void txtSearchOnAction(ActionEvent event) throws SQLException{
    String paymentid = txtPayId.getText();

    try {
       Payment payment= PaymentRepo.searchByPayment_ID(paymentid);

        if (payment != null) {
            txtPayId.setText(payment.getPayment_ID());
           txtPayMethod.setText(payment.getPayment_method());
            txtAddress.setText(payment.getAddress());

        }
    } catch (SQLException e) {
        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
    }
    }

    public void selectedPayment(MouseEvent mouseEvent) throws SQLException{
      PaymentTm selectedTm = tblPayment.getSelectionModel().getSelectedItem();
      txtPayId.setText(selectedTm.getPayment_ID());
      txtPayMethod.setText(selectedTm.getPayment_method());
      txtAddress.setText(selectedTm.getAddress());
    }
}

