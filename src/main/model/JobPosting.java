package model;

public class JobPosting {

    private int JOBid;
    private String role;
    private String company;
    private String location;
    private boolean status;

    public JobPosting(String role, String company,String location, boolean status) {
        this.role = role;
        this.company = company;
        this.location = location;
        this.status = status;
    }

    public int getJOBid() {
        return JOBid;
    }

    public void setJOBid(int JOBid) {
        this.JOBid = JOBid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
