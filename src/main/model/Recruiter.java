package model;

public class Recruiter {

    private int RecruiterID;
    private String name;
    private String company;

    public Recruiter(String name, String company) {
        this.company = company;
        this.name = name;
    }

    public int getRecruiterID() {
        return RecruiterID;
    }

    public void setRecruiterID(int recruiterID) {
        RecruiterID = recruiterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
