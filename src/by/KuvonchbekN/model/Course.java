package by.KuvonchbekN.model;

import java.util.UUID;

public class Course extends BaseModel{
    private UUID teacherId;
    private UUID parentId;
    private double price;
    private double discount;
    private int numberOfEnrolledStudents;
    private String teacherName;
    private String teacherPhoneNumber;


    public Course(String name, UUID teacherId, UUID parentId, double price, double discount,  String teacherName, String teacherPhoneNumber) {
        super(name);
        this.teacherId = teacherId;
        this.parentId = parentId;
        this.price = price;
        this.discount = discount;
        this.teacherName = teacherName;
        this.teacherPhoneNumber = teacherPhoneNumber;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfEnrolledStudents() {
        return numberOfEnrolledStudents;
    }

    public void setNumberOfEnrolledStudents(int numberOfEnrolledStudents) {
        this.numberOfEnrolledStudents = numberOfEnrolledStudents;
    }

    public double getDiscount() {
        return discount;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherPhoneNumber() {
        return teacherPhoneNumber;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setTeacherPhoneNumber(String teacherPhoneNumber) {
        this.teacherPhoneNumber = teacherPhoneNumber;
    }

}
