package app.tnz.com.unimarks.services.subjects;

import android.os.AsyncTask;

import app.tnz.com.unimarks.domain.subjects.ISYA;
import app.tnz.com.unimarks.domain.subjects.ISYB;
import app.tnz.com.unimarks.restapi.subjects.impl.ISYAAPIImpl;
import app.tnz.com.unimarks.restapi.subjects.impl.ISYBAPIImpl;

/**
 * Created by Admin on 2016/09/03.
 */
public class ISYAMarksService extends AsyncTask<String, Void, ISYA> {

    private ISYAAPIImpl isyaapi;

    @Override
    protected ISYA doInBackground(String... params) {

        isyaapi = new ISYAAPIImpl();

        String studentNumber = params[0];

        try {
            return isyaapi.getDOSMarks(studentNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
