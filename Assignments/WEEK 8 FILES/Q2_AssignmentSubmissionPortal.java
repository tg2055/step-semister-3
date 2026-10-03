public class Q2_AssignmentSubmissionPortal {

    enum Status {
        SUBMITTED,
        GRADED
    }

    static class Student {

        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static abstract class Assignment {

        protected String title;
        protected int maxMarks;
        protected int dueDay;

        public Assignment(
                String title,
                int maxMarks,
                int dueDay) {

            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDay = dueDay;
        }

        public abstract double applyLatePenalty(
                double marks,
                int lateDays);

        public String getTitle() {
            return title;
        }

        public int getMaxMarks() {
            return maxMarks;
        }

        public int getDueDay() {
            return dueDay;
        }
    }

    static class CodingAssignment
            extends Assignment {

        public CodingAssignment(
                String title,
                int maxMarks,
                int dueDay) {

            super(title, maxMarks, dueDay);
        }

        @Override
        public double applyLatePenalty(
                double marks,
                int lateDays) {

            return marks * Math.pow(0.90, lateDays);
        }
    }

    static class WrittenAssignment
            extends Assignment {

        public WrittenAssignment(
                String title,
                int maxMarks,
                int dueDay) {

            super(title, maxMarks, dueDay);
        }

        @Override
        public double applyLatePenalty(
                double marks,
                int lateDays) {

            return marks * Math.pow(0.80, lateDays);
        }
    }

    static class Submission {

        private Student student;
        private Assignment assignment;
        private int submissionDay;
        private Status status;

        public Submission(
                Student student,
                Assignment assignment,
                int submissionDay) {

            this.student = student;
            this.assignment = assignment;
            this.submissionDay = submissionDay;
            this.status = Status.SUBMITTED;
        }

        public void grade(double awardedMarks) {

            if (status == Status.GRADED) {
                System.out.println(
                    "Cannot grade again."
                );
                return;
            }

            int lateDays =
                    Math.max(
                        0,
                        submissionDay
                        - assignment.getDueDay()
                    );

            double finalMarks =
                    assignment.applyLatePenalty(
                        awardedMarks,
                        lateDays
                    );

            if (lateDays == 0) {

                System.out.println(
                    student.getName()
                    + " graded: "
                    + format(finalMarks)
                    + "/"
                    + assignment.getMaxMarks()
                );

            } else {

                double penalty =
                        100
                        - (finalMarks / awardedMarks * 100);

                System.out.println(
                    student.getName()
                    + " graded: "
                    + format(finalMarks)
                    + "/"
                    + assignment.getMaxMarks()
                    + " after "
                    + Math.round(penalty)
                    + "% late penalty."
                );
            }

            status = Status.GRADED;

            System.out.println(
                "Status: " + status
            );
        }

        public void resubmit(int newDay) {

            if (status == Status.GRADED) {

                System.out.println(
                    "Cannot resubmit: '"
                    + assignment.getTitle()
                    + "' has already been graded."
                );

                return;
            }

            submissionDay = newDay;

            System.out.println(
                "Submission updated."
            );
        }

        private String format(double value) {
            return String.format("%.0f", value);
        }
    }

    public static void main(String[] args) {

        Student asha =
                new Student("Asha");

        Student ravi =
                new Student("Ravi");

        CodingAssignment coding =
                new CodingAssignment(
                    "Linked List Lab",
                    50,
                    10
                );

        WrittenAssignment written =
                new WrittenAssignment(
                    "Design Essay",
                    50,
                    12
                );

        Submission ashaSubmission =
                new Submission(
                    asha,
                    coding,
                    10
                );

        System.out.println(
            "Asha's submission for "
            + "'Linked List Lab' received (on time)."
        );

        System.out.println(
            "Status: SUBMITTED."
        );

        Submission raviSubmission =
                new Submission(
                    ravi,
                    written,
                    14
                );

        System.out.println(
            "Ravi's submission for "
            + "'Design Essay' received (2 days late)."
        );

        System.out.println(
            "Status: SUBMITTED."
        );

        ashaSubmission.grade(45);

        raviSubmission.grade(40);

        ashaSubmission.resubmit(15);
    }
}