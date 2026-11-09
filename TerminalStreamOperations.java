import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        List<Integer> nums =
            Arrays.asList(10, 20, 30, 40, 50);

        // 1. forEach() - Print elements
        System.out.println("ForEach:");
        nums.stream()
            .forEach(System.out::println);

        // 2. collect() - Convert into List
        System.out.println("Collect:");
        List<Integer> list = nums.stream()
            .filter(n -> n > 20)
            .collect(Collectors.toList());

        System.out.println(list);

        // 3. reduce() - Find sum
        System.out.println("Reduce:");
        int total = nums.stream()
            .reduce(0, (a, b) -> a + b);

        System.out.println(total);

        // 4. count() - Count elements
        System.out.println("Count:");
        long count = nums.stream().count();

        System.out.println(count);

        // 5. min() - Find minimum
        System.out.println("Minimum:");
        Optional<Integer> min = nums.stream()
            .min(Comparator.naturalOrder());

        System.out.println(min.get());

        // 6. max() - Find maximum
        System.out.println("Maximum:");
        Optional<Integer> max = nums.stream()
            .max(Comparator.naturalOrder());

        System.out.println(max.get());

        // 7. findFirst() - Find first element
        System.out.println("FindFirst:");
        Optional<Integer> first = nums.stream()
            .findFirst();

        System.out.println(first.get());

        // 8. anyMatch() - Check condition
        System.out.println("AnyMatch:");
        boolean result = nums.stream()
            .anyMatch(n -> n > 40);

        System.out.println(result);

        // 9. groupingBy() - Group even and odd
        System.out.println("GroupingBy:");
        Map<String, List<Integer>> groups =
            nums.stream().collect(
                Collectors.groupingBy(
                    n -> n % 2 == 0 ? "Even" : "Odd"
                )
            );

        System.out.println(groups);

        // 10. partitioningBy() - Divide into two groups
        System.out.println("PartitioningBy:");
        Map<Boolean, List<Integer>> parts =
            nums.stream().collect(
                Collectors.partitioningBy(
                    n -> n > 25
                )
            );

        System.out.println(parts);

        // 11. sum() - Calculate total
        System.out.println("Sum:");
        int sum = nums.stream()
            .mapToInt(Integer::intValue)
            .sum();

        System.out.println(sum);
    }
}
