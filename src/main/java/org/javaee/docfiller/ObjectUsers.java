package org.javaee.docfiller;

public class ObjectUsers {
    private int idpeople;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String company;
    private String jobPosition;
    public ObjectUsers(int idpeople, String firstName, String lastName, String phone, String email, String company, String jobPosition) {
        this.idpeople = idpeople;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.company = company;
        this.jobPosition = jobPosition;
    }
    public int getIdpeople() {
        return idpeople;
    }
    public void setIdpeople(int idpeople) {
        this.idpeople = idpeople;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }
}

