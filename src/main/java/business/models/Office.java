package business.models;

public final class Office {
    private int office_id;
    private String name;
    private String address;
    private String phone;
    private String email;

    public Office(int office_id, String name, String address, String phone, String email) {
        this.office_id = office_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getOfficeId() {
        return office_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

