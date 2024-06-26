package com.example.mdsp.models;

public abstract class User {
    protected final int user_id;
    protected String first_name;
    protected String last_name;
    protected String email;
    protected String password_hash;
    protected Role role;
    protected String phone;

    //construct
    public User(int user_id, String first_name, String last_name, String email, String password_hash, Role role, String phone) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password_hash = password_hash;
        this.role = role;
        this.phone = phone;
    }

    public User(String first_name, String last_name, String email, String password_hash, Role role, String phone) {
        this.user_id = -1;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password_hash = password_hash;
        this.role = role;
        this.phone = phone;
    }


    //setters getters
    public int getUserId() {
        return user_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() { return password_hash; }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public abstract String getRole();
}