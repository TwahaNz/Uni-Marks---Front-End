package app.tnz.com.unimarks.domain.marks;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Admin on 2016/09/02.
 */
public class Term implements Serializable {

    private ArrayList<String> subjects;
    private int position;
    private String[] marks;

    public Term(ArrayList<String> subjects, String[] marks, int position) {
        this.subjects = subjects;
        this.marks = marks;
        this.position = position;
    }

    public String getSubject() {
        return subjects.get(position);
    }

    public String[] getMarks() {
        return marks;
    }
}
