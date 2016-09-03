package app.tnz.com.unimarks.services.subjects;

import android.os.AsyncTask;

import app.tnz.com.unimarks.domain.subjects.IRP;
import app.tnz.com.unimarks.domain.subjects.ISYB;
import app.tnz.com.unimarks.restapi.subjects.impl.IRPAPIImpl;
import app.tnz.com.unimarks.restapi.subjects.impl.ISYBAPIImpl;

/**
 * Created by Admin on 2016/09/03.
 */
public class ISYBMarksService extends AsyncTask<String, Void, ISYB> {

    private ISYBAPIImpl isybapi;

    @Override
    protected ISYB doInBackground(String... params) {

        isybapi = new ISYBAPIImpl();

        String studentNumber = params[0];

        try {
            return isybapi.getDOSMarks(studentNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
