package app.tnz.com.unimarks.restapi.marks.impl;

import com.google.gson.Gson;

import java.io.IOException;

import app.tnz.com.unimarks.configurations.utils.app.AppUtil;
import app.tnz.com.unimarks.domain.marks.Term;
import app.tnz.com.unimarks.domain.student.Student;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.restapi.marks.TermAPI;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Admin on 2016/09/02.
 */
public class TestAPIImpl implements TermAPI {

    private static final String url = AppUtil.getBaserURI() + "com/tnz/app/exam4me/subjects";

    @Override
    public Term getSubjecMarks(Student student) throws Exception {

        Request request = new Request.Builder()
                .url(url + "/" + student)
                .get()
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        return new Gson().fromJson(value, Term.class);
    }
}
