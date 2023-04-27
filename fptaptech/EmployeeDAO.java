package vn.fptaptech;

import java.sql.SQLException;

//defilne all the function
public interface EmployeeDAO {
    //trả về 1 nhân viên
    public Employee getEmployeeById(String ID) throws SQLException;
    //trả về toàn bộ 1 ds nhân viên
    //public ArrayList<Employee> getAllEmployee();
    public void getAllEmployee() throws SQLException;
    public Employee addEmployee(Employee e) throws SQLException;
    public Employee deleteEmployee(String ID) throws SQLException;
    public Employee updateEmployee(String ID, String name) throws SQLException;
}
