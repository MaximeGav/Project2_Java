package domein;

import java.util.*;
import persistentie.DAOFactory;
import enums.DAO;

public class GebruikerRepository {

    //Attributen

    //Constructors
    
    /**
     * Constructor van de gebruikerrepository
     */
    public GebruikerRepository() {
        DAOFactory.initFactory();

        System.out.println("GebruikerRepository loaded.");
        
    }

    //Methoden
    /**
     * Methode om een lijst van alle studenten te krijgen
     *
     * @return Lijst van alle studenten
     */
    public List<Student> geefLijstStudenten() {
        return DAOFactory.getDAO(DAO.STUDENT).findAll();
    }

    /**
     * Methode om een lijst van alle promotoren te krijgen
     *
     * @return Lijst van alle promotoren
     */
    public List<Promotor> geefLijstPromotoren() {
        return DAOFactory.getDAO(DAO.PROMOTOR).findAll();
    }

    /**
     * Methode die een student koppelt met een promotor door middel van het
     * studentId.
     *
     * @param studentEmail
     * @param promotorEmail
     */
    public void kenPromotorToe(String studentEmail, String promotorEmail) {
        Student s = (Student) DAOFactory.getDAO(DAO.STUDENT).get(studentEmail);
        Promotor p = (Promotor) DAOFactory.getDAO(DAO.PROMOTOR).get(promotorEmail);
        
        s.setPromotor(p);
        DAOFactory.getDAO(DAO.STUDENT).update(s);
        
    }

    public void verhoogAantalKeerJury(String promotorEmail, int aantalKeerJury) {
        Promotor p = (Promotor) DAOFactory.getDAO(DAO.PROMOTOR).get(promotorEmail);
        p.setAantalKeerJury(aantalKeerJury);
        DAOFactory.getDAO(DAO.PROMOTOR).update(p);
    }

    /**
     * Methode die een student toevoegd aan de lijst van studenten
     *
     * @param student
     */
    public void addStudent(Student student) {
        DAOFactory.getDAO(DAO.STUDENT).insert(student);
    }

    /**
     * Methode die een promotor toevoegd aan de lijst van promotoren
     *
     * @param promotor
     */
    public void addPromotor(Promotor promotor) {
        DAOFactory.getDAO(DAO.PROMOTOR).insert(promotor);
    }

    public Student findStudentByEmail(String studentEmail) {
        Student s = (Student) DAOFactory.getDAO(DAO.STUDENT).get(studentEmail);
        return s;
    }

    public Promotor findPromotorByEmail(String promotorEmail) {
        Promotor p = (Promotor) DAOFactory.getDAO(DAO.PROMOTOR).get(promotorEmail);
        return p;
    }

    //Getters en Setters

    public BPCoordinator getBpCoordinator() {
        return (BPCoordinator) DAOFactory.getDAO(DAO.BPCOORDINATOR).findAll().get(0);
    }
    

}
