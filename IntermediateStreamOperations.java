import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        List<Integer> nums =
            Arrays.asList(10, 20, 10, 30, 40, 20, 50);

        // 1. filter() - Select even numbers
        System.out.println("Filter:");
        nums.stream()
            .filter(n -> n % 2 == 0)
            .forEach(System.out::println);

        // 2. map() - Multiply each number by 2
        System.out.println("Map:");
        nums.stream()
            .map(n -> n * 2)
            .forEach(System.out::println);

        // 3. flatMap() - Flatten nested lists
        System.out.println("FlatMap:");
        List<List<Integer>> list = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(3, 4)
        );

        list.stream()
            .flatMap(l -> l.stream())
            .forEach(System.out::println);

        // 4. peek() - View elements
        System.out.println("Peek:");
        nums.stream()
            .peek(n -> System.out.println("Value: " + n))
            .count();

        // 5. distinct() - Remove duplicates
        System.out.println("Distinct:");
        nums.stream()
            .distinct()
            .forEach(System.out::println);

        // 6. sorted() - Sort elements
        System.out.println("Sorted:");
        nums.stream()
            .sorted()
            .forEach(System.out::println);

        // 7. skip() - Skip first 2 elements
        System.out.println("Skip:");
        nums.stream()
            .skip(2)
            .forEach(System.out::println);

        // 8. limit() - Display first 3 elements
        System.out.println("Limit:");
        nums.stream()
            .limit(3)
            .forEach(System.out::println);
    }
}
