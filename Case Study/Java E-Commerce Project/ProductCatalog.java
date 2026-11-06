
import java.util.ArrayList;

public class ProductCatalog {
    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Product> findByName(String name) {
        ArrayList<Product> result = new ArrayList<>();

        for (Product p : products) {
            if (p.getName().toLowerCase()
                    .contains(name.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    public ArrayList<Product> findByNamePrice(
            String name, double price) {
        ArrayList<Product> result = new ArrayList<>();

        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)
                    && p.getPrice() == price) {
                result.add(p);
            }
        }
        return result;
    }

    public ArrayList<Product> findByPriceRange(
            String name, double min, double max) {
        ArrayList<Product> result = new ArrayList<>();

        for (Product p : products) {
            if (p.getName().toLowerCase()
                    .contains(name.toLowerCase())
                    && p.getPrice() >= min
                    && p.getPrice() <= max) {
                result.add(p);
            }
        }
        return result;
    }

    public ArrayList<Product> findByCompany(
            String name, String company) {
        ArrayList<Product> result = new ArrayList<>();

        for (Product p : products) {
            if (p.getName().toLowerCase()
                    .contains(name.toLowerCase())
                    && p.getCompany().equalsIgnoreCase(company)) {
                result.add(p);
            }
        }
        return result;
    }

    public ArrayList<Product> findByNameCompanyPrice(
            String name, String company, double price) {
        ArrayList<Product> result = new ArrayList<>();

        for (Product p : products) {
            if (p.getName().toLowerCase()
                    .contains(name.toLowerCase())
                    && p.getCompany().equalsIgnoreCase(company)
                    && p.getPrice() == price) {
                result.add(p);
            }
        }
        return result;
    }

    public boolean deleteProduct(int id) {
        return products.removeIf(p -> p.getId() == id);
    }

    public boolean updatePrice(int id, double price) {
        Product p = findById(id);

        if (p == null) {
            return false;
        }

        p.setPrice(price);
        return true;
    }

    public void showAllProducts() {
        for (Product p : products) {
            System.out.println(p);
        }
    }
}
