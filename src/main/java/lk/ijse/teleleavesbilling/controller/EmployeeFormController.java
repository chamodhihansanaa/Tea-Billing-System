package lk.ijse.teleleavesbilling.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.teleleavesbilling.Tm.DeliveryTm;
import lk.ijse.teleleavesbilling.Tm.EmployeeTm;
import lk.ijse.teleleavesbilling.model.Delivery;
import lk.ijse.teleleavesbilling.model.Employee;
import lk.ijse.teleleavesbilling.repository.DeliveryRepo;
import lk.ijse.teleleavesbilling.repository.EmployeeRepo;

import java.sql.SQLException;
import java.util.List;

public class EmployeeFormController {


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
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEMP_ID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEMP_ID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSearch;

    private List<Employee> employeeList;

    public void initialize() throws SQLException {
        employeeList = getAllEmployee();
        setCellValueFactory();
        loadEmployeeTable();
    }

    private void loadEmployeeTable() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

        for (Employee employee : employeeList) {
            EmployeeTm employeeTm = new EmployeeTm(
                    employee.getEMP_ID(),
                    employee.getName(),
                    employee.getSalary(),
                    employee.getDate()
            );

            tmList.add(employeeTm);
        }
        tblEmployee.setItems(tmList);
        EmployeeTm selectedItem = tblEmployee.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }

    private void setCellValueFactory() {
        colEMP_ID.setCellValueFactory(new PropertyValueFactory<>("Emp_ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
    }

    private List<Employee> getAllEmployee() throws SQLException {
        List<Employee> employeeList = null;
        try {
            employeeList = EmployeeRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtEMP_ID.setText("");
        txtName.setText("");
        txtSalary.setText("");
        txtDate.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String emp_id = txtEMP_ID.getText();

        try {
            boolean isDeleted = EmployeeRepo.delete(emp_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String emp_id = txtEMP_ID.getText();
        String name = txtName.getText();
        String salary = txtSalary.getText();
        String date = txtDate.getText();

        Employee employee = new Employee(emp_id, name, salary, date);

        try {
            boolean isSaved = EmployeeRepo.save(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtEMP_ID.getText();

        try {
            Employee employee = EmployeeRepo.searchByEMP_ID(id);

            if (employee != null) {
                txtEMP_ID.setText(employee.getEMP_ID());
                txtName.setText(employee.getName());
                txtSalary.setText(employee.getSalary());
                txtDate.setText(employee.getDate());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String emp_id = txtEMP_ID.getText();
        String name = txtName.getText();
        String salary = txtSalary.getText();
        String date = txtDate.getText();

        Employee employee = new Employee(emp_id, name, salary, date);

        try {
            boolean isUpdated = EmployeeRepo.update(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void selectedEmployee(MouseEvent mouseEvent) throws SQLException{
        EmployeeTm selectedTm =tblEmployee.getSelectionModel().getSelectedItem();
        txtEMP_ID.setText(selectedTm.getEMP_ID());
        txtName.setText(selectedTm.getName());
        txtSalary.setText(selectedTm.getSalary());
        txtDate.setText(selectedTm.getDate());
    }
}
