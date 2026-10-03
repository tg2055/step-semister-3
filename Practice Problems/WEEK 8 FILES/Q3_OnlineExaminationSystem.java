import java.util.ArrayList;
import java.util.List;

public class Q3_OnlineExaminationSystem {

    static abstract class Question {

        protected String questionText;
        protected int points;

        public Question(
                String questionText,
                int points) {

            this.questionText = questionText;
            this.points = points;
        }

        public abstract boolean evaluate(String answer);

        public int getPoints() {
            return points;
        }
    }

    static class MultipleChoiceQuestion
            extends Question {

        private String correctAnswer;

        public MultipleChoiceQuestion(
                String questionText,
                int points,
                String correctAnswer) {

            super(questionText, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return correctAnswer.equalsIgnoreCase(answer);
        }
    }

    static class TrueFalseQuestion
            extends Question {

        private boolean correctAnswer;

        public TrueFalseQuestion(
                String questionText,
                int points,
                boolean correctAnswer) {

            super(questionText, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {

            return Boolean.parseBoolean(answer)
                    == correctAnswer;
        }
    }

    static class Answer {

        private Question question;
        private String response;

        public Answer(
                Question question,
                String response) {

            this.question = question;
            this.response = response;
        }

        public int getScore() {

            if (question.evaluate(response)) {
                return question.getPoints();
            }

            return 0;
        }

        public Question getQuestion() {
            return question;
        }
    }

    static class Examination {

        private String name;
        private List<Question> questions =
                new ArrayList<>();

        public Examination(String name) {
            this.name = name;
        }

        public void addQuestion(Question question) {
            questions.add(question);
        }

        public String getName() {
            return name;
        }

        public List<Question> getQuestions() {
            return questions;
        }
    }

    static class Attempt {

        private String student;
        private Examination examination;
        private List<Answer> answers =
                new ArrayList<>();

        private boolean submitted = false;

        public Attempt(
                String student,
                Examination examination) {

            this.student = student;
            this.examination = examination;
        }

        public void recordAnswer(
                Question question,
                String response) {

            if (submitted) {

                System.out.println(
                    "Cannot change answers for a submitted examination."
                );

                return;
            }

            answers.add(
                new Answer(question, response)
            );

            System.out.println(
                "Answer recorded."
            );
        }

        public void submit() {

            if (submitted) {
                return;
            }

            submitted = true;

            int totalScore = 0;
            int totalPoints = 0;

            for (Answer answer : answers) {

                int score = answer.getScore();

                totalScore += score;
                totalPoints +=
                        answer.getQuestion().getPoints();

                System.out.println(
                    "Question: "
                    + (score > 0
                    ? "Correct"
                    : "Incorrect")
                    + " ("
                    + score
                    + " points)"
                );
            }

            System.out.println(
                "Total score: "
                + totalScore
                + "/"
                + totalPoints
            );
        }
    }

    public static void main(String[] args) {

        Examination exam =
                new Examination("Exam A");

        MultipleChoiceQuestion q1 =
                new MultipleChoiceQuestion(
                    "Which is correct?",
                    5,
                    "C"
                );

        TrueFalseQuestion q2 =
                new TrueFalseQuestion(
                    "Java is object-oriented.",
                    5,
                    false
                );

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        Attempt attempt =
                new Attempt(
                    "Student 1",
                    exam
                );

        System.out.println(
            "Exam A started by Student 1."
        );

        attempt.recordAnswer(q1, "C");
        attempt.recordAnswer(q2, "True");

        System.out.println(
            "Exam A submitted by Student 1."
        );

        attempt.submit();

        attempt.recordAnswer(q1, "A");
    }
}