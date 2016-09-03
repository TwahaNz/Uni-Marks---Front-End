package app.tnz.com.unimarks.domain.student;

import java.io.Serializable;

/**
 * Created by Admin on 2016/08/25.
 */
public class StudentProfile implements Serializable {

    private long id;
    private String name;
    private String surname;
    private String classGroup;
    private String cellphone;

    private StudentProfile(){}

    public StudentProfile(Builder builder) {

        id = builder.id;
        name = builder.name;
        surname = builder.surname;
        classGroup = builder.classGroup;
        cellphone = builder.cellphone;

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getClassGroup() {
        return classGroup;
    }

    public String getCellphone() {
        return cellphone;
    }

    public long getId() {
        return id;
    }

    public static class Builder {

        public String name;
        public String classGroup;
        public String surname;
        public String cellphone;
        public long id;

        public Builder setId(long id) {

            this.id = id;

            return this;
        }

        public Builder setName(String name) {

            this.name = name;

            return this;
        }

        public Builder setClassGroup(String classGroup) {

            this.classGroup = classGroup;

            return this;
        }

        public Builder setSurname(String surname) {

            this.surname = surname;

            return this;
        }

        public Builder setCellphone(String cellphone) {

            this.cellphone = cellphone;

            return this;
        }

        public Builder copyStudentProfile(StudentProfile studentProfile) {

            this.id = studentProfile.id;
            this.name = studentProfile.name;
            this.surname = studentProfile.surname;
            this.classGroup = studentProfile.classGroup;
            this.cellphone = studentProfile.cellphone;

            return this;
        }

        public StudentProfile build(){
            return new StudentProfile(this);
        }

    }

}
