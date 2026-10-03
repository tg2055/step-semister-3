import java.util.ArrayList;
import java.util.List;

public class Q3_CampusPremiereTicketCounter {

    interface Seat {

        String getSeatNumber();

        double getPrice();
    }

    static class RegularSeat implements Seat {

        private String seatNumber;

        public RegularSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return 150;
        }
    }

    static class PremiumSeat implements Seat {

        private String seatNumber;

        public PremiumSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return 250;
        }
    }

    static class ReclinerSeat implements Seat {

        private String seatNumber;

        public ReclinerSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public double getPrice() {
            return 400;
        }
    }

    static class Customer {

        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Show {

        private String showTime;

        private List<Seat> bookedSeats =
                new ArrayList<>();

        public Show(String showTime) {
            this.showTime = showTime;
        }

        public boolean isAvailable(Seat seat) {

            for (Seat booked : bookedSeats) {

                if (booked.getSeatNumber()
                        .equals(seat.getSeatNumber())) {

                    return false;
                }
            }

            return true;
        }

        public boolean bookSeat(Seat seat) {

            if (!isAvailable(seat)) {
                return false;
            }

            bookedSeats.add(seat);
            return true;
        }

        public void releaseSeat(Seat seat) {
            bookedSeats.remove(seat);
        }

        public String getShowTime() {
            return showTime;
        }
    }

    static class Booking {

        private Customer customer;
        private Show show;
        private List<Seat> seats =
                new ArrayList<>();

        private boolean cancelled = false;

        public Booking(
                Customer customer,
                Show show) {

            this.customer = customer;
            this.show = show;
        }

        public boolean addSeat(Seat seat) {

            if (seats.size() >= 6) {

                System.out.println(
                    "Maximum 6 seats allowed per booking."
                );

                return false;
            }

            if (!show.isAvailable(seat)) {

                System.out.println(
                    "Seat "
                    + seat.getSeatNumber()
                    + " is already booked for this show."
                );

                return false;
            }

            show.bookSeat(seat);
            seats.add(seat);

            return true;
        }

        public double getTotal() {

            double total = 0;

            for (Seat seat : seats) {
                total += seat.getPrice();
            }

            return total;
        }

        public void cancel(boolean beforeShowStarts) {

            if (!beforeShowStarts) {

                System.out.println(
                    "Cannot cancel: show has already started."
                );

                return;
            }

            if (cancelled) {
                return;
            }

            for (Seat seat : seats) {
                show.releaseSeat(seat);
            }

            cancelled = true;

            System.out.println(
                customer.getName()
                + "'s booking cancelled."
            );

            System.out.print("Seats ");

            for (int i = 0; i < seats.size(); i++) {

                System.out.print(
                    seats.get(i).getSeatNumber()
                );

                if (i < seats.size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println(" released.");
        }
    }

    public static void main(String[] args) {

        Customer asha =
                new Customer("Asha");

        Customer ravi =
                new Customer("Ravi");

        Customer neha =
                new Customer("Neha");

        Show show =
                new Show("7 PM");

        Booking ashaBooking =
                new Booking(asha, show);

        ashaBooking.addSeat(
            new RegularSeat("A1")
        );

        ashaBooking.addSeat(
            new RegularSeat("A2")
        );

        ashaBooking.addSeat(
            new PremiumSeat("F5")
        );

        System.out.println(
            "Booking confirmed for Asha: "
            + "A1, A2, F5."
        );

        System.out.printf(
            "Total: ₹%.2f%n",
            ashaBooking.getTotal()
        );

        Booking raviBooking =
                new Booking(ravi, show);

        raviBooking.addSeat(
            new RegularSeat("A2")
        );

        raviBooking.addSeat(
            new ReclinerSeat("R1")
        );

        System.out.println(
            "Booking confirmed for Ravi: R1."
        );

        System.out.printf(
            "Total: ₹%.2f%n",
            raviBooking.getTotal()
        );

        ashaBooking.cancel(true);

        Booking nehaBooking =
                new Booking(neha, show);

        nehaBooking.addSeat(
            new RegularSeat("A2")
        );

        System.out.println(
            "Booking confirmed for Neha: A2."
        );

        System.out.printf(
            "Total: ₹%.2f%n",
            nehaBooking.getTotal()
        );
    }
}