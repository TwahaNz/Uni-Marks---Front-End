package app.tnz.com.unimarks.activities.term;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import app.tnz.com.unimarks.R;
import app.tnz.com.unimarks.configurations.utils.app.App;
import app.tnz.com.unimarks.domain.marks.Term;
import app.tnz.com.unimarks.domain.subjects.DOS;
import app.tnz.com.unimarks.domain.subjects.ISYA;

/**
 * Created by Admin on 2016/09/02.
 */
public class ISYAActivity extends AppCompatActivity {

    private TextView[] textViews;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        try {
            ISYA isya = (ISYA) getIntent().getSerializableExtra("isya");

            textViews = new TextView[4];

            textViews[0] = (TextView) findViewById(R.id.term1);
            textViews[1] = (TextView) findViewById(R.id.term2);
            textViews[2] = (TextView) findViewById(R.id.term3);
            textViews[3] = (TextView) findViewById(R.id.term4);

            textViews[0].setText(isya.getTerm1());
            textViews[1].setText(isya.getTerm2());
            textViews[2].setText(isya.getTerm3());
            textViews[3].setText(isya.getTerm4());
        } catch (Exception e) {
            Toast.makeText(App.getAppContext(), "\n Please check your internet settings \n", Toast.LENGTH_SHORT).show();
            finish();
            ;
        }

    }
}
