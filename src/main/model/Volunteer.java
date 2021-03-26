package model;

public class Volunteer {

    private int VolunteerID;
    private String name;
    private int age;

    public Volunteer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getVolunteerID() {
        return VolunteerID;
    }

    public void setVolunteerID(int volunteerID) {
        VolunteerID = volunteerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
