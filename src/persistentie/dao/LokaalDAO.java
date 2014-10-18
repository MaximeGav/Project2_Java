package persistentie.dao;

import domein.Lokaal;
import java.util.List;
import persistentie.GenericDao;

/**
 *
 * @author RootSoft
 * @param <T>
 */
public class LokaalDAO<T> extends GenericDao<Lokaal>{
    
    //Attributes
    
    //Constructors
    public LokaalDAO() {
        super(Lokaal.class);
    }

    //Callbacks
    
    @Override
    public List<Lokaal> findAll() {
        return super.findAll();
    }
    
    @Override
    public void insert(Lokaal l) {
        super.insert(l);
    }
    
    @Override
    public void update(Lokaal l) {
        super.update(l);
    }
    
    @Override
    public Lokaal get(int id) {
        return (Lokaal) super.get(id);
    }
    
        
    public List<Lokaal> findLokalenByCampusId(String campusNaam) {
        //select e from domein.Lokaal e JOIN e.campus c where c.naam = "Schoonmeersen"
        return em.createQuery("select e from " + type.getName() + " e JOIN e.campus c where c.naam = :campusNaam").setParameter("campusNaam", campusNaam).getResultList();
    }
   
   
    
    

    
}
