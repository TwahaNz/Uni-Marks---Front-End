package app.tnz.com.unimarks.services.student;

/**
 * Created by Admin on 2016/08/25.
 */
public interface StudentLoginService {

    boolean isAccountActivated();
    boolean isValidStudentLogin(String student_email, String student_password);
    boolean isValidStudentProfile(String john, String peterson);
}
