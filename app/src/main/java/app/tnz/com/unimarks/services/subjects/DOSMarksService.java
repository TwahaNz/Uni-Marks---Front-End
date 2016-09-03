package app.tnz.com.unimarks.services.subjects;

import android.os.AsyncTask;

import java.io.IOException;

import app.tnz.com.unimarks.domain.subjects.DOS;
import app.tnz.com.unimarks.domain.year.ThirdYear;
import app.tnz.com.unimarks.restapi.subjects.impl.DOSAPIImpl;
import app.tnz.com.unimarks.restapi.year.impl.ThirdYearAPIImpl;

/**
 * Created by Admin on 2016/09/03.
 */
public class DOSMarksService extends AsyncTask<String, Void, DOS> {

    private DOSAPIImpl dosapi;

    @Override
    protected DOS doInBackground(String... params) {

        dosapi = new DOSAPIImpl();

        String studentNumber = params[0];

        try {
            DOS dos = dosapi.getDOSMarks(studentNumber);
            return dos;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
