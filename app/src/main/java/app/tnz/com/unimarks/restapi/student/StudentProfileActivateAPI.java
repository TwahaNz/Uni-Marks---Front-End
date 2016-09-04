package app.tnz.com.unimarks.restapi.student;

import app.tnz.com.unimarks.domain.student.StudentProfile;

/**
 * Created by Admin on 2016/08/31.
 */
public interface StudentProfileActivateAPI {

    boolean isValidStudentProfile(String name, String surname);

    StudentProfile activateStudentAccount(String name, String suraname) throws Exception;

    StudentProfile loginStudentProfile(Long id) throws Exception;

    StudentProfile updateStudentProfile(StudentProfile studentProfile) throws Exception;

    StudentProfile deleteStudentProfile(Long id) throws Exception;
}
