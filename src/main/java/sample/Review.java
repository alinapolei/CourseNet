package main.java.sample;


import java.time.LocalDate;

public class Review {

    private String date;
    private String description;

    public Review() { }

    public Review(String description) {
        Main.localDate = LocalDate.now();
        this.date= String.valueOf(LocalDate.now());
        this.description = description;
    }

    public String getDate() {
        return date;
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
