package app.tnz.com.unimarks.services.student;

import android.os.AsyncTask;

import app.tnz.com.unimarks.domain.student.Student;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.domain.student.StudentProfile;
import app.tnz.com.unimarks.restapi.student.StudentAccountActivateAPI;
import app.tnz.com.unimarks.restapi.student.StudentProfileActivateAPI;
import app.tnz.com.unimarks.restapi.student.impl.StudenProfileActivateAPIImpl;
import app.tnz.com.unimarks.restapi.student.impl.StudentAccountActivateAPIImpl;

/**
 * Created by Admin on 2016/08/30.
 */

public class StudentRegisterService extends AsyncTask<String, Void, Student>{

    private StudentAccountActivateAPI studentAccountActivate;
    private StudentProfileActivateAPI studentProfileActivate;

    private Student student;

    @Override
    protected Student doInBackground(String... params) {

        try {

            String studEmail = params[0];
            String studPassword = params[1];
            String studName = params[2];
            String studSurname = params[3];

            studentAccountActivate = new StudentAccountActivateAPIImpl();
            StudentAccount studentAccount = studentAccountActivate.activateStudentAccount(studEmail, studPassword);

            studentProfileActivate = new StudenProfileActivateAPIImpl();
            StudentProfile studentProfile = studentProfileActivate.activateStudentAccount(studName, studSurname);

            student = new Student(studentAccount, studentProfile);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return  student;
    }
}
