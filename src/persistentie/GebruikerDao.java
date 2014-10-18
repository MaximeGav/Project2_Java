/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import java.util.List;

/**
 *
 * @author MAXIME
 * @param <Gebruiker>
 */
public class GebruikerDao<Gebruiker> extends GenericDao<Gebruiker>{
     public GebruikerDao(Class<Gebruiker> type) {
        super(type);
    }
     

    public List<Gebruiker> findByCampus(String campus) {
        return em.createQuery("select entity from " + type.getName() + " entity where entity.campus='" + campus + "'").getResultList();
    }
    
    
}
