package com.corejsf;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named("credential")
@RequestScoped

/**
 * The Credential class is created for CreateEmployee and EditEmployee demo purpose.
 * Delete this file once Backend team setup the models.
 */
public class Credential implements Serializable {
    private int empNo;
    private String userName;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private boolean active = true;

    public String createEmployee() {
        return "CREATE_EMPLOYEE";
    }

    public String editEmployee() {
        return "EDIT_EMPLOYEE";
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
