class Student {
    // Instance variables
    String name;
    int attendance;

    // Static variables
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    // Constructor
    Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    // Static method
    static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
}

public class M5_Student {
    public static void main(String[] args) {

        // Create two Student objects
        Student student1 = new Student("Ravi", 90);
        Student student2 = new Student("Anitha", 85);

        // Call static method using class name
        Student.printCollegeInfo();
    }
}