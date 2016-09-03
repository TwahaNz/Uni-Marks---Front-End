package app.tnz.com.unimarks.domain.subjects;


import java.io.Serializable;

/**
 * Created by Admin on 2016/09/02.
 */
public class ISYB implements Serializable {

    private String studentNumber;
    private String term1;
    private String term2;
    private String term3;
    private String term4;

    public ISYB() {
    }

    public ISYB(String studentNumber, String term1, String term2, String term3, String term4) {
        this.studentNumber = studentNumber;
        this.term1 = term1;
        this.term2 = term2;
        this.term3 = term3;
        this.term4 = term4;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getTerm1() {
        return term1;
    }

    public String getTerm2() {
        return term2;
    }

    public String getTerm3() {
        return term3;
    }

    public String getTerm4() {
        return term4;
    }
}
