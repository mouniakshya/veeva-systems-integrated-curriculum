
import java.util.HashMap;
import java.util.Map;

public class PopulationDirectory {

    // Nested HashMap
    private HashMap<String, HashMap<String, Long>> population =
            new HashMap<>();


    // 1. ADD STATE
    public void addState(String stateName) {

        if (population.containsKey(stateName)) {

            System.out.println("State already exists!");

            return;
        }

        population.put(stateName, new HashMap<>());

        System.out.println("State added successfully!");
    }


    // 2. ADD CITY
    public void addCity(String stateName, String cityName,
                        long cityPopulation) {

        if (!population.containsKey(stateName)) {

            System.out.println("State not found!");

            return;
        }

        if (cityPopulation < 0) {

            System.out.println("Population cannot be negative!");

            return;
        }

        HashMap<String, Long> cities = population.get(stateName);

        if (cities.containsKey(cityName)) {

            System.out.println("City already exists in this state!");

            return;
        }

        cities.put(cityName, cityPopulation);

        System.out.println("City added successfully!");
    }


    // 3. UPDATE POPULATION
    public void updatePopulation(String stateName, String cityName,
                                 long newPopulation) {

        if (!population.containsKey(stateName)) {

            System.out.println("State not found!");

            return;
        }

        if (newPopulation < 0) {

            System.out.println("Population cannot be negative!");

            return;
        }

        HashMap<String, Long> cities = population.get(stateName);

        if (!cities.containsKey(cityName)) {

            System.out.println("City not found!");

            return;
        }

        cities.put(cityName, newPopulation);

        System.out.println("Population updated successfully!");
    }


    // 4. QUERY 1: CITY LOOKUP
    public void cityLookup(String stateName, String cityName) {

        if (!population.containsKey(stateName)) {

            System.out.println("State not found!");

            return;
        }

        HashMap<String, Long> cities = population.get(stateName);

        if (!cities.containsKey(cityName)) {

            System.out.println("City not found!");

            return;
        }

        System.out.println("\nCity: " + cityName);

        System.out.println("State: " + stateName);

        System.out.println("Population: " + cities.get(cityName));
    }


    // 5. QUERY 2: STATE LEVEL AGGREGATION
    public void stateAggregation(String stateName) {

        if (!population.containsKey(stateName)) {

            System.out.println("State not found!");

            return;
        }

        HashMap<String, Long> cities = population.get(stateName);

        long totalPopulation = 0;

        System.out.println("\n===== " + stateName + " =====");

        for (Map.Entry<String, Long> entry : cities.entrySet()) {

            System.out.println(
                    entry.getKey() + " : " + entry.getValue()
            );

            totalPopulation += entry.getValue();
        }

        System.out.println("-------------------------");

        System.out.println("Total Population: " + totalPopulation);
    }


    // 6. QUERY 3: GLOBAL CITY SEARCH
    public void globalSearch(String cityName) {

        boolean found = false;

        for (Map.Entry<String, HashMap<String, Long>> stateEntry
                : population.entrySet()) {

            String stateName = stateEntry.getKey();

            HashMap<String, Long> cities = stateEntry.getValue();

            if (cities.containsKey(cityName)) {

                System.out.println("\nCity: " + cityName);

                System.out.println("State: " + stateName);

                System.out.println(
                        "Population: " + cities.get(cityName)
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println("City not found in the directory!");
        }
    }


    // 7. QUERY 4: THRESHOLD FILTERING
    public void thresholdFiltering(long threshold) {

        boolean found = false;

        System.out.println("\n===== CITIES ABOVE POPULATION "
                + threshold + " =====");

        for (Map.Entry<String, HashMap<String, Long>> stateEntry
                : population.entrySet()) {

            String stateName = stateEntry.getKey();

            HashMap<String, Long> cities = stateEntry.getValue();

            for (Map.Entry<String, Long> cityEntry
                    : cities.entrySet()) {

                if (cityEntry.getValue() > threshold) {

                    System.out.println(
                            "State: " + stateName +
                                    " | City: " + cityEntry.getKey() +
                                    " | Population: " + cityEntry.getValue()
                    );

                    found = true;
                }
            }
        }

        if (!found) {

            System.out.println("No cities found above this threshold!");
        }
    }


    // 8. DISPLAY ALL STATES AND CITIES
    public void displayAll() {

        if (population.isEmpty()) {

            System.out.println("Directory is empty!");

            return;
        }

        System.out.println("\n===== INDIA POPULATION DIRECTORY =====");

        for (Map.Entry<String, HashMap<String, Long>> stateEntry
                : population.entrySet()) {

            System.out.println("\nState: " + stateEntry.getKey());

            for (Map.Entry<String, Long> cityEntry
                    : stateEntry.getValue().entrySet()) {

                System.out.println(
                        cityEntry.getKey() + " : " + cityEntry.getValue()
                );
            }
        }
    }
}
