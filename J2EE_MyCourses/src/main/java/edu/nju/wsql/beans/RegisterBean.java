package edu.nju.wsql.beans;

public class RegisterBean {
    private String password;
    private String confirmPassword;
    private String name;
    private String email;

    public RegisterBean() {
        password = "";
        confirmPassword = "";
        name = "";
        email = "";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}