package app.tnz.com.unimarks.restapi.subjects.impl;

import com.google.gson.Gson;

import app.tnz.com.unimarks.configurations.utils.app.AppUtil;
import app.tnz.com.unimarks.domain.subjects.DOS;
import app.tnz.com.unimarks.restapi.subjects.DOSAPI;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Admin on 2016/09/03.
 */
public class DOSAPIImpl implements DOSAPI {

    private static final String url = AppUtil.getBaserURI() + "student/dos/";

    @Override
    public DOS getDOSMarks(String studentNumber) throws Exception {

        Request request = new Request.Builder()
                .url(url + studentNumber)
                .get()
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        return new Gson().fromJson(value, DOS.class);
    }
}
