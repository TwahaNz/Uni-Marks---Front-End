package app.tnz.com.unimarks.repositories.student;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import app.tnz.com.unimarks.configurations.utils.app.App;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.factories.student.StudentAccountFactory;
import app.tnz.com.unimarks.repositories.student.impl.StudentAccountRepositoryImpl;

/**
 * Created by Admin on 2016/08/21.
 */

public class StudentAccountRepositoryTest extends AndroidTestCase {

    private static final String TAG = "STUDENT REPOSITORY";
    private Long id;

    public void testAllCRUDOperations_whenCreatingTheStudentRepository(){

        StudentAccountRepository studentAccountRepository = new StudentAccountRepositoryImpl(App.getAppContext());

        StudentAccount studentAccount = StudentAccountFactory.getStudent("211234567@mycput.ac.za", "12345");

        //INSERT

        studentAccount = studentAccountRepository.insert(studentAccount);

        id = studentAccount.getId();

        Assert.assertNotNull(TAG, id);

        //UPDATE

        studentAccount = new StudentAccount.Builder()
                    .copyStudent(studentAccount)
                    .build();

        int updatedRow = studentAccountRepository.update(studentAccount);

        Assert.assertEquals(TAG, 1, updatedRow);

        //FIND STUDENT

        studentAccount = studentAccountRepository.find_by_id(studentAccount.getId());

        Assert.assertEquals(TAG, "211234567@mycput.ac.za", studentAccount.getStudentEmail());

        //FIND STUDENT BY DETAILS

        id = studentAccountRepository.find_by_details(studentAccount.getStudentEmail(), studentAccount.getStudentPassword());

        Assert.assertNotNull(id);

        //FIND ALL

        Set<StudentAccount> studentAccounts = studentAccountRepository.find_all();

        int totalStudents = studentAccounts.size();

        Assert.assertTrue(TAG, totalStudents > 0);

        //DELETE ALL

        int deletedRows = studentAccountRepository.delete_all();

        Assert.assertTrue(TAG, deletedRows > 0);
    }

}
