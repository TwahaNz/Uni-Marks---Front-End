package app.tnz.com.unimarks.restapi.student.impl;

import com.google.gson.Gson;

import app.tnz.com.unimarks.configurations.utils.app.AppUtil;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.factories.student.StudentAccountFactory;
import app.tnz.com.unimarks.restapi.student.StudentAccountActivateAPI;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Admin on 2016/08/25.
 */
public class StudentAccountActivateAPIImpl implements StudentAccountActivateAPI {

    private String url = AppUtil.getBaserURI() + "com/tnz/app/exam4me/account/";

    @Override
    public boolean isValidStudentAccount(String email, String password) {

        if (email.equals("null") || password.equals("null")) {
            return false;
        }

        return true;
    }

    @Override
    public StudentAccount activateStudentAccount(String studentEmail, String studentPassword) throws Exception {

        StudentAccount studentAccount = new StudentAccount.Builder()
                .setStudentEmail(studentEmail)
                .setStudentPassword(studentPassword)
                .build();

        String json = new Gson().toJson(studentAccount);

        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        StudentAccount studAccount = new Gson().fromJson(value, StudentAccount.class);

        if (!isValidStudentAccount(studentAccount.getStudentEmail(), studentAccount.getStudentPassword())) {
            return null;
        }

        return studAccount;
    }

    @Override
    public StudentAccount loginStudentAccount(StudentAccount studentAccount) throws Exception {

        url = AppUtil.getBaserURI() + "studentAccount/";

        String json = new Gson().toJson(studentAccount);

        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        studentAccount = new Gson().fromJson(value, StudentAccount.class);

        if (!isValidStudentAccount(studentAccount.getStudentEmail(), studentAccount.getStudentPassword())) {
            return null;
        }

        return studentAccount;
    }

    @Override
    public StudentAccount updateStudentAccount(StudentAccount studentAccount) throws Exception {

        url = AppUtil.getBaserURI() + "studentAccount/";

        String json = new Gson().toJson(studentAccount);

        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);

        Request request = new Request.Builder()
                .url(url + studentAccount.getId())
                .put(body)
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        studentAccount = new Gson().fromJson(value, StudentAccount.class);

        if(!isValidStudentAccount(studentAccount.getStudentEmail(), studentAccount.getStudentPassword())) {
            return null;
        }

        return StudentAccountFactory.getStudent(studentAccount.getId(), studentAccount.getStudentEmail(), studentAccount.getStudentPassword());
    }

    @Override
    public StudentAccount deleteStudentAccount(Long id) throws Exception {

        url = AppUtil.getBaserURI() + "studentAccount/";

        Request request = new Request.Builder()
                .url(url + id)
                .delete()
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        StudentAccount studAccount = new Gson().fromJson(value, StudentAccount.class);

        if(!isValidStudentAccount(studAccount.getStudentEmail(), studAccount.getStudentPassword())) {
            return null;
        }

        return StudentAccountFactory.getStudent(studAccount.getId(), studAccount.getStudentEmail(), studAccount.getStudentPassword());
    }
}
