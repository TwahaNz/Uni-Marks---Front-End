package app.tnz.com.unimarks.restapi.marks;

import app.tnz.com.unimarks.domain.marks.Term;
import app.tnz.com.unimarks.domain.student.Student;

/**
 * Created by Admin on 2016/09/02.
 */
public interface TermAPI {

    Term getSubjecMarks(Student student) throws Exception;
}
