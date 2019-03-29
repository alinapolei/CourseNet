package main.java.View;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.java.sample.Course;
import main.java.sample.Review;
import main.java.sample.queryDB;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;

public class MainScreenController {
    @FXML
    public javafx.scene.control.TextField txt_searchUser;
    public javafx.scene.control.TextField txt_searchDestination;
    public GridPane advancedSearchBox;
    public CheckBox advanceSearchCheckbox;
    public CheckBox cb_isTest;
    public CheckBox cb_isAttendance;
    public ComboBox timePicker= new ComboBox();
    public TableView<Course> coursesTable;

    public void initialize(){
        queryDB query=new queryDB();
        ObservableList<Course> courses = query.getAllCourse();
        if(courses != null)
            setTableData(courses);
        addvaluesComboBox();
    }

    private void addvaluesComboBox() {
        for (int i=8;i<21;i++)
            timePicker.getItems().add(String.valueOf(i)+":00");
    }

    private void setTableData(ObservableList<Course> courses) {
        TableColumn actionCol = new TableColumn();
        TableColumn<Course, String> courseIDCol = new TableColumn<Course, String>("מספר קורס");
        TableColumn<Course, String> courseNameCol = new TableColumn<Course, String>("שם הקורס");
        TableColumn<Course, String> isAttendanceCol = new TableColumn<Course, String>("נוכחות");
        TableColumn<Course, String> isTestCol = new TableColumn<Course, String>("כולל מבחן");
        TableColumn<Course, String> startTimeCol = new TableColumn<Course, String>("שעות הקורס");
        TableColumn actionCol1 = new TableColumn();



        //actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
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
                                try {
                                    watchCourse(getTableView().getItems().get(getIndex()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        //actionCol1.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<Course, String>, TableCell<Course, String>> btn2
                = new Callback<TableColumn<Course, String>, TableCell<Course, String>>() {
            @Override
            public TableCell call(final TableColumn<Course, String> param) {
                final TableCell<Course, String> cell = new TableCell<Course, String>() {
                    final Button btn = new Button("הוסף ביקורת");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                try {
                                    addFeedBack(getTableView().getItems().get(getIndex()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
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
        actionCol1.setCellFactory(btn2);


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
        coursesTable.getColumns().addAll(actionCol, courseIDCol, courseNameCol, isAttendanceCol, isTestCol, startTimeCol,actionCol1);
    setFilters(courses);
    }

    private void addFeedBack(Course course) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addReview.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        addReviewController controller = fxmlLoader.getController();
        controller.setCourseId(course.getIdCourse());

        Stage stage = new Stage();
        Scene scene = new Scene(root, 550, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(advanceSearchCheckbox.getScene().getWindow());
        stage.show();

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
        if (timePicker.getSelectionModel().getSelectedItem() != null && !timePicker.getSelectionModel().getSelectedItem().equals("") && !course.getHours().startsWith(timePicker.getSelectionModel().getSelectedItem().toString().split(":")[0]))
            return false;

        //is connection
        if (cb_isAttendance.isSelected() && course.isAttendence())
            return false;

        //is separate
        if (cb_isTest.isSelected() && course.isTest())
            return false;

        return true;
    }

    private void watchCourse(Course course) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/courseShow.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        NewReviewDetailsBoxController controller = fxmlLoader.getController();
        controller.setCourse(course);


        Stage stage = new Stage();
        Scene scene = new Scene(root, 700, 430);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(advanceSearchCheckbox.getScene().getWindow());
        stage.show();

    }

    public void advanceSearchChacked() {
        if (advanceSearchCheckbox.isSelected())
            advancedSearchBox.setVisible(true);
        else {
            advancedSearchBox.setVisible(false);

            timePicker.getSelectionModel().clearSelection();
            cb_isAttendance.setSelected(false);
            cb_isTest.setSelected(false);
        }
    }
}
