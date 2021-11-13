package by.KuvonchbekN.service;

import by.KuvonchbekN.model.Category;
import by.KuvonchbekN.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseService implements BaseService<Course, Category, List<Course>>{
    @Override
    public Course add(Course course) {
        BaseService.courseList.add(course);
        return course;
    }

    @Override
    public Category get(UUID id) {

        return null;
    }

    @Override
    public Course check(String str) {
        return null;
    }

    @Override
    public List<Course> list(Course course) {
        return BaseService.courseList;
    }

    public List<Course> myCourses(UUID id) {
        List<Course> courses = new ArrayList<>();

        for (Course course: BaseService.courseList) {
            if(course.getTeacherId().equals(id) && course.isActive())
                courses.add(course);
        }

        return courses;
    }

    public List<Course> courses(UUID id) {
        List<Course> courses1 = new ArrayList<>();

        for (Course course: BaseService.courseList) {
            if(course.getParentId().equals(id) && course.isActive())
                courses1.add(course);
        }

        return courses1;
    }

    @Override
    public Boolean delete(UUID id) {
        int ind = 0;
        for (Course course: BaseService.courseList) {
            if(course.getId().equals(id)){
                course.setActive(false);
                BaseService.courseList.set(ind, course);
            }
            ind++;
        }
        return null;
    }
}
