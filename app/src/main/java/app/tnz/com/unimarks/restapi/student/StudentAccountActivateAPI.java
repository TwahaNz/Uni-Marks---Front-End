package app.tnz.com.unimarks.restapi.student;

import app.tnz.com.unimarks.domain.student.Student;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.domain.student.StudentProfile;

/**
 * Created by Admin on 2016/08/25.
 */
public interface StudentAccountActivateAPI {

    boolean isValidStudentAccount(String email, String password);

    StudentAccount activateStudentAccount(String email, String password) throws Exception;

    StudentAccount loginStudentAccount(StudentAccount studentAccount) throws Exception;

    StudentAccount updateStudentAccount(StudentAccount studentAccount) throws Exception;

    StudentAccount deleteStudentAccount(Long id) throws Exception;

}
