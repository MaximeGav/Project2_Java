package persistentie;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

/**
 *
 * @author ROOTSOFT
 * @param <T>
 */
public abstract class GenericDao<T> implements IGenericDao<T>{
    
    
    protected Class<T> type;
    protected EntityManager em;

    public GenericDao(Class<T> type) {
        super();
        this.type = type;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    //@Transactional(readOnly = true)
    @Override
    public T get(int id) {
        T entity = em.find(type, id);
        return entity;
    }

    public T get(String id) {
        // Voor als de primary key geen cijfer is, zoals bij Gebruikers (Studenten en Promotoren)
        T entity = em.find(type, id);
        return entity;
    }

    //@Transactional(readOnly = true)
    @Override
    public List<T> findAll() {
        return em.createQuery("select e from " + type.getName() + " e").getResultList();
    }

    //@Transactional(readOnly = true)
    @Override
    public boolean exists(int id) {
        T entity = em.find(type, id);
        return entity != null;
    }

    @Override
    public void insert(T object) {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (RollbackException e) {
            System.out.println(object.toString() + " staat al in de database");
        }

    }

    @Override
    public void delete(T object) {
        em.getTransaction().begin();
        em.remove(em.merge(object));
        em.getTransaction().commit();
    }

    @Override
    public void update(T object) {
        em.getTransaction().begin();
        T o = em.merge(object);
        em.getTransaction().commit();
       
    }
}
    
    

