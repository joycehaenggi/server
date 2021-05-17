package AB5.initial.server.src.main.java.ch.fhnw.kvanc.server.web;

import AB5.initial.server.src.main.java.ch.fhnw.kvanc.server.domain.Vote;
import AB5.initial.server.src.main.java.ch.fhnw.kvanc.server.repository.QuestionRepository;
import AB5.initial.server.src.main.java.ch.fhnw.kvanc.server.repository.VoteRepository;
import AB5.initial.server.src.main.java.ch.fhnw.kvanc.server.web.dto.QuestionDTO;
import AB5.initial.server.src.main.java.ch.fhnw.kvanc.server.web.dto.TokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/votes")
public class VoteController {
    private Logger logger = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping
    public ResponseEntity<TokenDTO> createTokenForUser(@RequestBody TokenDTO dto) {
        String email = dto.getEmail();
        Vote vote = voteRepository.createVote(email);
        if (vote == null) {
            logger.info("Vote already created for '{}'", email);
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
        TokenDTO tokenDTO = new TokenDTO(vote.getId(), email);
        logger.debug("Vote created for '" + vote.getEmail() + "'");
        return new ResponseEntity<TokenDTO>(tokenDTO, HttpStatus.CREATED);
    }

    @GetMapping("/question")
    public ResponseEntity<QuestionDTO> getQuestion() {
        String content = questionRepository.getQuestionContent();
        if (content == null) {
            return new ResponseEntity<QuestionDTO>(HttpStatus.NO_CONTENT);
        }
        QuestionDTO dto = new QuestionDTO(content);
        logger.debug("Actual question requested: '{}' ", content);
        return new ResponseEntity<QuestionDTO>(dto, HttpStatus.OK);
    }

}