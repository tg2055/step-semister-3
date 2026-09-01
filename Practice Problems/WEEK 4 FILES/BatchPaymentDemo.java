class FeeAccount {
    public void pay(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    @Override
    public void pay(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class BatchPaymentDemo {
    private int hostelCount = 0;
    private int dayScholarCount = 0;

    public void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            hostelCount++;
        } else if (account instanceof FeeAccount) {
            dayScholarCount++;
        }
        account.pay(amount);
    }

    public void printBatchSummary() {
        System.out.println("Hostel accounts processed: " + hostelCount + " | Day-scholar accounts processed: " + dayScholarCount);
    }

    public static void main(String[] args) {
        BatchPaymentDemo processor = new BatchPaymentDemo();
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        for (FeeAccount acc : accounts) {
            processor.processPayment(acc, 60000);
        }

        processor.printBatchSummary();
    }
}