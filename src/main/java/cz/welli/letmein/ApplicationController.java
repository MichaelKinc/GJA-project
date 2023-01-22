package cz.welli.letmein;

import cz.welli.letmein.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ApplicationController {
    //todo maji byt mapingy tady nebo v controlleru?

    @Autowired
        private UserRepository userRepo;
}
