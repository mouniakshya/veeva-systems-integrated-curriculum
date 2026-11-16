
public class Company {

    private String companyId;
    private String companyName;
    private String contact;
    private String location;

    public Company(String companyId, String companyName,
                   String contact, String location) {

        this.companyId = companyId;
        this.companyName = companyName;
        this.contact = contact;
        this.location = location;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContact() {
        return contact;
    }

    public String getLocation() {
        return location;
    }

    public void updateDetails(String name, String contact,
                              String location) {

        this.companyName = name;
        this.contact = contact;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Company ID: " + companyId +
                "\nCompany Name: " + companyName +
                "\nContact: " + contact +
                "\nLocation: " + location;
    }
}
