package assignment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Maria
 */

@Transactional
@Service
public class HomeService {
    
    
    public String getWelcomeMessage(){
        return "Welcome to my Assignment";
    }
    
}
