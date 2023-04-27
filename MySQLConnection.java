package jdbcdemo;

import javax.swing.*;
import java.sql.*;
import java.util.Scanner;
import java.util.Stack;

public class MySQLConnection {
    public static Connection getMySQLConnection() throws SQLException {
        String hostname = "localhost";
        String dbName = "employeedb";
        String username = "root";
        String password = "";
        return getMySQLConnection(hostname, dbName, username, password);
    }
    public static Connection getMySQLConnection(String hostname, String dbName, String username, String password)
            throws SQLException {
        //ex: jdbc:mysql://localhost:3306/employeedb
        String connectionURL = "jdbc:mysql://" + hostname + ":3306/" + dbName;
        Connection connection = DriverManager.getConnection(connectionURL, username, password);
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        if (getMySQLConnection()!=null){
            System.out.println("Ket noi DB thanh cong!");
            //createDataVsStatement();
            //getDataVsStatement();
            //getDataVsPrepareStatement();
            /*final Scanner sc = new Scanner(System.in);
            System.out.println("Username: ");
            String user = sc.nextLine();
            System.out.println("Password: ");
            String pass = sc.nextLine();
            loginStatement(user,pass);*/
            //loginPrepareStatement("tester", "123");
            //crudMySQLdb();
            createEmployeeTable();
        }
    }
    //load data from database with: Statement
    public static void getDataVsStatement() throws SQLException {
        //1, tao dtg connection
        Connection connection = getMySQLConnection();
        //2, Tao dtg statement=> thực hiện các thao tác với database :CRUD
        Statement statement = connection.createStatement();
        //3, query string:câu lệnh truy vấn với CSDL
        String query = "select * from employee";
        //4, thực thi  lệnh truy vấn
        //tạo đtg ResultSet để nhận kết quả trả về từ database
        ResultSet resultSet = statement.executeQuery(query);;

        //5, duyệt (fetch) toàn bộ bản ghi có trong resultSet
        while (resultSet.next()){
            int empId = resultSet.getInt(1);
            String empName = resultSet.getString(2);
            String address = resultSet.getString(3);
            System.out.println("===================");
            System.out.println("Employee ID: " + empId);
            System.out.println("Employee Name: " + empName);
            System.out.println("Địa chỉ: " + address);
        }
        connection.close();
    }

    public static void createDataVsStatement() throws SQLException {
        //1, kết nối csdl
        Connection connection = getMySQLConnection();
        //2, tạo statement
        Statement statement = connection.createStatement();
        //3, truy vấn
        String query = "insert into employee(emp_id, emp_name, address) values(6,'Vu Duc Dam', 'Ha Noi')";
        //thực thi (execute statement) câu lệnh statement
        //để thực hiện insert, update, delete thì sd executeUpdate()
        int count = statement.executeUpdate(query);
        System.out.println("Count: " + count);
    }
    //load data from database with prepareStatement=> security (antoàn hơn)
    public static void getDataVsPrepareStatement() throws SQLException {
        //1, tạo kết nối
        Connection connection = getMySQLConnection();
        //2,
        String query = "select emp_id, emp_name, address from employee where emp_name like ? or emp_id = ?";
        //3,tạo prepareStatement
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //4,gán gtri cho tham số (parameter) của preparedStatement
        preparedStatement.setString(1,"Vu Duc Dam");
        preparedStatement.setInt(2, 2);
        //5, Thực thi
        ResultSet resultSet = preparedStatement.executeQuery();//executeQuery: chỉ đọc
        while (resultSet.next()){
            System.out.println("==========");
            System.out.println("EmpId: " + resultSet.getInt(1));
            System.out.println("Emp_name: " + resultSet.getString("emp_name"));
            System.out.println("Address: " + resultSet.getString("address"));
        }
    }
    //để tránh SQL Injection thì sd:
    //1. PrepareStatement
    //2. CallableStatement(gọi thủ tục lưu trữ Store Proceducre)
    public static void loginStatement(String username, String password) throws SQLException {
        //1. Tạo kết nối
        Connection connection = getMySQLConnection();
        //2. tạo query
        String query = "select username " +
                "from users where username like '"+username+"'"+
                "and password like '"+password + "'";
        //3. tạo Statement
        Statement statement = connection.createStatement();
        //4. Khởi tại ResulSet
        ResultSet resultSet = statement.executeQuery(query);
        //5. Fetch data : doc dl
        while (resultSet.next()) {
            System.out.println("Username is: " +resultSet.getString("username"));
            }

    }
    public static void loginPrepareStatement(String username, String password) throws SQLException {
        Connection connection = getMySQLConnection();
        String query = "select username from users where username like ? and password like ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            System.out.println("Username is: " + resultSet.getString("username"));
            }
    }
    public static void crudMySQLdb() throws SQLException {
        Connection connection = getMySQLConnection();
        //CREATE TABLE
        // with Statement
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS PRODUCT ");
        statement.execute("CREATE TABLE PRODUCT(id int primary key, proname varchar(20), description varchar(20))");
        //INSERT DATA DEMO
        statement.execute("INSERT INTO product VALUES(1,'iphone', 'Apple')");
        //SELECT DATA WITH Statement
        ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
        while (resultSet.next()){
            System.out.println("Product name: " + resultSet.getString("proname"));
        }
        //with PrepareStatement
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM product where proname like ? or id = ?");
        pstm.setString(1, "iphone");
        pstm.setInt(2, 3);
        resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id"));
            System.out.println("Product name is: " + resultSet.getString("proname"));
        }
    }
    public static void createEmployeeTable() throws SQLException {
        Connection connection = getMySQLConnection();
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS Employee ");
        statement.execute("CREATE TABLE Employee (id varchar(10) primary key, name varchar(20), Salary varchar(20))");
        statement.execute("INSERT INTO Employee VALUES(1,'Chuyen', '1000')");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee");
        while (resultSet.next()){
            System.out.println("Employee name: " + resultSet.getString("name"));
        }


    }

}