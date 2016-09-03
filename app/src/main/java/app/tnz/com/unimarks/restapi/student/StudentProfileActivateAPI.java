package app.tnz.com.unimarks.restapi.student;

import app.tnz.com.unimarks.domain.student.StudentProfile;

/**
 * Created by Admin on 2016/08/31.
 */
public interface StudentProfileActivateAPI {

    boolean isValidStudentProfile(String name, String surname);

    StudentProfile activateStudentAccount(String name, String suraname) throws Exception;

    StudentProfile updateStudentProfile(Long id, String studName, String studSurname) throws Exception;

    StudentProfile deleteStudentProfile(Long id, String studName, String studSurname) throws Exception;
}
