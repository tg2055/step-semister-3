class Employee {
    // Instance fields
    String empName;
    double salary;

    // Static fields
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class M5_EmployeeStatic {
    public static void main(String[] args) {

        Employee employee1 = new Employee("Divya", 65000);
        Employee employee2 = new Employee("Arjun", 30000);
        Employee employee3 = new Employee("Priya", 55000);

        Employee.printCompanyInfo();
    }
}