package app.tnz.com.unimarks.domain.student;

import java.io.Serializable;

/**
 * Created By Twaha:
 * Description: This class will be the main class that gets used in the app. This student class
 *              is responsible for the students details.
 */
public class StudentAccount implements Serializable {

    private long id;
    private String studentEmail;
    private String studentPassword;

    private StudentAccount(){}

    public StudentAccount(Builder stud_builder){

        id = stud_builder.id;
        studentEmail = stud_builder.studentEmail;
        studentPassword = stud_builder.studentPassword;
    }

    public long getId() {

        return id;
    }

    public String getStudentEmail() {

        return studentEmail;
    }

    public String getStudentPassword() {
        return studentPassword;
    }


    public static class Builder{

        private long id;
        private String studentEmail;
        public String studentPassword;

        public Builder setId(long id) {

            this.id = id;

            return this;
        }

        public Builder setStudentEmail(String studentEmail) {

            this.studentEmail = studentEmail;

            return this;
        }

        public Builder copyStudent(StudentAccount studentAccount){

            id = studentAccount.id;
            studentEmail = studentAccount.studentEmail;
            studentPassword = studentAccount.studentPassword;

            return this;
        }

        public Builder setStudentPassword(String studentPassword) {

            this.studentPassword = studentPassword;
            return this;
        }

        public StudentAccount build(){

            return new StudentAccount(this);
        }
    }
}
