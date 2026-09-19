public class Q4_MonthlyAttendanceAnnouncer {

    static class GymMember {

        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(
                String memberId,
                int monthlyFee) {

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        public void displayInfo(StringBuilder sb) {

            sb.append(
                "Standard | Sessions: "
                + sessionsAttended
                + " | "
            );
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
        public void displayInfo(StringBuilder sb) {

            sb.append(
                "Premium | Trainer: "
                + trainerName
                + " | Sessions: "
                + sessionsAttended
                + " | "
            );
        }

        public String getTrainerName() {
            return trainerName;
        }
    }

    static String batchPrint(
            GymMember[] members) {

        StringBuilder announcement =
                new StringBuilder();

        for (GymMember member : members) {

            member.displayInfo(announcement);

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                        (PremiumMember) member;

                announcement.append(
                    "[Trainer via downcast: "
                    + premium.getTrainerName()
                    + "] | "
                );
            }
        }

        return announcement.toString();
    }

    public static void main(String[] args) {

        GymMember[] members = {

            new GymMember(
                "MEM6",
                1000
            ),

            new PremiumMember(
                "MEM7",
                2000,
                "Coach Riya"
            )
        };

        System.out.println(
            batchPrint(members)
        );

        GymMember plain =
                new GymMember("MEM8", 1000);

        if (plain instanceof PremiumMember) {

            PremiumMember bad =
                    (PremiumMember) plain;

            System.out.println(
                bad.getTrainerName()
            );

        } else {

            System.out.println(
                "Not a PremiumMember"
            );
        }
    }
}