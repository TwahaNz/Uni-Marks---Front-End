package app.tnz.com.unimarks.restapi.student;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.restapi.student.impl.StudentAccountActivateAPIImpl;

/**
 * Created by Admin on 2016/08/25.
 */
public class StudentAccountActivateAPIImplTest extends AndroidTestCase {

    private final String TAG = "STUDENT ACCOUNT ACTIVATE RESTAPI";
    private StudentAccountActivateAPI studentAccountActivate;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        studentAccountActivate = new StudentAccountActivateAPIImpl();
    }

    public void testIfAStudentCanActivateTheirAccount() throws Exception {

        StudentAccount studentAccount = studentAccountActivate.activateStudentAccount("21234511@mycput.ac.za", "12145");

        Assert.assertNotNull(TAG, studentAccount);
        Assert.assertTrue(TAG, studentAccount.getId() > 0);

    }
}
