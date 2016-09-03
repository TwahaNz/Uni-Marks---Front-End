package app.tnz.com.unimarks.restapi.year;

/**
 * Created by Admin on 2016/09/03.
 */
public interface Year<T, S> {

    public T isStudentYear(S studentNumber) throws Exception;
}
