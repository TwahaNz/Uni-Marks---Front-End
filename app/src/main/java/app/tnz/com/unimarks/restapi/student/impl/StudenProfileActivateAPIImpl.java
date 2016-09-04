package app.tnz.com.unimarks.restapi.student.impl;

import com.google.gson.Gson;

import app.tnz.com.unimarks.configurations.utils.app.AppUtil;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.domain.student.StudentProfile;
import app.tnz.com.unimarks.factories.student.StudentProfileFactory;
import app.tnz.com.unimarks.restapi.student.StudentProfileActivateAPI;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Admin on 2016/08/31.
 */
public class StudenProfileActivateAPIImpl implements StudentProfileActivateAPI {

    private String url = AppUtil.getBaserURI() + "com/tnz/app/exam4me/account/profile";

    @Override
    public boolean isValidStudentProfile(String name, String surname) {

        if (name.equals("null") || surname.equals("null")) {
            return false;
        }

        return true;
    }

    @Override
    public StudentProfile activateStudentAccount(String name, String suraname) throws Exception {

        StudentProfile studentProfile = new StudentProfile.Builder()
                .setName(name)
                .setSurname(suraname)
                .build();

        String json = new Gson().toJson(studentProfile);

        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        studentProfile = new Gson().fromJson(value, StudentProfile.class);

        if(!isValidStudentProfile(studentProfile.getName(), studentProfile.getSurname())) {
            return null;
        }

        return StudentProfileFactory.getStudentProfile(studentProfile.getId(), studentProfile.getName(), studentProfile.getSurname());
    }

    @Override
    public StudentProfile loginStudentProfile(Long id) throws Exception {

        url = AppUtil.getBaserURI() + "studentProfile/";

        Request request = new Request.Builder()
                .url(url + id)
                .get()
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        StudentProfile studProfile = new Gson().fromJson(value, StudentProfile.class);

        if (!isValidStudentProfile(studProfile.getName(), studProfile.getSurname())) {
            return null;
        }

        return StudentProfileFactory.getStudentProfile(studProfile.getId(), studProfile.getName(), studProfile.getSurname());
    }

    @Override
    public StudentProfile updateStudentProfile(StudentProfile studentProfile) throws Exception {

        url = AppUtil.getBaserURI() + "studentProfile/";

        String json = new Gson().toJson(studentProfile);

        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);

        Request request = new Request.Builder()
                .url(url + studentProfile.getId())
                .put(body)
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        studentProfile= new Gson().fromJson(value, StudentProfile.class);

        if(!isValidStudentProfile(studentProfile.getName(), studentProfile.getSurname())) {
            return null;
        }

        return StudentProfileFactory.getStudentProfile(studentProfile.getId(), studentProfile.getName(), studentProfile.getSurname());
    }

    @Override
    public StudentProfile deleteStudentProfile(Long id) throws Exception {

        url = AppUtil.getBaserURI() + "studentProfile/";

        Request request = new Request.Builder()
                .url(url + id)
                .delete()
                .build();

        Response response = AppUtil.getConnection()
                .newCall(request)
                .execute();

        String value = response.body().string();

        StudentProfile studentProfile = new Gson().fromJson(value, StudentProfile.class);

        if(!isValidStudentProfile(studentProfile.getName(), studentProfile.getSurname())) {
            return null;
        }

        return StudentProfileFactory.getStudentProfile(studentProfile.getId(), studentProfile.getName(), studentProfile.getSurname());
    }
}
