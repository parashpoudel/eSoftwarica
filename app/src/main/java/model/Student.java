package model;

public class Student {
    private String name, age, gender, address;
    private int studentId;

    public Student(int studentId) {
        this.studentId = studentId;
    }

    public Student(String name, String age, String gender, String address, int studentId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
