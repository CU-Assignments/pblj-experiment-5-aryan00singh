import java.io.*;
import java.util.*;
public class EmployeeManagementApp {
    static class Employee implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private int id;
        private String designation;
        private double salary;
        public Employee(String name, int id, String designation, double salary) {
            this.name = name;
            this.id = id;
            this.designation = designation;
            this.salary = salary;
        }
        public String getName() {
            return name;
        }
        public int getId() {
            return id;
        }
        public String getDesignation() {
            return designation;
        }
        public double getSalary() {
            return salary;
        }
        @Override
        public String toString() {
            return "Employee [ID=" + id + ", Name=" + name + ", Designation=" + designation + ", Salary=" + salary + "]";
        }
    }
    private static final String FILE_NAME = "employees.ser";
    private static List<Employee> employeeList = new ArrayList<>();
    public static void main(String[] args) {
        loadEmployees();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 3);
        saveEmployees();
        scanner.close();
    }
    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter employee designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();
        Employee employee = new Employee(name, id, designation, salary);
        employeeList.add(employee);
        System.out.println("Employee added successfully.");
    }
    private static void displayEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("\nEmployee Details:");
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        }
    }
    private static void loadEmployees() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employeeList = (List<Employee>) in.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employees: " + e.getMessage());
        }
    }
    private static void saveEmployees() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(employeeList);
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }
}

OUTPUT :
Employee Management System
1. Add an Employee
2. Display All Employees
3. Exit
Enter your choice: 1
Enter employee name: John Doe
Enter employee ID: 1001
Enter employee designation: Software Engineer
Enter employee salary: 75000
Employee added successfully.
