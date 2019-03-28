package main.java.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import main.java.sample.Controller;
import main.java.sample.Course;
import main.java.sample.queryDB;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Comparator;

public class MainScreenController {
    @FXML
    public javafx.scene.control.TextField txt_searchUser;
    public javafx.scene.control.TextField txt_searchDestination;
    public GridPane advancedSearchBox;
    public CheckBox advanceSearchCheckbox;
    public CheckBox cb_isTest;
    public CheckBox cb_isAttendance;
    public DatePicker timePicker;
    public TableView<Course> coursesTable;

    public void initialize(){
        queryDB query=new queryDB();
        ObservableList<Course> courses = query.getAllCourse();
        if(courses != null)
            setTableData(courses);
    }

    private void setTableData(ObservableList<Course> courses) {
        TableColumn actionCol = new TableColumn();
        TableColumn<Course, String> courseIDCol = new TableColumn<Course, String>("מספר קורס");
        TableColumn<Course, String> courseNameCol = new TableColumn<Course, String>("שם הקורס");
        TableColumn<Course, String> isAttendanceCol = new TableColumn<Course, String>("נוכחות");
        TableColumn<Course, String> isTestCol = new TableColumn<Course, String>("כולל מבחן");
        TableColumn<Course, String> startTimeCol = new TableColumn<Course, String>("שעות הקורס");


        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<Course, String>, TableCell<Course, String>> cellFactory
                = new Callback<TableColumn<Course, String>, TableCell<Course, String>>() {
            @Override
            public TableCell call(final TableColumn<Course, String> param) {
                final TableCell<Course, String> cell = new TableCell<Course, String>() {
                    final Button btn = new Button("לצפייה");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                    watchCourse(getTableView().getItems().get(getIndex()));
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        actionCol.setCellFactory(cellFactory);
        courseIDCol.setCellValueFactory(new PropertyValueFactory<>("idCourse"));
        courseNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        isAttendanceCol.setCellValueFactory(new PropertyValueFactory<>("attendence"));
        isTestCol.setCellValueFactory(new PropertyValueFactory<>("test"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("hours"));

        /*isTestCol.setCellFactory(column -> {
            TableCell<Course, String> cell = new TableCell<Course, String>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        if(item == "true")
                            setText("כן");
                        else
                            setText("לא");
                    }
                }
            };
            return cell;
        });*/

        courseIDCol.setSortType(TableColumn.SortType.ASCENDING);
        coursesTable.setItems(courses);
        //destinationCol.getColumns().addAll(destinationCountryCol, destinationCityCol);
        coursesTable.getColumns().addAll(actionCol, courseIDCol, courseNameCol, isAttendanceCol, isTestCol, startTimeCol);

        setFilters(courses);
    }

    private void setFilters(ObservableList<Course> flights) {
       /* combo_sort.valueProperty().addListener((observable -> {
            if (combo_sort.getSelectionModel().getSelectedItem() == null || combo_sort.getSelectionModel().getSelectedItem().equals("לפי מס' טיסה")) {
                FXCollections.sort(flights, Comparator.comparingInt(Flight::getFlightID));
            } else {
                Comparator<Flight> priceComparator = Comparator.comparingInt(Flight::getPrice);
                if (combo_sort.getSelectionModel().getSelectedItem().equals("מהיקר לזול"))
                    priceComparator = priceComparator.reversed();
                FXCollections.sort(flights, priceComparator);
            }
        }));
*/
        FilteredList<Course> filteredData = new FilteredList<>(flights, p -> true);

        txt_searchDestination.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(flight -> filter(flight, newValue)));
        cb_isTest.selectedProperty().addListener((observable) -> filteredData.setPredicate(course -> filter(course, "")));
        cb_isAttendance.selectedProperty().addListener((observable -> filteredData.setPredicate(course -> filter(course, ""))));
        timePicker.valueProperty().addListener((observable -> filteredData.setPredicate(course -> filter(course, ""))));

        SortedList<Course> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(coursesTable.comparatorProperty());
        coursesTable.setItems(sortedData);
    }

    public boolean filter(Course course, String newValue) {

        String lowerCaseFilter = newValue.toLowerCase();
        if (!course.getName().toLowerCase().contains(lowerCaseFilter) &&
                !course.getName().toLowerCase().contains(lowerCaseFilter)) {
            return false;
        }

        //date
        if (timePicker.getValue() != null) {
            Instant instant = Instant.from(timePicker.getValue().atStartOfDay(ZoneId.systemDefault()));
            String start = java.util.Date.from(instant).toString();
            if (course.getHours().startsWith(start))
                return false;
        }

        //is connection
        if (cb_isAttendance.isSelected() && course.isAttendence())
            return false;

        //is separate
        if (cb_isTest.isSelected() && course.isTest())
            return false;

        return true;
    }

    private void watchCourse(Course course) {
    }

    public void advanceSearchChacked() {
        if (advanceSearchCheckbox.isSelected())
            advancedSearchBox.setVisible(true);
        else {
            advancedSearchBox.setVisible(false);

            timePicker.setValue(null);
            cb_isAttendance.setSelected(false);
            cb_isTest.setSelected(false);
        }
    }
}
