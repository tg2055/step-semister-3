public class Q4_FitZoneMembershipDesk {

    enum Status {
        ACTIVE,
        FROZEN,
        EXPIRED
    }

    interface MembershipPlan {

        double calculateFee();

        String getName();
    }

    static class MonthlyPlan
            implements MembershipPlan {

        public double calculateFee() {
            return 1000;
        }

        public String getName() {
            return "Monthly";
        }
    }

    static class QuarterlyPlan
            implements MembershipPlan {

        public double calculateFee() {
            return 1000 * 3 * 0.90;
        }

        public String getName() {
            return "Quarterly";
        }
    }

    static class AnnualPlan
            implements MembershipPlan {

        public double calculateFee() {
            return 1000 * 12 * 0.75;
        }

        public String getName() {
            return "Annual";
        }
    }

    static class Member {

        private String name;
        private Membership membership;

        public Member(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void buyMembership(
                MembershipPlan plan) {

            membership =
                    new Membership(this, plan);

            System.out.println(
                plan.getName()
                + " membership created for "
                + name
            );

            System.out.printf(
                "Fee: ₹%.2f%n",
                plan.calculateFee()
            );

            System.out.println(
                "Status: "
                + membership.getStatus()
            );
        }

        public void checkIn() {
            membership.checkIn();
        }

        public void freeze() {
            membership.freeze();
        }

        public void unfreeze() {
            membership.unfreeze();
        }

        public void expire() {
            membership.expire();
        }
    }

    static class Membership {

        private Member member;
        private MembershipPlan plan;
        private Status status;

        public Membership(
                Member member,
                MembershipPlan plan) {

            this.member = member;
            this.plan = plan;
            this.status = Status.ACTIVE;
        }

        public Status getStatus() {
            return status;
        }

        public void checkIn() {

            if (status == Status.ACTIVE) {

                System.out.println(
                    member.getName()
                    + " checked in successfully."
                );

            } else {

                System.out.println(
                    "Check-in denied: "
                    + member.getName()
                    + "'s membership is "
                    + status
                );
            }
        }

        public void freeze() {

            if (status == Status.ACTIVE) {

                status = Status.FROZEN;

                System.out.println(
                    member.getName()
                    + "'s membership frozen."
                );

                System.out.println(
                    "Status: " + status
                );

            } else {

                System.out.println(
                    "Cannot freeze an "
                    + status
                    + " membership."
                );
            }
        }

        public void unfreeze() {

            if (status == Status.FROZEN) {

                status = Status.ACTIVE;

                System.out.println(
                    member.getName()
                    + "'s membership unfrozen."
                );

                System.out.println(
                    "Status: " + status
                );

            } else {

                System.out.println(
                    "Cannot unfreeze an "
                    + status
                    + " membership."
                );
            }
        }

        public void expire() {

            if (status != Status.EXPIRED) {

                status = Status.EXPIRED;

                System.out.println(
                    member.getName()
                    + "'s membership expired."
                );

                System.out.println(
                    "Status: " + status
                );
            }
        }
    }

    public static void main(String[] args) {

        Member asha =
                new Member("Asha");

        Member ravi =
                new Member("Ravi");

        asha.buyMembership(
            new QuarterlyPlan()
        );

        ravi.buyMembership(
            new MonthlyPlan()
        );

        asha.checkIn();

        asha.freeze();

        asha.checkIn();

        ravi.expire();

        ravi.freeze();
    }
}