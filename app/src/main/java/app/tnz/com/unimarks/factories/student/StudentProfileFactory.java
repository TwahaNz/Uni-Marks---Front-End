package app.tnz.com.unimarks.factories.student;

import app.tnz.com.unimarks.domain.student.StudentProfile;

/**
 * Created by Admin on 2016/08/25.
 */
public class StudentProfileFactory {

    public static StudentProfile getStudentProfile(String name, String surname, String classGroup) {

        return new StudentProfile.Builder()
                .setName(name)
                .setSurname(surname)
                .setClassGroup(classGroup)
                .build();
    }

    public static StudentProfile getStudentProfile(String name, String surname) {

        return new StudentProfile.Builder()
                .setName(name)
                .setSurname(surname)
                .build();
    }

    public static StudentProfile getStudentProfile(Long id, String name, String surname) {

        return new StudentProfile.Builder()
                .setId(id)
                .setName(name)
                .setSurname(surname)
                .build();
    }

}
