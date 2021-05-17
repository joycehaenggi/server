package AB5.initial.server.src.main.java.ch.fhnw.kvanc.server.web;

import AB5.initial.server.src.main.java.ch.fhnw.kvanc.server.repository.QuestionRepository;
import AB5.initial.server.src.main.java.ch.fhnw.kvanc.server.repository.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/admin")
public class AdminController {
    private QuestionRepository questionRepository;

    private VoteRepository voteRepository;

    // using constructor injection instead of @Autowired
    public AdminController(QuestionRepository questionRepository, VoteRepository voteRepository) {
        this.questionRepository = questionRepository;
        this.voteRepository = voteRepository;
    }

    @PutMapping(params = "question")
    ResponseEntity<String> setQuestion(@RequestParam String question) {
        try {
            questionRepository.setQuestion(question);
            voteRepository.reOpenAll();
            return new ResponseEntity<String>("Done", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/votes", params = "method")
    public ResponseEntity<Void> admin(@RequestParam String method) {
        if ((method != null) && (method.equals("reset"))) {
            voteRepository.reset();
            return new ResponseEntity<>(HttpStatus.OK);
        } else if ((method != null) && (method.equals("reopen"))) {
            voteRepository.reOpenAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}