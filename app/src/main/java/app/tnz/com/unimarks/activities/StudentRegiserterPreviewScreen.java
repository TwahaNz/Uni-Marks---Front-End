package app.tnz.com.unimarks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.tnz.com.unimarks.R;
import app.tnz.com.unimarks.configurations.utils.app.App;
import app.tnz.com.unimarks.domain.student.Student;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.domain.student.StudentProfile;
import app.tnz.com.unimarks.repositories.student.StudentAccountRepository;
import app.tnz.com.unimarks.repositories.student.StudentProfileRepository;
import app.tnz.com.unimarks.repositories.student.impl.StudentAccountRepositoryImpl;
import app.tnz.com.unimarks.repositories.student.impl.StudentProfileRepositoryImpl;
import app.tnz.com.unimarks.services.student.StudentRegisterService;

/**
 * Created by Admin on 2016/08/31.
 */
public class StudentRegiserterPreviewScreen extends AppCompatActivity {

    private Student student;
    private StudentAccountRepository studentAccountRepository;
    private StudentProfileRepository studentProfileRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_confirm);

        student = (Student) getIntent().getSerializableExtra("student");

        ((TextView) findViewById(R.id.prestudName)).setText(student.getStudentProfile().getName());
        ((TextView) findViewById(R.id.prestudSurname)).setText(student.getStudentProfile().getSurname());
        ((TextView) findViewById(R.id.prestudEmail)).setText(student.getStudentAccount().getStudentEmail());
        ((TextView) findViewById(R.id.prestudPassword)).setText(student.getStudentAccount().getStudentPassword());

    }

    public void btn_submit(View view) {

        String studName = student.getStudentProfile().getName();
        String studSurname = student.getStudentProfile().getSurname();
        String studEmail = student.getStudentAccount().getStudentEmail();
        String studPassword = student.getStudentAccount().getStudentPassword();

        try {

            Student student = new StudentRegisterService().execute(studEmail, studPassword, studName, studSurname).get();

            if(student != null) {

                studentAccountRepository = new StudentAccountRepositoryImpl(App.getAppContext());
                studentAccountRepository.insert(student.getStudentAccount());

                studentProfileRepository = new StudentProfileRepositoryImpl(App.getAppContext());
                studentProfileRepository.insert(student.getStudentProfile());

                Toast.makeText(this, "Account Successfully Created", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, StudentLogInScreen.class));
                finish();

            } else {
                Toast.makeText(this, "Account Was Not Created", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btn_back(View view) {
        onBackPressed();
    }
}
