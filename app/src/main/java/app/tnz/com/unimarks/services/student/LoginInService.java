package app.tnz.com.unimarks.services.student;

import android.os.AsyncTask;

import app.tnz.com.unimarks.domain.student.Student;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.domain.student.StudentProfile;
import app.tnz.com.unimarks.factories.student.StudentAccountFactory;
import app.tnz.com.unimarks.restapi.student.StudentAccountActivateAPI;
import app.tnz.com.unimarks.restapi.student.StudentProfileActivateAPI;
import app.tnz.com.unimarks.restapi.student.impl.StudenProfileActivateAPIImpl;
import app.tnz.com.unimarks.restapi.student.impl.StudentAccountActivateAPIImpl;

/**
 * Created by Admin on 2016/09/03.
 */
public class LoginInService extends AsyncTask<String, Void, Student> {

    private StudentAccountActivateAPI studentAccountActivate;
    private StudentProfileActivateAPI studentProfileActivate;

    private StudentAccount studentAccount;
    private StudentProfile studentProfile;

    @Override
    protected Student doInBackground(String... params) {

        try {

            String studEmail = params[0];
            String studPassword = params[1];

            studentAccountActivate = new StudentAccountActivateAPIImpl();
            studentProfileActivate = new StudenProfileActivateAPIImpl();

            studentAccount = studentAccountActivate.loginStudentAccount(StudentAccountFactory.getStudent(studEmail, studPassword));
            studentProfile = studentProfileActivate.loginStudentProfile(studentAccount.getId());

            if (!(studentAccount == null) && !(studentProfile == null))
                return new Student(studentAccount, studentProfile);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

}
