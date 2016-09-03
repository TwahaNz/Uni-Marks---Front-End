package app.tnz.com.unimarks.repositories.student;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import app.tnz.com.unimarks.configurations.utils.app.App;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.domain.student.StudentProfile;
import app.tnz.com.unimarks.factories.student.StudentProfileFactory;
import app.tnz.com.unimarks.repositories.student.impl.StudentProfileRepositoryImpl;

/**
 * Created by Admin on 2016/08/31.
 */
public class StudentProfileRepositoryTest extends AndroidTestCase {

    private static final String TAG = "STUDENT REPOSITORY";
    private Long id;

    public void testAllCRUDOperations_whenCreatingTheStudentRepository(){

        StudentProfileRepository studentProfileRepository = new StudentProfileRepositoryImpl(App.getAppContext());

        StudentProfile studentProfile = StudentProfileFactory.getStudentProfile("John", "Peterson");

        //INSERT

        studentProfile = studentProfileRepository.insert(studentProfile);

        id = studentProfile.getId();

        Assert.assertNotNull(TAG, id);

        //UPDATE

        studentProfile = new StudentProfile.Builder()
                .copyStudentProfile(studentProfile)
                .build();

        int updatedRow = studentProfileRepository.update(studentProfile);

        Assert.assertEquals(TAG, 1, updatedRow);

        //FIND STUDENT

        studentProfile = studentProfileRepository.find_by_id(studentProfile.getId());

        Assert.assertEquals(TAG, "John", studentProfile.getName());

        //FIND ALL

        Set<StudentProfile> studentProfiles = studentProfileRepository.find_all();

        int totalStudents = studentProfiles.size();

        Assert.assertTrue(TAG, totalStudents > 0);

        //DELETE ALL

        int deletedRows = studentProfileRepository.delete_all();

        Assert.assertTrue(TAG, deletedRows > 0);
    }
}
