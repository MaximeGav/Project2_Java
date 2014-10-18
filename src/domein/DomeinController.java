package domein;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import persistentie.DAOFactory;
import enums.DAO;
import persistentie.dao.CampusRepository;
import persistentie.dao.GastRepository;
import persistentie.dao.InschrijvingDAO;
import persistentie.dao.InschrijvingRepository;
import persistentie.dao.LokaalDAO;
import persistentie.dao.LokaalRepository;
import persistentie.dao.PresentatieDAO;
import persistentie.dao.PresentatieRepository;

public class DomeinController {

    //Attributen
    private final GebruikerRepository gebruikerRepository;
    private final PresentatieRepository presentatieRepository;
    private final InschrijvingRepository inschrijvingRepository;
    private final CampusRepository campusRepository;
    private final LokaalRepository lokaalRepository;
    private final GastRepository gastRepository;
    private final BPCoordinator bpCoordinator;

    //Constructors
    /**
     * Constructor van de domeincontroller
     */
    public DomeinController() {
        DAOFactory.initFactory();
        gebruikerRepository = new GebruikerRepository();
        presentatieRepository = new PresentatieRepository();
        inschrijvingRepository = new  InschrijvingRepository();
        campusRepository = new CampusRepository();
        lokaalRepository = new LokaalRepository();
        gastRepository = new GastRepository();
        bpCoordinator = gebruikerRepository.getBpCoordinator();
    }

    /**
     * Constructor van de domeincontroller
     *
     * @param gebruikerRepository
     * @param presentatieRepository
     * @param inschrijvingRepository
     * @param campusRepository
     * @param lokaalRepository
     * @param gastRepository
     */
    public DomeinController(GebruikerRepository gebruikerRepository, PresentatieRepository presentatieRepository, InschrijvingRepository inschrijvingRepository, CampusRepository campusRepository, LokaalRepository lokaalRepository, GastRepository gastRepository) {
        DAOFactory.initFactory();
        this.gebruikerRepository = gebruikerRepository;
        this.presentatieRepository = presentatieRepository;
        this.inschrijvingRepository = inschrijvingRepository;
        this.campusRepository = campusRepository;
        this.lokaalRepository = lokaalRepository;
        this.gastRepository = gastRepository;
        bpCoordinator = gebruikerRepository.getBpCoordinator();
    }

    //Methoden
    // ---------- GETTERS ---------- //
    public Map<String, String> geefPromotoren() {
        Map<String, String> promotoren = new HashMap<>();
        for (Promotor p : findLijstPromotoren()) {
            promotoren.put(p.getEmail(), p.toString());
        }
        return promotoren;
    }

    public Map<String, String> geefStudenten() {
        Map<String, String> studenten = new HashMap<>();
        for (Student s : findLijstStudenten()) {
            studenten.put(s.toString(), s.getEmail());
        }
        return studenten;
    }

    public List<String> geefCampussen() {
        List<String> campussen = new ArrayList<>();
        for (Campus c : findLijstCampussen()) {
            campussen.add(c.getNaam());
        }
        return campussen;
    }

    public List<String> geefLokalenByCampusId(String campusNaam) {
        List<String> lokalen = new ArrayList<>();
        for (Lokaal l : findLokalenByCampusId2(campusNaam)) {
            lokalen.add(l.getNaam());
        }
        return lokalen;
    }

    public Map<String, String> geefLijstOngeplandeStudentenVanPromotor(String pmail) {
        Map<String, String> studenten = new HashMap<>();
        for (Student s : findLijstStudenten()) {
            if (s.getPromotor().getEmail().equals(pmail)) {
                studenten.put(s.toString(), s.getEmail());
            }
        }
        return studenten;
    }
    
    public Map<String, List<String>> geefInschrijvingenByGastEmail(String gastEmail) {
        Map<String, List<String>> inschrijvingen = new HashMap<>();
        for (Inschrijving i : findInschrijvingenByGastEmail(gastEmail)) {
            List<String> info = new ArrayList<>();
            info.add(String.valueOf(i.getId()));
            if (i.getGast() != null) {
                info.add(i.getGast().getEmail());
            } else {
                info.add(" ");
            }
            info.add(i.getPresentatie().getStudent().getEmail());
            info.add(Boolean.toString(i.isIsGoedgekeurd()));
            inschrijvingen.put(gastEmail, info);
        }
        return inschrijvingen; 
    }
    
    public Map<String, List<String>> geefInschrijvingenByPresentatieId(String studentEmail) {
        Map<String, List<String>> inschrijvingen = new HashMap<>();
        for (Inschrijving i : findInschrijvingenByPresentatieId(studentEmail)) {
            List<String> info = new ArrayList<>();
            info.add(String.valueOf(i.getId()));
            if (i.getGast() != null) {
                info.add(i.getGast().getEmail());
            } else {
                info.add(" ");
            }
            info.add(i.getPresentatie().getStudent().getEmail());
            info.add(Boolean.toString(i.isIsGoedgekeurd()));
            inschrijvingen.put(studentEmail, info);
        }
        return inschrijvingen; 
    }


