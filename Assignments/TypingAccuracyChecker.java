public class TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int firstMismatchPos = -1;
        char originalChar = ' ';
        char typedChar = ' ';

        int length = Math.min(original.length(), typed.length());

        for (int i = 0; i < length; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // 1-based index position
                originalChar = original.charAt(i);
                typedChar = typed.charAt(i);
            }
        }

        double accuracy = ((double) matched / original.length()) * 100;

        if (firstMismatchPos != -1) {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d ('%c' vs '%c')%n",
                    matched, original.length(), accuracy, firstMismatchPos, originalChar, typedChar);
        } else {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | No Mismatches%n",
                    matched, original.length(), accuracy);
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}