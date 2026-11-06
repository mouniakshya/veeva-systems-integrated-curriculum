package com.ecommerce.model.user;

/**
 * Represents an administrator who manages products, stock, and order progression.
 */
public class Admin extends User {
    private String department;

    public Admin(String id, String name, String email, String phone, String password, String department) {
        super(id, name, email, phone, password);
        this.department = department != null ? department : "Operations";
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }

    @Override
    public String toString() {
        return String.format("Admin[id='%s', name='%s', email='%s', department='%s']",
                getId(), getName(), getEmail(), department);
    }
}
