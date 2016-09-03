package app.tnz.com.unimarks.activities.term;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import app.tnz.com.unimarks.R;
import app.tnz.com.unimarks.domain.marks.Term;
import app.tnz.com.unimarks.domain.subjects.DOS;
import app.tnz.com.unimarks.domain.subjects.TP;

/**
 * Created by Admin on 2016/09/02.
 */
public class TPActivity extends AppCompatActivity {

    private TextView[] textViews;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TP tp = (TP) getIntent().getSerializableExtra("tp");

        textViews = new TextView[4];

        textViews[0] = (TextView) findViewById(R.id.term1);
        textViews[1] = (TextView) findViewById(R.id.term2);
        textViews[2] = (TextView) findViewById(R.id.term3);
        textViews[3] = (TextView) findViewById(R.id.term4);

        textViews[0].setText(tp.getTerm1());
        textViews[1].setText(tp.getTerm2());
        textViews[2].setText(tp.getTerm3());
        textViews[3].setText(tp.getTerm4());

    }
}
