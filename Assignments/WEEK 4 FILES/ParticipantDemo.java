class Participant {
    private String name;
    private String teamName;
    private boolean registered;

    // Main constructor setting all fields
    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    // Overloaded constructor chaining via this(...)
    public Participant(String name) {
        this(name, "Unassigned");
    }

    public void printStatus() {
        System.out.println(this.name + " | " + this.teamName + " | Registered: " + this.registered);
    }
}

public class ParticipantDemo {
    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya"};
        String[] teamNames = {"ByteBusters", "", "CodeCrafters", ""};

        for (int i = 0; i < names.length; i++) {
            Participant p;
            if (teamNames[i] == null || teamNames[i].trim().isEmpty()) {
                p = new Participant(names[i]);
            } else {
                p = new Participant(names[i], teamNames[i]);
            }
            p.printStatus();
        }
    }
}