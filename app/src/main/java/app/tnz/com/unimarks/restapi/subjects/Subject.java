package app.tnz.com.unimarks.restapi.subjects;

/**
 * Created by Admin on 2016/09/03.
 */
public interface Subject<T,S> {
    public T getDOSMarks(S studentNumber) throws Exception;
}
