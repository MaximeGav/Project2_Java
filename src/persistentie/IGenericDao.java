package persistentie;

import java.util.List;

/**
 *
 * @author MAXIME
 */
public interface IGenericDao<T>{
    
    public List<T> findAll();

    public void update(T object);
    
    public T get(int id);

    public T get(String id);

    public void delete(T object);

    public void insert(T object);

    public boolean exists(int id);

    
}
