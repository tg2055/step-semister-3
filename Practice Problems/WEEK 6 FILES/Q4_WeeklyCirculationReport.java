public class Q4_WeeklyCirculationReport {

    static class LibraryMember {

        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

        public LibraryMember(
                String memberId,
                int borrowLimit) {

            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        public void displayInfo(StringBuilder sb) {

            sb.append(
                "General | Books: "
                + booksBorrowed
                + " | "
            );
        }
    }

    static class StudentMember extends LibraryMember {

        private String course;

        public StudentMember(
                String memberId,
                int borrowLimit,
                String course) {

            super(memberId, borrowLimit);
            this.course = course;
        }

        @Override
        public void displayInfo(StringBuilder sb) {

            sb.append(
                "Student | Course: "
                + course
                + " | Books: "
                + booksBorrowed
                + " | "
            );
        }

        public String getCourse() {
            return course;
        }
    }

    static String batchPrint(
            LibraryMember[] members) {

        StringBuilder report =
                new StringBuilder();

        for (LibraryMember member : members) {

            member.displayInfo(report);

            if (member instanceof StudentMember) {

                StudentMember student =
                        (StudentMember) member;

                report.append(
                    "[Course via downcast: "
                    + student.getCourse()
                    + "] | "
                );
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {

        LibraryMember[] members = {

            new LibraryMember(
                "LB5", 3
            ),

            new StudentMember(
                "STU6", 3, "ECE"
            )
        };

        System.out.println(
            batchPrint(members)
        );

        LibraryMember plain =
                new LibraryMember("LB6", 3);

        if (plain instanceof StudentMember) {

            StudentMember bad =
                    (StudentMember) plain;

            System.out.println(
                bad.getCourse()
            );
        } else {

            System.out.println(
                "Not a StudentMember"
            );
        }
    }
}