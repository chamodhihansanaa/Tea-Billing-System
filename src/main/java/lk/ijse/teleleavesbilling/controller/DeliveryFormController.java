package lk.ijse.teleleavesbilling.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.teleleavesbilling.Tm.DeliveryTm;
import lk.ijse.teleleavesbilling.model.Delivery;
import lk.ijse.teleleavesbilling.repository.DeliveryRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryFormController {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtPrice;

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
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableView<DeliveryTm> tblDelivery;
    private List<Delivery> deliveryList = new ArrayList<>();

    private List<Delivery>getAllCollectors =new ArrayList<>();

    public void initialize() {
        this.getAllCollectors=getAllCollectors();
        this.deliveryList = getAllDelivery();
        setCellValueFactory();
        loadDeliveryTable();
        getAllDelivery();
    }

    private List<Delivery> getAllDelivery() {
        List<Delivery> deliveryList = null;
        try {
            deliveryList = DeliveryRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deliveryList;

    }

    private List<Delivery> getAllCollectors() {
        List<Delivery> deliveryList = null;
        try {
            deliveryList = DeliveryRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deliveryList;
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
    }

    private void loadDeliveryTable() {
        ObservableList<DeliveryTm> tmList = FXCollections.observableArrayList();

        for (Delivery delivery : deliveryList) {
             DeliveryTm deliveryTm = new DeliveryTm(
                    delivery.getCode(),
                    delivery.getPrice(),
                    delivery.getDate(),
                    delivery.getAddress()
            );

            tmList.add(deliveryTm);
        }
        tblDelivery.setItems(tmList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtCode.setText("");
        txtPrice.setText("");
        txtDate.setText("");
        txtAddress.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String code = txtCode.getText();

        try {
            boolean isDeleted = DeliveryRepo.delete(code);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String price = txtPrice.getText();
        String date = txtDate.getText();
        String address = txtAddress.getText();

        Delivery delivery = new Delivery(code, price,date,address);

        try {
            boolean isSaved = DeliveryRepo.save(delivery);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String code = txtCode.getText();

        try {
            Delivery delivery = DeliveryRepo.searchByCode(code);

            if (delivery != null) {
                txtCode.setText(delivery.getCode());
                txtPrice.setText(delivery.getPrice());
                txtDate.setText(delivery.getDate());
                txtAddress.setText(delivery.getAddress());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String price= txtPrice.getText();
        String date = txtDate.getText();
        String address = txtAddress.getText();

        Delivery delivery = new Delivery(code, price,date,address);

        try {
            boolean isUpdated = DeliveryRepo.update(delivery);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void selectDelivery(MouseEvent mouseEvent) throws SQLException{
        DeliveryTm selectedTm =tblDelivery.getSelectionModel().getSelectedItem();
        txtCode.setText(selectedTm.getCode());
        txtPrice.setText(selectedTm.getPrice());
        txtDate.setText(selectedTm.getDate());
        txtAddress.setText(selectedTm.getAddress());
    }
}
