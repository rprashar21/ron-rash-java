package com.corejava.corejava.equalsandhascodes;

import java.util.List;
import java.util.Objects;

import com.corejava.java8.streams.Address;

public class Student {

    private String name;
    private int age;
    private int marks;
    private String department;
    private List<String> hobbies;

    private List<Address> address;

    public Student() {

    }

    public Student(final String name, final int age, final int marks, final List<String> hobbies, final String department) {
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.hobbies = hobbies;
        this.department = department;
    }

    public Student(final String name, final int age, final int marks, final String department, final List<String> hobbies, final List<Address> address) {
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.department = department;
        this.hobbies = hobbies;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(final List<Address> address) {
        this.address = address;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(final int marks) {
        this.marks = marks;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(final List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(final String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", marks=" + marks +
                ", department='" + department + '\'' +
                ", hobbies=" + hobbies +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Student student = (Student) o;
        return age == student.age &&
                marks == student.marks &&
                Objects.equals(name, student.name) &&
                Objects.equals(department, student.department) &&
                Objects.equals(hobbies, student.hobbies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, marks, department, hobbies);
    }
}
