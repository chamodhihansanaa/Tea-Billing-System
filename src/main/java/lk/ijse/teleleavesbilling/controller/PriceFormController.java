package lk.ijse.teleleavesbilling.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.teleleavesbilling.Tm.PaymentTm;
import lk.ijse.teleleavesbilling.Tm.PriceTm;
import lk.ijse.teleleavesbilling.model.Collector;
import lk.ijse.teleleavesbilling.model.Orders;
import lk.ijse.teleleavesbilling.model.Payment;
import lk.ijse.teleleavesbilling.model.Price;
import lk.ijse.teleleavesbilling.repository.OrdersRepo;
import lk.ijse.teleleavesbilling.repository.PaymentRepo;
import lk.ijse.teleleavesbilling.repository.PriceRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriceFormController {

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
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colKGPrice;

    @FXML
    private TableView<PriceTm> tblPrice;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtKGPrice;

    @FXML
    private TextField txtSearch;
    private List<Price> priceList = new ArrayList<>();


    public void initialize() {
        this.priceList = getAllPrice();
        setCellValueFactory();
        loadPriceTable();
    }

    private void setCellValueFactory() {
        colCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        colKGPrice.setCellValueFactory(new PropertyValueFactory<>("KGPrice"));

    }

    private void loadPriceTable() {
        ObservableList<PriceTm> tmList = FXCollections.observableArrayList();

        for (Price price : priceList) {
            PriceTm priceTm= new PriceTm(
                    price.getCategory(),
                    price.getDuration(),
                   price.getKG_Price()

            );

            tmList.add(priceTm);
        }
        tblPrice.setItems(tmList);
        PriceTm selectedItem = tblPrice.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }


    private List<Price> getAllPrice() {
        List<Price> pricesList = null;
        try {
           pricesList = PriceRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pricesList;

    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtDuration.setText("");
        txtCategory.setText("");
        txtKGPrice.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String duration = txtDuration.getText();

        boolean isDeleted = PriceRepo.delete(duration);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String duration = txtDuration.getText();
        String category = txtCategory.getText();
        String kgprice = txtKGPrice.getText();


        Price price = new Price(duration, category, kgprice);

        boolean isSaved = PriceRepo.save(price);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String duration = txtDuration.getText();
        String category = txtCategory.getText();
        String kgprice = txtKGPrice.getText();


        Price price = new Price(duration, category, kgprice);

        boolean isUpdated = PriceRepo.update(price);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
        }
    }

    public void selectedPrice(MouseEvent mouseEvent) throws SQLException{
        PriceTm selectedTm = tblPrice.getSelectionModel().getSelectedItem();
        txtDuration.setText(selectedTm.getDuration());
        txtCategory.setText(selectedTm.getCategory());
        txtKGPrice.setText(selectedTm.getKG_Price());
    }
}
