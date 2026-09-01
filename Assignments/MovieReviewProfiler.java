public class MovieReviewProfiler {

    public static void classifyWordLengths(String review) {
        // Clean up punctuation and split by whitespace
        String[] words = review.replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+");

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {
            if (word.isEmpty()) continue;

            int len = word.length();
            if (len >= 1 && len <= 4) {
                shortCount++;
            } else if (len >= 5 && len <= 8) {
                mediumCount++;
            } else if (len >= 9) {
                longCount++;
            }
        }

        System.out.printf("Short: %d | Medium: %d | Long: %d%n", shortCount, mediumCount, longCount);
    }

    public static void main(String[] args) {
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}