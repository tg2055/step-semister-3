class Account {
    private String regNo;
    private double totalFee;

    public Account(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    // final method locked against overriding
    public final double calculateLateFee(int daysLate) {
        return this.totalFee * 0.01 * daysLate;
    }

    // final method locked against overriding
    public final void printSummary(int daysLate) {
        if (daysLate <= 0) {
            System.out.println(this.regNo + " - On time, no late fee");
        } else {
            double lateFee = calculateLateFee(daysLate);
            System.out.println(this.regNo + " | Total Fee: Rs " + this.totalFee + " | Late Fee: Rs " + lateFee);
        }
    }
}

public class LateFeesDemo {
    public static void main(String[] args) {
        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};
        double[] totalFees = {200000, 150000, 180000, 220000};
        int[] daysLate = {10, 0, -2, 5};

        for (int i = 0; i < regNos.length; i++) {
            Account acc = new Account(regNos[i], totalFees[i]);
            acc.printSummary(daysLate[i]);
        }
    }
}