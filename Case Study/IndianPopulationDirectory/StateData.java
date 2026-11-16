
import java.util.HashMap;

public class StateData {

    private String stateName;

    private HashMap<String, Long> cities;

    public StateData(String stateName) {

        this.stateName = stateName;

        cities = new HashMap<>();
    }

    public String getStateName() {
        return stateName;
    }

    public HashMap<String, Long> getCities() {
        return cities;
    }

    public void addCity(String cityName, long population) {

        cities.put(cityName, population);
    }

    public void updatePopulation(String cityName, long population) {

        cities.put(cityName, population);
    }
}
