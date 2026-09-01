import java.util.Arrays;

public class PalindromeChecker {

    // 1. Iterative Approach[cite: 1]
    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 2. Recursive Approach[cite: 1]
    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    // 3. Array-Reversal Approach[cite: 1]
    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        return Arrays.equals(original, reversed);
    }

    public static void verifyPalindrome(String text) {
        String iterRes = isPalindromeIterative(text) ? "Palindrome" : "Not Palindrome";
        String recurRes = isPalindromeRecursive(text) ? "Palindrome" : "Not Palindrome";
        String arrayRes = isPalindromeArrayReversal(text) ? "Palindrome" : "Not Palindrome";

        System.out.println("Input: \"" + text + "\"");
        System.out.println("Iterative: " + iterRes + " | Recursive: " + recurRes + " | Array Reversal: " + arrayRes);
        System.out.println();
    }

    public static void main(String[] args) {
        verifyPalindrome("madam");
        verifyPalindrome("hello");
    }
}