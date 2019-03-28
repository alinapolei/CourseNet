package main.java.sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class queryDB {

    public static Connection conn;
    Course course ;

    private static Connection connect() {
        // SQLite connection string
        //  DriverManager.getConnection("jdbc:sqlite:D:\\db\\my-db.sqlite");
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:sqlite:src/main/java/database/CourseNetDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("success");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    //region User
    // return: 0 if the insert succeed else 1
    public int insertCourse(Course course) {
        String sql = "INSERT INTO Coureses(Name,Description,PointNum,Sylbos,Rating,CourseHours,Test,numOfWorks,attendance,courseId , Day ) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,  course.getName());
            pstmt.setString(2,  course.getDescp());
            pstmt.setDouble(3,  course.getPointNum());
            pstmt.setString(4,  course.getSylabos());
            pstmt.setDouble(5, course.getRating());
            pstmt.setString(6, course.getHours());
            pstmt.setBoolean(7, course.isTest());
            pstmt.setInt(8, course.getNumOfworks());
            pstmt.setBoolean(9, course.isAttendence());
            pstmt.setString(10 ,course.getIdCourse());
            pstmt.setInt(11 ,course.getDay());


            pstmt.executeUpdate();
            //conn.close();

            return 0;
        } catch (SQLException e) {
            System.out.println(e);
            return 1;
        }
    }
    private  Course SearcByValue(String parameter, String sql) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1 , parameter);
            //
            ResultSet rs  = pstmt.executeQuery();
            if (rs.next())
                // conn.close();
                return new Course(rs.getString("courseId"),rs.getString("Name") , rs.getDouble("PointNum") , rs.getDouble("Rating") , rs.getInt("Day" ), rs.getString("CourseHours"), rs.getBoolean("Test") , rs.getBoolean("attendance") , rs.getInt("numOfWorks") , rs.getString("Description"), rs.getString("Sylbos")) ;
            else {
                //conn.close();
                return null;
            }
            // loop through the result set

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public  Course search_by_courseId(String courseId) throws SQLException {
        String sql = "SELECT Name,Description,PointNum,Sylbos,Rating,CourseHours,Test,numOfWorks,attendance,courseId,Day "
                + "FROM Coureses WHERE courseId = ?";
        return SearcByValue(courseId, sql);
    }


    public ObservableList<Course> getAllCoursePerDay(){
        String sql = "SELECT * FROM Coureses where Day = 2 ";
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            return Course_Per_Day_ResultSetToObservable(rs);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



    private ObservableList<Course> Course_Per_Day_ResultSetToObservable(ResultSet rs) throws SQLException {
        List<Course> Courses = new ArrayList<>();
        while (rs.next()) {
            Course f = new Course(rs.getString("courseId"),rs.getString("Name") , rs.getDouble("PointNum") , rs.getDouble("Rating") , rs.getInt("Day" ), rs.getString("CourseHours"), rs.getBoolean("Test") , rs.getBoolean("attendance") , rs.getInt("numOfWorks") , rs.getString("Description"), rs.getString("Sylbos")) ;

            Courses.add(f);
        }
        if(Courses != null) {
            ObservableList<Course> observableCourses = FXCollections.observableArrayList(Courses);
            return observableCourses;
        }
        return null;
    }

    public ObservableList<Course> getAllCourse(){
        String sql = "SELECT * FROM Coureses";
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            return CourseResultSetToObservable(rs);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



    private ObservableList<Course> CourseResultSetToObservable(ResultSet rs) throws SQLException {
        List<Course> Courses = new ArrayList<>();
        while (rs.next()) {
            Course f = new Course(rs.getString("courseId"),rs.getString("Name") , rs.getDouble("PointNum") , rs.getDouble("Rating") , rs.getInt("Day" ), rs.getString("CourseHours"), rs.getBoolean("Test") , rs.getBoolean("attendance") , rs.getInt("numOfWorks") , rs.getString("Description"), rs.getString("Sylbos")) ;

            Courses.add(f);
        }
        if(Courses != null) {
            ObservableList<Course> observableCourses = FXCollections.observableArrayList(Courses);
            return observableCourses;
        }
        return null;
    }


}