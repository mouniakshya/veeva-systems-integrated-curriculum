
import java.util.*;
import java.util.function.*;

public class LambdaExpressionsDemo {

    public static void main(String[] args) {

        // =========================================
        // 1. BASIC LAMBDA EXPRESSION
        // =========================================

        System.out.println("\n1. BASIC LAMBDA");

        // Without lambda
        Runnable oldWay = new Runnable() {
            public void run() {
                System.out.println("Hello Java");
            }
        };

        oldWay.run();

        // With lambda
        Runnable newWay = () -> System.out.println("Hello Lambda");

        newWay.run();


        // =========================================
        // 2. LAMBDA WITH PARAMETERS
        // =========================================

        System.out.println("\n2. LAMBDA WITH PARAMETERS");

        // One parameter
        Consumer<String> greet =
                name -> System.out.println("Hello " + name);

        greet.accept("Akshay");

        // Two parameters
        BiConsumer<String, Integer> student =
                (name, age) ->
                    System.out.println(name + " " + age);

        student.accept("Mouni", 21);


        // =========================================
        // 3. LAMBDA WITH RETURN VALUE
        // =========================================

        System.out.println("\n3. LAMBDA WITH RETURN");

        // Single statement - return is automatic
        Function<Integer, Integer> square =
                n -> n * n;

        System.out.println("Square: " + square.apply(5));

        // Multiple statements - use return
        Function<Integer, Integer> calculate =
                n -> {
                    int result = n * 2;
                    return result;
                };

        System.out.println("Double: " + calculate.apply(10));


        // =========================================
        // 4. FUNCTIONAL INTERFACE
        // =========================================

        System.out.println("\n4. FUNCTIONAL INTERFACE");

        // Custom functional interface
        Calculator addition = (a, b) -> a + b;

        Calculator subtraction = (a, b) -> a - b;

        Calculator multiplication = (a, b) -> a * b;

        System.out.println("Addition: " + addition.calculate(10, 5));
        System.out.println("Subtraction: " + subtraction.calculate(10, 5));
        System.out.println("Multiplication: " + multiplication.calculate(10, 5));


        // =========================================
        // 5. PREDICATE - TEST CONDITION
        // =========================================

        System.out.println("\n5. PREDICATE");

        Predicate<Integer> isEven = n -> n % 2 == 0;

        System.out.println(isEven.test(10));
        System.out.println(isEven.test(7));


        // Predicate with multiple conditions
        Predicate<Integer> isPositive = n -> n > 0;

        System.out.println(isEven.and(isPositive).test(8));
        System.out.println(isEven.or(isPositive).test(-3));
        System.out.println(isEven.negate().test(7));


        // =========================================
        // 6. FUNCTION - TRANSFORM VALUE
        // =========================================

        System.out.println("\n6. FUNCTION");

        Function<String, Integer> getLength =
                name -> name.length();

        System.out.println(getLength.apply("Java"));

        Function<Integer, Integer> doubleNumber =
                n -> n * 2;

        System.out.println(doubleNumber.apply(10));


        // =========================================
        // 7. CONSUMER - ACCEPT VALUE
        // =========================================

        System.out.println("\n7. CONSUMER");

        Consumer<String> printName =
                name -> System.out.println("Name: " + name);

        printName.accept("Akshay");

        // Consumer chaining
        Consumer<String> printUpper =
                name -> System.out.println(name.toUpperCase());

        printName.andThen(printUpper).accept("Mouni");


        // =========================================
        // 8. SUPPLIER - SUPPLY VALUE
        // =========================================

        System.out.println("\n8. SUPPLIER");

        Supplier<String> message =
                () -> "Welcome to Java Programming";

        System.out.println(message.get());

        Supplier<Double> randomNumber =
                () -> Math.random();

        System.out.println(randomNumber.get());


        // =========================================
        // 9. BICONSUMER
        // =========================================

        System.out.println("\n9. BICONSUMER");

        BiConsumer<String, Integer> display =
                (name, marks) ->
                    System.out.println(name + " scored " + marks);

        display.accept("Akshay", 95);


        // =========================================
        // 10. BIFUNCTION
        // =========================================

        System.out.println("\n10. BIFUNCTION");

        BiFunction<Integer, Integer, Integer> sum =
                (a, b) -> a + b;

        System.out.println("Sum: " + sum.apply(10, 20));


        // =========================================
        // 11. BIPREDICATE
        // =========================================

        System.out.println("\n11. BIPREDICATE");

        BiPredicate<Integer, Integer> isGreater =
                (a, b) -> a > b;

        System.out.println(isGreater.test(20, 10));


        // =========================================
        // 12. LAMBDA WITH COLLECTIONS
        // =========================================

        System.out.println("\n12. LAMBDA WITH COLLECTIONS");

        List<String> names = Arrays.asList(
                "Akshay", "Mouni", "Ravi", "Anu", "Kiran"
        );

        // forEach
        names.forEach(name -> System.out.println(name));

        // Sorting
        names.sort((a, b) -> a.compareTo(b));

        System.out.println("Sorted Names: " + names);

        // Filtering
        names.stream()
             .filter(name -> name.length() > 4)
             .forEach(System.out::println);


        // =========================================
        // 13. LAMBDA WITH MAP
        // =========================================

        System.out.println("\n13. LAMBDA WITH MAP");

        Map<Integer, String> students = new HashMap<>();

        students.put(101, "Akshay");
        students.put(102, "Mouni");
        students.put(103, "Ravi");

        // forEach with Map
        students.forEach((id, name) ->
                System.out.println(id + " : " + name));


        // =========================================
        // 14. METHOD REFERENCE
        // =========================================

        System.out.println("\n14. METHOD REFERENCE");

        List<String> languages = Arrays.asList(
                "Java", "Python", "C++"
        );

        // Lambda expression
        languages.forEach(name -> System.out.println(name));

        // Equivalent method reference
        languages.forEach(System.out::println);


        // =========================================
        // 15. CONSTRUCTOR REFERENCE
        // =========================================

        System.out.println("\n15. CONSTRUCTOR REFERENCE");

        Supplier<ArrayList<String>> createList =
                ArrayList::new;

        ArrayList<String> newList = createList.get();

        newList.add("Java");
        newList.add("Python");

        System.out.println(newList);


        // =========================================
        // 16. LAMBDA WITH THREADS
        // =========================================

        System.out.println("\n16. LAMBDA WITH THREADS");

        Thread thread = new Thread(() ->
                System.out.println("Thread running using Lambda")
        );

        thread.start();


        // =========================================
        // 17. LAMBDA WITH CUSTOM OBJECTS
        // =========================================

        System.out.println("\n17. LAMBDA WITH OBJECTS");

        List<Employee> employees = Arrays.asList(
                new Employee("Akshay", 30000),
                new Employee("Mouni", 45000),
                new Employee("Ravi", 25000)
        );

        // Sort employees by salary
        employees.sort(
                (e1, e2) -> Integer.compare(e1.salary, e2.salary)
        );

        employees.forEach(e ->
                System.out.println(e.name + " : " + e.salary)
        );

        // Filter employees
        employees.stream()
                 .filter(e -> e.salary > 30000)
                 .forEach(e -> System.out.println(e.name));


        System.out.println("\nPROGRAM COMPLETED");
    }


    // =========================================
    // CUSTOM FUNCTIONAL INTERFACE
    // =========================================

    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }


    // =========================================
    // EMPLOYEE CLASS
    // =========================================

    static class Employee {

        String name;
        int salary;

        Employee(String name, int salary) {
            this.name = name;
            this.salary = salary;
        }
    }
}
