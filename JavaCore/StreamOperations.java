
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

// Complete Java Stream API Operations
public class StreamOperationsDemo {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(
            10, 20, 30, 40, 50, 20, 30
        );

        List<String> names = Arrays.asList(
            "Akshay", "Mouni", "Ravi", "Anu", "Kiran"
        );

        // ==========================================
        // 1. INTERMEDIATE OPERATIONS
        // ==========================================

        // 1. filter() - Select elements based on condition
        System.out.println("\n1. FILTER");
        numbers.stream()
               .filter(n -> n > 20)
               .forEach(System.out::println);


        // 2. map() - Transform each element
        System.out.println("\n2. MAP");
        numbers.stream()
               .map(n -> n * 2)
               .forEach(System.out::println);


        // 3. flatMap() - Convert nested streams into one stream
        System.out.println("\n3. FLATMAP");
        List<List<Integer>> nested = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(3, 4),
            Arrays.asList(5, 6)
        );

        nested.stream()
              .flatMap(list -> list.stream())
              .forEach(System.out::println);


        // 4. distinct() - Remove duplicate elements
        System.out.println("\n4. DISTINCT");
        numbers.stream()
               .distinct()
               .forEach(System.out::println);


        // 5. sorted() - Sort elements in ascending order
        System.out.println("\n5. SORTED ASCENDING");
        numbers.stream()
               .sorted()
               .forEach(System.out::println);


        // 6. sorted(Comparator) - Sort in descending order
        System.out.println("\n6. SORTED DESCENDING");
        numbers.stream()
               .sorted(Comparator.reverseOrder())
               .forEach(System.out::println);


        // 7. limit() - Get first n elements
        System.out.println("\n7. LIMIT");
        numbers.stream()
               .limit(3)
               .forEach(System.out::println);


        // 8. skip() - Skip first n elements
        System.out.println("\n8. SKIP");
        numbers.stream()
               .skip(3)
               .forEach(System.out::println);


        // 9. peek() - Perform an action while processing elements
        System.out.println("\n9. PEEK");
        numbers.stream()
               .peek(n -> System.out.println("Before: " + n))
               .map(n -> n * 2)
               .forEach(n -> System.out.println("After: " + n));


        // 10. mapToInt() - Convert to IntStream
        System.out.println("\n10. MAP TO INT");
        numbers.stream()
               .mapToInt(n -> n)
               .forEach(System.out::println);


        // 11. mapToLong() - Convert to LongStream
        System.out.println("\n11. MAP TO LONG");
        numbers.stream()
               .mapToLong(n -> n)
               .forEach(System.out::println);


        // 12. mapToDouble() - Convert to DoubleStream
        System.out.println("\n12. MAP TO DOUBLE");
        numbers.stream()
               .mapToDouble(n -> n)
               .forEach(System.out::println);


        // 13. Java 9+ takeWhile() - Take elements while condition is true
        System.out.println("\n13. TAKE WHILE");
        numbers.stream()
               .takeWhile(n -> n <= 30)
               .forEach(System.out::println);


        // 14. Java 9+ dropWhile() - Skip elements while condition is true
        System.out.println("\n14. DROP WHILE");
        numbers.stream()
               .dropWhile(n -> n <= 30)
               .forEach(System.out::println);


        // ==========================================
        // 2. TERMINAL OPERATIONS
        // ==========================================

        // 1. forEach() - Process each element
        System.out.println("\n1. FOR EACH");
        numbers.stream().forEach(System.out::println);


        // 2. forEachOrdered() - Process elements in encounter order
        System.out.println("\n2. FOR EACH ORDERED");
        numbers.parallelStream()
               .forEachOrdered(System.out::println);


        // 3. toArray() - Convert stream into array
        System.out.println("\n3. TO ARRAY");
        Object[] arr = numbers.stream().toArray();
        System.out.println(Arrays.toString(arr));


        // 4. reduce() - Combine elements into one result
        System.out.println("\n4. REDUCE");
        int sum = numbers.stream()
                         .reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum);


        // 5. collect() - Convert stream into a collection
        System.out.println("\n5. COLLECT");
        List<Integer> result = numbers.stream()
                                      .filter(n -> n > 20)
                                      .collect(Collectors.toList());
        System.out.println(result);


        // 6. count() - Count elements
        System.out.println("\n6. COUNT");
        System.out.println(numbers.stream().count());


        // 7. min() - Find minimum element
        System.out.println("\n7. MIN");
        System.out.println(
            numbers.stream().min(Integer::compareTo).get()
        );


        // 8. max() - Find maximum element
        System.out.println("\n8. MAX");
        System.out.println(
            numbers.stream().max(Integer::compareTo).get()
        );


        // 9. anyMatch() - At least one element matches
        System.out.println("\n9. ANY MATCH");
        System.out.println(
            numbers.stream().anyMatch(n -> n > 40)
        );


        // 10. allMatch() - All elements match
        System.out.println("\n10. ALL MATCH");
        System.out.println(
            numbers.stream().allMatch(n -> n > 0)
        );


        // 11. noneMatch() - No element matches
        System.out.println("\n11. NONE MATCH");
        System.out.println(
            numbers.stream().noneMatch(n -> n < 0)
        );


        // 12. findFirst() - Get first element
        System.out.println("\n12. FIND FIRST");
        System.out.println(
            numbers.stream().findFirst().get()
        );


        // 13. findAny() - Get any element
        System.out.println("\n13. FIND ANY");
        System.out.println(
            numbers.stream().findAny().get()
        );


        // 14. iterator() - Get iterator from stream
        System.out.println("\n14. ITERATOR");
        Iterator<Integer> iterator = numbers.stream().iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        // ==========================================
        // 3. COLLECTORS OPERATIONS
        // ==========================================

        // 1. toList() - Collect into list (Java 16+)
        System.out.println("\n1. TO LIST");
        List<Integer> list = numbers.stream().toList();
        System.out.println(list);


        // 2. toSet() - Collect into set
        System.out.println("\n2. TO SET");
        Set<Integer> set = numbers.stream()
                                  .collect(Collectors.toSet());
        System.out.println(set);


        // 3. joining() - Join strings
        System.out.println("\n3. JOINING");
        String joined = names.stream()
                             .collect(Collectors.joining(", "));
        System.out.println(joined);


        // 4. groupingBy() - Group elements by condition/key
        System.out.println("\n4. GROUPING BY");
        Map<Integer, List<String>> grouped = names.stream()
            .collect(Collectors.groupingBy(String::length));
        System.out.println(grouped);


        // 5. partitioningBy() - Divide into true and false groups
        System.out.println("\n5. PARTITIONING BY");
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
            .collect(Collectors.partitioningBy(n -> n > 30));
        System.out.println(partitioned);


        // 6. counting() - Count elements using collector
        System.out.println("\n6. COUNTING");
        long count = numbers.stream()
                            .collect(Collectors.counting());
        System.out.println(count);


        // 7. summingInt() - Calculate sum
        System.out.println("\n7. SUMMING INT");
        int total = numbers.stream()
                           .collect(Collectors.summingInt(n -> n));
        System.out.println(total);


        // 8. averagingInt() - Calculate average
        System.out.println("\n8. AVERAGING INT");
        double average = numbers.stream()
                                .collect(Collectors.averagingInt(n -> n));
        System.out.println(average);


        // 9. summarizingInt() - Get count, sum, min, max and average
        System.out.println("\n9. SUMMARIZING INT");
        IntSummaryStatistics stats = numbers.stream()
            .collect(Collectors.summarizingInt(n -> n));

        System.out.println(stats);


        // ==========================================
        // 4. PRIMITIVE STREAM OPERATIONS
        // ==========================================

        // IntStream sum()
        System.out.println("\n1. INTSTREAM SUM");
        System.out.println(numbers.stream()
                                  .mapToInt(n -> n)
                                  .sum());


        // IntStream average()
        System.out.println("\n2. INTSTREAM AVERAGE");
        System.out.println(numbers.stream()
                                  .mapToInt(n -> n)
                                  .average().getAsDouble());


        // IntStream range()
        System.out.println("\n3. INTSTREAM RANGE");
        IntStream.range(1, 5).forEach(System.out::println);


        // IntStream rangeClosed()
        System.out.println("\n4. INTSTREAM RANGE CLOSED");
        IntStream.rangeClosed(1, 5).forEach(System.out::println);


        // boxed() - Convert primitive stream to object stream
        System.out.println("\n5. BOXED");
        IntStream.range(1, 5)
                 .boxed()
                 .forEach(System.out::println);


        // ==========================================
        // 5. PARALLEL STREAM
        // ==========================================

        System.out.println("\nPARALLEL STREAM");

        numbers.parallelStream()
               .forEach(System.out::println);

    }
}
