package by.KuvonchbekN.model;

public class User extends BaseModel {
    private String phoneNumber;
    private String password;
    private boolean isSuperAdmin;
    private UserRole role;

    public User(String name, String phoneNumber, String password, UserRole role) {
        super(name);
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
