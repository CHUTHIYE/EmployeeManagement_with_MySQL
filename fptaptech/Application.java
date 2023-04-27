package vn.fptaptech;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Application {
    public static void main(String[] args) throws SQLException {
        int choice,num;
        EmployeeImpl dao = new EmployeeImpl();
        Employee e ;//defaul null
        do {
            System.out.println("<=====Menu=====>");
            System.out.println("1. Get Employee by ID");
            System.out.println("2. Add Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Get all Employee");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter the ID: ");
                    String id1 = sc.next();
                    dao.getEmployeeById(id1);
                    break;
                case 2:
                    System.out.println("Enter Employee ID: ");
                    String id2 = sc.next();
                    System.out.println("Enter Employee name: ");
                    String name = sc.next();
                    System.out.println("Enter Salary: ");
                    String salary = sc.next();
                    Employee e1 = new Employee(id2, name, salary);
                    dao.addEmployee(e1);
                    break;
                case 3:
                    System.out.println("Enter the ID to change: ");
                    String id3 = sc.next();
                    System.out.println("Enter the new Name: ");
                    String name1 = sc.next();
                    dao.updateEmployee(id3, name1);
                    break;
                case 4:
                    System.out.println("Enter the ID to delete: ");
                    String id4 = sc.next();
                    dao.deleteEmployee(id4);
                    break;
                case 5:
                    System.out.println("All Employee");
                    System.out.println("--------------------------");
                    dao.getAllEmployee();

                    break;
                case 6:
                    exit(0);

                default:
                    System.out.println("Wrong input!");
                    break;
            }
            System.out.println("Enter 6 to continue...");
            num = sc.nextInt();
        }while (num == 6);
    }
}
