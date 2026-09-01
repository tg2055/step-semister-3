class ParkingTicket {
    private String vehicleNo;
    private double ratePerMinute;

    public ParkingTicket(String vehicleNo, double ratePerMinute) {
        this.vehicleNo = vehicleNo;
        this.ratePerMinute = ratePerMinute;
    }

    // final method locked against overriding
    public final double calculateFine(int overstayMinutes) {
        return overstayMinutes * this.ratePerMinute;
    }

    // final method locked against overriding
    public final void printReceipt(int overstayMinutes) {
        double fine = calculateFine(overstayMinutes);
        System.out.println(this.vehicleNo + " - Fine: Rs " + fine);
    }

    public String getVehicleNo() {
        return this.vehicleNo;
    }
}

public class ParkingDemo {
    public static void main(String[] args) {
        String[] vehicleNos = {"TN09AB1234", "TN22CD5678", "TN09EF9012", "TN10GH3456"};
        double[] ratePerMinute = {2, 2, 3, 2};
        int[] overstayMinutes = {15, 0, -5, 8};

        for (int i = 0; i < vehicleNos.length; i++) {
            ParkingTicket ticket = new ParkingTicket(vehicleNos[i], ratePerMinute[i]);
            if (overstayMinutes[i] > 0) {
                ticket.printReceipt(overstayMinutes[i]);
            } else {
                System.out.println(ticket.getVehicleNo() + " - No fine, within allotted time");
            }
        }
    }
}