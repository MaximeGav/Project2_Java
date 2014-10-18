package persistentie.dao;

import domein.BPCoordinator;
import domein.Presentatie;
import enums.DAO;
import java.util.ArrayList;
import java.util.List;
import persistentie.DAOFactory;

/**
 *
 * @author MAXIME
 */
public class PresentatieRepository {
    
    private BPCoordinator bpCoordinator;
    
     public List<Integer> geefPresentaties() {
        List<Presentatie> presentaties = bpCoordinator.geefPlanning().geefPresentaties();
        List<Integer> geplandePresentaties = new ArrayList<>();
        for (Presentatie p : presentaties) {
            geplandePresentaties.add(p.getDag());
            geplandePresentaties.add(p.getTijdsvak());
        }
        return geplandePresentaties;
    }
    
    public Presentatie geefPresentatie(int dag, int tijdsvak) {
        PresentatieDAO pDAO = (PresentatieDAO) DAOFactory.getDAO(DAO.PRESENTATIE);
        return pDAO.findPresentatieByDagAndTijdsvak(dag, tijdsvak);
    }
    
    public void verwijderPresentatie(String studentEmail) {
        Presentatie p = (Presentatie) DAOFactory.getDAO(DAO.PRESENTATIE).get(studentEmail);
        DAOFactory.getDAO(DAO.PRESENTATIE).delete(p);
    }
    
    public List<Presentatie> findPresentatiesByLokaalId(String lokaalNaam) {
        PresentatieDAO pDAO = (PresentatieDAO) DAOFactory.getDAO(DAO.PRESENTATIE);
        return pDAO.findPresentatieByLokaalId(lokaalNaam);
    }
    
    public Presentatie findPresentatieByStudentEmail(String sEmail) {
        PresentatieDAO pDAO = (PresentatieDAO) DAOFactory.getDAO(DAO.PRESENTATIE);
        return pDAO.findPresentatieByStudentEmail(sEmail);
    }
    
    public void planningAanpassen(String studentEmail, int dag, int tijdslot, String campusNaam, String lokaalNaam) {
        PresentatieDAO pDAO = (PresentatieDAO) DAOFactory.getDAO(DAO.PRESENTATIE);
        pDAO.planningAanpassen(studentEmail, dag, tijdslot, campusNaam, lokaalNaam);
    }
    
}
