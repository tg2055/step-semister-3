public class Q2_EmployeeLeaveRequestWorkflow {

    enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    static abstract class Employee {

        protected String name;

        public Employee(String name) {
            this.name = name;
        }

        public abstract boolean isLeaveAllowed(int days);

        public String getName() {
            return name;
        }
    }

    static class FullTimeEmployee extends Employee {

        public FullTimeEmployee(String name) {
            super(name);
        }

        @Override
        public boolean isLeaveAllowed(int days) {
            return days <= 30;
        }
    }

    static class PartTimeEmployee extends Employee {

        public PartTimeEmployee(String name) {
            super(name);
        }

        @Override
        public boolean isLeaveAllowed(int days) {
            return days <= 10;
        }
    }

    static class Contractor extends Employee {

        public Contractor(String name) {
            super(name);
        }

        @Override
        public boolean isLeaveAllowed(int days) {
            return days <= 5;
        }
    }

    static class LeaveRequest {

        private Employee employee;
        private String startDate;
        private String endDate;
        private int days;
        private Status status;

        public LeaveRequest(
                Employee employee,
                String startDate,
                String endDate,
                int days) {

            this.employee = employee;
            this.startDate = startDate;
            this.endDate = endDate;
            this.days = days;
            this.status = Status.PENDING;
        }

        public void approve() {

            if (status != Status.PENDING) {
                System.out.println(
                    "Cannot approve a request that is not Pending."
                );
                return;
            }

            if (!employee.isLeaveAllowed(days)) {
                System.out.println(
                    "Leave policy does not allow this request."
                );
                return;
            }

            status = Status.APPROVED;

            System.out.println(
                employee.getName()
                + "'s leave request ("
                + startDate
                + "-"
                + endDate
                + ") approved."
            );
        }

        public void reject() {

            if (status != Status.PENDING) {
                System.out.println(
                    "Cannot reject a request that is not Pending."
                );
                return;
            }

            status = Status.REJECTED;

            System.out.println(
                employee.getName()
                + "'s leave request ("
                + startDate
                + "-"
                + endDate
                + ") rejected."
            );
        }

        public void changeStatus(Status newStatus) {

            if (status != Status.PENDING) {

                System.out.println(
                    "Cannot change leave request status from "
                    + status
                    + " to "
                    + newStatus
                );

                return;
            }

            status = newStatus;
        }

        public Status getStatus() {
            return status;
        }
    }

    public static void main(String[] args) {

        FullTimeEmployee john =
                new FullTimeEmployee("John");

        LeaveRequest johnRequest =
                new LeaveRequest(
                    john,
                    "Jan 1",
                    "Jan 5",
                    5
                );

        System.out.println(
            "Leave request submitted for "
            + john.getName()
            + " (Jan 1-Jan 5). Status: "
            + johnRequest.getStatus()
        );

        johnRequest.approve();

        System.out.println(
            "Status: "
            + johnRequest.getStatus()
        );

        johnRequest.changeStatus(Status.PENDING);
    }
}
