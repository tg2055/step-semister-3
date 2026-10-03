public class Q1_HostelLaundryQueue {

    interface WashType {
        int getDuration();
        double getCharge();
        String getName();
    }

    static class QuickWash implements WashType {

        public int getDuration() {
            return 30;
        }

        public double getCharge() {
            return 20;
        }

        public String getName() {
            return "Quick";
        }
    }

    static class NormalWash implements WashType {

        public int getDuration() {
            return 45;
        }

        public double getCharge() {
            return 30;
        }

        public String getName() {
            return "Normal";
        }
    }

    static class HeavyWash implements WashType {

        public int getDuration() {
            return 60;
        }

        public double getCharge() {
            return 45;
        }

        public String getName() {
            return "Heavy";
        }
    }

    static class Student {

        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class WashingMachine {

        private String machineId;
        private boolean free = true;
        private WashCycle currentCycle;

        public WashingMachine(String machineId) {
            this.machineId = machineId;
        }

        public String getMachineId() {
            return machineId;
        }

        public boolean isFree() {
            return free;
        }

        public WashCycle startWash(
                Student student,
                WashType washType) {

            if (!free) {
                System.out.println(
                    "Machine " + machineId + " is currently busy."
                );
                return null;
            }

            free = false;

            WashCycle cycle =
                    new WashCycle(
                        student,
                        this,
                        washType
                    );

            currentCycle = cycle;

            System.out.println(
                washType.getName()
                + " wash started on "
                + machineId
                + " for "
                + student.getName()
                + " ("
                + washType.getDuration()
                + " min)."
            );

            System.out.printf(
                "Charge: ₹%.2f%n",
                washType.getCharge()
            );

            return cycle;
        }

        private void completeWash() {
            free = true;
            currentCycle = null;

            System.out.println(
                machineId + " cycle completed."
            );

            System.out.println(
                machineId + " is now free."
            );
        }
    }

    static class WashCycle {

        private Student student;
        private WashingMachine machine;
        private WashType washType;

        public WashCycle(
                Student student,
                WashingMachine machine,
                WashType washType) {

            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }

        public void complete() {
            machine.completeWash();
        }
    }

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 =
                new WashingMachine("M1");

        WashingMachine m2 =
                new WashingMachine("M2");

        WashCycle cycle1 =
                m1.startWash(
                    asha,
                    new QuickWash()
                );

        m1.startWash(
            ravi,
            new HeavyWash()
        );

        m2.startWash(
            ravi,
            new HeavyWash()
        );

        if (cycle1 != null) {
            cycle1.complete();
        }

        m1.startWash(
            neha,
            new NormalWash()
        );
    }
}