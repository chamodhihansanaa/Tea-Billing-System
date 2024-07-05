package lk.ijse.teleleavesbilling.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.teleleavesbilling.Tm.CollectorTm;
import lk.ijse.teleleavesbilling.model.Collector;
import lk.ijse.teleleavesbilling.repository.CollectorRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollectorFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCollect_ID;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<CollectorTm> tblCollector;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    private List<Collector> collectorList = new ArrayList<>();

    public void initialize() {
        this.collectorList = getAllCollectors();
        setCellValueFactory();
        loadCollectorTable();
    }

    private List<Collector> getAllCollectors() {
        List<Collector> collectorList = null;
        try {
            collectorList = CollectorRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return collectorList;
    }

    private void setCellValueFactory() {
        colCollect_ID.setCellValueFactory(new PropertyValueFactory<>("Collect_ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
    }

    private void loadCollectorTable() {
        ObservableList<CollectorTm> tmList = FXCollections.observableArrayList();

        for (Collector collector : collectorList) {
            CollectorTm collectorTm = new CollectorTm(
                    collector.getCollect_ID(),
                    collector.getName(),
                    collector.getAddress(),
                    collector.getContactNo()
            );

            tmList.add(collectorTm);
        }
        tblCollector.setItems(tmList);
        CollectorTm selectedItem = tblCollector.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContactNo.setText("");
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String collect_id = txtID.getText();

        try {
            boolean isDeleted = CollectorRepo.delete(collect_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String collect_id = txtID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contactno = txtContactNo.getText();

        Collector collector = new Collector(collect_id, name, address, contactno);

        try {
            System.out.println(collector.toString());
            boolean isSaved = CollectorRepo.save(collector);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String collect_id = txtID.getText();

        try {
            Collector collector = CollectorRepo.searchByCollect_ID(collect_id);

            if (collector != null) {
                txtID.setText(collector.getCollect_ID());
                txtName.setText(collector.getName());
                txtAddress.setText(collector.getAddress());
                txtContactNo.setText(collector.getContactNo());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String collect_id = txtID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contactno = txtContactNo.getText();

        Collector collector = new Collector(collect_id, name, address, contactno);

        try {
            boolean isUpdated = CollectorRepo.update(collector);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void selectCollectors(MouseEvent mouseEvent) throws SQLException{
        CollectorTm selectedTm = tblCollector.getSelectionModel().getSelectedItem();
        txtID.setText(selectedTm.getCollect_ID());
        txtName.setText(selectedTm.getName());
        txtAddress.setText(selectedTm.getAddress());
        txtContactNo.setText(selectedTm.getContactNo());
    }
}
