package persistentie.dao;

import domein.Lokaal;
import enums.DAO;
import java.util.List;
import persistentie.DAOFactory;

/**
 *
 * @author MAXIME
 */
public class LokaalRepository {
    
     public Lokaal findLokaalByNaam(String lNaam) {
        return (Lokaal) DAOFactory.getDAO(DAO.LOKAAL).get(lNaam);
    }
     
     public List<Lokaal> findLokalenByCampusId(String campusNaam) {
        LokaalDAO lDAO = (LokaalDAO) DAOFactory.getDAO(DAO.LOKAAL);
        return  lDAO.findLokalenByCampusId(campusNaam);
    }
    
}
