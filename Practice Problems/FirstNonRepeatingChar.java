import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingChar {

    public static char findFirstNonRepeatingChar(String text) {
        Map<Character, Integer> counts = new LinkedHashMap<>();

        // Calculate frequencies[cite: 1]
        for (char c : text.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        // Scan left to right for first frequency of 1[cite: 1]
        for (char c : text.toCharArray()) {
            if (counts.get(c) == 1) {
                return c;
            }
        }

        return '\0'; // Return null character if no unique char exists
    }

    public static void test(String input) {
        char result = findFirstNonRepeatingChar(input);
        if (result != '\0') {
            System.out.println("Input: \"" + input + "\" -> First Non-Repeating Character: '" + result + "'");
        } else {
            System.out.println("Input: \"" + input + "\" -> No Non-Repeating Character Found");
        }
    }

    public static void main(String[] args) {
        test("swiss");
        test("aabbcc");
    }
}