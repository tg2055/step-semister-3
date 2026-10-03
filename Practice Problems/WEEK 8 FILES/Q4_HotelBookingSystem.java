import java.util.ArrayList;
import java.util.List;

public class Q4_HotelBookingSystem {

    static abstract class Room {

        protected String roomNumber;
        protected List<Reservation> reservations =
                new ArrayList<>();

        public Room(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public abstract double calculatePrice(int days);

        public boolean isAvailable(
                int startDay,
                int endDay) {

            for (Reservation r : reservations) {

                if (!r.isCancelled()
                        && startDay < r.getEndDay()
                        && endDay > r.getStartDay()) {

                    return false;
                }
            }

            return true;
        }

        public void addReservation(
                Reservation reservation) {

            reservations.add(reservation);
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    static class StandardRoom extends Room {

        public StandardRoom(String roomNumber) {
            super(roomNumber);
        }

        @Override
        public double calculatePrice(int days) {
            return days * 100;
        }
    }

    static class DeluxeRoom extends Room {

        public DeluxeRoom(String roomNumber) {
            super(roomNumber);
        }

        @Override
        public double calculatePrice(int days) {
            return days * 150;
        }
    }

    static class Suite extends Room {

        public Suite(String roomNumber) {
            super(roomNumber);
        }

        @Override
        public double calculatePrice(int days) {
            return days * 250;
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

    static class Reservation {

        private Customer customer;
        private Room room;
        private int startDay;
        private int endDay;
        private boolean cancelled = false;

        public Reservation(
                Customer customer,
                Room room,
                int startDay,
                int endDay) {

            this.customer = customer;
            this.room = room;
            this.startDay = startDay;
            this.endDay = endDay;
        }

        public int getStartDay() {
            return startDay;
        }

        public int getEndDay() {
            return endDay;
        }

        public boolean isCancelled() {
            return cancelled;
        }

        public void cancel(int currentDay) {

            if (currentDay <= startDay) {

                cancelled = true;

                System.out.println(
                    "Reservation for "
                    + customer.getName()
                    + ", Room "
                    + room.getRoomNumber()
                    + " cancelled successfully."
                );

            } else {

                System.out.println(
                    "Cancellation deadline has passed."
                );
            }
        }

        public double getPrice() {
            return room.calculatePrice(
                endDay - startDay
            );
        }
    }

    static Reservation reserveRoom(
            Customer customer,
            Room room,
            int startDay,
            int endDay) {

        if (!room.isAvailable(startDay, endDay)) {

            System.out.println(
                "Room "
                + room.getRoomNumber()
                + " is not available."
            );

            return null;
        }

        Reservation reservation =
                new Reservation(
                    customer,
                    room,
                    startDay,
                    endDay
                );

        room.addReservation(reservation);

        System.out.println(
            "Reservation confirmed for "
            + customer.getName()
            + ", Room "
            + room.getRoomNumber()
        );

        System.out.println(
            "Price: $"
            + reservation.getPrice()
        );

        return reservation;
    }

    public static void main(String[] args) {

        Customer customerA =
                new Customer("Customer A");

        Customer customerB =
                new Customer("Customer B");

        StandardRoom room101 =
                new StandardRoom("101");

        Reservation r =
                reserveRoom(
                    customerA,
                    room101,
                    1,
                    5
                );

        reserveRoom(
            customerB,
            room101,
            3,
            7
        );

        if (r != null) {
            r.cancel(1);
        }

        Customer customerC =
                new Customer("Customer C");

        DeluxeRoom room201 =
                new DeluxeRoom("201");

        reserveRoom(
            customerC,
            room201,
            10,
            12
        );
    }
}