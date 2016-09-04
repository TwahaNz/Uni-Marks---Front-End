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
 * Created by Admin on 2016/09/03.
 */
public class StudentUpdateService extends AsyncTask<Student, Void, Student> {

    private StudentAccountActivateAPI studentAccountActivate;
    private StudentProfileActivateAPI studentProfileActivate;

    private Student student;

    @Override
    protected Student doInBackground(Student... params) {

        try {

            student = params[0];

            studentAccountActivate = new StudentAccountActivateAPIImpl();
            StudentAccount studentAccount = studentAccountActivate.updateStudentAccount(student.getStudentAccount());

            studentProfileActivate = new StudenProfileActivateAPIImpl();
            StudentProfile studentProfile = studentProfileActivate.updateStudentProfile(student.getStudentProfile());

            student = new Student(studentAccount, studentProfile);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return  student;
    }
}
