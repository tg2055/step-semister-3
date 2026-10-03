import java.util.ArrayList;
import java.util.List;

public class Q5_CampusNoticeBroadcaster {

    interface NotificationChannel {

        void send(
                Student student,
                Notice notice);
    }

    static class EmailChannel
            implements NotificationChannel {

        public void send(
                Student student,
                Notice notice) {

            System.out.println(
                "[Email → "
                + student.getName()
                + "] "
                + notice.getTitle()
            );
        }
    }

    static class SmsChannel
            implements NotificationChannel {

        public void send(
                Student student,
                Notice notice) {

            System.out.println(
                "[SMS → "
                + student.getName()
                + "] "
                + notice.getTitle()
            );
        }
    }

    static class AppChannel
            implements NotificationChannel {

        public void send(
                Student student,
                Notice notice) {

            System.out.println(
                "[App → "
                + student.getName()
                + "] "
                + notice.getTitle()
            );
        }
    }

    static class Student {

        private String name;
        private String department;

        private List<NotificationChannel> channels =
                new ArrayList<>();

        public Student(
                String name,
                String department) {

            this.name = name;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public void addChannel(
                NotificationChannel channel) {

            channels.add(channel);
        }

        public List<NotificationChannel> getChannels() {
            return channels;
        }
    }

    static class Notice {

        private String title;
        private List<String> departments;

        public Notice(
                String title,
                List<String> departments) {

            this.title = title;
            this.departments = departments;
        }

        public String getTitle() {
            return title;
        }

        public List<String> getDepartments() {
            return departments;
        }

        public boolean isValid() {

            return title != null
                    && !title.trim().isEmpty()
                    && departments != null
                    && !departments.isEmpty();
        }
    }

    static class NoticeBoard {

        private List<Student> students =
                new ArrayList<>();

        public void addStudent(Student student) {
            students.add(student);
        }

        public void postNotice(Notice notice) {

            if (!notice.isValid()) {

                System.out.println(
                    "Cannot post notice: "
                    + "At least one target department is required."
                );

                return;
            }

            System.out.print(
                "Notice '"
                + notice.getTitle()
                + "' posted to "
            );

            for (int i = 0;
                 i < notice.getDepartments().size();
                 i++) {

                System.out.print(
                    notice.getDepartments().get(i)
                );

                if (i <
                    notice.getDepartments().size() - 1) {

                    System.out.print(", ");
                }
            }

            System.out.println(".");

            for (Student student : students) {

                if (notice.getDepartments()
                        .contains(student.getDepartment())) {

                    for (NotificationChannel channel
                            : student.getChannels()) {

                        channel.send(
                            student,
                            notice
                        );
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Student asha =
                new Student("Asha", "CSE");

        asha.addChannel(
            new EmailChannel()
        );

        asha.addChannel(
            new AppChannel()
        );

        Student ravi =
                new Student("Ravi", "ECE");

        ravi.addChannel(
            new SmsChannel()
        );

        NoticeBoard board =
                new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        List<String> cse =
                new ArrayList<>();

        cse.add("CSE");

        Notice notice1 =
                new Notice(
                    "Lab Closed Tomorrow",
                    cse
                );

        board.postNotice(notice1);

        List<String> cseEce =
                new ArrayList<>();

        cseEce.add("CSE");
        cseEce.add("ECE");

        Notice notice2 =
                new Notice(
                    "Fee Deadline Extended",
                    cseEce
                );

        board.postNotice(notice2);

        List<String> empty =
                new ArrayList<>();

        Notice notice3 =
                new Notice(
                    "Sports Day",
                    empty
                );

        board.postNotice(notice3);
    }
}