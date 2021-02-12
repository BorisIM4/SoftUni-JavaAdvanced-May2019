package L10_Exam_Preparation_One.P1_Cooking;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Queue
        ArrayDeque<Integer> liquids = Arrays
                .stream(scanner
                        .nextLine()
                        .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        //Stack
        ArrayDeque<Integer> ingredients = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt)
                .forEach(ingredients::push);

        Map<Integer, String> cookingTable = new HashMap<>();
        cookingTable.put(25, "Bread");
        cookingTable.put(50, "Cake");
        cookingTable.put(75, "Pastry");
        cookingTable.put(100, "Fruit Pie");

        Map<String, Integer> productCooked = new TreeMap<>();

        cookingTable.values()
                .forEach(p -> productCooked.put(p, 0));

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            int currentLiquid = liquids.poll();
            int currentIngridient = ingredients.pop();

            int sum = currentLiquid + currentIngridient;
            if (ableToCookProiduct(sum)) {
                String product = cookingTable.get(sum);
                productCooked.put(product, productCooked.get(product) + 1);
            } else {
                ingredients.push(currentIngridient + 3);
            }

        }
        System.out.println();

        if (hasCookedEachMeal(productCooked)) {
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        } else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to to cook everything.");
        }

        System.out.println("Liquids left: " + getElementsInfo(liquids));
        System.out.println("Ingredients left: " + getElementsInfo(ingredients));

        productCooked.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
    }

    private static String getElementsInfo(ArrayDeque<Integer> deque) {
        return deque.isEmpty()
                ? "none"
                : deque
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    private static boolean hasCookedEachMeal(Map<String, Integer> productCooked) {
         return productCooked.values().stream().noneMatch(c -> c == 0);
    }

    private static boolean ableToCookProiduct(int sum) {
        return sum == 25 || sum == 50 || sum == 75 || sum ==100;
    }
}
