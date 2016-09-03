package app.tnz.com.unimarks.services.subjects;

import android.os.AsyncTask;

import app.tnz.com.unimarks.domain.subjects.ISYB;
import app.tnz.com.unimarks.domain.subjects.TP;
import app.tnz.com.unimarks.restapi.subjects.impl.ISYBAPIImpl;
import app.tnz.com.unimarks.restapi.subjects.impl.TPAPIImpl;

/**
 * Created by Admin on 2016/09/03.
 */
public class TPMarksService extends AsyncTask<String, Void, TP> {

    private TPAPIImpl tpapi;

    @Override
    protected TP doInBackground(String... params) {

        tpapi = new TPAPIImpl();

        String studentNumber = params[0];

        try {
            return tpapi.getDOSMarks(studentNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
