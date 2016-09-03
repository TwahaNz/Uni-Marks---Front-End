package app.tnz.com.unimarks.activities.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.tnz.com.unimarks.domain.student.Student;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.domain.student.StudentProfile;
import app.tnz.com.unimarks.factories.student.StudentAccountFactory;
import app.tnz.com.unimarks.factories.student.StudentProfileFactory;

/**
 * Created by Admin on 2016/09/01.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private Student student;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs, Student student) {
        super(fm);
        this.tabCount = numberOfTabs;
        this.student = student;
    }

    @Override
    public Fragment getItem(int position) {

        FragmentMain fragmentMain;
        FragmentSub fragmentSub;

        switch (position) {

            case 0: fragmentMain = new FragmentMain().setStudent(student);
                    return fragmentMain;

            case 1: fragmentSub = new FragmentSub().setStudent(student);
                    return  fragmentSub;

            default: return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}