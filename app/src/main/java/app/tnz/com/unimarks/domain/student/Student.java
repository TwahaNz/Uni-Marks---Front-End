package app.tnz.com.unimarks.domain.student;

import java.io.Serializable;

/**
 * Created by Admin on 2016/08/31.
 */
public class Student implements Serializable {

    private StudentAccount studentAccount;
    private StudentProfile studentProfile;

    public Student(StudentAccount studentAccount, StudentProfile studentProfile) {
        this.studentAccount = studentAccount;
        this.studentProfile = studentProfile;
    }

    public StudentAccount getStudentAccount() {
        return studentAccount;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setAccount(StudentAccount account) {
        this.studentAccount = account;
    }

    public void setProfile(StudentProfile profile) {
        this.studentProfile = profile;
    }

}
