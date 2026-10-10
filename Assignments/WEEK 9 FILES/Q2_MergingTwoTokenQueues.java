import java.util.Arrays;

public class Q2_MergingTwoTokenQueues {

    public static int[] mergeTokens(int[] counterA, int[] counterB) {
        int m = counterA.length;
        int n = counterB.length;

        int[] result = new int[m + n];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < m && j < n) {
            if (counterA[i] <= counterB[j]) {
                result[k] = counterA[i];
                i++;
            } else {
                result[k] = counterB[j];
                j++;
            }
            k++;
        }

        while (i < m) {
            result[k] = counterA[i];
            i++;
            k++;
        }

        while (j < n) {
            result[k] = counterB[j];
            j++;
            k++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] counterA = {3, 8, 15, 20};
        int[] counterB = {5, 8, 12};

        System.out.println(Arrays.toString(
            mergeTokens(counterA, counterB)
        ));

        int[] empty = {};
        int[] another = {4, 9};

        System.out.println(Arrays.toString(
            mergeTokens(empty, another)
        ));
    }
}