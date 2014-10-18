package persistentie.dao;

import domein.Campus;
import domein.Lokaal;
import domein.Presentatie;
import domein.Promotor;
import domein.Student;
import enums.DAO;
import java.util.List;
import persistentie.DAOFactory;
import persistentie.GenericDao;

/**
 *
 * @author RootSoft
 * @param <T>
 */
public class PresentatieDAO<T> extends GenericDao<Presentatie>{
    
    //Attributes
    
    //Constructors
    public PresentatieDAO() {
        super(Presentatie.class);
    }

    //Callbacks
    
    @Override
    public List<Presentatie> findAll() {
        return super.findAll();
    }
    
    @Override
    public void insert(Presentatie p) {
        super.insert(p);
    }
    
    @Override
    public void update(Presentatie p) {
        super.update(p);
    }
    
    @Override
    public Presentatie get(int id) {
        return (Presentatie) super.get(id);
    }
    
    public void planningAanpassen(String studentEmail, int dag, int tijdslot, String campusNaam, String lokaalNaam) {
        Lokaal lokaal = (Lokaal) DAOFactory.getDAO(DAO.LOKAAL).get(lokaalNaam);
        Campus campus = (Campus) DAOFactory.getDAO(DAO.CAMPUS).get(campusNaam);
        Student student = (Student) DAOFactory.getDAO(DAO.STUDENT).get(studentEmail);
        Presentatie p = new Presentatie(dag, tijdslot, lokaal, campus, student);
        update(p);
    }
    
    public void planPresentatie(int dag, int tijdsvak, String lokaalNaam, String campusNaam, String studentEmail) {
        Lokaal lokaal = (Lokaal) DAOFactory.getDAO(DAO.LOKAAL).get(lokaalNaam);
        Campus campus = (Campus) DAOFactory.getDAO(DAO.CAMPUS).get(campusNaam);
        Student student = (Student) DAOFactory.getDAO(DAO.STUDENT).get(studentEmail);
        Presentatie p = new Presentatie(dag, tijdsvak, lokaal, campus, student);
        insert(p);
    }
    
    public Presentatie findPresentatieByDagAndTijdsvak(int dag, int tijdsvak) {
        //select e from Presentatie e where e.dag = 2 and e.tijdsvak = 5
        try {
            return (Presentatie) em.createQuery("SELECT e FROM " + type.getName() + " e WHERE e.dag = :dag and e.tijdsvak = :tijdsvak")
                    .setParameter("dag", dag).setParameter("tijdsvak", tijdsvak).getSingleResult();
        } catch (Exception e) {
            return null;
        }
         
    }
    
    public List<Presentatie> findPresentatieByLokaalId(String lokaalNaam) {
        //select e from Presentatie e join e.lokaal l where l.naam = "test"
        return em.createQuery("SELECT e FROM " + type.getName() + " e JOIN e.lokaal l WHERE l.naam = :lokaalNaam")
                 .setParameter("lokaalNaam", lokaalNaam).getResultList();
    }
    
    public Presentatie findPresentatieByStudentEmail(String email) {
        //SELECT e FROM Presentatie e JOIN e.student s WHERE s.email = "amedeo.vandenbroecke.s6682@student.hogent.be"
        try {
            return (Presentatie) em.createQuery("SELECT e FROM " + type.getName() + " e JOIN e.student s WHERE s.email = :email")
                 .setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public void kenJuryledenToe(String studentEmail, String jurylidEmail) {
        Promotor jurylid = (Promotor) DAOFactory.getDAO(DAO.PROMOTOR).get(jurylidEmail);
        Presentatie presentatie = findPresentatieByStudentEmail(studentEmail);
        presentatie.setJurylid(jurylid);
        update(presentatie);
    }
    
}
