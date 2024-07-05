package lk.ijse.teleleavesbilling.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import lk.ijse.teleleavesbilling.util.Navigation;

import java.io.IOException;

public class DashBoardController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCollector;

    @FXML
    private Button btnDelivery;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnItems;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnPrice;

    @FXML
    private Button btnSupplier;

    @FXML
    private Button btnTeaType;


    @FXML
    private Pane paginPane;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException{
Navigation.switchPaging(paginPane,"DashBoard.fxml");
    }

    @FXML
    void btnCollectorOnAction(ActionEvent event) throws IOException {
       Navigation.switchPaging(paginPane,"Collector.fxml");
    }

    @FXML
    void btnDeliveryOnAction(ActionEvent event)throws IOException {
       Navigation.switchPaging(paginPane,"Delivery.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event)throws IOException {
          Navigation.switchPaging(paginPane,"Employee.fxml");
    }

    @FXML
    void btnItemsOnAction(ActionEvent event)throws IOException {
         Navigation.switchPaging(paginPane,"Items.fxml");
    }

    @FXML
    void btnOrdersOnAction(ActionEvent event) throws IOException{
       Navigation.switchPaging(paginPane,"Orders.fxml");
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException{
       Navigation.switchPaging(paginPane,"Payment.fxml");
    }

    @FXML
    void btnPriceOnAction(ActionEvent event) throws IOException{
         Navigation.switchPaging(paginPane,"Price.fxml");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event)throws IOException {
        Navigation.switchPaging(paginPane,"TeaSupplier.fxml");
    }

    @FXML
    void btnTeaTypeOnAction(ActionEvent event)throws IOException {
Navigation.switchPaging(paginPane,"TeaType.fxml");
    }

}
