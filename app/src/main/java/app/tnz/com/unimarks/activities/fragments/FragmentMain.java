package app.tnz.com.unimarks.activities.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import app.tnz.com.unimarks.R;
import app.tnz.com.unimarks.activities.MyAdapter;
import app.tnz.com.unimarks.activities.term.DOSActivity;
import app.tnz.com.unimarks.activities.term.IRPActivity;
import app.tnz.com.unimarks.activities.term.ISYAActivity;
import app.tnz.com.unimarks.activities.term.ISYBActivity;
import app.tnz.com.unimarks.activities.term.TPActivity;
import app.tnz.com.unimarks.activities.term.TermResults;
import app.tnz.com.unimarks.configurations.utils.app.App;
import app.tnz.com.unimarks.domain.student.Student;
import app.tnz.com.unimarks.domain.subjects.DOS;
import app.tnz.com.unimarks.domain.subjects.IRP;
import app.tnz.com.unimarks.domain.subjects.ISYA;
import app.tnz.com.unimarks.domain.subjects.ISYB;
import app.tnz.com.unimarks.domain.subjects.TP;
import app.tnz.com.unimarks.services.subjects.DOSMarksService;
import app.tnz.com.unimarks.services.subjects.IRPMarksService;
import app.tnz.com.unimarks.services.subjects.ISYAMarksService;
import app.tnz.com.unimarks.services.subjects.ISYBMarksService;
import app.tnz.com.unimarks.services.subjects.TPMarksService;


public class FragmentMain extends Fragment {

    private ListView lv_items;
    private String[] items;
    private ArrayAdapter<String> adapter;

    private Student student;

    private DOS dos;
    private IRP irp;
    private TP tp;
    private ISYB isyb;
    private ISYA isya;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        lv_items = (ListView) view.findViewById(R.id.listView);

        items = new String[]{
                "Development Software 3",
                "Information Systems 3B",
                "Internet Programming 3",
                "Information Systems 3A",
                "Technical Programming 3"
        };

        adapter = new MyAdapter(App.getAppContext(), items); //new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, items);

        lv_items.setAdapter(adapter);


        lv_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String studentNumber = student.getStudentAccount().getStudentEmail().split("@")[0];

                Toast.makeText(getContext(), "" + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

                try {
                    processDOS(studentNumber, position);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        return view;
    }

    public void processDOS(String studentNumber, int position) throws Exception{

        try{
            switch (position) {
                case 0:
                    dos = new DOSMarksService().execute(studentNumber).get();
                    Intent intent =  new Intent(getContext(), DOSActivity.class);
                    intent.putExtra("dos", dos);
                    startActivity(intent);
                    return;
                case 1:
                    isyb = new ISYBMarksService().execute(studentNumber).get();
                    startActivity(new Intent(App.getAppContext(), ISYBActivity.class)
                            .putExtra("isyb", isyb));
                    return;
                case 2:
                    irp = new IRPMarksService().execute(studentNumber).get();
                    startActivity(new Intent(App.getAppContext(), IRPActivity.class)
                            .putExtra("irp", irp));
                    return;
                case 3:
                    isya = new ISYAMarksService().execute(studentNumber).get();
                    startActivity(new Intent(App.getAppContext(), ISYAActivity.class)
                            .putExtra("isya", isya));
                    return;
                case 4:
                    tp = new TPMarksService().execute(studentNumber).get();
                    startActivity(new Intent(App.getAppContext(), TPActivity.class)
                            .putExtra("tp", tp));
                    return;
                default:
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FragmentMain setStudent(Student student) {
        this.student = student;
        return this;
    }
}
