package app.tnz.com.unimarks.restapi.student.impl;

import com.google.gson.Gson;

import app.tnz.com.unimarks.configurations.utils.app.AppUtil;
import app.tnz.com.unimarks.domain.student.Student;
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
    public StudentAccount loginStudentAccount(Long id) throws Exception {

            Request request = new Request.Builder()
                    .url(url + "" + id)
                    .get()
                    .build();

            Response response = AppUtil.getConnection()
                    .newCall(request)
                    .execute();

            String value = response.body().string();

        return new Gson().fromJson(value, StudentAccount.class);
    }

    @Override
    public StudentAccount updateStudentAccount(Long id, String email, String password) throws Exception {

        url = AppUtil.getBaserURI() + "studentAccount/";

        StudentAccount studAccount = StudentAccountFactory.getStudent(id, email, password);

        String json = new Gson().toJson(studAccount);

        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        studAccount = new Gson().fromJson(value, StudentAccount.class);

        if(!isValidStudentAccount(studAccount.getStudentEmail(), studAccount.getStudentPassword())) {
            return null;
        }

        return StudentAccountFactory.getStudent(studAccount.getId(), studAccount.getStudentEmail(), studAccount.getStudentPassword());
    }

    @Override
    public StudentAccount deleteStudentAccount(Long id, String studEmail, String studPassword) throws Exception {

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
