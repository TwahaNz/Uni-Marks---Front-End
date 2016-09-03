package app.tnz.com.unimarks.repositories.student;

import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.repositories.Repository;

/**
 * Created by Admin on 2016/08/21.
 */
public interface StudentAccountRepository extends Repository<StudentAccount, Long> {

    Long find_by_details(String email, String password);
}
