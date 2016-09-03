package app.tnz.com.unimarks.restapi.subjects.impl;

import com.google.gson.Gson;

import app.tnz.com.unimarks.configurations.utils.app.AppUtil;
import app.tnz.com.unimarks.domain.subjects.ISYA;
import app.tnz.com.unimarks.restapi.subjects.ISYAAPI;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Admin on 2016/09/03.
 */
public class ISYAAPIImpl implements ISYAAPI {

    private static final String url = AppUtil.getBaserURI() + "student/isya/";

    @Override
    public ISYA getDOSMarks(String studentNumber) throws Exception {

        Request request = new Request.Builder()
                .url(url + studentNumber)
                .get()
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        return new Gson().fromJson(value, ISYA.class);
    }
}
