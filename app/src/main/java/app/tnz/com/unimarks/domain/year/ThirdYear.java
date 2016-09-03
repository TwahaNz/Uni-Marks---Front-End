package app.tnz.com.unimarks.domain.year;

import java.io.Serializable;

/**
 * Created by Admin on 2016/09/02.
 */

public class ThirdYear implements Serializable {

    private String studentNumber;

    public ThirdYear(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
}
