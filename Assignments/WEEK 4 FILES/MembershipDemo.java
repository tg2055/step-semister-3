class MembershipCard {
    private static String libraryName;
    private static String validUntil;
    private String studentName;

    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";
        System.out.println("Library info loaded");
    }

    public MembershipCard(String studentName) {
        this.studentName = studentName;
        System.out.println("Membership card issued: " + this.studentName);
    }

    // Accessing static fields clears the compiler warnings
    public static void printLibraryInfo() {
        System.out.println("Library: " + libraryName + " | Valid Until: " + validUntil);
    }
}

public class MembershipDemo {
    public static void main(String[] args) {
        String[] names = {"Ananya", "Rohan", "Priya", "Arjun", "Sneha"};

        for (String name : names) {
            new MembershipCard(name);
        }
    }
}