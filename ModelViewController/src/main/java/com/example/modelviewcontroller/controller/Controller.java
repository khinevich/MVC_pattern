package com.example.modelviewcontroller.controller;

import com.example.modelviewcontroller.model.Course;
import com.example.modelviewcontroller.view.CourseDetailView;
import com.example.modelviewcontroller.view.CourseListView;

public class Controller {

    private CourseListView courseListView;
    private CourseDetailView courseDetailView;

    public void saveCourse(Course course) {
        courseListView.addCourse(course);
        course.notifyObservers();
    }

    public void selectCourse(Course course) {
        this.courseDetailView = new CourseDetailView(this, course);
        courseDetailView.show();
    }

    public void setCourseListView(CourseListView courseListView) {
        this.courseListView = courseListView;
    }
}
