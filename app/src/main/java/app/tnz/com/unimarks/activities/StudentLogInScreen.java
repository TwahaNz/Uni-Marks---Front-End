package app.tnz.com.unimarks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import app.tnz.com.unimarks.R;
import app.tnz.com.unimarks.activities.fragments.MainActivity;
import app.tnz.com.unimarks.configurations.utils.app.App;
import app.tnz.com.unimarks.domain.student.Student;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.domain.student.StudentProfile;
import app.tnz.com.unimarks.factories.student.StudentAccountFactory;
import app.tnz.com.unimarks.repositories.student.StudentAccountRepository;
import app.tnz.com.unimarks.repositories.student.StudentProfileRepository;
import app.tnz.com.unimarks.repositories.student.impl.StudentAccountRepositoryImpl;
import app.tnz.com.unimarks.repositories.student.impl.StudentProfileRepositoryImpl;
import app.tnz.com.unimarks.services.student.LoginInService;

/**
 * Created by Admin on 2016/08/29.
 */
public class StudentLogInScreen extends AppCompatActivity {

    private StudentAccountRepository studentAccountRepository;
    private StudentProfileRepository studentProfileRepository;

    private EditText email;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    public void btn_register(View view) {
        startActivity(new Intent(this, StudentRegisterScreen.class));
    }

    //// TODO: 2016/08/31 Remove the null and get student profile
    public void btn_login(View view) {

        String studEmail = email.getText().toString();
        String studPass = password.getText().toString();

        Student student = null;

        try {
            student = new LoginInService().execute(studEmail, studPass).get();
        } catch (Exception e) {
            Toast.makeText(StudentLogInScreen.this, "\n Unable To Connect. Please check your internet connection \n", Toast.LENGTH_SHORT).show();
        }

        if (student != null) {

            studentAccountRepository = new StudentAccountRepositoryImpl(App.getAppContext());
            studentAccountRepository.insert(student.getStudentAccount());

            studentProfileRepository = new StudentProfileRepositoryImpl(App.getAppContext());
            StudentProfile studentProfile = studentProfileRepository.insert(student.getStudentProfile());


            Intent intent = new Intent(StudentLogInScreen.this, MainActivity.class);

            intent.putExtra("student", student);

            startActivity(intent);
            Toast.makeText(StudentLogInScreen.this, "\n Welcome To Uni-Marks! \n", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(StudentLogInScreen.this, "\n No Such User Exists \n", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
