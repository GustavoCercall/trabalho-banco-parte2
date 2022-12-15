package persistencia;

import java.util.List;

public interface DAO<T> {

    public List<T> selectAll() throws Exception;

    public void insert(T objeto) throws Exception;

    public void delete(T objeto) throws Exception;

    public void update(T objeto) throws Exception;

}
