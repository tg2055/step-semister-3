import java.util.HashMap;
import java.util.Map;

public class Q3_MostPopularCanteenOrder {

    public static Object[] mostPopular(String[] orders) {
        HashMap<String, Integer> count = new HashMap<>();

        for (String item : orders) {
            count.put(item, count.getOrDefault(item, 0) + 1);
        }

        String popularItem = orders[0];
        int highestCount = 0;

        for (String item : orders) {
            int currentCount = count.get(item);

            if (currentCount > highestCount) {
                highestCount = currentCount;
                popularItem = item;
            }
        }

        return new Object[]{popularItem, highestCount};
    }

    public static void main(String[] args) {
        String[] orders = {
            "dosa", "idli", "vada", "dosa",
            "idli", "dosa", "tea"
        };

        Object[] result = mostPopular(orders);

        System.out.println("(" + result[0] + ", " + result[1] + ")");

        String[] orders2 = {"tea", "coffee", "coffee", "tea"};

        Object[] result2 = mostPopular(orders2);

        System.out.println("(" + result2[0] + ", " + result2[1] + ")");
    }
}