package persistentie.dao;

import domein.Beschikbaarheid;
import java.util.List;
import persistentie.GenericDao;

/**
 *
 * @author RootSoft
 * @param <T>
 */
public class BeschikbaarheidDAO<T> extends GenericDao<Beschikbaarheid>{
    
    //Attributes
    
    //Constructors
    public BeschikbaarheidDAO() {
        super(Beschikbaarheid.class);
    }

    //Callbacks
    
    @Override
    public List<Beschikbaarheid> findAll() {
        return super.findAll();
    }
    
    @Override
    public void insert(Beschikbaarheid b) {
        super.insert(b);
    }
    
    @Override
    public void update(Beschikbaarheid b) {
        super.update(b);
    }
    
    @Override
    public Beschikbaarheid get(int id) {
        return (Beschikbaarheid) super.get(id);
    }
    
        
    public List<Beschikbaarheid> findBeschikbaarhedenByPromotorEmail(String pEmail) {
        //select e from Beschikbaarheid e JOIN e.promotor c where c.email = "Harry.DeMan@hogent.be"
        return em.createQuery("select e from " + type.getName() + " e JOIN e.promotor p where p.email = :pEmail").setParameter("pEmail", pEmail).getResultList();
    }
   
   
    
    

    
}
