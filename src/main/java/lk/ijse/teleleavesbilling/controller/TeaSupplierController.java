package lk.ijse.teleleavesbilling.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.teleleavesbilling.Tm.TeaSupplierTm;
import lk.ijse.teleleavesbilling.model.TeaSupplier;
import lk.ijse.teleleavesbilling.repository.TeaSupplierRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeaSupplierController {

    @FXML
    private TextField TEXT;

    @FXML
    private TextField txtTS_ADDRESS;

    @FXML
    private TextField txtTS_CONTACT;

    @FXML
    private TextField txtTS_ID;

    @FXML
    private TextField txtTS_NAME;

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
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTS_ID;
    @FXML
    public TableView<TeaSupplierTm> tblTeaSupplier;
    private List<TeaSupplier> teaSupplierList = new ArrayList<>();

    public void initialize() {
        this.teaSupplierList = getAllTeaSupplier();
        setCellValueFactory();
        loadTeaSupplierTable();
    }

    private void setCellValueFactory() {
        colTS_ID.setCellValueFactory(new PropertyValueFactory<>("TS_ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("Contact"));
    }

    private void loadTeaSupplierTable() {
        ObservableList<TeaSupplierTm> tmList = FXCollections.observableArrayList();

        for (TeaSupplier teaSupplier : teaSupplierList) {
            TeaSupplierTm teaSupplierTm = new TeaSupplierTm(
                    teaSupplier.getTS_ID(),
                    teaSupplier.getName(),
                    teaSupplier.getAddress(),
                    teaSupplier.getContact()
            );

            tmList.add(teaSupplierTm);
        }
        tblTeaSupplier.setItems(tmList);
        TeaSupplierTm selectedCollector = tblTeaSupplier.getSelectionModel().getSelectedItem();
        String selectedItem;
        System.out.println("selectedItem = " + "selectedItem");
    }

    private List<TeaSupplier> getAllTeaSupplier() {


        List<TeaSupplier> teaSupplierList = null;
        try {
            teaSupplierList = TeaSupplierRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teaSupplierList;
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtTS_ID.setText("");
        txtTS_NAME.setText("");
        txtTS_ADDRESS.setText("");
        txtTS_CONTACT.setText("");
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String tsid = txtTS_ID.getText();

        boolean isDeleted = TeaSupplierRepo.delete(tsid);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String tsid = txtTS_ID.getText();
        String name = txtTS_NAME.getText();
        String address = txtTS_ADDRESS.getText();
        String contact = txtTS_CONTACT.getText();

        TeaSupplier teasupplier = new TeaSupplier(tsid, name, address, contact);

        boolean isSaved = TeaSupplierRepo.save(teasupplier);
        System.out.printf("", teasupplier.toString());
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
        }


    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String tsid = txtTS_ID.getText();
        String name = txtTS_NAME.getText();
        String address = txtTS_ADDRESS.getText();
        String contact = txtTS_CONTACT.getText();

        TeaSupplier teasupplier = new TeaSupplier(tsid, name, address, contact);

        boolean isUpdated = TeaSupplierRepo.update(teasupplier);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
        }
    }

    public void selectedTeaSupplier(MouseEvent mouseEvent)throws SQLException {
        TeaSupplierTm selectedTm =tblTeaSupplier.getSelectionModel().getSelectedItem();
        txtTS_ID.setText(selectedTm.getTS_ID());
        txtTS_NAME.setText(selectedTm.getName());
        txtTS_ADDRESS.setText(selectedTm.getAddress());
        txtTS_CONTACT.setText(selectedTm.getContact());
    }
}




