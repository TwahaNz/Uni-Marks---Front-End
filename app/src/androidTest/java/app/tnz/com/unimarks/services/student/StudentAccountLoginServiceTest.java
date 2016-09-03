package app.tnz.com.unimarks.services.student;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import app.tnz.com.unimarks.configurations.utils.app.App;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.domain.student.StudentProfile;
import app.tnz.com.unimarks.factories.student.StudentAccountFactory;
import app.tnz.com.unimarks.factories.student.StudentProfileFactory;
import app.tnz.com.unimarks.repositories.student.StudentAccountRepository;
import app.tnz.com.unimarks.repositories.student.StudentProfileRepository;
import app.tnz.com.unimarks.repositories.student.impl.StudentAccountRepositoryImpl;
import app.tnz.com.unimarks.repositories.student.impl.StudentProfileRepositoryImpl;

/**
 * Created by Admin on 2016/08/25.
 */
public class StudentAccountLoginServiceTest extends AndroidTestCase {

    private final String TAG = "STUDENT LOGIN SERVICE:";

    private StudentLoginServiceImpl studentLoginService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {

        super.setUp();

        studentLoginService = new StudentLoginServiceImpl();

        insertStudentToDatabase();

        Intent intent = new Intent(App.getAppContext(), StudentLoginServiceImpl.class);
        App.getAppContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            StudentLoginServiceImpl.StudentLoginServiceLocalBinder localBinder
                    = (StudentLoginServiceImpl.StudentLoginServiceLocalBinder) service;

            studentLoginService = localBinder.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testIfTheStudentHasAlreadyCreatedAnAccount() {

        boolean isStudentAccountCreated = studentLoginService.isAccountActivated();
        boolean isStudentProfileCreated = studentLoginService.isAccountActivated();

        Assert.assertEquals(TAG, true, isStudentAccountCreated);
    }

    public void testIfTheStudentHasEnteredValidLoginInDetails() {

        boolean isValidStudentLogin = studentLoginService.isValidStudentLogin("211234567@mycput.ac.za", "12345");
        boolean isValidStudentProfile = studentLoginService.isValidStudentProfile("John", "Peterson");

        Assert.assertEquals(TAG, true, isValidStudentLogin);
        Assert.assertEquals(TAG, true, isValidStudentProfile);
    }

    public void insertStudentToDatabase() {

        StudentAccountRepository studentAccountRepository = new StudentAccountRepositoryImpl(App.getAppContext());
        StudentProfileRepository studentProfileRepository = new StudentProfileRepositoryImpl(App.getAppContext());

        StudentAccount studentAccount = StudentAccountFactory.getStudent("211234567@mycput.ac.za", "12345");
        studentAccount = studentAccountRepository.insert(studentAccount);

        StudentProfile studentProfile = StudentProfileFactory.getStudentProfile("John", "Peterson");
        studentProfile = studentProfileRepository.insert(studentProfile);

        Assert.assertNotNull(TAG, studentAccount.getId());
        Assert.assertNotNull(TAG, studentProfile.getId());
    }
}
