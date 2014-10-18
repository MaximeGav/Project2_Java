package persistentie.dao;

import domein.Student;
import java.util.List;
import persistentie.GenericDao;

/**
 *
 * @author RootSoft
 * @param <T>
 */
public class StudentDAO<T> extends GenericDao<Student>{
    
    //Attributes
    
    //Constructors
    public StudentDAO() {
        super(Student.class);
    }

    //Callbacks
    
    @Override
    public List<Student> findAll() {
        return super.findAll();
    }
    
    @Override
    public void insert(Student s) {
        super.insert(s);
    }
    
    @Override
    public void update(Student s) {
        super.update(s);
    }
    
    @Override
    public Student get(String email) {
        return (Student) super.get(email);
    }
   
   
    
    

    
}
