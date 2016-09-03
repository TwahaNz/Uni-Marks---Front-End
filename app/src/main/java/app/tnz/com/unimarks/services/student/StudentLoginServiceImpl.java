package app.tnz.com.unimarks.services.student;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Set;

import app.tnz.com.unimarks.configurations.utils.app.App;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.domain.student.StudentProfile;
import app.tnz.com.unimarks.repositories.student.StudentAccountRepository;
import app.tnz.com.unimarks.repositories.student.StudentProfileRepository;
import app.tnz.com.unimarks.repositories.student.impl.StudentAccountRepositoryImpl;
import app.tnz.com.unimarks.repositories.student.impl.StudentProfileRepositoryImpl;

/**
 * Created by Admin on 2016/08/25.
 */
public class StudentLoginServiceImpl extends Service implements StudentLoginService {

    private final StudentAccountRepository studentAccountRepository;
    private final StudentProfileRepository studentProfileRepository;
    private static StudentLoginServiceImpl studentLoginService = null;

    private IBinder studentLocalBinder = new StudentLoginServiceLocalBinder();

    // TODO: 2016/08/31 Dependency Injection Required
    public StudentLoginServiceImpl() {
        studentAccountRepository = new StudentAccountRepositoryImpl(App.getAppContext());
        studentProfileRepository = new StudentProfileRepositoryImpl(App.getAppContext());
    }

    public static StudentLoginService getInstance() {

        if(studentLoginService == null) {
            studentLoginService = new StudentLoginServiceImpl();
        }

        return studentLoginService;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return studentLocalBinder;
    }

    @Override
    public boolean isAccountActivated() {

        Set<StudentAccount> studentAccounts = studentAccountRepository.find_all();
        Set<StudentProfile> studentProfiles = studentProfileRepository.find_all();

        return studentAccounts.size() > 0 && studentProfiles.size() > 0;
    }

    @Override
    public boolean isValidStudentLogin(String studentEmail, String studentPassword) {

        Set<StudentAccount> studentAccounts = studentAccountRepository.find_all();

        String studentLoginEmail;
        String studentLoginPassword;

        for (StudentAccount studentAccount : studentAccounts){

            studentLoginEmail = studentAccount.getStudentEmail();
            studentLoginPassword = studentAccount.getStudentPassword();

            if (studentLoginEmail.equals(studentEmail) && studentLoginPassword.equals(studentPassword)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isValidStudentProfile(String name, String surname) {

        Set<StudentProfile> studentProfiles = studentProfileRepository.find_all();

        String studentName;
        String studentSurname;

        for (StudentProfile studentProfile : studentProfiles){

            studentName = studentProfile.getName();
            studentSurname = studentProfile.getSurname();

            if (studentName.equals(studentName) && studentSurname.equals(studentSurname)) {
                return true;
            }
        }

        return false;
    }

    public class StudentLoginServiceLocalBinder extends Binder {

        public StudentLoginServiceImpl getService() {
            return StudentLoginServiceImpl.this;
        }
    }
}
