package lk.ijse.teleleavesbilling.repository;

import lk.ijse.teleleavesbilling.db.DBConnection;
import lk.ijse.teleleavesbilling.model.Delivery;
import lk.ijse.teleleavesbilling.model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static boolean save(Employee employee) throws SQLException {
//        In here you can now save your customer
        String sql = "INSERT INTO Employee VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, employee.getEMP_ID());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getSalary());
        pstm.setObject(4, employee.getDate());

        return pstm.executeUpdate() > 0;


    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET Name = ?, Salary = ?, Date = ? WHERE Emp_ID = ?";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getSalary());
        pstm.setObject(3, employee.getDate());
        pstm.setObject(4, employee.getEMP_ID());

        return pstm.executeUpdate() > 0;
    }


    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE Emp_ID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }


    public static Employee searchByEMP_ID(String id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE Emp_ID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Employee employee = null;

        if (resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String salary = resultSet.getString(3);
            String date = resultSet.getString(4);

            employee = new Employee(emp_id, name, salary, date);
        }
        return employee;
    }

    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String salary = resultSet.getString(3);
            String date = resultSet.getString(4);

            Employee employee = new Employee(emp_id, name, salary, date);
            employeeList.add(employee);
        }
        return employeeList;
    }
}
