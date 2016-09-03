package app.tnz.com.unimarks.services.year;

import android.os.AsyncTask;

import java.io.IOException;

import app.tnz.com.unimarks.domain.student.Student;
import app.tnz.com.unimarks.domain.year.ThirdYear;
import app.tnz.com.unimarks.restapi.year.impl.ThirdYearAPIImpl;

/**
 * Created by Admin on 2016/09/03.
 */
public class StudentThirdYear extends AsyncTask<String, Void, ThirdYear> {

    private ThirdYearAPIImpl thirdYearAPI;

    @Override
    protected ThirdYear doInBackground(String... params) {

        thirdYearAPI = new ThirdYearAPIImpl();

        String studentNumber = params[0];

        try {
            return thirdYearAPI.isStudentYear(studentNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
