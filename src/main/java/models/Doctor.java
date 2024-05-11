package models;

public final class Doctor extends User {
    private String specialization;
    private String description;
    private int office_id;

    public Doctor(int user_id, String first_name, String last_name, String email, String password_hash, String role, String phone, String specialization, String description, int office_id) {
        super(user_id, first_name, last_name, email, password_hash, role, phone);
        this.specialization = specialization;
        this.description = description;
        this.office_id = office_id;
    }

    //setters getters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOfficeId() {
        return office_id;
    }

    public void setOfficeId(int office_id) {
        this.office_id = office_id;
    }

    @Override
    public String getRole() {
        return Role.doctor.toString();
    }
}

