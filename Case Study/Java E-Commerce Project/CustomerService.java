
import java.util.ArrayList;

public class CustomerService {
    private ArrayList<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer c) {
        customers.add(c);
        System.out.println("Customer registered");
    }

    public Customer findByName(String name) {
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public Customer findByEmail(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    public Customer findByPhone(String phone) {
        for (Customer c : customers) {
            if (c.getPhone().equals(phone)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Customer> findByCity(String city) {
        ArrayList<Customer> result = new ArrayList<>();

        for (Customer c : customers) {
            if (c.getAddress().toLowerCase()
                    .contains(city.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }

    public boolean login(String email, String password) {
        Customer c = findByEmail(email);
        return c != null && c.checkPassword(password);
    }

    public boolean deleteCustomer(int id) {
        return customers.removeIf(c -> c.getId() == id);
    }

    public void updateCustomerName(int id, String name) {
        for (Customer c : customers) {
            if (c.getId() == id) {
                c.setName(name);
                return;
            }
        }
    }
}
