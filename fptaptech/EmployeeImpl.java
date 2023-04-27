package vn.fptaptech;

import jdbcdemo.MySQLConnection;

import java.net.IDN;
import java.sql.*;


public class EmployeeImpl implements EmployeeDAO{

    Employee e = new Employee();

    @Override
    public Employee getEmployeeById(String ID) throws SQLException {
        Connection conn = MySQLConnection.getMySQLConnection();
        String query = "Select * from employee where id = " + ID;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String id = rs.getString(1);
            e.setID(id);//is co gtri
            String name = rs.getString(2);
            e.setName(name);
            String sal = rs.getString(3);
            e.setSalary(sal);
            System.out.println("______Employee Data______");
            System.out.println("==========================");
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Salary: " + sal);

        }
        return null;
    }

    @Override
    public void getAllEmployee() throws SQLException {
        Connection conn = MySQLConnection.getMySQLConnection();
        String query = "Select * from employee";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String id = rs.getString(1);
            e.setID(id);
            String name = rs.getString(2);
            e.setName(name);
            String sal = rs.getString(3);
            e.setSalary(sal);
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Salary: " + sal + "$");
            System.out.println("<---------->");
        }
    }

    @Override
    public Employee addEmployee(Employee e) throws SQLException {
        Connection conn = MySQLConnection.getMySQLConnection();
        PreparedStatement pstm = conn.prepareStatement("INSERT INTO Employee VALUES(?, ?, ?)");
        pstm.setString(1, e.getID());
        pstm.setString(2, e.getName());
        pstm.setString(3, e.getSalary());
        int result = pstm.executeUpdate();
        if (result > 0) {
            System.out.println(" Employee added successfully!");

        }else
            System.out.println("Failed to add!");
        return null;
    }

    @Override
    public Employee deleteEmployee(String ID) throws SQLException {
        Connection conn = MySQLConnection.getMySQLConnection();
        PreparedStatement pstm = conn.prepareStatement("DELETE FROM Employee where ID = ?");
        pstm.setString(1, ID);
        int i = pstm.executeUpdate();
        if (i > 0) {
            System.out.println("Delete successfully!");
        }else
            System.out.println("Failed to delete!");

        return null;
    }

    @Override
    public Employee updateEmployee(String ID, String name) throws SQLException {
        Connection conn = MySQLConnection.getMySQLConnection();
        PreparedStatement pstm = conn.prepareStatement("UPDATE Employee SET Name = ? WHERE ID = ?");
        pstm.setString(1, name);
        pstm.setString(2, ID);

        int i = pstm.executeUpdate();
        if (i > 0){
            System.out.println("Updated successfully!");
        }else
            System.out.println("Failed to update!");

        return null;
    }

}
