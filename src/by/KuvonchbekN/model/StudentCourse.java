package by.KuvonchbekN.model;

import java.util.UUID;

public class StudentCourse {

    private User user;
    private Course course;

    public StudentCourse(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
