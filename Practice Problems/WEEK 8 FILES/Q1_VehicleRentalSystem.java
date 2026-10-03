import java.util.ArrayList;
import java.util.List;

public class Q1_VehicleRentalSystem {

    static abstract class Vehicle {

        protected String name;
        protected boolean available = true;

        public Vehicle(String name) {
            this.name = name;
        }

        public abstract double calculateCharge(int days);

        public String getName() {
            return name;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }

    static class Sedan extends Vehicle {

        public Sedan(String name) {
            super(name);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 50;
        }
    }

    static class SUV extends Vehicle {

        public SUV(String name) {
            super(name);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 80;
        }
    }

    static class Truck extends Vehicle {

        public Truck(String name) {
            super(name);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 100;
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

    static class Rental {

        private Vehicle vehicle;
        private Customer customer;
        private int days;

        public Rental(
                Vehicle vehicle,
                Customer customer,
                int days) {

            this.vehicle = vehicle;
            this.customer = customer;
            this.days = days;
        }

        public double getCharge() {
            return vehicle.calculateCharge(days);
        }

        public void returnVehicle() {
            vehicle.setAvailable(true);

            System.out.println(
                vehicle.getName()
                + " returned by "
                + customer.getName()
            );
        }
    }

    static Rental rentVehicle(
            Vehicle vehicle,
            Customer customer,
            int days) {

        if (!vehicle.isAvailable()) {

            System.out.println(
                vehicle.getName()
                + " is currently unavailable."
            );

            return null;
        }

        vehicle.setAvailable(false);

        Rental rental =
                new Rental(vehicle, customer, days);

        System.out.println(
            vehicle.getName()
            + " rented successfully by "
            + customer.getName()
        );

        System.out.println(
            "Rental charge: $"
            + rental.getCharge()
        );

        return rental;
    }

    public static void main(String[] args) {

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Sedan sedan = new Sedan("Sedan A");
        SUV suv = new SUV("SUV B");

        Rental rental1 =
                rentVehicle(sedan, c1, 3);

        rentVehicle(sedan, c2, 2);

        if (rental1 != null) {
            rental1.returnVehicle();
        }

        rentVehicle(suv, c3, 5);
    }
}