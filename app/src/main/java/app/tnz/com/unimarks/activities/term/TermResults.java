package app.tnz.com.unimarks.activities.term;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.tnz.com.unimarks.R;
import app.tnz.com.unimarks.domain.marks.Term;

/**
 * Created by Admin on 2016/09/02.
 */
public class TermResults extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Term termResults = (Term) getIntent().getSerializableExtra("term");

    }
}
