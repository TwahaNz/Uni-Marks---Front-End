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
import app.tnz.com.unimarks.services.student.StudentDeleteService;
import app.tnz.com.unimarks.services.student.StudentRegisterService;
import app.tnz.com.unimarks.services.student.StudentUpdateService;


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

        Button delete = (Button) view.findViewById(R.id.btn_delete);
        Button update = (Button) view.findViewById(R.id.btn_update);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();
            }
        });

        final View viewFinal = view;

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewStudentDetails(viewFinal);
                Toast.makeText(App.getAppContext(), "Successfully Updated Your Details", Toast.LENGTH_SHORT).show();
                disableAllFields();
            }
        });

        return view;

    }

    private void updateDetails() {

        Student upStudent = student;

        try {

            Student student = new StudentUpdateService().execute(String.valueOf(upStudent.getStudentAccount().getId()), upStudent.getStudentProfile().getName(),
                    upStudent.getStudentProfile().getSurname(), upStudent.getStudentAccount().getStudentEmail(), upStudent.getStudentAccount().getStudentPassword())
                    .get();

            if (student != null) {

                studentAccountRepository = new StudentAccountRepositoryImpl(App.getAppContext());
                studentAccountRepository.update(student.getStudentAccount());

                studentProfileRepository = new StudentProfileRepositoryImpl(App.getAppContext());
                studentProfileRepository.update(student.getStudentProfile());

                Toast.makeText(App.getAppContext(), "Account Successfully Updated", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(App.getAppContext(), "Account Was Not Updated", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteStudent() {

        Student upStudent = student;

        try {

            Student student = new StudentDeleteService().execute(String.valueOf(upStudent.getStudentAccount().getId()), upStudent.getStudentProfile().getName(),
                    upStudent.getStudentProfile().getSurname(), upStudent.getStudentAccount().getStudentEmail(), upStudent.getStudentAccount().getStudentPassword())
                    .get();

            if (student != null) {

                /*studentAccountRepository = new StudentAccountRepositoryImpl(App.getAppContext());
                studentAccountRepository.update(student.getStudentAccount());

                studentProfileRepository = new StudentProfileRepositoryImpl(App.getAppContext());
                studentProfileRepository.update(student.getStudentProfile());*/

                Toast.makeText(App.getAppContext(), "Account Successfully Deleted", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(App.getAppContext(), "Account Was Not Deleted", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void setNewStudentDetails(View view) {

        studentDetails[0] = (EditText) view.findViewById(R.id.editName);
        studentDetails[0].setText(studentDetails[0].getText());

        studentDetails[1] = (EditText) view.findViewById(R.id.editSurname);
        studentDetails[1].setText(studentDetails[1].getText());

        studentDetails[3] = (EditText) view.findViewById(R.id.editPassword);
        studentDetails[3].setText(studentDetails[2].getText());
    }

    private void disableAllFields(){
        studentDetails[2].setFocusable(false);
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
