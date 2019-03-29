package main.java.sample;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Review {

    private String CourseId;
    private String date;
    private String description;
    private double rank;


    public Review( String CourseId , String description ,String d,double rank)
    {
        this.CourseId = CourseId;
        this.date = d;
        this.rank=rank;
        this.description = description;
    }

    public Review( String CourseId , String description,double rank )
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDate localDate = LocalDate.now();
        this.CourseId = CourseId;
        this.date = localDate.toString();
        this.description = description;
        this.rank=rank;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public Review(String description) {
        this.date= String.valueOf(LocalDate.now());
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
