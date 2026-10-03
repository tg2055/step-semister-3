import java.util.ArrayList;
import java.util.List;

public class Q5_PaymentProcessingShoppingSystem {

    enum OrderStatus {
        PENDING,
        PAID
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

    static class Product {

        private String name;
        private double price;

        public Product(
                String name,
                double price) {

            this.name = name;
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }
    }

    static class OrderItem {

        private Product product;
        private int quantity;

        public OrderItem(
                Product product,
                int quantity) {

            this.product = product;
            this.quantity = quantity;
        }

        public double getTotal() {
            return product.getPrice() * quantity;
        }
    }

    interface PaymentMethod {

        boolean processPayment(
                double amount,
                String orderId);
    }

    static class CreditCardPayment
            implements PaymentMethod {

        @Override
        public boolean processPayment(
                double amount,
                String orderId) {

            System.out.println(
                "Processing Credit Card payment..."
            );

            return true;
        }
    }

    static class PayPalPayment
            implements PaymentMethod {

        @Override
        public boolean processPayment(
                double amount,
                String orderId) {

            System.out.println(
                "Processing PayPal payment..."
            );

            return false;
        }
    }

    static class BankTransferPayment
            implements PaymentMethod {

        @Override
        public boolean processPayment(
                double amount,
                String orderId) {

            System.out.println(
                "Processing Bank Transfer..."
            );

            return true;
        }
    }

    static class Order {

        private String orderId;
        private Customer customer;

        private List<OrderItem> items =
                new ArrayList<>();

        private OrderStatus status =
                OrderStatus.PENDING;

        public Order(
                String orderId,
                Customer customer) {

            this.orderId = orderId;
            this.customer = customer;
        }

        public void addProduct(
                Product product,
                int quantity) {

            if (quantity > 0) {

                items.add(
                    new OrderItem(
                        product,
                        quantity
                    )
                );
            }
        }

        public double getTotal() {

            double total = 0;

            for (OrderItem item : items) {
                total += item.getTotal();
            }

            return total;
        }

        public void pay(
                PaymentMethod paymentMethod) {

            if (items.isEmpty()) {

                System.out.println(
                    "Cannot process payment for an empty order."
                );

                return;
            }

            System.out.println(
                "Payment initiated for Order "
                + orderId
            );

            boolean success =
                    paymentMethod.processPayment(
                        getTotal(),
                        orderId
                    );

            if (success) {

                status = OrderStatus.PAID;

                System.out.println(
                    "Payment for Order "
                    + orderId
                    + " successful."
                );

            } else {

                System.out.println(
                    "Payment for Order "
                    + orderId
                    + " failed."
                );
            }

            System.out.println(
                "Order status: "
                + status
            );
        }
    }

    public static void main(String[] args) {

        Customer customerX =
                new Customer("Customer X");

        Product productA =
                new Product("Product A", 100);

        Product productB =
                new Product("Product B", 200);

        Order orderX =
                new Order("X", customerX);

        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        System.out.println(
            "Order created for Customer X."
        );

        orderX.pay(
            new CreditCardPayment()
        );

        Customer customerY =
                new Customer("Customer Y");

        Order orderY =
                new Order("Y", customerY);

        orderY.pay(
            new CreditCardPayment()
        );

        Customer customerZ =
                new Customer("Customer Z");

        Product productC =
                new Product("Product C", 300);

        Order orderZ =
                new Order("Z", customerZ);

        orderZ.addProduct(productC, 1);

        orderZ.pay(
            new PayPalPayment()
        );
    }
}