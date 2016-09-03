package app.tnz.com.unimarks.restapi.subjects.impl;

import com.google.gson.Gson;

import app.tnz.com.unimarks.configurations.utils.app.AppUtil;
import app.tnz.com.unimarks.domain.subjects.DOS;
import app.tnz.com.unimarks.domain.subjects.TP;
import app.tnz.com.unimarks.restapi.subjects.DOSAPI;
import app.tnz.com.unimarks.restapi.subjects.TPAPI;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Admin on 2016/09/03.
 */
public class TPAPIImpl implements TPAPI {

    private static final String url = AppUtil.getBaserURI() + "student/tp/";

    @Override
    public TP getDOSMarks(String studentNumber) throws Exception {

        Request request = new Request.Builder()
                .url(url + studentNumber)
                .get()
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        return new Gson().fromJson(value, TP.class);
    }
}
