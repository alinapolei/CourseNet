package main.java.sample;

public class Course {

    private String idCourse;
    private String name;
    private double pointNum;
    private double rating;
    private int day;
    private String hours;
    private boolean test;
    private boolean attendence;
    private int numOfworks;
    private String descp;


    public Course(String idCourse, String name, double pointNum, double rating, int day, String hours, boolean test, boolean attendence, int numOfworks, String descp, String sylabos) {
        this.idCourse = idCourse;
        this.name = name;
        this.pointNum = pointNum;
        this.rating = rating;
        this.day = day;
        this.hours = hours;
        this.test = test;
        this.attendence = attendence;
        this.numOfworks = numOfworks;
        this.descp = descp;
        this.sylabos = sylabos;
    }

    public String getSylabos() {
        return sylabos;
    }

    public void setSylabos(String sylabos) {
        this.sylabos = sylabos;
    }

    private String sylabos;

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPointNum() {
        return pointNum;
    }

    public void setPointNum(double pointNum) {
        this.pointNum = pointNum;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public boolean isAttendence() {
        return attendence;
    }

    public void setAttendence(boolean attendence) {
        this.attendence = attendence;
    }

    public int getNumOfworks() {
        return numOfworks;
    }

    public void setNumOfworks(int numOfworks) {
        this.numOfworks = numOfworks;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}