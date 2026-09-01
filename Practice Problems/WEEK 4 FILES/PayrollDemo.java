class Employee {
    private String id;
    private double salary;

    public Employee(String id, double salary) {
        this.id = id;
        this.salary = salary;
    }

    // Resolving parameter/field name clash using this keyword
    public void raiseSalary(double salary) {
        this.salary += salary;
    }

    public void printFinalSalary() {
        System.out.println(this.id + " | Final Salary: Rs " + this.salary);
    }
}

public class PayrollDemo {
    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("E-101", 40000),
            new Employee("E-102", 55000),
            new Employee("E-103", 62000),
            new Employee("E-104", 48000)
        };

        double bonus = 5000;

        for (Employee emp : employees) {
            emp.raiseSalary(bonus);
            emp.printFinalSalary();
        }
    }
}