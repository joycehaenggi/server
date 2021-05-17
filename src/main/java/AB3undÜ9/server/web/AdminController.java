package AB3undÜ9.server.web;


import AB3undÜ9.server.repository.QuestionRepository;
import AB3undÜ9.server.repository.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private QuestionRepository questionRepository;
    private VoteRepository voteRepository;

    //using constructor injection indeat of @Autowired
    public AdminController(QuestionRepository questionRepository, VoteRepository voteRepository){
        this.questionRepository = questionRepository;
        this.voteRepository = voteRepository;
    }


    @PutMapping(params = "question")
    public ResponseEntity<String> setQuestion(@RequestParam String question) {
        try {
            questionRepository.setQuestion(question);
            voteRepository.reOpenAll();
            return new ResponseEntity<String>("Done", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}