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
public class LoginInService extends AsyncTask<Long, Void, StudentAccount> {

    private StudentAccountActivateAPI studentAccountActivate;
    private StudentAccount studentAccount;

    @Override
    protected StudentAccount doInBackground(Long... params) {

        try {

            Long id = params[0];

            studentAccountActivate = new StudentAccountActivateAPIImpl();
            studentAccount = studentAccountActivate.loginStudentAccount(id);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return  studentAccount;
    }

}
