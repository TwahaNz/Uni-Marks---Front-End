package app.tnz.com.unimarks.restapi.year.impl;

import com.google.gson.Gson;

import java.io.IOException;

import app.tnz.com.unimarks.configurations.utils.app.AppUtil;
import app.tnz.com.unimarks.domain.subjects.DOS;
import app.tnz.com.unimarks.domain.year.ThirdYear;
import app.tnz.com.unimarks.restapi.year.ThirdYearAPI;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Admin on 2016/09/03.
 */
public class ThirdYearAPIImpl implements ThirdYearAPI {

    private static final String url = AppUtil.getBaserURI() + "student/thirdyear/ds/";

    @Override
    public ThirdYear isStudentYear(String studentNumber) throws IOException {

        Request request = new Request.Builder()
                .url(url + studentNumber)
                .get()
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        return new Gson().fromJson(value, ThirdYear.class);
    }
}
