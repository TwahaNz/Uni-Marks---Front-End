package app.tnz.com.unimarks.activities.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import app.tnz.com.unimarks.R;
import app.tnz.com.unimarks.domain.student.Student;

public class MainActivity extends AppCompatActivity {

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);

/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        student = (Student) getIntent().getSerializableExtra("student");


        if(student != null) {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

            tabLayout.addTab(tabLayout.newTab().setText("Subject"));
            tabLayout.addTab(tabLayout.newTab().setText("Profile"));

            final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), student);

            viewPager.setAdapter(adapter);

            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    return;
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }

            });
        } else {
            Toast.makeText(MainActivity.this, "\n Unable To Log You In \n Please contact the Admin \n", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
