package app.tnz.com.unimarks.factories.student;

import app.tnz.com.unimarks.domain.student.StudentAccount;

/**
 * Created by Admin on 2016/08/21.
 */
public class StudentAccountFactory {

    public static StudentAccount getStudent(String studentEmail, String studentPassword){

        return new StudentAccount.Builder()
                .setStudentEmail(studentEmail)
                .setStudentPassword(studentPassword)
                .build();
    }

    public static StudentAccount getStudent(long id, String studentEmail, String studentPassword) {

        return new StudentAccount.Builder()
                .setId(id)
                .setStudentEmail(studentEmail)
                .setStudentPassword(studentPassword)
                .build();
    }
}
