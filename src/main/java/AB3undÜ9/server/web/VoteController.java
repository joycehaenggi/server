package AB3undÜ9.server.web;

import AB3undÜ9.server.repository.QuestionRepository;
import AB3undÜ9.server.web.dto.QuestionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votes")
public class VoteController {
	private Logger logger = LoggerFactory.getLogger(VoteController.class);


	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/question")
	public ResponseEntity<QuestionDTO> getQuestion(){
		String content = questionRepository.getQuestionContent();
		if (content == null) {
			return new ResponseEntity<QuestionDTO>(HttpStatus.NO_CONTENT);
		}
		QuestionDTO dto = new QuestionDTO(content);
		return new ResponseEntity<QuestionDTO>(dto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<String> sayHello() {
		logger.debug("Server called successfully");
		return new ResponseEntity<String>("Hello from AB3.server", HttpStatus.OK);
	}
}
