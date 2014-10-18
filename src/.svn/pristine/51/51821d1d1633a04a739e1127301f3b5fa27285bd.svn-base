package persistentie.dao;

import domein.Inschrijving;
import enums.DAO;
import java.util.List;
import persistentie.DAOFactory;

/**
 *
 * @author MAXIME
 */
public class InschrijvingRepository {
    
    
    public List<Inschrijving> findInschrijvingenByPresentatieId(String studentEmail) {
        InschrijvingDAO iDAO = (InschrijvingDAO) DAOFactory.getDAO(DAO.INSCHRIJVING);
        return iDAO.findInschrijvingenByPresentatieId(studentEmail);
    }
    
    public List<Inschrijving> findInschrijvingenByGastEmail(String gastEmail) {
        InschrijvingDAO iDAO = (InschrijvingDAO) DAOFactory.getDAO(DAO.INSCHRIJVING);
        return iDAO.findInschrijvingenByGast(gastEmail);
    }
}
