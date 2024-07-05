package lk.ijse.teleleavesbilling.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.teleleavesbilling.Tm.CollectorTm;
import lk.ijse.teleleavesbilling.Tm.TeaSupplierTm;
import lk.ijse.teleleavesbilling.Tm.TeaTypeTm;
import lk.ijse.teleleavesbilling.model.*;
import lk.ijse.teleleavesbilling.repository.CollectorRepo;
import lk.ijse.teleleavesbilling.repository.EmployeeRepo;
import lk.ijse.teleleavesbilling.repository.PriceRepo;
import lk.ijse.teleleavesbilling.repository.TeaTypeRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeaTypeFormController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colTeaID;

    @FXML
    private TableView<TeaTypeTm> tblTeaType;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtTeaID;
    private  List<TeaType> teatypeList = new ArrayList<>();


    public void initialize() {
         this.teatypeList = getAllTeaType();
        setCellValueFactory();
        loadTeaTypeTable();
    }

    private void setCellValueFactory() {
        colTeaID.setCellValueFactory(new PropertyValueFactory<>("TeaID"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
    }

    private void loadTeaTypeTable() {
        ObservableList<TeaTypeTm> tmList = FXCollections.observableArrayList();

        for (TeaType teaType : teatypeList) {
            TeaTypeTm teaTypeTm = new TeaTypeTm(
                   teaType.getTea_ID(),
                    teaType.getPrice(),
                    teaType.getTea_Category()

            );

            tmList.add(teaTypeTm);
        }
        tblTeaType.setItems(tmList);
        TeaTypeTm selectedItem = tblTeaType.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }


    private List<TeaType> getAllTeaType()  {
        List<TeaType> teaTypeList = null;
        try {
           teaTypeList = TeaTypeRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teaTypeList;


    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtTeaID.setText("");
        txtCategory.setText("");
        txtPrice.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String teaid = txtTeaID.getText();

        boolean isDeleted = TeaTypeRepo. delete(teaid);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String teaid = txtTeaID.getText();
        String category = txtCategory.getText();
        String price = txtPrice.getText();


        TeaType teatype = new TeaType(teaid, category, price);

        boolean isSaved = TeaTypeRepo.save(teatype);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException {
        String teaid = txtTeaID.getText();

        TeaType teatype = TeaTypeRepo.searchByTeaID(teaid);
        if (teatype!= null) {
            txtTeaID.setText(teatype.getTea_ID());
            txtCategory.setText(teatype.getTea_Category());
            txtPrice.setText(teatype.getPrice());

        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String teaid = txtTeaID.getText();
        String category = txtCategory.getText();
        String price = txtPrice.getText();


        TeaType teatype = new TeaType(teaid, category, price);

        boolean isUpdated = TeaTypeRepo.update(teatype);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
        }
    }

    public void selectedTeaType(MouseEvent mouseEvent) throws SQLException{
        TeaTypeTm selectedTm =tblTeaType.getSelectionModel().getSelectedItem();
        txtTeaID.setText(selectedTm.getTea_ID());
        txtCategory.setText(selectedTm.getTea_Category());
        txtPrice.setText(selectedTm.getPrice());
    }
}
