/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistentie.dao;

import domein.Campus;
import enums.DAO;
import java.util.List;
import persistentie.DAOFactory;

/**
 *
 * @author MAXIME
 */
public class CampusRepository {
    
    public List<Campus> geefLijstCampussen() {
        return DAOFactory.getDAO(DAO.CAMPUS).findAll();
    }
    
     public Campus findCampusByNaam(String cNaam) {
        return (Campus) DAOFactory.getDAO(DAO.CAMPUS).get(cNaam);
    }
    
}
