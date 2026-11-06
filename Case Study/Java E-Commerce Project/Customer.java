
public class Customer {
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String password;

    public Customer(int id, String name, String email,
                    String address, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
