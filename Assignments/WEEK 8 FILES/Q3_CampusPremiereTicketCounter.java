import java.util.ArrayList;
import java.util.List;

public class Q3_CampusPremiereTicketCounter {

    interface Seat {
        String getSeatNumber();
        double getPrice();
    }

    static class RegularSeat implements Seat {
        private String seatNumber;

        RegularSeat(String seatNumber) {
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

        PremiumSeat(String seatNumber) {
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

        ReclinerSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return 400;
        }
    }

    static class Customer {
        private String name;

        Customer(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static class Show {
        private String showTime;
        private List<Seat> bookedSeats = new ArrayList<>();

        Show(String showTime) {
            this.showTime = showTime;
        }

        boolean isAvailable(Seat seat) {
            for (Seat booked : bookedSeats) {
                if (booked.getSeatNumber().equals(seat.getSeatNumber())) {
                    return false;
                }
            }
            return true;
        }

        boolean bookSeat(Seat seat) {
            if (isAvailable(seat)) {
                bookedSeats.add(seat);
                return true;
            }

            return false;
        }

        void releaseSeat(Seat seat) {
            bookedSeats.removeIf(
                s -> s.getSeatNumber().equals(seat.getSeatNumber())
            );
        }

        String getShowTime() {
            return showTime;
        }
    }

    static class Booking {
        private Customer customer;
        private Show show;
        private List<Seat> seats = new ArrayList<>();
        private boolean cancelled = false;

        Booking(Customer customer, Show show) {
            this.customer = customer;
            this.show = show;
        }

        boolean addSeat(Seat seat) {

            if (cancelled) {
                System.out.println("Booking is cancelled.");
                return false;
            }

            if (seats.size() >= 6) {
                System.out.println("Maximum 6 seats allowed.");
                return false;
            }

            if (!show.bookSeat(seat)) {
                System.out.println(
                    "Seat " + seat.getSeatNumber() + " is already booked."
                );
                return false;
            }

            seats.add(seat);
            return true;
        }

        double getTotal() {
            double total = 0;

            for (Seat seat : seats) {
                total += seat.getPrice();
            }

            return total;
        }

        void cancel(boolean beforeShowStarts) {

            if (!beforeShowStarts) {
                System.out.println(
                    "Cannot cancel after the show starts."
                );
                return;
            }

            if (cancelled) {
                System.out.println("Booking is already cancelled.");
                return;
            }

            for (Seat seat : seats) {
                show.releaseSeat(seat);
            }

            cancelled = true;

            System.out.println(
                customer.getName() + "'s booking cancelled."
            );
        }
    }

    public static void main(String[] args) {

        Show show = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Booking ashaBooking = new Booking(asha, show);

        ashaBooking.addSeat(new RegularSeat("A1"));
        ashaBooking.addSeat(new RegularSeat("A2"));
        ashaBooking.addSeat(new PremiumSeat("F5"));

        System.out.println("Asha's booking confirmed.");
        System.out.printf(
            "Asha Total: ₹%.2f%n",
            ashaBooking.getTotal()
        );

        Customer ravi = new Customer("Ravi");
        Booking raviBooking = new Booking(ravi, show);

        raviBooking.addSeat(new RegularSeat("A2"));
        raviBooking.addSeat(new ReclinerSeat("R1"));

        System.out.println("Ravi's booking confirmed for R1.");
        System.out.printf(
            "Ravi Total: ₹%.2f%n",
            raviBooking.getTotal()
        );

        ashaBooking.cancel(true);

        Customer neha = new Customer("Neha");
        Booking nehaBooking = new Booking(neha, show);

        nehaBooking.addSeat(new RegularSeat("A2"));

        System.out.println("Neha's booking confirmed for A2.");
        System.out.printf(
            "Neha Total: ₹%.2f%n",
            nehaBooking.getTotal()
        );
    }
}