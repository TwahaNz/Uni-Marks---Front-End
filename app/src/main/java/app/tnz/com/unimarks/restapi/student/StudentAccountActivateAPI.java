package app.tnz.com.unimarks.restapi.student;

import app.tnz.com.unimarks.domain.student.StudentAccount;

/**
 * Created by Admin on 2016/08/25.
 */
public interface StudentAccountActivateAPI {

    boolean isValidStudentAccount(String email, String password);

    StudentAccount activateStudentAccount(String email, String password) throws Exception;
}
