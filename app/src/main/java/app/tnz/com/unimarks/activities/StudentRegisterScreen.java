package app.tnz.com.unimarks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.tnz.com.unimarks.R;
import app.tnz.com.unimarks.domain.student.Student;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.domain.student.StudentProfile;
import app.tnz.com.unimarks.domain.year.ThirdYear;
import app.tnz.com.unimarks.factories.student.StudentAccountFactory;
import app.tnz.com.unimarks.factories.student.StudentProfileFactory;
import app.tnz.com.unimarks.services.year.StudentThirdYear;

/**
 * Created by Admin on 2016/08/30.
 */
public class StudentRegisterScreen extends AppCompatActivity {

    private EditText[] studentDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);

        studentDetails = new EditText[4];

        studentDetails[0] = (EditText) findViewById(R.id.studName);
        studentDetails[1] = (EditText) findViewById(R.id.studSurname);
        studentDetails[2] = (EditText) findViewById(R.id.studEmail);
        studentDetails[3] = (EditText) findViewById(R.id.studPassword);

/*
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MyActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
*/

    }

    public void btn_preview(View view) {

        if(isValidStudentAccount()) {

            StudentAccount studentAccount = StudentAccountFactory.getStudent(studentDetails[2].getText().toString(),
                    studentDetails[3].getText().toString());

            StudentProfile studentProfile = StudentProfileFactory.getStudentProfile(studentDetails[0].getText().toString(),
                    studentDetails[1].getText().toString());

            Student student = new Student(studentAccount, studentProfile);

            startActivity(new Intent(this, StudentRegiserterPreviewScreen.class).putExtra("student", student));
        }
    }

    private boolean isValidStudentAccount() {

        String studentNumber = studentDetails[2].getText().toString();

        try {

            if (studentNumber.contains("@")) {

                studentNumber = studentNumber.split("@")[0];

                ThirdYear year = new StudentThirdYear().execute(studentNumber).get();

                if(year != null)
                    return true;
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        Toast.makeText(StudentRegisterScreen.this, "\n Sorry this is not a valid DS student email \n Try Again.\n", Toast.LENGTH_LONG).show();

        return false;
    }
}
