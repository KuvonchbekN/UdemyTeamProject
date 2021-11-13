package by.KuvonchbekN.service;

import by.KuvonchbekN.model.Course;
import by.KuvonchbekN.model.StudentCourse;
import by.KuvonchbekN.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentCourseService{
    public static final List<StudentCourse> studentCourseList = new ArrayList<>();

    public static void add(StudentCourse studentCourse){
        studentCourseList.add(studentCourse);
    }


    public static List<User> getStudents(Course course){
        List<User> users = new ArrayList<>();
        for (StudentCourse sc: studentCourseList) {
            if(sc.getCourse().equals(course))
                users.add(sc.getUser());
        }
        return users;
    }

    public static List<Course> getCourses(User user){
        List<Course> courses = new ArrayList<>();
        for (StudentCourse sc: studentCourseList) {
            if(sc.getUser().getId().equals(user.getId())) {
                courses.add(sc.getCourse());
            }
        }
        return courses;
    }
}
