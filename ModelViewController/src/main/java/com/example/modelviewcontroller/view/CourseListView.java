package com.example.modelviewcontroller.view;

import com.example.modelviewcontroller.controller.Controller;
import com.example.modelviewcontroller.model.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class CourseListView extends Stage implements Observer {

    private static final int SCENE_WIDTH = 400;
    private static final int SCENE_HEIGHT = 400;
    private final ObservableList<Course> courses;
    private final ListView<Course> courseListView;
    private final Controller controller;
    private Button createButton;

    public CourseListView(Controller controller, List<Course> courseList) {
        this.courses = FXCollections.observableArrayList(courseList);
        this.courseListView = new ListView<>(courses);
        this.controller = controller;
        for (Course c : courses) {
            c.addObserver(this);
        }
        controller.setCourseListView(this);
        generateUserInterface();
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            this.courses.add(course);
            course.addObserver(this);
            courseListView.refresh();
        }
    }

    private void selectCourse(Course course) {
        controller.selectCourse(course);
    }

    private void createCourse() {
        selectCourse(new Course());

    }

    @Override
    public void update() {
        displayCourses();
    }

    private void displayCourses() {
        courseListView.refresh();
    }

    private void generateUserInterface() {
        VBox vbox = new VBox();

        createButton = new Button("Create a course");
        createButton.setOnAction(event -> createCourse());

        Label courseListLabel = new Label("Course List");
        courseListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        courseListView.setOnMouseClicked(event -> selectCourse(courseListView.getSelectionModel().getSelectedItem()));
        vbox.getChildren().addAll(courseListLabel, courseListView, createButton);

        Scene scene = new Scene(vbox, SCENE_WIDTH, SCENE_HEIGHT);
        setScene(scene);
        setTitle("Course List");
        displayCourses();
    }

    public ObservableList<Course> getCourses() {
        return courses;
    }
}
