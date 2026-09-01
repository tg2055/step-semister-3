class SrmStudent {
    private static String collegeName;
    private static String academicYear;
    private String name;

    static {
        collegeName = "SRM";
        academicYear = "2026";
        System.out.println("College info loaded");
    }

    public SrmStudent(String name) {
        this.name = name;
        System.out.println("Student record created: " + this.name);
    }

    // Accessing static fields clears the compiler warnings
    public static void printCollegeDetails() {
        System.out.println("College: " + collegeName + " | Year: " + academicYear);
    }
}

public class StudentSetupDemo {
    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        for (String name : names) {
            new SrmStudent(name);
        }
    }
}