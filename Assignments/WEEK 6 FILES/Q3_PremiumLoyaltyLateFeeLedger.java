import java.util.Arrays;

public class Q3_PremiumLoyaltyLateFeeLedger {

    static class GymMember {

        protected String memberId;
        protected int monthlyFee;

        private int[] lateFeeHistory = new int[10];
        private int feeCount = 0;
        private int totalLateFees = 0;

        public GymMember(
                String memberId,
                int monthlyFee) {

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        protected void chargeLateFee(int amount) {

            if (feeCount < lateFeeHistory.length) {
                lateFeeHistory[feeCount] = amount;
                feeCount++;
            }

            totalLateFees += amount;
        }

        public int[] getLateFeeHistory() {

            return Arrays.copyOf(
                lateFeeHistory,
                feeCount
            );
        }

        public int getTotalLateFees() {
            return totalLateFees;
        }
    }

    static class PremiumMember extends GymMember {

        private String trainerName;

        public PremiumMember(
                String memberId,
                int monthlyFee,
                String trainerName) {

            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        protected void chargeLateFee(int amount) {

            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {

        PremiumMember p =
                new PremiumMember(
                    "MEM5",
                    2000,
                    "Coach Riya"
                );

        p.chargeLateFee(200);

        System.out.println(
            p.getTotalLateFees()
        );

        int[] history = p.getLateFeeHistory();

        history[0] = 999;

        System.out.println(
            Arrays.toString(
                p.getLateFeeHistory()
            )
        );
    }
}