    /**
     * Geeft een lijst van beschikbaarheden terug. 
     * Key: BeschikbaarheidsId
     * Value: Lijst van Dag en Tijdsvak
     *
     * @param pMail de mail van de promotor.
     * @return Een lijst van beschikbaarheden.
     */
    public Map<String, List<Integer>> geefBeschikbaarhedenVanPromotor(String pMail) {
        Map<String, List<Integer>> beschikbaarheden = new HashMap<>();
        for (Beschikbaarheid b : findBeschikbaarhedenVanPromotor(pMail)) {
            List<Integer> info = new ArrayList<>();
            info.add(b.getDag());
            info.add(b.getTijdsvak());
            beschikbaarheden.put(String.valueOf(b.getBeschikbaarheidsId()), info);
        }
        return beschikbaarheden;
    }

    public List<String> geefPresentatie(int dag, int tijdsvak) {
        List<String> info = new ArrayList<>();

        try {
            Presentatie p = findPresentatie(dag, tijdsvak);
            info.add(p.getCampus().getNaam());
            info.add(p.getLokaal().getNaam());
            info.add(p.getStudent().getEmail());
            info.add(String.valueOf(p.getDag()));
            info.add(String.valueOf(p.getTijdsvak()));
            return info;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public List<String> geefPresentatieByStudentEmail(String sEmail) {
        List<String> info = new ArrayList<>();
        try {
            Presentatie p = findPresentatieByStudentEmail(sEmail);
            info.add(p.getCampus().getNaam());
            info.add(p.getLokaal().getNaam());
            info.add(p.getStudent().getEmail());
            info.add(String.valueOf(p.getDag()));
            info.add(String.valueOf(p.getTijdsvak()));
            return info;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Map<String, List<String>> geefPresentatiesByLokaalId(String lokaalNaam) {
        Map<String, List<String>> presentaties = new HashMap<>();
        for (Presentatie p : findPresentatiesByLokaalId(lokaalNaam)) {
            List<String> info = new ArrayList<>();
            info.add(p.getCampus().getNaam());
            info.add(p.getLokaal().getNaam());
            info.add(p.getStudent().getEmail());
            info.add(String.valueOf(p.getDag()));
            info.add(String.valueOf(p.getTijdsvak()));
            presentaties.put(p.getStudent().getEmail(), info);
        }
        return presentaties;
    }

    
    public List<Student> geefStudentenVanPromotor(String promotorEmail)
    {
        List<Student> studentLijst = new ArrayList<>();
        for(Student s : findLijstStudenten())
        {
            if(s.getPromotor() !=null)
            {
                if(s.getPromotor().getEmail().equals(promotorEmail))
                 {
                       System.out.println(s.getPromotor().getEmail() +"faggot");
                         studentLijst.add(s);
                 }
            }
        }
        return studentLijst;
    }
    
    // ---------- FIND ---------- //
    public List<String> geefStudentByEmail(String email) {
        List<String> info = new ArrayList<>();
        
        Student s = findStudentByEmail(email);
        info.add(s.getVoornaam());
        info.add(s.getFamilienaam());
        info.add(s.getUsername());
        if (s.getPromotor()!=null) {
            info.add(s.getPromotor().getEmail());
        } else {
            info.add("");
        }
        info.add(s.getEmail());
        return info;
    }

    public List<String> geefPromotorByEmail(String email) {
        List<String> info = new ArrayList<>();
        Promotor p = findPromotorByEmail(email);
        info.add(p.getVoornaam());
        info.add(p.getFamilienaam());
        info.add(p.getUsername());
        info.add(p.getEmail());
        info.add(String.valueOf(p.getAantalKeerJury()));
        return info;
    }
    
    public List<String> geefGastByEmail(String email) {
        List<String> info = new ArrayList<>();
        Gast g = findGastByEmail(email);
        info.add(g.getVoornaam());
        info.add(g.getFamilienaam());
        info.add(g.getUsername());
        info.add(g.getEmail());
        return info;
    }

    public Campus findCampusByNaam(String cNaam) {
        return campusRepository.findCampusByNaam(cNaam);
    }

    public Lokaal findLokaalByNaam(String lNaam) {
        return lokaalRepository.findLokaalByNaam(lNaam);
    }

    public List<Lokaal> findLokalenByCampusId2(String campusNaam) {
        LokaalDAO lDAO = (LokaalDAO) DAOFactory.getDAO(DAO.LOKAAL);
        return lDAO.findLokalenByCampusId(campusNaam);
    }

    // --------- PROMOTOR TOEKENNEN ---------- //
    /**
     * Methode die een student koppelt met een promotor door middel van het
     * studentId. Voegt daarna de student toe aan de lijst van studenten van de
     * promotor. Dit werd in commentaar gezet.
     *
     * @param sEmail
     * @param pEmail
     */
    public void kenPromotorToe(String sEmail, String pEmail) {
        gebruikerRepository.kenPromotorToe(sEmail, pEmail);
    }
    
    public void koppelLos(String sEmail) {
        Student s = findStudentByEmail(sEmail);
        s.setPromotor(null);
        DAOFactory.getDAO(DAO.STUDENT).update(s);
    }

    public void verhoogAantalKeerJury(String promotorEmail, int aantalKeerJury) {
        gebruikerRepository.verhoogAantalKeerJury(promotorEmail, aantalKeerJury);

    }

    // ---------- PRESENTATIE & PLANNING ---------- //
    public void startEditingPlanning() {
        bpCoordinator.startEditingPlanning();
    }

    public List<Integer> geefGeplandePresentaties() {
        return presentatieRepository.geefPresentaties();
    }

    //---------------------------------------------------------------------mis------------------------------------

    public void planPresentatie(int dag, int tijdsvak, String lokaalNaam, String campusNaam, String studentEmail) {
        PresentatieDAO pDAO = (PresentatieDAO) DAOFactory.getDAO(DAO.PRESENTATIE);
        pDAO.planPresentatie(dag, tijdsvak, lokaalNaam, campusNaam, studentEmail);
    }

    //------------------------------------------------------------------------------------------------------------------
    public void verwijderPresentatie(String studentEmail) {
        presentatieRepository.verwijderPresentatie(studentEmail);
    }
    
    public List<Presentatie> findPresentatiesByLokaalId(String lokaalNaam) {
        return presentatieRepository.findPresentatiesByLokaalId(lokaalNaam);
    }
    
    //---------------------------------------------------------------------mis------------------------------------

    public void planningAanpassen(String studentEmail, int dag, int tijdslot, String campusNaam, String lokaalNaam) {
        PresentatieDAO pDAO = (PresentatieDAO) DAOFactory.getDAO(DAO.PRESENTATIE);
        pDAO.planningAanpassen(studentEmail, dag, tijdslot, campusNaam, lokaalNaam);
    }
    
    // ---------- JURYLEDEN ---------- //
    public void kenJuryLedenToe(String studentEmail, String juryLidEmail) {
        PresentatieDAO pDAO = (PresentatieDAO) DAOFactory.getDAO(DAO.PRESENTATIE);
        pDAO.kenJuryledenToe(studentEmail, juryLidEmail);
    }

    // ---------- INSCHRIJVINGEN ---------- //

    public void inschrijvingGoedkeuren(String studentEmail, String gastEmail) {
        InschrijvingDAO iDAO = (InschrijvingDAO) DAOFactory.getDAO(DAO.INSCHRIJVING);
        iDAO.inschrijvingGoedkeuren(studentEmail, gastEmail);
    }

    // ---------- BESCHIKBAARHEDEN --------- //
    public List<Beschikbaarheid> findBeschikbaarhedenVanPromotor(String pEmail) {
        Promotor p = findPromotorByEmail(pEmail);
        return p.getBeschikbaarheden();
    }

    // ---------- NATIVE METHODS ---------- //
    public List<Student> findLijstStudenten() {
        return gebruikerRepository.geefLijstStudenten();
    }

    public List<Promotor> findLijstPromotoren() {
        return gebruikerRepository.geefLijstPromotoren();
    }

    public List<Campus> findLijstCampussen() {
        return DAOFactory.getDAO(DAO.CAMPUS).findAll();
    }

    public List<Presentatie> findLijstPresentaties() {
        return DAOFactory.getDAO(DAO.PRESENTATIE).findAll();
    }

    public BPCoordinator findBPCoordinator() {
        return gebruikerRepository.getBpCoordinator();
    }

    public Presentatie findPresentatie(int dag, int tijdsvak) {
        PresentatieDAO pDAO = (PresentatieDAO) DAOFactory.getDAO(DAO.PRESENTATIE);
        return pDAO.findPresentatieByDagAndTijdsvak(dag, tijdsvak);
    }

    public Student findStudentByEmail(String email) {
        return gebruikerRepository.findStudentByEmail(email);
    }

    public Promotor findPromotorByEmail(String email) {
        return gebruikerRepository.findPromotorByEmail(email);
    }

    public Presentatie findPresentatieByStudentEmail(String sEmail) {
        PresentatieDAO pDAO = (PresentatieDAO) DAOFactory.getDAO(DAO.PRESENTATIE);
        return pDAO.findPresentatieByStudentEmail(sEmail);
    }
    
    public List<Inschrijving> findInschrijvingenByPresentatieId(String studentEmail) {
        return inschrijvingRepository.findInschrijvingenByPresentatieId(studentEmail);
    }
    
    public Gast findGastByEmail(String email) {
        return gastRepository.findGastByEmail(email);
    }
    
    public List<Inschrijving> findInschrijvingenByGastEmail(String gastEmail) {
        return inschrijvingRepository.findInschrijvingenByGastEmail(gastEmail);
    }

}
