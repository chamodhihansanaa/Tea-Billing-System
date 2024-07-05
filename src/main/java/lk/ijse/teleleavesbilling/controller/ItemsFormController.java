package lk.ijse.teleleavesbilling.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.teleleavesbilling.Tm.EmployeeTm;
import lk.ijse.teleleavesbilling.Tm.ItemsTm;
import lk.ijse.teleleavesbilling.model.Collector;
import lk.ijse.teleleavesbilling.model.Employee;
import lk.ijse.teleleavesbilling.model.Items;
import lk.ijse.teleleavesbilling.repository.EmployeeRepo;
import lk.ijse.teleleavesbilling.repository.ItemsRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsFormController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItem_ID;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableView<ItemsTm> tblItems;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSearch;
    private List<Items> itemsList = new ArrayList<>();

    public void initialize() {
        this.itemsList = getAllItems();
        setCellValueFactory();
        loadItemsTable();
    }

    private List<Items> getAllItems() {
        List<Items> employeeList = null;
        try {
            itemsList = ItemsRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemsList;
    }



    private void setCellValueFactory() {
        colItem_ID.setCellValueFactory(new PropertyValueFactory<>("Item_ID"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

    }

    private void loadItemsTable() {
        ObservableList<ItemsTm> tmList = FXCollections.observableArrayList();

        for (Items items : itemsList) {
           ItemsTm itemsTm = new ItemsTm(
                   items.getI_ID(),
                   items.getDescription(),
                   items.getPrice()

            );

            tmList.add(itemsTm);
        }
        tblItems.setItems(tmList);
        ItemsTm selectedItem = tblItems.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }




    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtItemID.setText("");
        txtDescription.setText("");
        txtPrice.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String itemid = txtItemID.getText();

        try {
            boolean isDeleted = ItemsRepo.delete(itemid);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

            String itemid = txtItemID.getText();
            String description = txtDescription.getText();
            String price = txtPrice.getText();


            Items items = new Items(itemid, description, price);

            try {
                boolean isSaved = ItemsRepo.save(items);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String itemid = txtItemID.getText();

        try {
            Items items = ItemsRepo.searchByI_ID(itemid);

            if (items != null) {
                txtItemID.setText(items.getI_ID());
                txtDescription.setText(items.getDescription());
                txtPrice.setText(items.getPrice());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String itemid = txtItemID.getText();
        String description = txtDescription.getText();
        String price = txtPrice.getText();


        Items items = new Items(itemid, description, price);

        try {
            boolean isUpdated = ItemsRepo.update(items);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void selectedItems(MouseEvent mouseEvent)throws SQLException {
        ItemsTm selectedTm =tblItems.getSelectionModel().getSelectedItem();
        txtItemID.setText(selectedTm.getI_ID());
        txtDescription.setText(selectedTm.getDescription());
        txtPrice.setText(selectedTm.getDescription());
    }
}
