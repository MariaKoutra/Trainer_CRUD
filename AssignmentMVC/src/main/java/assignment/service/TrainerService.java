package assignment.service;

import assignment.entity.Trainer;
import assignment.repository.TrainerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Maria
 */

@Transactional
@Service
public class TrainerService {

    @Autowired
    TrainerRepository trainerRepository;

    public Trainer updateTrainer(Trainer t) {
        return trainerRepository.save(t);
    }

    public Trainer getTrainerById(int id) {
        return trainerRepository.find(id);
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public void deleteTrainerById(int id) {
        trainerRepository.delete(id);
    }

}
