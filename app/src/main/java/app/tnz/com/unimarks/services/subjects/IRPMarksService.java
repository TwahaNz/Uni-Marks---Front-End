package app.tnz.com.unimarks.services.subjects;

import android.os.AsyncTask;

import app.tnz.com.unimarks.domain.subjects.DOS;
import app.tnz.com.unimarks.domain.subjects.IRP;
import app.tnz.com.unimarks.restapi.subjects.impl.IRPAPIImpl;

/**
 * Created by Admin on 2016/09/03.
 */
public class IRPMarksService extends AsyncTask<String, Void, IRP> {

    private IRPAPIImpl irpapi;

    @Override
    protected IRP doInBackground(String... params) {

        irpapi = new IRPAPIImpl();

        String studentNumber = params[0];

        try {
            return irpapi.getDOSMarks(studentNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
