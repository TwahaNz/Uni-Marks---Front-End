package app.tnz.com.unimarks;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import app.tnz.com.unimarks.configurations.utils.app.App;
import app.tnz.com.unimarks.domain.student.Student;
import app.tnz.com.unimarks.domain.student.StudentProfile;
import app.tnz.com.unimarks.repositories.student.StudentAccountRepository;
import app.tnz.com.unimarks.repositories.student.StudentProfileRepository;
import app.tnz.com.unimarks.repositories.student.impl.StudentAccountRepositoryImpl;
import app.tnz.com.unimarks.repositories.student.impl.StudentProfileRepositoryImpl;
import app.tnz.com.unimarks.services.student.LoginInService;

/**
 * Created by Admin on 2016/09/03.
 */
public class LoginTest extends AndroidTestCase {

    public void testLogin() throws Exception {

        String studEmail = "212345678";
        String studPass = "test";

        Student student = null;

        student = new LoginInService().execute(studEmail, studPass).get();

        Assert.assertNotNull(student.getStudentAccount().getId());

        StudentAccountRepository studentAccountRepository = new StudentAccountRepositoryImpl(App.getAppContext());
        studentAccountRepository.insert(student.getStudentAccount());

        StudentProfileRepository studentProfileRepository = new StudentProfileRepositoryImpl(App.getAppContext());

        StudentProfile studentProfile = studentProfileRepository.insert(student.getStudentProfile());


    }
}
