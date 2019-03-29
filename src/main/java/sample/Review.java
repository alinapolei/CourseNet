package main.java.sample;


import java.time.LocalDate;
import java.util.Date;

public class Review {

    private String CourseId;
    private String date;
    private String description;

    public Review() { }

    public Review( String CourseId , String description , String d)
    {
        this.CourseId = CourseId;
        this.date = d;
        this.description = description;
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
