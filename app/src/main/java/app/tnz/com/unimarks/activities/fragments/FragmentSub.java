package app.tnz.com.unimarks.activities.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.tnz.com.unimarks.R;
import app.tnz.com.unimarks.activities.StudentLogInScreen;
import app.tnz.com.unimarks.configurations.utils.app.App;
import app.tnz.com.unimarks.domain.student.Student;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.repositories.student.StudentAccountRepository;
import app.tnz.com.unimarks.repositories.student.StudentProfileRepository;
import app.tnz.com.unimarks.repositories.student.impl.StudentAccountRepositoryImpl;
import app.tnz.com.unimarks.repositories.student.impl.StudentProfileRepositoryImpl;
import app.tnz.com.unimarks.services.student.StudentRegisterService;


public class FragmentSub extends Fragment {

    private StudentAccountRepository studentAccountRepository;
    private StudentProfileRepository studentProfileRepository;

    private Student student;

    private EditText[] studentDetails;

    public FragmentSub() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sub, container, false);

        studentDetails = new EditText[4];

        setStudentDetails(view);
        disableAllFields();

        Button edit = (Button) view.findViewById(R.id.btn_edit);
        Button update = (Button) view.findViewById(R.id.btn_update);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableAllFields();
                Toast.makeText(App.getAppContext(), "Editing Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        final View viewFinal = view;

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStudentDetails(viewFinal);
                Toast.makeText(App.getAppContext(), "Successfully Updated Your Details", Toast.LENGTH_SHORT).show();
                disableAllFields();
            }
        });

        return view;

    }

    private void updateDetails() {

        /*try {

            Student student = new StudentUpdateService().execute(studEmail, studPassword, studName, studSurname).get();

            if (student != null) {

                studentAccountRepository = new StudentAccountRepositoryImpl(App.getAppContext());
                studentAccountRepository.update(student.getStudentAccount());

                studentProfileRepository = new StudentProfileRepositoryImpl(App.getAppContext());
                studentProfileRepository.update(student.getStudentProfile());

                Toast.makeText(App.getAppContext(), "Account Successfully Created", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(App.getAppContext(), "Account Was Not Created", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private void setStudentDetails(View view) {

        studentDetails[0] = (EditText) view.findViewById(R.id.editName);
        studentDetails[0].setText(student.getStudentProfile().getName());

        studentDetails[1] = (EditText) view.findViewById(R.id.editSurname);
        studentDetails[1].setText(student.getStudentProfile().getSurname());

        studentDetails[2] = (EditText) view.findViewById(R.id.editEmail);
        studentDetails[2].setText(student.getStudentAccount().getStudentEmail());

        studentDetails[3] = (EditText) view.findViewById(R.id.editPassword);
        studentDetails[3].setText(student.getStudentAccount().getStudentPassword());
    }

    private void disableAllFields(){
        studentDetails[0].setFocusable(false);
        studentDetails[1].setFocusable(false);
        studentDetails[2].setFocusable(false);
        studentDetails[3].setFocusable(false);
    }

    private void enableAllFields(){
        studentDetails[0].setFocusable(true);
        studentDetails[1].setFocusable(true);
        //studentDetails[2].setFocusable(true);
        studentDetails[3].setFocusable(true);
    }

    public FragmentSub setStudent(Student student) {
        this.student = student;
        return this;
    }
}
