package assignment.repository;

import assignment.entity.Trainer;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author Maria
 */

@Repository
public class TrainerRepository extends HibernateUtils<Trainer>{
    
    public Trainer save(Trainer t){
        return super.save(t);
    }
    
    public Trainer find(int id){
        return super.find(Trainer.class, id);
    }
    
    public List<Trainer> findAll(){
        return super.findAll("Trainer.findAll");
    }
    
   public void delete(int id){
       super.delete(Trainer.class, id);
   }
   
}
