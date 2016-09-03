package app.tnz.com.unimarks.factories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.factories.student.StudentAccountFactory;

/**
 * Created by Admin on 2016/08/21.
 */
public class StudentAccountFactoryTest {

    private StudentAccount studentAccount;
    private static final String TAG = "STUDENT FACTORY";

    @Before
    public void setup(){

        studentAccount = StudentAccountFactory.getStudent("212234567@mycput.ac.za", "12345");
    }

    @Test
    public void whenCreatingAStudentFactory_checkIfAllTheInsertedValuesAreCorrect(){

        Assert.assertEquals(TAG, "212234567@mycput.ac.za", studentAccount.getStudentEmail());
        Assert.assertEquals(TAG, "12345", studentAccount.getStudentPassword());
    }

    @Test
    public void whenChangingAStudentValue_checkIfTheValueChangedIsCorrect(){

        studentAccount = new StudentAccount.Builder()
                .copyStudent(studentAccount)
                .build();

        Assert.assertEquals(TAG, "212234567@mycput.ac.za", studentAccount.getStudentEmail());
    }

    @Test
    public void whenAddingAStudentIDValue_checkIfTheIDIsInserted(){

        studentAccount = StudentAccountFactory.getStudent(1, "211234567@mycput.ac.za", "12345");

        Assert.assertEquals(TAG, "211234567@mycput.ac.za", studentAccount.getStudentEmail());
        Assert.assertEquals(TAG, "12345", studentAccount.getStudentPassword());
        Assert.assertEquals(TAG, 1, studentAccount.getId());
    }
}
