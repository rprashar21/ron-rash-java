package com.corejava.java8.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.corejava.corejava.equalsandhascodes.Student;

public interface StudentUtility {

    static List<Student> getStudentList() {
        Student s6 = null;
        Student s7 = new Student("johnny", 30, 99, "null", null, null);
        Student s8 = new Student("mahesh", 30, 99, "null", null,
                Collections.singletonList(new Address("CR0 1HB", "", "")));
        Student s9 = new Student("rajesh", 30, 99, "null", Arrays.asList("cricket", "chess", "ladkibaazi"),
                Collections.singletonList(new Address("CR0 1HB", "", "")));
        Student s1 = new Student("rahul", 30, 90, Arrays.asList("cricket", "football", "gaming"),"CS");
        Student s2 = new Student("shamir", 31, 89, Arrays.asList("cricket", "chess", "ladkibaazi"),"CS");
        Student s3 = new Student("bob", 29, 79, Arrays.asList("cricket", "hockey", "shaadi"),"EE");
        Student s4 = new Student("jones", 21, 99, Arrays.asList("badminton", "football", "chutiyabaazi"),"EE");
        Student s5 = new Student("peter", 38, 80, Arrays.asList("basketball", "football", "gaandu"),"ME");

        List<Student> studentList = Arrays.asList(s1, s2,s3,s4,s5,s6,s7,s8,s9);
        return studentList;
    }
}
